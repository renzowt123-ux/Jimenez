package com.jimenez.pedido.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "clientes")
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String razonSocial;
    private String documento;
    private String direccion;
    private String telefono;
}
