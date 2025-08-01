package com.alura.ForoHub.dto;

import java.time.LocalDateTime;

public record TopicoResponse(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String status,
        String autor,
        String curso
) {}
