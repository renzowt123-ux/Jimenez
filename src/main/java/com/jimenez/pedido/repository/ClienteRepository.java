package com.jimenez.pedido.repository;

import com.jimenez.pedido.entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Clientes, Long> {

}
