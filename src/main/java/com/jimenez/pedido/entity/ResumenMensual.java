package com.jimenez.pedido.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "resumen_mensual")
public class ResumenMensual {
    
    @Id
    @Column(length = 36)
    private String id;
    
    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", insertable = false, updatable = false)
    private UsuarioEmpresarial usuario;
    
    @Column(nullable = false)
    private Integer anio;
    
    @Column(nullable = false)
    private Integer mes;
    
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalMes;
    
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal promedioDiario;
    
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal proyeccionMes;
    
    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal porcentajeLimite;
    
    @Column(nullable = false)
    private Long createdAt;
    
    @Column(nullable = false)
    private Long updatedAt;
}
