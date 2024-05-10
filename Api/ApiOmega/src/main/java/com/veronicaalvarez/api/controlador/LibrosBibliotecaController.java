package com.veronicaalvarez.api.controlador;

import com.veronicaalvarez.api.modelo.LibrosBiblioteca;
import com.veronicaalvarez.api.repositorio.LibrosBibliotecaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/omega/librosBiblioteca")
public class LibrosBibliotecaController {

    private final LibrosBibliotecaRepository librosBibliotecaRepository;

    public LibrosBibliotecaController( LibrosBibliotecaRepository libroBibliotecaRepository) {
        this.librosBibliotecaRepository = libroBibliotecaRepository;
    }

    @GetMapping
    public ResponseEntity<?> obtenerLibrosBiblioteca() {
        List<LibrosBiblioteca> libros = librosBibliotecaRepository.findAll();

        if (libros.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(libros);
    }
}
