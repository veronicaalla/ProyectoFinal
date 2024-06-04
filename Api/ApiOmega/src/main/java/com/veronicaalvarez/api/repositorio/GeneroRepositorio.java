package com.veronicaalvarez.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veronicaalvarez.api.modelo.Genero;

import java.util.Optional;

/**
 * Interfaz que proporciona métodos para interactuar con la base de datos de géneros.
 * 
 * Esta interfaz extiende JpaRepository, lo que significa que hereda operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * comunes para la entidad Genero.
 */
public interface GeneroRepositorio extends JpaRepository<Genero, Integer>{

     /**
     * Encuentra un género por su nombre.
     * 
     * @param nombre el nombre del género
     * @return el género encontrado, o null si no se encuentra ninguno
     */
    Genero findByNombre(String nombre);
}
