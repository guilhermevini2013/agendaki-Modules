package com.agendaki.financially.models.payment;

import com.agendaki.financially.dtos.payment.CardBankCreateDTO;
import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.models.user.TypeSignature;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CardBank extends Payment {
    private String number;
    private String dueDate;
    private Short cvv;
    private String titularName;

    public CardBank(String idUser, BigDecimal price, String cpf, PaymentStatus paymentStatus, TypeSignature typeSignature, TypePayment typePayment, LocalDate dateOpen, LocalDate dateTransaction, String number, String dueDate, Short cvv, String titularName) {
        super(idUser, price, cpf, paymentStatus, typeSignature, typePayment, dateOpen, dateTransaction);
        this.number = number;
        this.dueDate = dueDate;
        this.cvv = cvv;
        this.titularName = titularName;
    }

    public CardBank(String idPreUser, PaymentCreateDTO paymentCreateDTO, PasswordEncoder passwordEncoder) {
        super(idPreUser, paymentCreateDTO);
        CardBankCreateDTO cardBankCreateDTO = paymentCreateDTO.payment().orElseThrow(() -> new IllegalArgumentException("Card bank does not exist"));
        this.number = passwordEncoder.encode(cardBankCreateDTO.number());
        this.dueDate = cardBankCreateDTO.dueDate();
        this.cvv = cardBankCreateDTO.cvv();
        this.titularName = cardBankCreateDTO.titularName();
    }

    public CardBank() {
    }
}
