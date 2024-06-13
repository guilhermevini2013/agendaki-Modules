package com.agendaki.financially.services.user;

import com.agendaki.financially.models.user.PreUser;
import com.agendaki.financially.repositories.PreUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsImpl implements UserDetailsService {
    private final PreUserRepository preUserRepository;

    public UserDetailsImpl(PreUserRepository prUserRepository) {
        this.preUserRepository = prUserRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email){
        PreUserRepository.UserAuth userAuth = preUserRepository.findByEmail(email).orElse(null);
        return new PreUser(userAuth);
    }
}
