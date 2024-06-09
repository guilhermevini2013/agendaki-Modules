package com.agendaki.financially.models.user;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

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
