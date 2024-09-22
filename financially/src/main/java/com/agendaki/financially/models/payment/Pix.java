package com.agendaki.financially.models.payment;

import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.models.preuser.TypeSignature;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Pix extends Payment {
    private String idQrCode;
    private String urlPix;
    private String urlImagePix;
    private LocalTime expireTime;

    public Pix(String idUser, BigDecimal price, String cpf, PaymentStatus paymentStatus, TypeSignature typeSignature, TypePayment typePayment, LocalDate dateOpen, LocalDate dateTransaction) {
        super(idUser, price, cpf, paymentStatus, typeSignature, typePayment, dateOpen, dateTransaction);
    }

    public Pix(String idPreUser, PaymentCreateDTO paymentCreateDTO, String urlPix, String urlImagePix, String idQrCode, LocalTime expireTime) {
        super(idPreUser, paymentCreateDTO);
        this.urlPix = urlPix;
        this.idQrCode = idQrCode;
        this.urlImagePix = urlImagePix;
        this.expireTime = expireTime;
    }

    public Pix() {
    }

    public String getIdQrCode() {
        return idQrCode;
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
}
