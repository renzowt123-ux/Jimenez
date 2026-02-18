package com.jimenez.pedido.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "ventas")
public class Venta {
    
    @Id
    @Column(length = 36)
    private String id;
    
    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", insertable = false, updatable = false)
    private UsuarioEmpresarial usuario;
    
    @Column(nullable = false)
    private Long fecha;
    
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal montoTotal;
    
    @Column(length = 30)
    private String metodoPago;
    
    @Column(length = 255)
    private String observacion;
    
    @Column(nullable = false)
    private Long createdAt;
    
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VentaItem> items = new ArrayList<>();
}
