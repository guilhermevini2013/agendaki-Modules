package com.agendaki.financially.controllers;

import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.services.payment.PaymentService;
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
    public void createPayment(@RequestBody final PaymentCreateDTO paymentDTO) {
        paymentService.createPayment(paymentDTO);
    }
}
