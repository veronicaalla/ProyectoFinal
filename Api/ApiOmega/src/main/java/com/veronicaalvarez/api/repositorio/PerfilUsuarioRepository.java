package com.veronicaalvarez.api.repositorio;

import com.veronicaalvarez.api.modelo.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Interfaz que proporciona métodos para interactuar con la base de datos de perfiles de usuario.
 * 
 * Esta interfaz extiende JpaRepository, lo que significa que hereda operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * comunes para la entidad PerfilUsuario.
 * 
 * Además, se define un método personalizado para encontrar un perfil de usuario por ID de usuario.
 */
public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, Integer> {

    /**
     * Encuentra un perfil de usuario específico por ID de usuario.
     * 
     * @param idUsuario el ID del usuario al que pertenece el perfil
     * @return un perfil de usuario que coincida con el ID especificado, o null si no se encuentra
     */
    PerfilUsuario findByIdUsuario(int idUsuario);
}
