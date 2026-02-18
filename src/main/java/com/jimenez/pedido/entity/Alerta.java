package com.jimenez.pedido.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "alertas")
public class Alerta {
    
    @Id
    @Column(length = 36)
    private String id;
    
    @Column(name = "usuario_id", nullable = false)
    private Integer usuarioId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", insertable = false, updatable = false)
    private UsuarioEmpresarial usuario;
    
    @Column(nullable = false, length = 40)
    private String tipo;
    
    @Column(nullable = false, length = 255)
    private String mensaje;
    
    @Column(nullable = false, length = 10)
    private String nivel;
    
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal valorProyectado;
    
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal limiteRus;
    
    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal porcentajeLimite;
    
    @Column(nullable = false)
    private Long createdAt;
    
    private Long readAt;
}
