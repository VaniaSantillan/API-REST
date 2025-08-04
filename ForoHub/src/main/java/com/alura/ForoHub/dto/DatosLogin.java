package com.alura.ForoHub.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosLogin(
        @NotBlank String login,
        @NotBlank String password
) {}
