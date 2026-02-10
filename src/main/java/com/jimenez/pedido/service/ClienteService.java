package com.jimenez.pedido.service;

import com.jimenez.pedido.entity.Clientes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {

    Page<Clientes> findAll(Pageable pageable);
    Clientes findById(Long id);
    Clientes create(Clientes clientes);
    Clientes update(Long id, Clientes clientes);
    void deleteById(Long id);
}
