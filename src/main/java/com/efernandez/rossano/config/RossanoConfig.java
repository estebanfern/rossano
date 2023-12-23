package com.efernandez.rossano.config;

import com.efernandez.rossano.model.Iva;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties("rossano")
@Getter
@Setter
public class RossanoConfig {

    private List<Iva> iva;

}
