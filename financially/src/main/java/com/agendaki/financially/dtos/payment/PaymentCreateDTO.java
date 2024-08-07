package com.agendaki.financially.dtos.payment;

import com.agendaki.financially.dtos.api.dtos.AddressDTO;
import com.agendaki.financially.models.payment.TypePayment;
import com.agendaki.financially.models.preuser.TypeSignature;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Optional;

public record PaymentCreateDTO(
        @NotBlank(message = "CPF is blank.")
        @Pattern(regexp = "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}")
        String cpf,
        @NotNull(message = "Type payment is blank.")
        TypePayment typePayment,
        @NotNull(message = "Type signature is blank.")
        TypeSignature typeSignature,
        @Valid
        Optional<AddressDTO> address) {

}
