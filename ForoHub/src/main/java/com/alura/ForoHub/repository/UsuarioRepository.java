package com.alura.ForoHub.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.alura.ForoHub.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String login);
}
