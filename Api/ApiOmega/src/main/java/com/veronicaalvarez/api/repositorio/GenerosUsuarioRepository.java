package com.veronicaalvarez.api.repositorio;

import com.veronicaalvarez.api.modelo.GeneroUsuario;
import com.veronicaalvarez.api.modelo.GenerosUsuarioId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenerosUsuarioRepository extends JpaRepository<GeneroUsuario, GenerosUsuarioId> {
    List<GeneroUsuario> findByIdUsuario(int idUsuario);
}