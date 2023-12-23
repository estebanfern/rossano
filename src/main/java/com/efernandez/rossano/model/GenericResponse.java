package com.efernandez.rossano.model;

import lombok.Data;

@Data
public class GenericResponse {

    private String message;
    private int status;
    private String code;

    public GenericResponse(String message, int status, String code) {
        this.message = message;
        this.status = status;
        this.code = code;
    }

}
