package com.veronicaalvarez.api.controlador;

import com.veronicaalvarez.api.modelo.Libro;
import com.veronicaalvarez.api.modelo.LibroErroneo;
import com.veronicaalvarez.api.modelo.Usuario;
import com.veronicaalvarez.api.repositorio.LibroErroneoRepositorio;
import com.veronicaalvarez.api.repositorio.LibroRepositorio;
import com.veronicaalvarez.api.repositorio.UsuarioRepositorio;
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

    @GetMapping
    public ResponseEntity<?> obtenerLibrosErroneos() {
        List<LibroErroneo> librosErroneos = libroErroneoRepositorio.findAll();
        if (librosErroneos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(librosErroneos);
    }

    @GetMapping("/sin-resolver")
    public ResponseEntity<?> obtenerLibrosErroneosSinResolver() {
        List<LibroErroneo> librosErroneosSinResolver = libroErroneoRepositorio.findByResueltoIsNull();
        if (librosErroneosSinResolver.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(librosErroneosSinResolver);
    }

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

        libroErroneo.setAuditCreator(usuario.getUsername());
        libroErroneo.setAuditUpdater(usuario.getUsername());


        libroErroneo.setIdLibro(libro.getId());
        libroErroneo.setIdReportante(usuario.getId());

        libroErroneoRepositorio.save(libroErroneo);

        return ResponseEntity.ok("LibroErroneo creado correctamente");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarLibroErroneo(@RequestBody LibroErroneo libroErroneoNuevo,
                                                @RequestParam("idLibro") int idLibro,
                                                @RequestParam("idReportante") int idReportante) {

        LibroErroneo libroErroneo = libroErroneoRepositorio.findById(libroErroneoNuevo.getId()).orElse(null);
        Libro libro = libroRepositorio.findById(idLibro).orElse(null);
        Usuario usuario = usuarioRepositorio.findById(idReportante).orElse(null);

        if (libro == null || usuario == null) {
            return ResponseEntity.notFound().build();
        }

        libroErroneo.setIdLibro(libro.getId());
        libroErroneo.setIdReportante(usuario.getId());
        libroErroneo.setResuelto(libroErroneoNuevo.getResuelto());

        //Modificamos la auditoria
        libroErroneo.setAuditCreated(LocalDateTime.now());
        libroErroneo.setAuditUpdater(usuario.getUsername());

        libroErroneoRepositorio.save(libroErroneo);

        return ResponseEntity.ok("LibroErroneo actualizado correctamente");
    }


}
