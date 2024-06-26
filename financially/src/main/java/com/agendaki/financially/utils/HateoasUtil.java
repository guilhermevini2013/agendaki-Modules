package com.agendaki.financially.utils;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import java.util.List;

public class HateoasUtil {
    private static final String BASE_URL_PREUSER_CONTROLLER = "http://localhost:8081/financially/api/pre-user";

    public static void insertHateoasIntoPreUser(EntityModel entityModel) {
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
