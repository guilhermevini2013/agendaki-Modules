package com.agendaki.financially.models.preuser;

import java.math.BigDecimal;
import java.time.LocalDate;

public enum TypeSignature {
    MONTHLY("Mensal"),
    QUARTERLY("Tri-mensal"),
    ANNUAL("Anual");
    private final String value;
    TypeSignature(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public SignatureSpecification getInformation() {
        switch (this) {
            case MONTHLY:
                return new SignatureSpecification(LocalDate.now().plusMonths(1), BigDecimal.valueOf(5000), "Acesso a uma assinatura de 1 mês dos serviços AgendaKi.");
            case QUARTERLY:
                return new SignatureSpecification(LocalDate.now().plusMonths(3), BigDecimal.valueOf(12000), "Acesso a uma assinatura de 3 mêses dos serviços AgendaKi.");
            case ANNUAL:
                return new SignatureSpecification(LocalDate.now().plusMonths(12), BigDecimal.valueOf(36000), "Acesso a uma assinatura de 1 ano dos serviços AgendaKi.");
            default:
                throw new IllegalArgumentException("Invalid signature");
        }
    }
}
