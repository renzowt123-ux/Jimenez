package com.jimenez.pedido.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
@Table(name = "horarios_medicamentos")
public class HorarioMedicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String dosis;

    @Column(nullable = false)
    private LocalTime hora;

    @Column(nullable = false, length = 50)
    private String frecuencia;

    @Column(length = 255)
    private String notas;

    @Column(nullable = false)
    private Boolean activo = true;
}
