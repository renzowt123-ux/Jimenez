package com.jimenez.pedido.repository;

import com.jimenez.pedido.entity.Alerta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, String> {
    Page<Alerta> findByUsuarioId(Integer usuarioId, Pageable pageable);
}
