package com.jimenez.pedido.service.impi;

import com.jimenez.pedido.entity.HorarioMedicamento;
import com.jimenez.pedido.repository.HorarioMedicamentoRepository;
import com.jimenez.pedido.service.HorarioMedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HorarioMedicamentoServiceImpi implements HorarioMedicamentoService {

    @Autowired
    private HorarioMedicamentoRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Page<HorarioMedicamento> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HorarioMedicamento> search(String texto, Pageable pageable) {
        if (texto == null || texto.isBlank()) {
            return repository.findAll(pageable);
        }
        return repository.findByNombreContainingIgnoreCase(texto, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public HorarioMedicamento findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public HorarioMedicamento create(HorarioMedicamento horarioMedicamento) {
        return repository.save(horarioMedicamento);
    }

    @Override
    @Transactional
    public HorarioMedicamento update(Long id, HorarioMedicamento horarioMedicamento) {
        HorarioMedicamento existente = findById(id);
        existente.setNombre(horarioMedicamento.getNombre());
        existente.setDosis(horarioMedicamento.getDosis());
        existente.setHora(horarioMedicamento.getHora());
        existente.setFrecuencia(horarioMedicamento.getFrecuencia());
        existente.setNotas(horarioMedicamento.getNotas());
        existente.setActivo(horarioMedicamento.getActivo());
        return repository.save(existente);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
