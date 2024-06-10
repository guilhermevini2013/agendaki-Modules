package com.agendaki.financially.dtos.user;

import com.agendaki.financially.models.user.TypeSignature;

public record PreUserSaveDTO(String name, String tradeName, String password, String email, TypeSignature typeSignature,
                             String tellPhone) {
}
