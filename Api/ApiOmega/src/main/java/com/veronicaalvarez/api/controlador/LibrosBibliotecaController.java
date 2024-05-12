package com.veronicaalvarez.api.controlador;

import com.veronicaalvarez.api.modelo.Biblioteca;
import com.veronicaalvarez.api.modelo.Libro;
import com.veronicaalvarez.api.modelo.LibrosBiblioteca;
import com.veronicaalvarez.api.repositorio.LibrosBibliotecaRepository;
import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/omega/librosBiblioteca")
public class LibrosBibliotecaController {

    private final LibrosBibliotecaRepository librosBibliotecaRepository;

    public LibrosBibliotecaController( LibrosBibliotecaRepository libroBibliotecaRepository) {
        this.librosBibliotecaRepository = libroBibliotecaRepository;
    }


    @GetMapping("/biblioteca/{bibliotecaId}")
    public ResponseEntity<?>  obtenerLibrosPorBiblioteca(Integer bibliotecaId) {

        List<LibrosBiblioteca> librosBiblioteca = librosBibliotecaRepository.findByBibliotecaId(bibliotecaId);

        if (librosBiblioteca.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(librosBiblioteca);
    }



    @PostMapping("/{bibliotecaId}/libro/{libroId}")
    public ResponseEntity<String> agregarLibroABiblioteca(@PathVariable("bibliotecaId") int bibliotecaId, @PathVariable("libroId") int libroId) {
        try {
            // Crear una nueva instancia de LibrosBiblioteca
            LibrosBiblioteca librosBiblioteca = new LibrosBiblioteca();
            Biblioteca biblioteca = new Biblioteca();
            biblioteca.setId(bibliotecaId); // Suponiendo que ya tienes un método para buscar la biblioteca por ID
            librosBiblioteca.setBiblioteca(biblioteca);
            Libro libro = new Libro();
            libro.setId(libroId); // Suponiendo que ya tienes un método para buscar el libro por ID
            librosBiblioteca.setLibro(libro);

            // Guardar la nueva instancia en la base de datos
            librosBibliotecaRepository.save(librosBiblioteca);

            return ResponseEntity.ok("Libro agregado exitosamente a la biblioteca.");
        } catch (Exception e) {
            // Si ocurre un error, devuelve una respuesta de error al cliente
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al agregar el libro a la biblioteca. Por favor, inténtalo de nuevo más tarde.");
        }
    }

    @DeleteMapping("/{bibliotecaId}/libro/{libroId}")
    public ResponseEntity<String> eliminarLibroDeBiblioteca(@PathVariable("bibliotecaId") int bibliotecaId, @PathVariable("libroId") int libroId) {
        try {
            // Buscar el libro a eliminar de la biblioteca
            Optional<LibrosBiblioteca> librosBibliotecaOptional = librosBibliotecaRepository.findByBibliotecaIdAndLibroId(bibliotecaId, libroId);

            // Verificar si el libro existe en la biblioteca
            if (librosBibliotecaOptional.isPresent()) {
                // Eliminar el libro de la biblioteca
                librosBibliotecaRepository.delete(librosBibliotecaOptional.get());
                return ResponseEntity.ok("Libro eliminado de la biblioteca exitosamente.");
            } else {
                return ResponseEntity.notFound().build(); // Si el libro no existe en la biblioteca, devolver una respuesta 404
            }
        } catch (Exception e) {
            // Si ocurre un error, devuelve una respuesta de error al cliente
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el libro de la biblioteca. Por favor, inténtalo de nuevo más tarde.");
        }
    }


    @PutMapping("/{bibliotecaOrigenId}/libro/{libroId}/mover/{bibliotecaDestinoId}")
    public ResponseEntity<String> moverLibroEntreBibliotecas(@PathVariable("bibliotecaOrigenId") int bibliotecaOrigenId,
                                                             @PathVariable("libroId") int libroId,
                                                             @PathVariable("bibliotecaDestinoId") int bibliotecaDestinoId) {
        try {
            // Eliminar el libro de la biblioteca original
            Optional<LibrosBiblioteca> libroEnBibliotecaOrigenOptional = librosBibliotecaRepository.findByBibliotecaIdAndLibroId(bibliotecaOrigenId, libroId);
            if (!libroEnBibliotecaOrigenOptional.isPresent()) {
                return ResponseEntity.notFound().build(); // Si el libro no existe en la biblioteca original, devolver una respuesta 404 (No encontrado)
            }
            librosBibliotecaRepository.delete(libroEnBibliotecaOrigenOptional.get());

            // Agregar el libro a la nueva biblioteca
            LibrosBiblioteca nuevoLibroEnBibliotecaDestino = new LibrosBiblioteca();
            Biblioteca bibliotecaDestino = new Biblioteca();
            bibliotecaDestino.setId(bibliotecaDestinoId); // Suponiendo que ya tienes un método para buscar la biblioteca por ID
            nuevoLibroEnBibliotecaDestino.setBiblioteca(bibliotecaDestino);
            Libro libro = new Libro();
            libro.setId(libroId); // Suponiendo que ya tienes un método para buscar el libro por ID
            nuevoLibroEnBibliotecaDestino.setLibro(libro);
            librosBibliotecaRepository.save(nuevoLibroEnBibliotecaDestino);

            return ResponseEntity.ok("Libro movido exitosamente a la nueva biblioteca.");
        } catch (Exception e) {
            // Si ocurre un error, devuelve una respuesta de error al cliente
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al mover el libro entre bibliotecas. Por favor, inténtalo de nuevo más tarde.");
        }
    }

    /*@GetMapping
    public ResponseEntity<?> obtenerLibrosBiblioteca() {
        List<LibrosBiblioteca> libros = librosBibliotecaRepository.findAll();

        if (libros.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(libros);
    }

    @GetMapping("/biblioteca/{bibliotecaId}")
    public ResponseEntity<?> obtenerLibrosPorBiblioteca(@PathVariable int bibliotecaId) {
        List<LibrosBiblioteca> librosBiblioteca = librosBibliotecaRepository.findByBibliotecaId(bibliotecaId);

        if (librosBiblioteca.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Extraer la lista de libros de los objetos LibrosBiblioteca
        List<Libro> libros = librosBiblioteca.stream()
                .map(LibrosBiblioteca::getLibro)
                .collect(Collectors.toList());

        return ResponseEntity.ok(libros);
    }*/

}
