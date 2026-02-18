package com.jimenez.pedido.controller;

import com.jimenez.pedido.dto.AuthResponseDTO;
import com.jimenez.pedido.dto.LoginRequestDTO;
import com.jimenez.pedido.dto.RegisterRequestDTO;
import com.jimenez.pedido.dto.UsuarioEmpresarialResponseDTO;
import com.jimenez.pedido.entity.UsuarioEmpresarial;
import com.jimenez.pedido.security.JwtService;
import com.jimenez.pedido.service.UsuarioEmpresarialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private UsuarioEmpresarialService usuarioService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            UsuarioEmpresarial usuario = usuarioService.findByRuc(loginRequest.getRuc());
            
            if (!passwordEncoder.matches(loginRequest.getPassword(), usuario.getPassword())) {
                return ResponseEntity.status(401).build();
            }

            String token = jwtService.generateToken(
                    usuario.getRuc(),
                    Map.of("rol", usuario.getRol().name(), "id", usuario.getId())
            );
            
            AuthResponseDTO response = AuthResponseDTO.builder()
                    .id(usuario.getId())
                    .ruc(usuario.getRuc())
                    .razonSocial(usuario.getRazonSocial())
                    .token(token)
                    .rol(usuario.getRol().name())
                    .tokenType("Bearer")
                    .build();
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO registerRequest) {
        try {
            UsuarioEmpresarial usuario = new UsuarioEmpresarial();
            usuario.setRuc(registerRequest.getRuc());
            usuario.setRazonSocial(registerRequest.getRazonSocial());
            usuario.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
            usuario.setRol(UsuarioEmpresarial.RolEnum.valueOf(registerRequest.getRol().toUpperCase()));
            usuario.setEstado(true);
            
            UsuarioEmpresarialResponseDTO created = usuarioService.create(usuario);

            String token = jwtService.generateToken(
                    created.getRuc(),
                    Map.of("rol", created.getRol(), "id", created.getId())
            );
            
            AuthResponseDTO response = AuthResponseDTO.builder()
                    .id(created.getId())
                    .ruc(created.getRuc())
                    .razonSocial(created.getRazonSocial())
                    .token(token)
                    .rol(created.getRol())
                    .tokenType("Bearer")
                    .build();
            
            return ResponseEntity.status(201).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }
}
