package com.agendaki.financially.services.user;

import com.agendaki.financially.dtos.user.PreUserLoadDTO;
import com.agendaki.financially.dtos.user.PreUserSaveDTO;
import com.agendaki.financially.exceptions.ExistingDataException;
import com.agendaki.financially.models.user.PreUser;
import com.agendaki.financially.repositories.PreUserRepository;
import com.agendaki.financially.services.jwt.JWTService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PreUserServiceImpl implements PreUserService {
    private final PreUserRepository preUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public PreUserServiceImpl(PreUserRepository preUserRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.preUserRepository = preUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    @Transactional
    public void save(PreUserSaveDTO userDTO) {
        PreUser preUser = new PreUser(userDTO,passwordEncoder);
        try {
            preUserRepository.save(preUser);
        } catch (DuplicateKeyException ex) {
            throw new ExistingDataException("Existing data, check the fields");
        }
    }

    @Override
    @Transactional
    public String load(PreUserLoadDTO userLoadDTO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuth = new UsernamePasswordAuthenticationToken(userLoadDTO.email(), userLoadDTO.password());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuth);
        return jwtService.generateToken(((PreUser)authenticate.getPrincipal()).getUsername());
    }
}
