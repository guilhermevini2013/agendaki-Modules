package com.agendaki.financially.services.payment.strategy;

import com.agendaki.financially.api.PagBankClient;
import com.agendaki.financially.dtos.api.dtos.pix.PaymentPixCreateDTO;
import com.agendaki.financially.dtos.payment.PaymentCreateDTO;
import com.agendaki.financially.models.payment.Payment;
import com.agendaki.financially.models.payment.Pix;
import com.agendaki.financially.models.user.PreUser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class PixCreate implements IPaymentCreate {
    private final Gson gson = new Gson();

    @Override
    public Payment createPayment(PreUser preUser, PaymentCreateDTO paymentDTO, PagBankClient pagBankClient, String apiKey) {
        String response = pagBankClient.payPix(apiKey, new PaymentPixCreateDTO(paymentDTO, preUser));
        JsonObject json = gson.fromJson(response, JsonObject.class);
        return new Pix(preUser.getId(), paymentDTO, recoverQrCodeTextByJson(json),
                recoverQrCodeImageByJson(json),
                recoverQrCodeIdByJson(json),
                recoverQrCodeDueDateByJson(json));
    }

    private String recoverQrCodeTextByJson(JsonObject json) {
        return json.getAsJsonArray("qr_codes").get(0).getAsJsonObject().get("text").getAsString();
    }

    private String recoverQrCodeImageByJson(JsonObject json) {
        return json.getAsJsonArray("qr_codes").get(0)
                .getAsJsonObject().getAsJsonArray("links").get(0).getAsJsonObject().get("href").getAsString();
    }

    private LocalTime recoverQrCodeDueDateByJson(JsonObject json) {
        return OffsetDateTime.parse(json.getAsJsonArray("qr_codes").get(0)
                .getAsJsonObject().get("expiration_date").getAsString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME).toLocalTime();
    }

    private String recoverQrCodeIdByJson(JsonObject json) {
        return json.getAsJsonArray("qr_codes").get(0)
                .getAsJsonObject().get("id").getAsString();
    }
}
