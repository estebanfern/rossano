package com.efernandez.rossano.dao;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedidoId;
    private OffsetDateTime fecha;
    private String name;
    private String ruc;
    private String condicion;
    private Long iva10;
    private Long iva5;
    private Long ivaEx;
    private BigDecimal totalIva;
    private BigDecimal total;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PedidoProducto> productos;

    @JsonManagedReference
    public List<PedidoProducto> getProductos() {
        return productos;
    }

}
