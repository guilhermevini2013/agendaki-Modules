package com.agendaki.scheduling.repositories;

import com.agendaki.scheduling.models.user.Instance;
import com.agendaki.scheduling.models.user.TypeRole;
import com.agendaki.scheduling.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<UserAuthProjection> findByEmail(String email);

    interface UserAuthProjection {
        Collection<? extends GrantedAuthority> role = List.of(new SimpleGrantedAuthority(TypeRole.ROLE_USER.getAuthority()));

        String getEmail();

        String getPassword();

        String getId();

        Instance getInstance();
    }
}
