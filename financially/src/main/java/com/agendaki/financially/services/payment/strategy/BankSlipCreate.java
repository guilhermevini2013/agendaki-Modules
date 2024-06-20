package com.agendaki.financially.services.payment.strategy;

import com.agendaki.financially.api.PagBankClient;
import com.agendaki.financially.api.dtos.bankSlip.PaymentBankSlipCreateDTO;
import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.models.payment.BankSlip;
import com.agendaki.financially.models.payment.Payment;
import com.agendaki.financially.models.user.PreUser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class BankSlipCreate implements IPaymentCreate {
    private final Gson gson = new Gson();

    @Override
    public Payment createPayment(PreUser preUser, PaymentCreateDTO paymentDTO, PagBankClient pagBankClient, String apiKey) {
        String response = pagBankClient.payBankSlip(apiKey, new PaymentBankSlipCreateDTO(paymentDTO, preUser));
        JsonObject json = gson.fromJson(response, JsonObject.class);
        String linkPDF = json.getAsJsonArray("charges")
                .get(0).getAsJsonObject().getAsJsonArray("links").get(0).getAsJsonObject().get("href").getAsString();

        String codeBar = json.getAsJsonArray("charges")
                .get(0).getAsJsonObject().getAsJsonObject("payment_method").getAsJsonObject("boleto").get("formatted_barcode").getAsString();
        return new BankSlip(preUser.getId(), paymentDTO, linkPDF, codeBar);
    }
}
