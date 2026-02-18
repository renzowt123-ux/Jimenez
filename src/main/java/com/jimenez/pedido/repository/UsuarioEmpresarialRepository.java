package com.jimenez.pedido.repository;

import com.jimenez.pedido.entity.UsuarioEmpresarial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioEmpresarialRepository extends JpaRepository<UsuarioEmpresarial, Integer> {
    Optional<UsuarioEmpresarial> findByRuc(String ruc);
    Page<UsuarioEmpresarial> findByRucContainingIgnoreCaseOrRazonSocialContainingIgnoreCase(
            String ruc, String razonSocial, Pageable pageable);
}
