package com.veronicaalvarez.api.controlador;

import com.veronicaalvarez.api.modelo.Libro;
import com.veronicaalvarez.api.modelo.LibroErroneo;
import com.veronicaalvarez.api.modelo.Usuario;
import com.veronicaalvarez.api.repositorio.LibroErroneoRepositorio;
import com.veronicaalvarez.api.repositorio.LibroRepositorio;
import com.veronicaalvarez.api.repositorio.UsuarioRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/omega/libroerroneno")
public class LibroErroneoController {

    public LibroErroneoRepositorio libroErroneoRepositorio;
    private UsuarioRepositorio usuarioRepositorio;
    private LibroRepositorio libroRepositorio;

    public LibroErroneoController (LibroErroneoRepositorio libroErroneoRepositorio, UsuarioRepositorio usuarioRepositorio, LibroRepositorio libroRepositorio) {
        this.libroErroneoRepositorio = libroErroneoRepositorio;
        this.libroRepositorio = libroRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }


    /**
     * Obtiene todos los libros erróneos.
     * @return ResponseEntity con la lista de todos los libros erróneos o un 204 NO CONTENT si no hay libros erróneos.
     */
    @GetMapping
    public ResponseEntity<?> obtenerLibrosErroneos() {
        List<LibroErroneo> librosErroneos = libroErroneoRepositorio.findAll();
        if (librosErroneos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(librosErroneos);
    }


    /**
     * Obtiene todos los libros erróneos sin resolver.
     * @return ResponseEntity con la lista de libros erróneos sin resolver o un 204 NO CONTENT si no hay libros erróneos sin resolver.
     */
    @GetMapping("/sin-resolver")
    public ResponseEntity<?> obtenerLibrosErroneosSinResolver() {
        List<LibroErroneo> librosErroneosSinResolver = libroErroneoRepositorio.findByResueltoIsNull();
        if (librosErroneosSinResolver.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(librosErroneosSinResolver);
    }


    /**
     * Obtiene un libro erróneo por su ID, es decir, un libro que ha sido indicado como que, alguno de sus campos no son correctos
     * @param id El ID del libro erróneo.
     * @return ResponseEntity con el libro erróneo encontrado o un 404 NOT FOUND si no se encuentra.
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<?> obtenerLibrosErroneosId(@PathVariable Integer id) {
        LibroErroneo erroneo = libroErroneoRepositorio.findById(id).orElse(null);

        if(erroneo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(erroneo);
    }

    /**
     * Crea un nuevo libro erróneo.
     * @param idLibro El ID del libro.
     * @param idReportante El ID del usuario que reporta.
     * @return ResponseEntity con un mensaje de éxito o un 400 BAD REQUEST si el libro o el usuario no existen.
     */
    @PostMapping("/crear")
    public ResponseEntity<?> crearLibroErroneo(@RequestParam("idLibro") int idLibro,
                                               @RequestParam("idReportante") int idReportante) {

        Libro libro = libroRepositorio.findById(idLibro).orElse(null);
        Usuario usuario = usuarioRepositorio.findById(idReportante).orElse(null);

        if (libro == null || usuario == null) {
            return ResponseEntity.badRequest().body("El libro o el reportante no existen");
        }

        LibroErroneo libroErroneo = new LibroErroneo();
        libroErroneo.setAuditCreated(LocalDateTime.now());
        libroErroneo.setAuditUpdated(LocalDateTime.now());

        libroErroneo.setAuditCreator(usuario.getAlias());
        libroErroneo.setAuditUpdater(usuario.getAlias());


        libroErroneo.setIdLibro(libro.getId());
        libroErroneo.setIdReportante(usuario.getId());

        libroErroneoRepositorio.save(libroErroneo);

        return ResponseEntity.ok("LibroErroneo creado correctamente");
    }


    /**
     * Edita un libro erróneo existente.
     * @param libroErroneoNuevo El libro erróneo con los datos actualizados.
     * @param idUsuario El ID del usuario que realiza la actualización.
     * @return ResponseEntity con un mensaje de éxito o un 404 NOT FOUND si el libro erróneo o el usuario no se encuentran.
     */
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarLibroErroneo(@RequestBody LibroErroneo libroErroneoNuevo,
                                                @RequestParam("idUsuario") int idUsuario) {

        LibroErroneo libroErroneo = libroErroneoRepositorio.findById(libroErroneoNuevo.getId()).orElse(null);
        Usuario usuario = usuarioRepositorio.findById(idUsuario).orElse(null);

        // Verificar si el usuario existe
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }

        // Verificar si el libro erroneo existe
        if (libroErroneo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro erroneo no encontrado");
        }


        libroErroneo.setResuelto(libroErroneoNuevo.getResuelto());

        //Modificamos la auditoria
        libroErroneo.setAuditUpdated(LocalDateTime.now());
        String id= usuario.getId() + "";
        libroErroneo.setAuditUpdater(id);

        libroErroneoRepositorio.save(libroErroneo);

        return ResponseEntity.ok("Libro Erroneo actualizado correctamente");
    }


}
