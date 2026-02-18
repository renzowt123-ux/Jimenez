package com.jimenez.pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductoResponseDTO {
    private String id;
    private String nombre;
    private String descripcion;
    private String categoriaId;
    private String categoriaNombre;
    private BigDecimal precio;
    private Integer stock;
    private Boolean activo;
}
