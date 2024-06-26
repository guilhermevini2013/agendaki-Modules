package com.agendaki.financially.controllers;

import com.agendaki.financially.dtos.user.request.PreUserLoadDTO;
import com.agendaki.financially.dtos.user.request.PreUserSaveDTO;
import com.agendaki.financially.dtos.user.request.PreUserUpdateDTO;
import com.agendaki.financially.dtos.user.response.PreUserProfileResponseDTO;
import com.agendaki.financially.dtos.user.response.PreUserSaveResponseDTO;
import com.agendaki.financially.dtos.user.response.PreUserTokenDTO;
import com.agendaki.financially.services.user.PreUserService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/pre-user")
public class PreUserController {
    private final PreUserService preUserService;
    private static final String BASE_URL_PREUSER_CONTROLLER = "http://localhost:8081/financially/api/pre-user";

    public PreUserController(final PreUserService preUserService) {
        this.preUserService = preUserService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EntityModel<PreUserSaveResponseDTO> savePreUser(@RequestBody @Valid PreUserSaveDTO preUserDto) {
        EntityModel<PreUserSaveResponseDTO> preUserSavedDTO = preUserService.save(preUserDto);
        insertHateoas(preUserSavedDTO);
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
        insertHateoas(preUserProfileDTO);
        return preUserProfileDTO;
    }

    private void insertHateoas(EntityModel entityModel) {
        entityModel.add(List.of(Link.of(BASE_URL_PREUSER_CONTROLLER, "savePreUser")
                        .withType("POST")
                        .withRel("save"),
                Link.of(BASE_URL_PREUSER_CONTROLLER + "/auth", "loadPreUsers")
                        .withType("POST")
                        .withRel("load"),
                Link.of(BASE_URL_PREUSER_CONTROLLER, "updatePreUser")
                        .withType("PUT")
                        .withRel("update"),
                Link.of(BASE_URL_PREUSER_CONTROLLER, "getPreUserById")
                        .withType("GET")
                        .withRel("get profile")));
    }
}
