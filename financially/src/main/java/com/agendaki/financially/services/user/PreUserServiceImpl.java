package com.agendaki.financially.services.user;

import com.agendaki.financially.dtos.email.EmailToPreUserDTO;
import com.agendaki.financially.dtos.user.request.PreUserLoadDTO;
import com.agendaki.financially.dtos.user.request.PreUserSaveDTO;
import com.agendaki.financially.dtos.user.request.PreUserUpdateDTO;
import com.agendaki.financially.dtos.user.response.PreUserProfileResponseDTO;
import com.agendaki.financially.dtos.user.response.PreUserSaveResponseDTO;
import com.agendaki.financially.dtos.user.response.PreUserTokenDTO;
import com.agendaki.financially.exceptions.ExistingDataException;
import com.agendaki.financially.models.user.PreUser;
import com.agendaki.financially.repositories.PreUserRepository;
import com.agendaki.financially.services.jwt.JWTService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PreUserServiceImpl implements PreUserService {
    private final PreUserRepository preUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final RabbitTemplate rabbitTemplate;

    public PreUserServiceImpl(PreUserRepository preUserRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTService jwtService, RabbitTemplate rabbitTemplate) {
        this.preUserRepository = preUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public EntityModel<PreUserSaveResponseDTO> save(PreUserSaveDTO userDTO) {
        PreUser preUserSaved = null;
        try {
            preUserSaved = preUserRepository.save(new PreUser(userDTO, passwordEncoder));
            PreUserSaveResponseDTO preUserSaveResponseDTO = new PreUserSaveResponseDTO(preUserSaved);
            rabbitTemplate.convertAndSend("email.preuser.pending", new EmailToPreUserDTO(preUserSaveResponseDTO, "WELCOME"));
            return EntityModel.of(preUserSaveResponseDTO);
        } catch (DuplicateKeyException ex) {
            throw new ExistingDataException("Existing data, check the fields");
        }
    }

    @Override
    public PreUserTokenDTO load(PreUserLoadDTO userLoadDTO) {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuth = new UsernamePasswordAuthenticationToken(userLoadDTO.email(), userLoadDTO.password());
            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuth);
            return new PreUserTokenDTO(jwtService.generateToken(((PreUser) authenticate.getPrincipal()).getUsername()));
        } catch (AuthenticationException ex) {
            throw new UsernameNotFoundException("Credentials not found or incorrect");
        }
    }

    @Override
    public void update(PreUserUpdateDTO userUpdateDTO) {
        PreUser userRecovered = (PreUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userRecovered.updateData(userUpdateDTO);
        preUserRepository.save(userRecovered);
    }

    @Override
    public EntityModel<PreUserProfileResponseDTO> getPreUserById() {
        String idRecovered = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        PreUserRepository.PreUserRead preUserRead = preUserRepository.getPreUserById(idRecovered).orElseThrow(() -> new UsernameNotFoundException("Not have pre user for this id"));
        return EntityModel.of(new PreUserProfileResponseDTO(preUserRead));
    }
}
