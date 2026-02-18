package com.jimenez.pedido.mapper;

import com.jimenez.pedido.dto.CategoriaResponseDTO;
import com.jimenez.pedido.entity.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {
    
    public CategoriaResponseDTO toDTO(Categoria entity) {
        return new CategoriaResponseDTO(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion()
        );
    }
    
    public Categoria toEntity(CategoriaResponseDTO dto) {
        Categoria entity = new Categoria();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        return entity;
    }
}
