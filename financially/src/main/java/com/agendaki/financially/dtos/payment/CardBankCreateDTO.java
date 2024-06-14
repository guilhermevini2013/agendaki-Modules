package com.agendaki.financially.dtos.payment;

public record CardBankCreateDTO(String number,String dueDate,Short cvv,String titularName) {
}
