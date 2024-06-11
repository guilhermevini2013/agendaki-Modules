package com.agendaki.financially.controllers;

import com.agendaki.financially.dtos.user.PreUserSaveDTO;
import com.agendaki.financially.services.user.PreUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/pre-user/")
public class PreUserController {
    private final PreUserService preUserService;

    public PreUserController(final PreUserService preUserService) {
        this.preUserService = preUserService;
    }

    @PostMapping
    public ResponseEntity<Void> savePreUser(@RequestBody final PreUserSaveDTO preUserDto) {
        preUserService.save(preUserDto);
        return ResponseEntity.noContent().build();
    }
}
