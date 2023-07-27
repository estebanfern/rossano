package com.efernandez.rossano.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PasswordGenerator {

    private Integer passwordLength = 8;

    public String generate() {
        String randomUUID = UUID.randomUUID().toString();
        String password = randomUUID.replaceAll("[\\-]", "");
        if (password.length() > passwordLength) {
            password = password.substring(0, passwordLength);
        }
        return password;
    }
}
