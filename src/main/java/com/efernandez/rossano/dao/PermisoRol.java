package com.efernandez.rossano.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermisoRol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String rolCode;
    private String permisoCode;

    @ManyToOne
    @JoinColumn(name = "rolCode", referencedColumnName = "code", insertable = false, updatable = false)
    @JsonBackReference
    private Rol rol;

    @ManyToOne(optional = false)
    @JoinColumn(name = "permisoCode", referencedColumnName = "code", insertable = false, updatable = false)
    private Permiso permiso;

}
