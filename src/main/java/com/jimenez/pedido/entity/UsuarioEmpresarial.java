package com.jimenez.pedido.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@Entity
@Table(name = "usuarios_empresariales")
public class UsuarioEmpresarial implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(unique = true, nullable = false, length = 15)
    private String ruc;
    
    @Column(nullable = false, length = 150)
    private String razonSocial;
    
    @Column(nullable = false, length = 255)
    @JsonAlias("password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwordHash;
    
    @Column(nullable = false)
    private Boolean estado = true;
    
    @Column(nullable = false)
    private Long createdAt;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RolEnum rol;
    
    public enum RolEnum {
        EMPRESA, CONTADOR, ADMIN
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
            new SimpleGrantedAuthority("ROLE_" + rol.name())
        );
    }
    
    @Override
    @JsonIgnore
    public String getPassword() {
        return passwordHash;
    }
    
    @Override
    public String getUsername() {
        return ruc;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return estado;
    }
}
