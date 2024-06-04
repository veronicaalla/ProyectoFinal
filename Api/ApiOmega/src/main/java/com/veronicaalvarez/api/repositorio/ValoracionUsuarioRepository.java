package com.veronicaalvarez.api.repositorio;

import com.veronicaalvarez.api.modelo.ValoracionUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * Interfaz que proporciona métodos para interactuar con la base de datos de valoraciones de usuarios.
 * 
 * Esta interfaz extiende JpaRepository, lo que significa que hereda operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * comunes para la entidad ValoracionUsuario.
 * 
 * Además, se define un método personalizado para encontrar una valoración de usuario específica por ID de libro y ID de usuario.
 */
public interface ValoracionUsuarioRepository extends JpaRepository<ValoracionUsuario, Integer> {

     /**
     * Encuentra una valoración de usuario específica por ID de libro y ID de usuario.
     * 
     * @param idLibro el ID del libro relacionado con la valoración
     * @param idUsuario el ID del usuario que realizó la valoración
     * @return una valoración de usuario que coincida con los IDs especificados, o un Optional vacío si no se encuentra
     */
    Optional<ValoracionUsuario> findByLibroIdAndUsuarioId(int idLibro, int idUsuario);
}
