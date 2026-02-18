package com.jimenez.pedido.dto;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String ruc;
    private String razonSocial;
    private String password;
    private String rol;
}
