package com.efernandez.rossano.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GenericException extends Exception {

    private String code;
    private int status;

    public GenericException(String message, String code, int status) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public GenericException(String message, Throwable cause, String code, int status) {
        super(message, cause);
        this.code = code;
        this.status = status;
    }

}
