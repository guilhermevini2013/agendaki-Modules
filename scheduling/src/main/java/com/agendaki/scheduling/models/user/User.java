package com.agendaki.scheduling.models.user;

import com.agendaki.scheduling.repositories.UserRepository;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "client")
public class User implements UserDetails {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String tradeName;
    private String tellPhone;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Signature signature;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Instance instance;

    public User() {
    }

    public User(UserRepository.UserAuthProjection userAuthProjection) {
        this.id = userAuthProjection.getId();
        this.email = userAuthProjection.getEmail();
        this.password = userAuthProjection.getPassword();
    }

    public User(Map<String, Object> attributes) {
        this.id = (String) attributes.get("id");
        this.name = (String) attributes.get("name");
        this.tellPhone = (String) attributes.get("tellPhone");
        this.password = (String) attributes.get("password");
        this.tradeName = (String) attributes.get("tradeName");
        this.email = (String) attributes.get("email");
        List<Integer> dates = (List<Integer>) attributes.get("expiryDate");
        this.signature = new Signature(TypeSignature.valueOf((String) attributes.get("typeSignature")), LocalDate.of(dates.get(0), dates.get(1), dates.get(2)));
        this.instance = new Instance(this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(TypeRole.ROLE_USER.getAuthority()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public Instance getInstance() {
        return instance;
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

    public String getEmail() {
        return email;
    }
}
