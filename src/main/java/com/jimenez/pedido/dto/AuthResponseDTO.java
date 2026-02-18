package com.jimenez.pedido.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDTO {
    private Integer id;
    private String ruc;
    private String razonSocial;
    private String token;
    private String tokenType;
    private String rol;
}
