package com.agendaki.financially.models.user;

import java.math.BigDecimal;
import java.time.LocalDate;

public enum TypeSignature {
    MONTHLY,
    QUARTERLY,
    ANNUAL;

    public SignatureSpecification getDateBySignature() {
        switch (this) {
            case MONTHLY:
                return new SignatureSpecification(LocalDate.now().plusMonths(1), BigDecimal.valueOf(5000), "Access to a 1-month subscription to AgendaKi services.");
            case QUARTERLY:
                return new SignatureSpecification(LocalDate.now().plusMonths(3), BigDecimal.valueOf(4000), "Access to a 3-month subscription to AgendaKi services.");
            case ANNUAL:
                return new SignatureSpecification(LocalDate.now().plusMonths(12), BigDecimal.valueOf(3000), "Access to a 1-year subscription to AgendaKi services.");
            default:
                throw new IllegalArgumentException("Invalid signature");
        }
    }
}
