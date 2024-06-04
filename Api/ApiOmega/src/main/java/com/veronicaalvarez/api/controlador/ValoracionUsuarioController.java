package com.veronicaalvarez.api.controlador;

import com.veronicaalvarez.api.modelo.Libro;
import com.veronicaalvarez.api.modelo.Usuario;
import com.veronicaalvarez.api.modelo.ValoracionUsuario;
import com.veronicaalvarez.api.repositorio.LibroRepositorio;
import com.veronicaalvarez.api.repositorio.UsuarioRepositorio;
import com.veronicaalvarez.api.repositorio.ValoracionUsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/omega/valoracionesUsuarios")
public class ValoracionUsuarioController {

    private final ValoracionUsuarioRepository valoracionUsuarioRepository;
    private final UsuarioRepositorio usuarioRepository;
    private final LibroRepositorio libroRepository;

    public ValoracionUsuarioController(ValoracionUsuarioRepository valoracionUsuarioRepository, UsuarioRepositorio usuarioRepository, LibroRepositorio libroRepository) {
        this.valoracionUsuarioRepository = valoracionUsuarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.libroRepository = libroRepository;
    }

    /**
     * Obtiene todas las valoraciones de usuarios.
     * @return ResponseEntity con la lista de valoraciones o un 404 NOT FOUND si no hay valoraciones.
     */
    @GetMapping
    public ResponseEntity<?> obtenerValoracionUsuarios() {
        List<ValoracionUsuario> valoracionUsuarios = valoracionUsuarioRepository.findAll();
        if (valoracionUsuarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(valoracionUsuarios);
    }


    /**
     * Obtiene la valoración de un libro por un usuario específico.
     * @param idLibro El ID del libro.
     * @param idUsuario El ID del usuario.
     * @return ResponseEntity con la valoración del usuario para el libro especificado o un 404 NOT FOUND si no se encuentra la valoración.
     */
    @GetMapping("/libro/{idLibro}/usuario/{idUsuario}")
    public ResponseEntity<?> obtenerValoracionLibroPorUsuario(@PathVariable("idLibro") int idLibro, @PathVariable("idUsuario") int idUsuario) {
        Optional<ValoracionUsuario> valoracionUsuarioOptional = valoracionUsuarioRepository.findByLibroIdAndUsuarioId(idLibro, idUsuario);

        if (valoracionUsuarioOptional.isPresent()) {
            ValoracionUsuario valoracionUsuario = valoracionUsuarioOptional.get();
            return ResponseEntity.ok(valoracionUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Crea una nueva valoración de un usuario para un libro específico.
     * @param idUsuario El ID del usuario.
     * @param idLibro El ID del libro.
     * @param valoracionUsuario La valoración del usuario para el libro.
     * @return ResponseEntity con un mensaje de éxito y la valoración creada, o un error 500 INTERNAL SERVER ERROR si ocurre algún problema.
     */
    @PostMapping("/usuarios/{idUsuario}/libros/{idLibro}/valoraciones")
    public ResponseEntity<?> crearValoracionUsuario(@PathVariable int idUsuario, @PathVariable int idLibro, @RequestBody ValoracionUsuario valoracionUsuario) {
        try {
            // Cargar el usuario y el libro
            Usuario usuario = usuarioRepository.getOne(idUsuario);
            Libro libro = libroRepository.getOne(idLibro);

            // Establecer el usuario y el libro en la valoración
            valoracionUsuario.setUsuario(usuario);
            valoracionUsuario.setLibro(libro);

            // Guardar la valoración
            ValoracionUsuario valoracionUsuarioSalva = valoracionUsuarioRepository.save(valoracionUsuario);

            return ResponseEntity.status(HttpStatus.CREATED).body(valoracionUsuarioSalva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la valoración. Por favor, inténtalo de nuevo más tarde.");
        }
    }
}
