package com.agendaki.financially.dtos.api.dtos.bankSlip;

import com.agendaki.financially.dtos.api.dtos.AddressDTO;

public record HolderDTO(String name, String email, String tax_id, AddressDTO address) {
}
