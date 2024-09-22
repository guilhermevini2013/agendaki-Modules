package com.agendaki.financially.controllers;

import com.agendaki.financially.dtos.api.dtos.PaymentReadDTO;
import com.agendaki.financially.dtos.api.dtos.webhook.PaymentNotificationDTO;
import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.dtos.payment.PaymentViewOrderDTO;
import com.agendaki.financially.services.payment.PaymentService;
import com.agendaki.financially.utils.HateoasUtil;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(final PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EntityModel<PaymentReadDTO> createPayment(@RequestBody @Valid final PaymentCreateDTO paymentDTO) {
        EntityModel<PaymentReadDTO> paymentReadDTO = paymentService.createPayment(paymentDTO);
        HateoasUtil.insertHateoasIntoPayment(paymentReadDTO);
        return paymentReadDTO;
    }

    @GetMapping
    public ResponseEntity<PaymentReadDTO> findPaymentByIdUser() {
        PaymentReadDTO paymentRead = paymentService.findPaymentByPreUserId();
        if (paymentRead == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(paymentRead);
    }

    @GetMapping(value = "/orders")
    public ResponseEntity<List<PaymentViewOrderDTO>> getAllPaymentOrders() {
        List<PaymentViewOrderDTO> allOrders = paymentService.getAllOrders();
        if (allOrders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allOrders);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deletePayment() {
        paymentService.deletePayment();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(value = "/notification")
    public void notificationWebHook(@RequestBody PaymentNotificationDTO paymentNotificationDTO) {
        paymentService.pushNotification(paymentNotificationDTO);
    }
}
