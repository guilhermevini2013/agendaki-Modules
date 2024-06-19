package com.agendaki.financially.services.payment.strategy;

import com.agendaki.financially.api.PagBankClient;
import com.agendaki.financially.api.dtos.pix.PaymentPixCreateDTO;
import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.models.payment.Payment;
import com.agendaki.financially.models.payment.Pix;
import com.agendaki.financially.models.user.PreUser;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class PixCreate implements IPaymentCreate {
    private final Gson gson = new Gson();

    @Override
    public Payment createPayment(PreUser preUser, PaymentCreateDTO paymentDTO, PagBankClient pagBankClient, String apiKey) {
        String response = pagBankClient.payPix(apiKey, new PaymentPixCreateDTO(paymentDTO, preUser));
        JsonObject json = gson.fromJson(response, JsonObject.class);
        JsonElement qrCodes = json.getAsJsonArray("qr_codes").get(0);
        return new Pix(preUser.getId(), paymentDTO, qrCodes.getAsJsonObject().get("text").getAsString(),
                qrCodes.getAsJsonObject().getAsJsonArray("links").get(0).getAsJsonObject().get("href").getAsString(),
                qrCodes.getAsJsonObject().get("id").getAsString(),
                OffsetDateTime.parse(qrCodes.getAsJsonObject().get("expiration_date").getAsString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME).toLocalTime());
    }
}
