package com.efernandez.rossano.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String rol;
    private String rolName;
    private String documento;
}
