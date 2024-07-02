package com.agendaki.financially.dtos.user.response;

import com.agendaki.financially.models.user.PreUser;

import java.util.HashMap;
import java.util.Map;

public record PreUserSaveResponseDTO(String name,
                                     String tradeName,
                                     String email,
                                     String tellPhone) {
    public PreUserSaveResponseDTO(PreUser preUserSaved) {
        this(preUserSaved.getName(), preUserSaved.getTradeName(), preUserSaved.getUsername(), preUserSaved.getTellPhone());
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("tradeName", tradeName);
        return map;
    }
}
