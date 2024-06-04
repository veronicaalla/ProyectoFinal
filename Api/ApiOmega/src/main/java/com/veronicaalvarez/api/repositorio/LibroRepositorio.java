package com.veronicaalvarez.api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veronicaalvarez.api.modelo.Libro;

/**
 * Interfaz que proporciona métodos para interactuar con la base de datos de libros.
 * 
 * Esta interfaz extiende JpaRepository, lo que significa que hereda operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * comunes para la entidad Libro.
 * 
 * Además, se definen dos métodos personalizados para encontrar libros por género o título.
 */
public interface LibroRepositorio extends JpaRepository<Libro, Integer>{
    /**
     * Encuentra todos los libros que pertenezcan a un género específico.
     * 
     * @param generoId el ID del género al que pertenecen los libros
     * @return una lista de libros que pertenezcan al género especificado
     */
    List<Libro> findByGenero(int generoId);

    /**
     * Encuentra todos los libros que tengan un título específico.
     * 
     * @param titulo el título del libro a buscar
     * @return una lista de libros que tengan el título especificado
     */
    List<Libro> findByTitulo(String titulo);
}
