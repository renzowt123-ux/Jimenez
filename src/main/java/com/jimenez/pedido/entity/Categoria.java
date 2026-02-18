package com.jimenez.pedido.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categorias")
public class Categoria {
    
    @Id
    @Column(length = 36)
    private String id;
    
    @Column(nullable = false, length = 80)
    private String nombre;
    
    @Column(length = 255)
    private String descripcion;
    
    @Column(nullable = false)
    private Long createdAt;
    
    @Column(nullable = false)
    private Long updatedAt;
}
