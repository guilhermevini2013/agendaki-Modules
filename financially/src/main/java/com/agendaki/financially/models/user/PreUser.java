package com.agendaki.financially.models.user;

import com.agendaki.financially.dtos.user.PreUserSaveDTO;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

@Document(collection = "pre-user")
public class PreUser implements UserDetails {
    @MongoId
    private String id;
    private String name;
    private String tradeName;
    private String password;
    private String email;
    private TypeSignature typeSignature;
    private String tellPhone;

    public PreUser(PreUserSaveDTO userDTO, PasswordEncoder passwordEncoder) {
        this.email = userDTO.email();
        this.tradeName = userDTO.tradeName();
        this.password = passwordEncoder.encode(userDTO.password());
        this.typeSignature = userDTO.typeSignature();
        this.tellPhone = userDTO.tellPhone();
        this.name = userDTO.name();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_PRE-USER"));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public TypeSignature getTypeSignature() {
        return typeSignature;
    }

    public String getTellPhone() {
        return tellPhone;
    }

    public String getTradeName() {
        return tradeName;
    }
}
