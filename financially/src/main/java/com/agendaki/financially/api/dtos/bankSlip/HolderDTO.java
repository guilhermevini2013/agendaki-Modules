package com.agendaki.financially.api.dtos.bankSlip;

import com.agendaki.financially.api.dtos.AddressDTO;

public record HolderDTO(String name, String email, String tax_id, AddressDTO address) {
}
