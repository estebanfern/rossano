package com.efernandez.rossano.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permiso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String code;
    private String nombre;
}
