package com.jimenez.pedido.controller;

import com.jimenez.pedido.dto.CategoriaResponseDTO;
import com.jimenez.pedido.entity.Categoria;
import com.jimenez.pedido.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    
    @Autowired
    private CategoriaService service;
    
    @GetMapping
    public Page<CategoriaResponseDTO> getAll(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 10, sort = "id") Pageable pageable
    ) {
        return service.search(search, pageable);
    }
    
    @GetMapping("/{id}")
    public CategoriaResponseDTO getById(@PathVariable String id) {
        return service.findById(id);
    }
    
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> create(@RequestBody Categoria categoria) {
        return ResponseEntity.status(201).body(service.create(categoria));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> update(@PathVariable String id, @RequestBody Categoria categoria) {
        return ResponseEntity.ok(service.update(id, categoria));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
