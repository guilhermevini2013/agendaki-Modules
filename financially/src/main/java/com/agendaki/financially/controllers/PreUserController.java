package com.agendaki.financially.controllers;

import com.agendaki.financially.dtos.user.PreUserLoadDTO;
import com.agendaki.financially.dtos.user.PreUserSaveDTO;
import com.agendaki.financially.dtos.user.PreUserTokenDTO;
import com.agendaki.financially.dtos.user.PreUserUpdateDTO;
import com.agendaki.financially.services.user.PreUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/pre-user")
public class PreUserController {
    private final PreUserService preUserService;

    public PreUserController(final PreUserService preUserService) {
        this.preUserService = preUserService;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void savePreUser(@RequestBody @Valid PreUserSaveDTO preUserDto) {
        preUserService.save(preUserDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/auth")
    public PreUserTokenDTO loadPreUsers(@RequestBody @Valid PreUserLoadDTO preUserLoadDTO) {
        return preUserService.load(preUserLoadDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping
    public void updatePreUser(@RequestBody @Valid PreUserUpdateDTO preUserUpdateDTO) {
        preUserService.update(preUserUpdateDTO);
    }
}
