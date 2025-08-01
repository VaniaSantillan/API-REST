package com.alura.ForoHub.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alura.ForoHub.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    // Para verificar duplicados
    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    // Filtro opcional: por nombre de curso y año
    @Query("SELECT t FROM Topico t WHERE t.curso.nombre = :curso AND YEAR(t.fechaCreacion) = :year")
    Page<Topico> findByCursoAndYear(String curso, int year, Pageable pageable);

     Optional<Topico> findByTituloAndMensaje(String titulo, String mensaje);
}