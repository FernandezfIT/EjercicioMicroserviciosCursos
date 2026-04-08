package com.EjercicioEV1_3.GestionCursos.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EjercicioEV1_3.GestionCursos.dto.request.CursoCreateRequest;
import com.EjercicioEV1_3.GestionCursos.dto.response.CursoResponse;
import com.EjercicioEV1_3.GestionCursos.model.Curso;
import com.EjercicioEV1_3.GestionCursos.repository.CursoRepository;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    // Crear Producto
    public CursoResponse guardarCurso(CursoCreateRequest request) {
        Curso curso = new Curso();

        // Setear Curso con Request
        curso.setIdCurso(request.getIdCurso());
        curso.setNombreCurso(request.getNombreCurso());
        curso.setCategoria(request.getCategoria());
        curso.setDuracionHoras(request.getDuracionHoras());
        curso.setCuposDisponibles(request.getCuposDisponibles());
        curso.setNombreInstructor(request.getNombreInstructor());

        Optional<Curso> buscarCurso = cursoRepository.buscarPorId(curso.getIdCurso());
        if (buscarCurso.isPresent()) {
            return null;
        }
        Curso cursoGuardado = cursoRepository.guardarCurso(curso);

        return new CursoResponse(
                cursoGuardado.getIdCurso(),
                cursoGuardado.getNombreCurso(),
                cursoGuardado.getCategoria(),
                cursoGuardado.getDuracionHoras(),
                cursoGuardado.getCuposDisponibles(),
                cursoGuardado.getNombreInstructor()

        );

    }

    // Obtener todos los Cursos de la lista
    public List<CursoResponse> obtenerTodoCurso() {
        List<Curso> cursos = cursoRepository.obtenerCursos();
        List<CursoResponse> respuesta = new ArrayList<>();

        for (Curso curso : cursos) {
            CursoResponse response = new CursoResponse(
                    curso.getIdCurso(),
                    curso.getNombreCurso(),
                    curso.getCategoria(),
                    curso.getDuracionHoras(),
                    curso.getCuposDisponibles(),
                    curso.getNombreInstructor());
            respuesta.add(response);
        }
        return respuesta;

    }

    // Obtener Curso por ID
    public CursoResponse buscarPorId(Integer idCurso) {
        Curso curso = cursoRepository.buscarPorId(idCurso).orElse(null);

        if (curso == null) {
            return null;
        }

        return new CursoResponse(
                curso.getIdCurso(),
                curso.getNombreCurso(),
                curso.getCategoria(),
                curso.getDuracionHoras(),
                curso.getCuposDisponibles(),
                curso.getNombreInstructor()

        );

    }

    // Actualizar un curso
    public CursoResponse actualizarCurso(Integer idCurso, CursoCreateRequest request) {
        Curso curso = new Curso();
        curso.setIdCurso(idCurso);
        curso.setNombreCurso(request.getNombreCurso());
        curso.setCategoria(request.getCategoria());
        curso.setDuracionHoras(request.getDuracionHoras());
        curso.setCuposDisponibles(request.getCuposDisponibles());
        curso.setNombreInstructor(request.getNombreInstructor());

        Curso cursoActualizado = cursoRepository.actualizarCurso(curso);

        if (cursoActualizado == null) {
            return null;
        }
        return new CursoResponse(
                curso.getIdCurso(),
                curso.getNombreCurso(),
                curso.getCategoria(),
                curso.getDuracionHoras(),
                curso.getCuposDisponibles(),
                curso.getNombreInstructor()

        );
    }

    // Eliminar Un Curso
    public boolean eliminarCurso(Integer idCurso) {
        return cursoRepository.eliminarCurso(idCurso);
    }

    // Ordenar Por Duracion
    public List<CursoResponse> ordenarPorDuracion() {

        List<CursoResponse> respuesta = new ArrayList<>();
        List<Curso> cursos = cursoRepository.obtenerCursos();
        for (Curso curso : cursos) {
            CursoResponse response = new CursoResponse(
                    curso.getIdCurso(),
                    curso.getNombreCurso(),
                    curso.getCategoria(),
                    curso.getDuracionHoras(),
                    curso.getCuposDisponibles(),
                    curso.getNombreInstructor());
            respuesta.add(response);
        }
        respuesta.sort(Comparator.comparingInt(CursoResponse::getDuracionHoras));
        return respuesta;

    }

}
