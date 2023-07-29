package com.efernandez.rossano.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    private String codigoBarra;
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
}
