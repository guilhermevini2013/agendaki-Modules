package com.agendaki.financially.api;

import com.agendaki.financially.api.dtos.bankSlip.PaymentBankSlipCreateDTO;
import com.agendaki.financially.api.dtos.pix.PaymentPixCreateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "pagBank", url = "${api.pagBank.url}"+"/orders")
public interface PagBankClient {

    @PostMapping
    String payPix(@RequestHeader("Authorization") String token, @RequestBody PaymentPixCreateDTO paymentPixCreateDTO);

    @PostMapping
    String payBankSlip(@RequestHeader("Authorization") String token, @RequestBody PaymentBankSlipCreateDTO paymentBankSlipCreateDTO);
}
