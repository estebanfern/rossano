package com.efernandez.rossano.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.mail")
@Data
public class MailConfiguration {

    private String username;
    private String password;

}
