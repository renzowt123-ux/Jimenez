package com.jimenez.pedido.repository;

import com.jimenez.pedido.entity.HorarioMedicamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioMedicamentoRepository extends JpaRepository<HorarioMedicamento, Long> {

    Page<HorarioMedicamento> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);

}
