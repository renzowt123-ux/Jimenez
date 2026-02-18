package com.jimenez.pedido.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "push_tokens")
public class PushToken {
    
    @Id
    @Column(length = 36)
    private String id;
    
    @Column(nullable = false)
    private Integer usuarioId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", insertable = false, updatable = false)
    private UsuarioEmpresarial usuario;
    
    @Column(nullable = false, length = 255)
    private String token;
    
    @Column(nullable = false, length = 20)
    private String platform;
    
    @Column(nullable = false)
    private Long createdAt;
    
    private Long lastSeenAt;
}
