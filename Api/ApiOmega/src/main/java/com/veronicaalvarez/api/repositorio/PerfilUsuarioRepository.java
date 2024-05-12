package com.veronicaalvarez.api.repositorio;

import com.veronicaalvarez.api.modelo.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, Integer> {
    PerfilUsuario findByIdUsuario(int idUsuario);
}
