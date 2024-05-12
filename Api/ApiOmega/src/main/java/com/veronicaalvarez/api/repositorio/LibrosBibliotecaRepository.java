package com.veronicaalvarez.api.repositorio;

import com.veronicaalvarez.api.modelo.Biblioteca;
import com.veronicaalvarez.api.modelo.Libro;
import com.veronicaalvarez.api.modelo.LibrosBiblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibrosBibliotecaRepository extends JpaRepository<LibrosBiblioteca, Integer> {
    List<LibrosBiblioteca> findByBibliotecaId(Integer bibliotecaId);
    Optional<LibrosBiblioteca> findByBibliotecaIdAndLibroId(int bibliotecaId, int libroId);
}
