package com.jimenez.pedido.service;

import com.jimenez.pedido.dto.ProductoResponseDTO;
import com.jimenez.pedido.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductoService {
    Page<ProductoResponseDTO> findAll(Pageable pageable);
    Page<ProductoResponseDTO> search(String texto, Pageable pageable);
    ProductoResponseDTO findById(String id);
    ProductoResponseDTO create(Producto producto);
    ProductoResponseDTO update(String id, Producto producto);
    void deleteById(String id);
}
