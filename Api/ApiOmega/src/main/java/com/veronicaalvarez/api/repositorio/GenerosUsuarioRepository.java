package com.veronicaalvarez.api.repositorio;

import com.veronicaalvarez.api.modelo.GeneroUsuario;
import com.veronicaalvarez.api.modelo.GenerosUsuarioId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio para la entidad GeneroUsuario.
 */
public interface GenerosUsuarioRepository extends JpaRepository<GeneroUsuario, GenerosUsuarioId> {
    /**
     * Busca la lista de géneros asociados a un usuario por su ID.
     *
     * @param idUsuario El ID del usuario cuyos géneros se van a buscar.
     * @return List<GeneroUsuario> La lista de géneros asociados al usuario especificado.
     */
    List<GeneroUsuario> findByIdUsuario(int idUsuario);
}