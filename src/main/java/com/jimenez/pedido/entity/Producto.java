package com.jimenez.pedido.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String descripcion;
    private String codigo;
    @Column(name= "precio_unitario",precision = 8, scale= 2, nullable = false)
    private BigDecimal precioUnitario;
}
