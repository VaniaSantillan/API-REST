package com.alura.ForoHub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alura.ForoHub.dto.DatosRegistroTopico;
import com.alura.ForoHub.model.Topico;
import com.alura.ForoHub.repository.TopicoRepository;

import jakarta.validation.Valid;

public class TopicoController {

     @Autowired
    private TopicoRepository repository;

    @PostMapping
    public ResponseEntity<?> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos) {
        // Validar duplicado
        if (repository.findByTituloAndMensaje(datos.titulo(), datos.mensaje()).isPresent()) {
            return ResponseEntity.badRequest().body("Ya existe un tópico con ese título y mensaje.");
        }

        Topico topico = new Topico(datos.titulo(), datos.mensaje(), datos.autor(), datos.curso());
        repository.save(topico);
        return ResponseEntity.ok("Tópico registrado correctamente.");
    }

}
