package com.veronicaalvarez.api.repositorio;

import com.veronicaalvarez.api.modelo.LibroErroneo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interfaz que proporciona métodos para interactuar con la base de datos de libros erróneos.
 * 
 * Esta interfaz extiende JpaRepository, lo que significa que hereda operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * comunes para la entidad LibroErroneo.
 * 
 * Además, se define un método personalizado para encontrar todos los libros erróneos no resueltos.
 */
public interface LibroErroneoRepositorio extends JpaRepository<LibroErroneo, Integer> {

    /**
     * Encuentra todos los libros erróneos que no estén resueltos.
     * 
     * @return una lista de libros erróneos no resueltos
     */
    List<LibroErroneo> findByResueltoIsNull();
}
