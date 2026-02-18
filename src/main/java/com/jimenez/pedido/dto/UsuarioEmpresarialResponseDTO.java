package com.jimenez.pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioEmpresarialResponseDTO {
    private Integer id;
    private String ruc;
    private String razonSocial;
    private Boolean estado;
    private String rol;
}
