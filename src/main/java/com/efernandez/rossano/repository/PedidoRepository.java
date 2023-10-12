package com.efernandez.rossano.repository;

import com.efernandez.rossano.dao.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
