package com.jimenez.pedido.service;

import com.jimenez.pedido.entity.HorarioMedicamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HorarioMedicamentoService {

    Page<HorarioMedicamento> findAll(Pageable pageable);
    Page<HorarioMedicamento> search(String texto, Pageable pageable);
    HorarioMedicamento findById(Long id);
    HorarioMedicamento create(HorarioMedicamento horarioMedicamento);
    HorarioMedicamento update(Long id, HorarioMedicamento horarioMedicamento);
    void deleteById(Long id);
}
