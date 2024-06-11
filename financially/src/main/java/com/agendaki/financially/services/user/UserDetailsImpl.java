package com.agendaki.financially.services.user;

import com.agendaki.financially.repositories.PreUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImpl implements UserDetailsService {
    private final PreUserRepository preUserRepository;

    public UserDetailsImpl(PreUserRepository prUserRepository) {
        this.preUserRepository = prUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return preUserRepository.findByEmail(email);
    }
}
