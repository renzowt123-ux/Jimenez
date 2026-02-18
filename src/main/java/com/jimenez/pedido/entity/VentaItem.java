package com.jimenez.pedido.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "venta_items")
public class VentaItem {
    
    @Id
    @Column(length = 36)
    private String id;
    
    @Column(length = 36, nullable = false)
    private String ventaId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id", insertable = false, updatable = false)
    private Venta venta;
    
    @Column(length = 36)
    private String productoId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", insertable = false, updatable = false)
    private Producto producto;
    
    @Column(nullable = false, length = 120)
    private String productoNombre;
    
    @Column(nullable = false)
    private Integer cantidad;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;
    
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal subtotal;
}
