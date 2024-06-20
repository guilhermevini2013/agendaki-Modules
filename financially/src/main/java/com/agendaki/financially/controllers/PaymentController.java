package com.agendaki.financially.controllers;

import com.agendaki.financially.api.dtos.PaymentReadDTO;
import com.agendaki.financially.api.dtos.pix.PixReadDTO;
import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.services.payment.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(final PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PaymentReadDTO createPayment(@RequestBody @Valid final PaymentCreateDTO paymentDTO) {
        PaymentReadDTO payment = paymentService.createPayment(paymentDTO);
        return payment;
    }

    @GetMapping
    public ResponseEntity<PaymentReadDTO> findPaymentByIdUser() {
        PaymentReadDTO paymentRead = paymentService.findPaymentByPreUserId();
        if (paymentRead == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(paymentRead);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deletePayment() {
        paymentService.deletePayment();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/pix")
    public PixReadDTO findPaymentByPreUserId() {
        return null;
    }
}
