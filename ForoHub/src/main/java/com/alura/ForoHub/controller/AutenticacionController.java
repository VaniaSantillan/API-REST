package com.alura.ForoHub.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.ForoHub.dto.DatosJWTToken;
import com.alura.ForoHub.dto.DatosLogin;
import com.alura.ForoHub.service.TokenService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/login")
public class AutenticacionController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AutenticacionController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public DatosJWTToken login(@RequestBody @Valid DatosLogin datos) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.clave());
        authenticationManager.authenticate(authToken);
        String token = tokenService.generarToken(datos.login());
        return new DatosJWTToken(token);
    }

}

