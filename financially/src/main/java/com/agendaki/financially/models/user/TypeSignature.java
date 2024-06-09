package com.agendaki.financially.models.user;

import java.time.LocalDate;

public enum TypeSignature {
    MONTHLY,
    QUARTERLY,
    ANNUAL;

    public LocalDate getDateBySignature(TypeSignature signature) {
        switch (signature) {
            case MONTHLY:
                LocalDate.now().plusMonths(1);
            case QUARTERLY:
                LocalDate.now().plusMonths(3);
            case ANNUAL:
                LocalDate.now().plusMonths(12);
            default:
                throw new IllegalArgumentException("Invalid signature: " + signature);
        }
    }
}
