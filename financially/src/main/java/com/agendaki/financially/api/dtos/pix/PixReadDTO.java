package com.agendaki.financially.api.dtos.pix;

import com.agendaki.financially.api.dtos.PaymentReadDTO;
import com.agendaki.financially.models.payment.Payment;
import com.agendaki.financially.models.payment.Pix;
import com.agendaki.financially.models.user.SignatureSpecification;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PixReadDTO extends PaymentReadDTO {
    private final String urlPix;
    private final String urlImagePix;
    private final LocalTime expireTime;
    private final SignatureSpecification signatureSpecification;

    public PixReadDTO(Payment payment) {
        super(payment);
        Pix pix = (Pix) payment;
        System.out.println(pix.getUrlPix());
        this.urlPix = pix.getUrlPix();
        this.urlImagePix = pix.getUrlImagePix();
        this.expireTime = pix.getExpireTime();
        this.signatureSpecification = pix.getTypeSignature().getDateBySignature();
    }

    public String getUrlPix() {
        return urlPix;
    }

    public String getUrlImagePix() {
        return urlImagePix;
    }

    public LocalTime getExpireTime() {
        return expireTime;
    }

    public SignatureSpecification getSignatureSpecification() {
        return signatureSpecification;
    }
}
