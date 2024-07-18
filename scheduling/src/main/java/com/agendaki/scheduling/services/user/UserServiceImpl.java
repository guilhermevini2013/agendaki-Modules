package com.agendaki.scheduling.services.user;

import com.agendaki.scheduling.dtos.request.PreUserToSaveDTO;
import com.agendaki.scheduling.dtos.request.UserLoadDTO;
import com.agendaki.scheduling.dtos.response.UserTokenDTO;
import com.agendaki.scheduling.models.user.User;
import com.agendaki.scheduling.repositories.UserRepository;
import com.agendaki.scheduling.services.token.JWTService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public void saveUser(PreUserToSaveDTO preUserToSaveDTO) {
        User user = new User(preUserToSaveDTO.attributes());
        userRepository.save(user);
    }

    @Override
    public UserTokenDTO loadUser(UserLoadDTO userLoadDTO) {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuth = new UsernamePasswordAuthenticationToken(userLoadDTO.email(), userLoadDTO.password());
            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuth);
            return new UserTokenDTO(jwtService.generateToken(((User) authenticate.getPrincipal()).getEmail()));
        } catch (AuthenticationException ex) {
            throw new UsernameNotFoundException("Credentials not found");
        }

    }
}
