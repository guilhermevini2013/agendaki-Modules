package com.agendaki.financially.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseErrorModel {
    private final Instant timestamp;
    private final Integer status;
    private final String path;
    private List<String> errors;
    private String error;

    public ResponseErrorModel(Instant timestamp, Integer status, List<String> errors, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.errors = errors;
        this.path = path;
    }

    public ResponseErrorModel(Instant timestamp, Integer status, String error, String path) {
        this.error = error;
        this.path = path;
        this.timestamp = timestamp;
        this.status = status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }
}
