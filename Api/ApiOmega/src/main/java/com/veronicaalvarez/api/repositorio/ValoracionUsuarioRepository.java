package com.veronicaalvarez.api.repositorio;

import com.veronicaalvarez.api.modelo.ValoracionUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ValoracionUsuarioRepository extends JpaRepository<ValoracionUsuario, Integer> {
    Optional<ValoracionUsuario> findByLibroIdAndUsuarioId(int idLibro, int idUsuario);
}
