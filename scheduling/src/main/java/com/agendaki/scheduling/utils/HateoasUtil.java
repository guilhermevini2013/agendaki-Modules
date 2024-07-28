package com.agendaki.scheduling.utils;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class HateoasUtil {

    private static final String URL_BASE_SCHEDULING = "http://localhost:8081/scheduling/api/dateJob";

    public static void insertHateoasIntoDateJob(RepresentationModel entityModel) {
        entityModel.add(List.of(
                Link.of(URL_BASE_SCHEDULING + "/common", "insertDateJob")
                        .withType("POST")
                        .withRel("create date job for instance"),
                Link.of(URL_BASE_SCHEDULING + "/holiday", "insertHolidayJob")
                        .withType("POST")
                        .withRel("create holiday job for instance"),
                Link.of(URL_BASE_SCHEDULING + "/common", "updateDateJob")
                        .withType("PUT")
                        .withRel("Update date job common"),
                Link.of(URL_BASE_SCHEDULING + "/common", "getAllDateJob")
                        .withType("GET")
                        .withRel("Get all date job by instance"),
                Link.of(URL_BASE_SCHEDULING + "/holiday", "getAllHolidayJob")
                        .withType("GET")
                        .withRel("Get all date holiday by instance")));
    }
}
