package com.agendaki.financially.api.dtos;

public record AddressDTO(String country, String region, String region_code, String city, String postal_code,
                         String street, String number, String locality) {
}
