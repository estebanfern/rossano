package com.efernandez.rossano.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Producto {
    @Id
    private String codigoBarra;
    @Column(nullable = true, unique = true)
    private String codigoInterno;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private Long existencia;
    @Column(nullable = false)
    private String descripcion;
    @Column(nullable = false)
    private Long precioCosto;
    @Column(nullable = false)
    private Long precioFinal;
    @Column(nullable = false)
    private String cat;

    @ManyToOne(optional = false)
    @JsonManagedReference
    @JoinColumn(name = "cat", referencedColumnName = "code", insertable = false, updatable = false)
    private Categoria categoria;

}
