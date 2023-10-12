package com.efernandez.rossano.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class PedidoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer detalleId;
    private String id_producto;
    private Long id_pedido;
    private Integer cantidad;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_producto", referencedColumnName = "codigoBarra", insertable=false, updatable=false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_pedido", referencedColumnName = "pedidoId", insertable = false, updatable = false)
    private Pedido pedido;

    @JsonBackReference
    public Pedido getPedido() {
        return pedido;
    }

}
