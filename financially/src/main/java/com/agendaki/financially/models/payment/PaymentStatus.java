package com.agendaki.financially.models.payment;

public enum PaymentStatus {
    PAID("PAGO"),
    DECLINED("RECUSADO"),
    CANCELED("CANCELADO"),
    WAITING("AGUARDANDO");

    private final String statusInPtBr;

    PaymentStatus(String statusInPtBr) {
        this.statusInPtBr = statusInPtBr;
    }

    public String getStatusInPtBr() {
        return statusInPtBr;
    }
}
