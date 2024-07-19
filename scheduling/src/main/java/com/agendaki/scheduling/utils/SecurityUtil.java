package com.agendaki.scheduling.utils;

import com.agendaki.scheduling.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static UserRepository.UserAuthProjection getProjectionOfUserEntityAuthenticated() {
        return (UserRepository.UserAuthProjection) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
