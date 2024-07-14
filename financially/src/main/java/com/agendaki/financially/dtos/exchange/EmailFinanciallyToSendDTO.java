package com.agendaki.financially.dtos.exchange;

import com.agendaki.financially.dtos.api.dtos.PaymentReadDTO;
import com.agendaki.financially.dtos.user.response.PreUserSaveResponseDTO;
import com.agendaki.financially.repositories.PaymentRepository;

import java.util.Map;

public record EmailFinanciallyToSendDTO(Map<String, Object> attributes,
                                        String emailTo, String typeTemplate) {

    public EmailFinanciallyToSendDTO(PaymentRepository.PaymentStatusProjection paymentStatusProjection) {
        this(paymentStatusProjection.toMap(), paymentStatusProjection.preUserData().email(), "WARNING_PAYMENT");
    }

    public EmailFinanciallyToSendDTO(PaymentRepository.PaymentCompletedProjection paymentCompletedProjection) {
        this(paymentCompletedProjection.toMap(), paymentCompletedProjection.preUserData().email(), "PAYMENT_COMPLETED_AND_ACCOUNT_RELEASED");
    }

    public EmailFinanciallyToSendDTO(PreUserSaveResponseDTO preUserSaveResponseDTO) {
        this(preUserSaveResponseDTO.toMap(), preUserSaveResponseDTO.email(), "WELCOME");
    }
    public EmailFinanciallyToSendDTO(PaymentReadDTO content, String username) {
        this(content.toMapToPaymentCreated(), username, "PAYMENT_CREATED");
    }

}
