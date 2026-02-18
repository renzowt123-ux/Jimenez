package com.jimenez.pedido.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "productos")
public class Producto {
    
    @Id
    @Column(length = 36)
    private String id;
    
    @Column(nullable = false, length = 120)
    private String nombre;
    
    @Column(length = 255)
    private String descripcion;
    
    @Column(name = "categoria_id", length = 36)
    private String categoriaId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", insertable = false, updatable = false)
    private Categoria categoria;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal precio;
    
    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer stock;
    
    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean activo = true;
    
    @Column(nullable = false)
    private Long createdAt;
    
    @Column(nullable = false)
    private Long updatedAt;
}
