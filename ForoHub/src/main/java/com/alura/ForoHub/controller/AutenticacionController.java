package com.alura.ForoHub.controller;

import com.alura.ForoHub.dto.DatosLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public String login(@RequestBody @Valid DatosLogin datos) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.password());
        authenticationManager.authenticate(authToken);
        return "Usuario autenticado correctamente";
    }
}

