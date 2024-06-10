package com.agendaki.financially.services.user;

import com.agendaki.financially.dtos.user.PreUserSaveDTO;
import com.agendaki.financially.models.user.PreUser;
import com.agendaki.financially.repositories.PreUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PreUserServiceImpl implements PreUserService {
    private final PreUserRepository preUserRepository;
    private final PasswordEncoder passwordEncoder;

    public PreUserServiceImpl(PreUserRepository preUserRepository, PasswordEncoder passwordEncoder) {
        this.preUserRepository = preUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(PreUserSaveDTO userDTO) {
        PreUser preUser = new PreUser(userDTO,passwordEncoder);
        preUserRepository.save(preUser);
    }
}
