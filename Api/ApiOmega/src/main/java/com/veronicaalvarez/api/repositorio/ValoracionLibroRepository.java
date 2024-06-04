package com.veronicaalvarez.api.repositorio;

import com.veronicaalvarez.api.modelo.ValoracionLibro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interfaz que proporciona métodos para interactuar con la base de datos de valoraciones de libros.
 * 
 * Esta interfaz extiende JpaRepository, lo que significa que hereda operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * comunes para la entidad ValoracionLibro.
 * 
 * Además, se define un método personalizado para encontrar una valoración de libro por ID de libro.
 */
public interface ValoracionLibroRepository extends JpaRepository<ValoracionLibro, Integer> {

    /**
     * Encuentra una valoración de libro específica por ID de libro.
     * 
     * @param idLibro el ID del libro al que pertenece la valoración
     * @return una valoración de libro que coincida con el ID especificado, o un Optional vacío si no se encuentra
     */
    Optional<ValoracionLibro> findByIdLibro(int idLibro);
}
