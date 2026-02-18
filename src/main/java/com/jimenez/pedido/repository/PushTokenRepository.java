package com.jimenez.pedido.repository;

import com.jimenez.pedido.entity.PushToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PushTokenRepository extends JpaRepository<PushToken, String> {
    Page<PushToken> findByUsuarioId(Integer usuarioId, Pageable pageable);
}
