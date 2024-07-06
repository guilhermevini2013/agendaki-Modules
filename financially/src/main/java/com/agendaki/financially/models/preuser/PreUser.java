package com.agendaki.financially.models.preuser;

import com.agendaki.financially.dtos.user.request.PreUserSaveDTO;
import com.agendaki.financially.dtos.user.request.PreUserUpdateDTO;
import com.agendaki.financially.repositories.PreUserRepository;
import org.springframework.data.mongodb.core.index.Indexed;
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
    @Indexed(unique = true)
    private String email;
    private String tellPhone;

    public PreUser(PreUserSaveDTO userDTO, PasswordEncoder passwordEncoder) {
        this.email = userDTO.email();
        this.tradeName = userDTO.tradeName();
        this.password = passwordEncoder.encode(userDTO.password());
        this.tellPhone = userDTO.tellPhone();
        this.name = userDTO.name();
    }

    public PreUser() {
    }

    public PreUser(String id, String name, String tradeName, String password, String email, TypeSignature typeSignature, String tellPhone) {
        this.id = id;
        this.name = name;
        this.tradeName = tradeName;
        this.password = password;
        this.email = email;
        this.tellPhone = tellPhone;
    }

    public PreUser(PreUserRepository.PreUserAuth userAuth) {
        this.id = userAuth.getId();
        this.email = userAuth.getEmail();
        this.password = userAuth.getPassword();
    }

    public String getId() {
        return id;
    }

    public void updateData(PreUserUpdateDTO userUpdateDTO) {
        this.name = userUpdateDTO.name();
        this.tradeName = userUpdateDTO.tradeName();
        this.tellPhone = userUpdateDTO.tellPhone();
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
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getTellPhone() {
        return tellPhone;
    }

    public String getTradeName() {
        return tradeName;
    }
}
