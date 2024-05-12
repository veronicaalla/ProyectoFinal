package com.veronicaalvarez.api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veronicaalvarez.api.modelo.Libro;

public interface LibroRepositorio extends JpaRepository<Libro, Integer>{
	/*List<Libro> findByGenero(String genero);
	List <Libro> findByISBN (String ISBN);
	List <Libro> findByTitulo (String titulo);
	List <Libro> findByAutor (String autor );*/
    List<Libro> findByGenero(int generoId);
}
