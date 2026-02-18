package com.jimenez.pedido.repository;

import com.jimenez.pedido.entity.ResumenMensual;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumenMensualRepository extends JpaRepository<ResumenMensual, String> {
    Page<ResumenMensual> findByUsuarioId(Integer usuarioId, Pageable pageable);
}
