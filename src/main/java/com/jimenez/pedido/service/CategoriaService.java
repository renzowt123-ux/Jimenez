package com.jimenez.pedido.service;

import com.jimenez.pedido.dto.CategoriaResponseDTO;
import com.jimenez.pedido.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoriaService {
    Page<CategoriaResponseDTO> findAll(Pageable pageable);
    Page<CategoriaResponseDTO> search(String texto, Pageable pageable);
    CategoriaResponseDTO findById(String id);
    CategoriaResponseDTO create(Categoria categoria);
    CategoriaResponseDTO update(String id, Categoria categoria);
    void deleteById(String id);
}
