package com.veronicaalvarez.api.repositorio;

import com.veronicaalvarez.api.modelo.Genero;
import com.veronicaalvarez.api.modelo.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz que proporciona métodos para interactuar con la base de datos de grupos.
 * 
 * Esta interfaz extiende JpaRepository, lo que significa que hereda operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * comunes para la entidad Grupo.
 */
public interface GrupoRepository extends JpaRepository<Grupo, Integer> {

}
