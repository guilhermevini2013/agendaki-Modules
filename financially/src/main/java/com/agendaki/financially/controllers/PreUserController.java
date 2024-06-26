package com.agendaki.financially.controllers;

import com.agendaki.financially.dtos.user.request.PreUserLoadDTO;
import com.agendaki.financially.dtos.user.request.PreUserSaveDTO;
import com.agendaki.financially.dtos.user.request.PreUserUpdateDTO;
import com.agendaki.financially.dtos.user.response.PreUserProfileResponseDTO;
import com.agendaki.financially.dtos.user.response.PreUserSaveResponseDTO;
import com.agendaki.financially.dtos.user.response.PreUserTokenDTO;
import com.agendaki.financially.services.user.PreUserService;
import com.agendaki.financially.utils.HateoasUtil;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/pre-user")
public class PreUserController {
    private final PreUserService preUserService;

    public PreUserController(final PreUserService preUserService) {
        this.preUserService = preUserService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EntityModel<PreUserSaveResponseDTO> savePreUser(@RequestBody @Valid PreUserSaveDTO preUserDto) {
        EntityModel<PreUserSaveResponseDTO> preUserSavedDTO = preUserService.save(preUserDto);
        HateoasUtil.insertHateoasIntoPreUser(preUserSavedDTO);
        return preUserSavedDTO;
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public EntityModel<PreUserProfileResponseDTO> getPreUserById() {
        EntityModel<PreUserProfileResponseDTO> preUserProfileDTO = preUserService.getPreUserById();
        HateoasUtil.insertHateoasIntoPreUser(preUserProfileDTO);
        return preUserProfileDTO;
    }
}
