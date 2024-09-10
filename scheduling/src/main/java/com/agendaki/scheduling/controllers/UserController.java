package com.agendaki.scheduling.controllers;

import com.agendaki.scheduling.dtos.request.UserLoadDTO;
import com.agendaki.scheduling.dtos.response.UserTokenDTO;
import com.agendaki.scheduling.services.user.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<Void> authUser(@RequestBody @Valid UserLoadDTO userLoadDTO, HttpServletResponse response) {
        UserTokenDTO userTokenDTO = userService.loadUser(userLoadDTO);
        Cookie cookie = new Cookie("jwtAdministration",userTokenDTO.token());
        cookie.setPath("/");
        cookie.setMaxAge(259200);
        cookie.setHttpOnly(false);
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }
}
