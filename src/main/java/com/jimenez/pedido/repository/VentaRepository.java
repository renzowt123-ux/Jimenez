package com.jimenez.pedido.repository;

import com.jimenez.pedido.entity.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, String> {
    Page<Venta> findByUsuarioId(Integer usuarioId, Pageable pageable);
}
