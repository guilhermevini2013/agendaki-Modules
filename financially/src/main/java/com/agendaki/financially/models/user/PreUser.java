package com.agendaki.financially.models.user;

import com.agendaki.financially.dtos.user.PreUserSaveDTO;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.crypto.password.PasswordEncoder;

@Document(collection = "pre-user")
public class PreUser {
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

    public String getPassword() {
        return password;
    }

    public String getEmail() {
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
