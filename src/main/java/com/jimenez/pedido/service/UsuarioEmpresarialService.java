package com.jimenez.pedido.service;

import com.jimenez.pedido.dto.UsuarioEmpresarialResponseDTO;
import com.jimenez.pedido.entity.UsuarioEmpresarial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioEmpresarialService {
    Page<UsuarioEmpresarialResponseDTO> findAll(Pageable pageable);
    Page<UsuarioEmpresarialResponseDTO> search(String texto, Pageable pageable);
    UsuarioEmpresarialResponseDTO findById(Integer id);
    UsuarioEmpresarialResponseDTO create(UsuarioEmpresarial usuario);
    UsuarioEmpresarialResponseDTO update(Integer id, UsuarioEmpresarial usuario);
    void deleteById(Integer id);
    UsuarioEmpresarial findByRuc(String ruc);
}
