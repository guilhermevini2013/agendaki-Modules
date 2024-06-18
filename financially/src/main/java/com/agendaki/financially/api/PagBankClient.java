package com.agendaki.financially.api;

import com.agendaki.financially.dtos.payment.pix.PaymentPixCreateDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "pagBank", url = "${api.pagBank.url}")
public interface PagBankClient {

    @PostMapping
    String payPix(@Value("${api.pagBank.key}") @RequestHeader("Authorization") String token, @RequestBody PaymentPixCreateDTO paymentPixCreateDTO);
}
