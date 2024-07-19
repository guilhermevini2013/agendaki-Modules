package com.agendaki.scheduling.services.user;

import com.agendaki.scheduling.models.user.User;
import com.agendaki.scheduling.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserRepository.UserAuthProjection userAuthProjection = userRepository.findByEmail(email).orElse(null);
        return new User(userAuthProjection);
    }
}
