package com.jimenez.pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoriaResponseDTO {
    private String id;
    private String nombre;
    private String descripcion;
}
