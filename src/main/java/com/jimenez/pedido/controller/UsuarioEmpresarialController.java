package com.jimenez.pedido.controller;

import com.jimenez.pedido.dto.UsuarioEmpresarialResponseDTO;
import com.jimenez.pedido.entity.UsuarioEmpresarial;
import com.jimenez.pedido.service.UsuarioEmpresarialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioEmpresarialController {
    
    @Autowired
    private UsuarioEmpresarialService service;
    
    @GetMapping
    public Page<UsuarioEmpresarialResponseDTO> getAll(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 10, sort = "id") Pageable pageable
    ) {
        return service.search(search, pageable);
    }
    
    @GetMapping("/{id}")
    public UsuarioEmpresarialResponseDTO getById(@PathVariable Integer id) {
        return service.findById(id);
    }
    
    @PostMapping
    public ResponseEntity<UsuarioEmpresarialResponseDTO> create(@RequestBody UsuarioEmpresarial usuario) {
        return ResponseEntity.status(201).body(service.create(usuario));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEmpresarialResponseDTO> update(@PathVariable Integer id, @RequestBody UsuarioEmpresarial usuario) {
        return ResponseEntity.ok(service.update(id, usuario));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
