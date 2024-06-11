package com.agendaki.financially.controllers;

import com.agendaki.financially.dtos.user.PreUserLoadDTO;
import com.agendaki.financially.dtos.user.PreUserSaveDTO;
import com.agendaki.financially.services.user.PreUserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/api/pre-user")
public class PreUserController {
    private final PreUserService preUserService;

    public PreUserController(final PreUserService preUserService) {
        this.preUserService = preUserService;
    }

    @ResponseStatus(NO_CONTENT)
    @PostMapping
    public void savePreUser(@RequestBody @Valid PreUserSaveDTO preUserDto) {
        preUserService.save(preUserDto);
    }

    @ResponseStatus(OK)
    @PostMapping(value = "/auth")
    public String loadPreUsers(@RequestBody @Valid PreUserLoadDTO preUserLoadDTO) {
        return preUserService.load(preUserLoadDTO);
    }
}
