package com.veronicaalvarez.api.controlador;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.veronicaalvarez.api.modelo.Usuario;
import com.veronicaalvarez.api.repositorio.UsuarioRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veronicaalvarez.api.modelo.Libro;
import com.veronicaalvarez.api.repositorio.LibroRepositorio;

@RestController
@RequestMapping("/omega/libros")
public class LibroController {

    private final LibroRepositorio libroRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;

    public LibroController(LibroRepositorio libroRepositorio, UsuarioRepositorio usuarioRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    /**
     * Obtiene todos los libros.
     * @return ResponseEntity con la lista de todos los libros o un 404 NOT FOUND si no hay libros.
     */
    @GetMapping
    public ResponseEntity<?> obtenerLibros() {
        List<Libro> libros = libroRepositorio.findAll();

        if (libros.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(libros);
    }

    /**
     * Obtiene una lista de libros aleatorios.
     * @return ResponseEntity con una lista de hasta 20 libros aleatorios o un 404 NOT FOUND si no hay libros.
     */
    @GetMapping("/librosaleatorios")
    public ResponseEntity<?> obtenerLibrosAleatorios() {
        List<Libro> libros = libroRepositorio.findAll();
        if (libros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Barajar la lista de libros y seleccionar los primeros 20
        Collections.shuffle(libros);
        List<Libro> randomLibros = libros.stream().limit(20).collect(Collectors.toList());

        return ResponseEntity.ok(randomLibros);
    }


    /**	
     * Obtiene un libro por su ID.
     * @param id El ID del libro.
     * @return ResponseEntity con el libro encontrado o un 404 NOT FOUND si no se encuentra.
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<?> obtenerLibrosPorId(@PathVariable int id) {
        Libro libro = libroRepositorio.findById(id).orElse(null);

        if (libro == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(libro);
    }


    /**
     * Obtiene libros por su título.
     * @param titulo El título del libro.
     * @return ResponseEntity con la lista de libros encontrados o un 404 NOT FOUND si no se encuentran libros.
     */
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<?> obtenerLibrosPorTitulo(@PathVariable String titulo) {
        List<Libro> libros = libroRepositorio.findByTitulo(titulo);

        if (libros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(libros);
    }


    /**
     * Obtiene libros por su género.
     * @param generoId El ID del género.
     * @return ResponseEntity con la lista de libros encontrados para el género especificado o un 404 NOT FOUND si no se encuentran libros.
     */
    @GetMapping("/genero/{generoId}")
    public ResponseEntity<?> obtenerLibrosPorGenero(@PathVariable("generoId") int generoId) {
        List<Libro> librosPorGenero = libroRepositorio.findByGenero(generoId);

        if (librosPorGenero.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron libros para el género especificado.");
        } else {
            return ResponseEntity.ok(librosPorGenero);
        }
    }


    /**
     * Crea un nuevo libro.
     * @param nuevoLibro El libro a crear.
     * @return ResponseEntity con el libro creado o un 500 INTERNAL SERVER ERROR si hay un error al crear el libro.
     */
    @PostMapping
    public ResponseEntity<?> crearLibro(@RequestBody Libro nuevoLibro) {
        try {
            Libro libroCreado = libroRepositorio.save(nuevoLibro);
            return ResponseEntity.status(HttpStatus.CREATED).body(libroCreado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el libro. Por favor, inténtalo de nuevo más tarde.");
        }
    }

    /**
     * Edita un libro existente.
     * @param id El ID del libro a editar.
     * @param libroActualizado El libro con todos los datos actualizados.
     * @return ResponseEntity con un mensaje de éxito o un 404 NOT FOUND si el libro o el usuario no se encuentran.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> editarLibro(@PathVariable("id") int id, @RequestBody Libro libroActualizado) {

        Libro libro = libroRepositorio.findById(libroActualizado.getId()).orElse(null);
        //Buscamos el usuario
        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);

        if (libro == null || usuario == null) {
            return ResponseEntity.notFound().build();
        }


        // Actualizar los campos del libro existente con los datos del libro actualizado
        libro.setTitulo(libroActualizado.getTitulo());
        libro.setAutor(libroActualizado.getAutor());
        libro.setDescripcion(libroActualizado.getDescripcion());
        libro.setGenero(libroActualizado.getGenero());
        libro.setFechaPublicacion(libroActualizado.getFechaPublicacion());
        libro.setPaginas(libroActualizado.getPaginas());
        libro.setIsbn(libroActualizado.getIsbn());

        //Modificamos la auditoria
		libro.setAuditUpdated(LocalDateTime.now());
		libro.setAuditUpdater(String.valueOf(usuario.getId()));

         libroRepositorio.save(libro);

        return ResponseEntity.ok("El libro se ha modificado correctamente");

    }

    /**
     * Elimina un libro por su ID.
     * @param id El ID del libro a eliminar.
     * @return ResponseEntity con un 200 OK si se elimina correctamente o un 500 INTERNAL SERVER ERROR si hay un error al eliminar el libro.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarLibro(@PathVariable("id") int id) {
        try {
            Optional<Libro> libroOptional = libroRepositorio.findById(id);

            if (!libroOptional.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            libroRepositorio.deleteById(id);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el libro. Por favor, inténtalo de nuevo más tarde.");
        }
    }

}
