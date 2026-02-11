package com.jimenez.pedido.controller;

import com.jimenez.pedido.entity.HorarioMedicamento;
import com.jimenez.pedido.service.HorarioMedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/horarios-medicamentos")
public class HorarioMedicamentoController {

    @Autowired
    private HorarioMedicamentoService service;

    @GetMapping
    public Page<HorarioMedicamento> getAll(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 10, sort = "id") Pageable pageable
    ) {
        return service.search(search, pageable);
    }

    @GetMapping("/{id}")
    public HorarioMedicamento getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<HorarioMedicamento> create(@RequestBody HorarioMedicamento horarioMedicamento) {
        HorarioMedicamento created = service.create(horarioMedicamento);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioMedicamento> update(@PathVariable Long id, @RequestBody HorarioMedicamento horarioMedicamento) {
        return ResponseEntity.ok(service.update(id, horarioMedicamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
