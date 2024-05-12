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

    @GetMapping
    public ResponseEntity<?> obtenerValoracionUsuarios() {
        List<ValoracionUsuario> valoracionUsuarios = valoracionUsuarioRepository.findAll();
        if (valoracionUsuarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(valoracionUsuarios);
    }

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

    /*
    {
  "id": 0,
  "libro": {
  "id": 2,
  "auditCreated": "2024-04-27T19:13:49",
  "auditCreator": "admi",
  "auditUpdated": "2024-04-27T19:13:49",
  "auditUpdater": "admi",
  "titulo": "Un sueño real",
  "autor": "Alice Kellen",
  "descripcion": "La vida real no es un cuento de hadas, pero a veces lo parece...",
  "genero": 3,
  "fechaPublicacion": "2016-07-07",
  "paginas": 352,
  "isbn": "9788408192299"
},
  "usuario": {
  "id": 1,
  "auditCreated": "2024-04-27T19:11:40",
  "auditCreator": "admi",
  "auditUpdated": "2024-04-27T19:11:40",
  "auditUpdater": "admi",
  "user": "juanpe",
  "nombre": "Juan",
  "apellidos": "Pérez",
  "fechaNacimiento": "1990-01-01",
  "correo": "juan@example.com",
  "clave": "clave123",
  "telefono": "123456789",
  "tipo": 3,
  "publico": false
},
  "playList": "string",
  "recomendacion": true,
  "opinion": "string",
  "puntuacion": 7,
  "fechaValoracion": "2024-05-12T12:07:16.123Z",
  "personaje_odiado": "string",
  "personaje_fav": "string",
  "frase_iconica": "string"
}
     */
}
