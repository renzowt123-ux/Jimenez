package com.jimenez.pedido.mapper;

import com.jimenez.pedido.dto.ProductoResponseDTO;
import com.jimenez.pedido.entity.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {
    
    public ProductoResponseDTO toDTO(Producto entity) {
        String categoriaNombre = entity.getCategoria() != null ? entity.getCategoria().getNombre() : null;
        
        return new ProductoResponseDTO(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getCategoriaId(),
                categoriaNombre,
                entity.getPrecio(),
                entity.getStock(),
                entity.getActivo()
        );
    }
    
    public Producto toEntity(ProductoResponseDTO dto) {
        Producto entity = new Producto();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setCategoriaId(dto.getCategoriaId());
        entity.setPrecio(dto.getPrecio());
        entity.setStock(dto.getStock());
        entity.setActivo(dto.getActivo());
        return entity;
    }
}
