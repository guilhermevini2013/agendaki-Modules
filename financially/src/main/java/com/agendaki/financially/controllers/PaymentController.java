package com.agendaki.financially.controllers;

import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.dtos.payment.PaymentReadDTO;
import com.agendaki.financially.services.payment.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(final PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void createPayment(@RequestBody @Valid final PaymentCreateDTO paymentDTO) {
        paymentService.createPayment(paymentDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public PaymentReadDTO findPaymentByIdUser() {
        return paymentService.findPaymentByPreUserId();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deletePayment() {
        paymentService.deletePayment();
    }
}
