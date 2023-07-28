package com.efernandez.rossano.model;

import lombok.Data;

@Data
public class GenericExceptionResponse {
    private String message;
    private int status;
}
