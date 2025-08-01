package com.alura.ForoHub.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizarTopico(
    @NotBlank String titulo,
    @NotBlank String mensaje,
    @NotBlank String autor,
    @NotBlank String curso
) {

}
