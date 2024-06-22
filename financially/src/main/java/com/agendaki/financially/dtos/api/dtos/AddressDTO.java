package com.agendaki.financially.dtos.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressDTO(
        @NotBlank(message = "Country is blank.")
        @Size(max = 50)
        String country,
        @NotBlank(message = "Region is blank.")
        @Size(max = 70)
        String region,
        @NotBlank(message = "Region code is blank.")
        @Size(max = 10)
        String region_code,
        @NotBlank(message = "City is blank.")
        @Size(max = 80)
        String city,
        @NotBlank(message = "Postal code is blank.")
        @Size(max = 8)
        @Pattern(regexp = "^[0-9]{8}$", message = "Regexp incorrect, Don't use symbols or letters ")
        String postal_code,
        @NotBlank(message = "Street is blank.")
        @Size(max = 125)
        String street,
        @NotBlank(message = "Number is blank.")
        @Size(max = 8)
        String number,
        @NotBlank(message = "Locality is blank.")
        @Size(max = 50)
        String locality) {
}
