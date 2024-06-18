package com.agendaki.financially.dtos.payment.pix;

import com.agendaki.financially.models.user.SignatureSpecification;

import java.time.LocalTime;

public record PaymentPixReadDTO(String urlPix, String urlImagePix, LocalTime expireTime, SignatureSpecification signatureSpecification) {
}
