package com.jimenez.pedido.service;

import com.jimenez.pedido.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface ProductoService {
    Page<Producto> findAll(Pageable pageable);
    Page<Producto> search(String texto, Pageable pageable);
    Producto findById(Long id);
    Producto create(Producto producto);
    Producto update(Long id, Producto producto);
    void deleteById(Long id);
}
