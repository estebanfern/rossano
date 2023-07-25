package com.efernandez.rossano.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String code;
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rol")
    @JsonManagedReference
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<PermisoRol> permisos;
}
