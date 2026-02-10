package com.jimenez.pedido.repository;


import com.jimenez.pedido.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Page<Producto> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);

}
