package com.EjercicioEV1_3.GestionCursos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    private Integer idCurso;
    private String nombreCurso;
    private String categoria;
    private Integer duracionHoras;
    private Integer cuposDisponibles;
    private String nombreInstructor;
}
