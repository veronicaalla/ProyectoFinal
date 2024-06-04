package com.veronicaalvarez.api.repositorio;

import com.veronicaalvarez.api.modelo.Biblioteca;
import com.veronicaalvarez.api.modelo.Libro;
import com.veronicaalvarez.api.modelo.LibrosBiblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


/**
 * Interfaz que proporciona métodos para interactuar con la base de datos de relaciones entre libros y bibliotecas.
 * 
 * Esta interfaz extiende JpaRepository, lo que significa que hereda operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * comunes para la entidad LibrosBiblioteca.
 * 
 * Además, se definen dos métodos personalizados para encontrar relaciones de libros y bibliotecas por ID de biblioteca o
 * por ID de biblioteca e ID de libro.
 */
public interface LibrosBibliotecaRepository extends JpaRepository<LibrosBiblioteca, Integer> {

    /**
     * Encuentra todas las relaciones de libros y bibliotecas que pertenezcan a una biblioteca específica.
     * 
     * @param bibliotecaId el ID de la biblioteca a la que pertenecen las relaciones
     * @return una lista de relaciones de libros y bibliotecas que pertenezcan a la biblioteca especificada
     */
    List<LibrosBiblioteca> findByBibliotecaId(Integer bibliotecaId);

    /**
     * Encuentra una relación de libro y biblioteca específica por ID de biblioteca e ID de libro.
     * 
     * @param bibliotecaId el ID de la biblioteca
     * @param libroId el ID del libro
     * @return una relación de libro y biblioteca que coincida con los ID especificados, o un Optional vacío si no se encuentra
     */
    Optional<LibrosBiblioteca> findByBibliotecaIdAndLibroId(int bibliotecaId, int libroId);
}
