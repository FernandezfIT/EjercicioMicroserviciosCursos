package com.EjercicioEV1_3.GestionCursos.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.EjercicioEV1_3.GestionCursos.model.Curso;

@Repository
public class CursoRepository {

    // Arreglo que contiene los cursos creados
    private List<Curso> listaCursos = new ArrayList<>();

    // Guardar en la lista
    public Curso guardarCurso(Curso curso) {
        listaCursos.add(curso);
        return curso;
    }

    // Listar cursos
    public List<Curso> obtenerCursos() {
        return new ArrayList<>(listaCursos);
    }

    // Buscar Curso por ID
    public Optional<Curso> buscarPorId(Integer idCurso) {
        for (Curso curso : listaCursos) {
            if (idCurso.equals(curso.getIdCurso())) {
                return Optional.of(curso);
            }
        }
        return Optional.empty();
    }

    // Actualizar Curso por ID
    public Curso actualizarCurso(Curso actualizar) {
        for (Curso curso : listaCursos) {
            if (actualizar.getIdCurso().equals(curso.getIdCurso())) {
                curso.setNombreCurso(actualizar.getNombreCurso());
                curso.setCategoria(actualizar.getCategoria());
                curso.setDuracionHoras(actualizar.getDuracionHoras());
                curso.setCuposDisponibles(actualizar.getCuposDisponibles());
                curso.setNombreInstructor(actualizar.getNombreInstructor());

                return curso;
            }
        }
        return null;
    }

    // Eliminar Curso por ID
    public boolean eliminarCurso(Integer idCurso){
        for (Curso curso : listaCursos) {
            if (idCurso.equals(curso.getIdCurso())) {
                listaCursos.remove(curso);
                return true;
            }
        }
        return false;
    }

}
