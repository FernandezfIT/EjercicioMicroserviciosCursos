package com.EjercicioEV1_3.GestionCursos.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoUpdateRequest {

    @NotBlank(message = "EL nombre del curso es obligatorio")
    @Size(min = 5, max = 50, message = "El nombre del curso no puede tener menos de 5 y más de 50 caracteres")
    private String nombreCurso;

    @NotBlank(message = "La categoría del curso es obligatorio")
    @Size(min = 5, max = 50, message = "La categoría del curso no puede tener menos de 5 y más de 50 caracteres")
    private String categoria;

    @Positive(message = "La DURACION del curso debe ser positiva")
    private Integer duracionHoras;

    @Min(value = 0, message = "El valor mínimo de cupos al agregar el curso es 0")
    private Integer cuposDisponibles;

    @NotBlank(message = "EL nombre del instructor es obligatorio")
    @Size(min = 5, max = 50, message = "El nombre del instructor no puede tener menos de 2 y más de 50 caracteres")
    private String nombreInstructor;
}
