package com.EjercicioEV1_3.GestionCursos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EjercicioEV1_3.GestionCursos.dto.request.CursoCreateRequest;
import com.EjercicioEV1_3.GestionCursos.dto.response.CursoResponse;
import com.EjercicioEV1_3.GestionCursos.service.CursoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/GestionCursos")
public class CursoController {

    @Autowired
    private CursoService cursoService = new CursoService();

    // EndPoint para listar todo
    @GetMapping
    public ResponseEntity<?> obtenerTodo() {
        List<CursoResponse> cursos = cursoService.obtenerTodoCurso();
        return ResponseEntity.ok(cursos);
    }

    // EndPoint Para Buscar Por ID
    @GetMapping("/{idCurso}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer idCurso) {
        CursoResponse curso = cursoService.buscarPorId(idCurso);
        if (curso == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(curso);
    }

    // EndPoint para Crear Producto
    @PostMapping
    public ResponseEntity<?> guardarCurso(@Valid @RequestBody CursoCreateRequest request) {
        CursoResponse cursoGuardado = cursoService.guardarCurso(request);
        if (cursoGuardado == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoGuardado);
        }

    }

    // EndPoint para Actualizar Producto
    @PutMapping("/{idCurso}")
    public ResponseEntity<?> actualizarCurso(
            @PathVariable Integer idCurso,
            @Valid @RequestBody CursoCreateRequest request) {
        CursoResponse cursoActualizado = cursoService.actualizarCurso(idCurso, request);
        if (cursoActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cursoActualizado);
    }

    // EndPont para Eliminar Curso
    @DeleteMapping("/{idCurso}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Integer idCurso) {
        boolean eliminado = cursoService.eliminarCurso(idCurso);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    // Endpoint para Recibir Ordenado
    @GetMapping("/ObtenerOrdenado")
    public ResponseEntity<?> obtenerTodoOrdenado() {
        List<CursoResponse> cursos = cursoService.ordenarPorDuracion();
        return ResponseEntity.ok(cursos);
    }
}
