package com.agendaki.financially.services.payment;

import com.agendaki.financially.configurations.rabbitmq.RabbitMQConstants;
import com.agendaki.financially.dtos.api.dtos.PaymentReadDTO;
import com.agendaki.financially.dtos.api.dtos.bankSlip.BankSplitReadDTO;
import com.agendaki.financially.dtos.api.dtos.pix.PixReadDTO;
import com.agendaki.financially.dtos.api.dtos.webhook.ChargesNotificationDTO;
import com.agendaki.financially.dtos.api.dtos.webhook.PaymentNotificationDTO;
import com.agendaki.financially.dtos.exchange.EmailFinanciallyToSendDTO;
import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.exceptions.ExistingDataException;
import com.agendaki.financially.models.payment.Payment;
import com.agendaki.financially.models.preuser.PreUser;
import com.agendaki.financially.repositories.PaymentRepository;
import com.agendaki.financially.repositories.PreUserRepository;
import com.agendaki.financially.services.payment.strategy.BankSlipCreate;
import com.agendaki.financially.services.payment.strategy.PaymentStrategy;
import com.agendaki.financially.services.payment.strategy.PixCreate;
import org.bson.types.ObjectId;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentStrategy paymentStrategy;
    private final RabbitTemplate rabbitTemplate;
    private final PreUserRepository preUserRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentStrategy paymentStrategy, RabbitTemplate rabbitTemplate, PreUserRepository preUserRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentStrategy = paymentStrategy;
        this.rabbitTemplate = rabbitTemplate;
        this.preUserRepository = preUserRepository;
    }

    @Override
    public EntityModel<PaymentReadDTO> createPayment(PaymentCreateDTO paymentDTO) {
        try {
            EntityModel<PaymentReadDTO> entityModel = null;
            switch (paymentDTO.typePayment()) {
                case PIX ->
                        entityModel = EntityModel.of(new PixReadDTO(paymentStrategy.createPayment(paymentDTO, new PixCreate())));
                case BANK_SLIP ->
                        entityModel = EntityModel.of(new BankSplitReadDTO(paymentStrategy.createPayment(paymentDTO, new BankSlipCreate())));
                default -> throw new IllegalArgumentException("Type payment not supported");
            }
            PreUser preUserAuth = recoverPreUserOfAuthenticated();
            rabbitTemplate.convertAndSend(RabbitMQConstants.QUEUE_EMAIL_FINANCIALLY.value(), new EmailFinanciallyToSendDTO(entityModel.getContent(), preUserAuth.getUsername()));
            return entityModel;
        } catch (DuplicateKeyException ex) {
            throw new ExistingDataException("Request already active on your account");
        }
    }

    @Override
    public PaymentReadDTO findPaymentByPreUserId() {
        Payment payment = paymentRepository.findByIdPreUser(recoverIdOfAuthenticated()).orElse(null);
        if (payment == null) {
            return null;
        }
        switch (payment.getTypePayment()) {
            case PIX -> {
                return new PixReadDTO(payment);
            }
            case BANK_SLIP -> {
                return new BankSplitReadDTO(payment);
            }
            default -> throw new IllegalArgumentException("Type payment not supported");
        }
    }

    @Override
    public void deletePayment() {
        String idAuthenticated = recoverIdOfAuthenticated();
        if (!paymentRepository.existsByIdPreUser(idAuthenticated)) {
            throw new ExistingDataException("Not a payment on your account");
        }
        paymentRepository.deleteByIdPreUser(idAuthenticated);
    }

    @Override
    public void pushNotification(PaymentNotificationDTO paymentNotificationDTO) {
        ChargesNotificationDTO chargesNotificationDTO = paymentNotificationDTO.charges().get(0);
        paymentRepository.updatePaymentStatusAndDateTransactionByIdPreUser(chargesNotificationDTO.reference_id(), chargesNotificationDTO.status(),
                OffsetDateTime.parse(chargesNotificationDTO.paid_at(), DateTimeFormatter.ISO_OFFSET_DATE_TIME).toLocalDate());
        PaymentRepository.PaymentCompletedProjection paymentCompletedProjection = paymentRepository.getPaymentCompletedProjectionById(new ObjectId(chargesNotificationDTO.reference_id()));
        rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE_NOTIFICATION_AND_SCHEDULING.value(), new EmailFinanciallyToSendDTO(paymentCompletedProjection));
    }

    private String recoverIdOfAuthenticated() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }

    private PreUser recoverPreUserOfAuthenticated() {
        return (PreUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
