package com.jimenez.pedido.mapper;

import com.jimenez.pedido.dto.UsuarioEmpresarialResponseDTO;
import com.jimenez.pedido.entity.UsuarioEmpresarial;
import org.springframework.stereotype.Component;

@Component
public class UsuarioEmpresarialMapper {
    
    public UsuarioEmpresarialResponseDTO toDTO(UsuarioEmpresarial entity) {
        return new UsuarioEmpresarialResponseDTO(
                entity.getId(),
                entity.getRuc(),
                entity.getRazonSocial(),
                entity.getEstado(),
                entity.getRol().name()
        );
    }
    
    public UsuarioEmpresarial toEntity(UsuarioEmpresarialResponseDTO dto) {
        UsuarioEmpresarial entity = new UsuarioEmpresarial();
        entity.setId(dto.getId());
        entity.setRuc(dto.getRuc());
        entity.setRazonSocial(dto.getRazonSocial());
        entity.setEstado(dto.getEstado());
        return entity;
    }
}
