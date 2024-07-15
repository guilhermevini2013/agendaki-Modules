package com.agendaki.scheduling.models.user;

import org.springframework.security.core.GrantedAuthority;

public enum TypeRole implements GrantedAuthority {
    ROLE_USER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
