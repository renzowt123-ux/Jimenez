package com.jimenez.pedido.repository;

import com.jimenez.pedido.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, String> {
    Page<Categoria> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
