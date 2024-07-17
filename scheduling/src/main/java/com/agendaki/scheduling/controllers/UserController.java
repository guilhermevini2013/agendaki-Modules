package com.agendaki.scheduling.controllers;

import com.agendaki.scheduling.dtos.request.UserLoadDTO;
import com.agendaki.scheduling.dtos.response.UserTokenDTO;
import com.agendaki.scheduling.services.user.UserService;
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
    public ResponseEntity<UserTokenDTO> authUser(@RequestBody UserLoadDTO userLoadDTO) {
        UserTokenDTO userTokenDTO = userService.loadUser(userLoadDTO);
        return ResponseEntity.ok(userTokenDTO);
    }
}
