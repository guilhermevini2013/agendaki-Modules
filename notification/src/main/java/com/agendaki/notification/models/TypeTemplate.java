package com.agendaki.notification.models;

public enum TypeTemplate {
    WELCOME("Bem-vindo a plataforma AgendaKi", "welcomePreUser"),
    PAYMENT_PAID("Pagamento efetuado!", "paymentPaid"),
    PAYMENT_CREATED("Pedido de pagamento pendente!", "paymentCreated"),
    WARNING_PAYMENT("Você tem um pedido aberto, efetue o pagamento!", "warningPayment"),
    ;
    private final String subject;
    private final String resourceFileName;

    TypeTemplate(String subject, String resourceFileName) {
        this.subject = subject;
        this.resourceFileName = resourceFileName;
    }

    public String getSubject() {
        return subject;
    }

    public String getResourceFileName() {
        return resourceFileName;
    }
}
