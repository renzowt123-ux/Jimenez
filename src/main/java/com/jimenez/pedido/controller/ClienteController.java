package com.jimenez.pedido.controller;

import com.jimenez.pedido.entity.Clientes;
import com.jimenez.pedido.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public Page<Clientes> getAll(
            @PageableDefault(size = 10, sort = "id") Pageable pageable
    ) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Clientes getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Clientes create(@RequestBody Clientes clientes) {
        return service.create(clientes);
    }

    @PutMapping("/{id}")
    public Clientes update(
            @PathVariable Long id,
            @RequestBody Clientes clientes
    ) {
        return service.update(id, clientes);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
