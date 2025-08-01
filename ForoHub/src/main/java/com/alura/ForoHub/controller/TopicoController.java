package com.alura.ForoHub.controller;

import com.alura.ForoHub.dto.DatosRegistroTopico;
import com.alura.ForoHub.dto.TopicoResponse;
import com.alura.ForoHub.model.Topico;
import com.alura.ForoHub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    // Registrar nuevo tópico
    @PostMapping
    public ResponseEntity<?> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos) {

        if (repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            return ResponseEntity.badRequest().body("Ya existe un tópico con ese título y mensaje.");
        }

        Topico topico = new Topico(datos.titulo(), datos.mensaje(), datos.autor(), datos.curso());
        repository.save(topico);
        return ResponseEntity.ok("Tópico registrado correctamente.");
    }

    // Listar con paginación y filtro opcional
    @GetMapping
    public Page<TopicoResponse> listar(
            @RequestParam String curso,
            @RequestParam int year,
            @PageableDefault(size = 10, sort = "fechaCreacion") Pageable pageable) {

        return repository.findByCursoAndYear(curso, year, pageable)
        .map(t -> new TopicoResponse(
                t.getTitulo(),
                t.getMensaje(),
                t.getFechaCreacion(),
                t.getStatus().toString(),
                t.getAutor(),
                t.getCurso()
        ));

    }
}

