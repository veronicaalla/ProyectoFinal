package com.veronicaalvarez.api.controlador;

import java.time.LocalDateTime;
import java.util.List;

import com.veronicaalvarez.api.modelo.Comentario;
import com.veronicaalvarez.api.modelo.Usuario;
import com.veronicaalvarez.api.repositorio.ComentarioRepositorio;
import com.veronicaalvarez.api.repositorio.UsuarioRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.veronicaalvarez.api.modelo.ComentarioReportado;
import com.veronicaalvarez.api.repositorio.ComentarioReportadoRepositorio;

@RestController
@RequestMapping("/omega/comentarioreportado")
public class ComentarioReportadoController {

    public ComentarioReportadoRepositorio reportadoRepositorio;
    private ComentarioRepositorio comentarioRepository;
    private UsuarioRepositorio usuarioRepository;

    public ComentarioReportadoController(ComentarioReportadoRepositorio reportadoRepositorio, ComentarioRepositorio comentarioRepository, UsuarioRepositorio usuarioRepository, ComentarioReportadoRepositorio comentarioReportadoRepositorio) {
        this.reportadoRepositorio = reportadoRepositorio;
        this.comentarioRepository = comentarioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Obtiene todos los comentarios reportados.
     * @return ResponseEntity con la lista de comentarios reportados o un 204 NO CONTENT si no hay comentarios reportados.
     */
    @GetMapping
    public ResponseEntity<?> obtenerComentariosReportados() {
        List<ComentarioReportado> comentariosReportados = reportadoRepositorio.findAll();

        if (comentariosReportados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comentariosReportados);

    }


     /**
     * Obtiene todos los comentarios reportados sin resolver.
     * @return ResponseEntity con la lista de comentarios reportados sin resolver o un 204 NO CONTENT si no hay comentarios sin resolver.
     */
    @GetMapping("/sin-resolver")
    public ResponseEntity<?> obtenerComentariosReportadosSinResolver() {
        List<ComentarioReportado> librosErroneosSinResolver = reportadoRepositorio.findByOfensivoIsNull();

        if (librosErroneosSinResolver.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(librosErroneosSinResolver);
    }

	
    /**
     * Obtiene un comentario reportado por su ID.
     * @param id El ID del comentario reportado.
     * @return ResponseEntity con el comentario reportado encontrado o un 404 NOT FOUND si no se encuentra.
     */
    @GetMapping("/comentario/{id}")
    public ResponseEntity<?> obtenerComentarioReportadoPorId(@PathVariable int id) {
        ComentarioReportado comentarioReportado = reportadoRepositorio.findById(id).orElse(null);

        if (comentarioReportado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(comentarioReportado);
    }


    /**
     * Reporta un comentario, es decir, se indica que el comentario es ofensivo 
     * @param comentarioId El ID del comentario a reportar.
     * @param usuarioId El ID del usuario que reporta el comentario.
     * @return ResponseEntity con un mensaje de éxito o un 400 BAD REQUEST si el comentario o el usuario no se encuentran.
     */
    @PostMapping("/crear")
    public ResponseEntity<?> reportarComentario(@RequestParam int comentarioId, @RequestParam int usuarioId) {

        Comentario comentario = comentarioRepository.findById(comentarioId).orElse(null);
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        if (comentario == null || usuario == null) {
            return ResponseEntity.badRequest().build();
        }

        ComentarioReportado nuevoReporte = new ComentarioReportado();
        nuevoReporte.setAuditCreated(LocalDateTime.now());
        nuevoReporte.setAuditUpdated(LocalDateTime.now());

        nuevoReporte.setAuditCreator(usuario.getAlias());
        nuevoReporte.setAuditUpdater(usuario.getAlias());

        nuevoReporte.setIdComentario(comentario.getId());
        nuevoReporte.setIdReportante(usuario.getId());

        reportadoRepositorio.save(nuevoReporte);
        return ResponseEntity.ok("Comentario reportado creado correctamente");
    }


    /**
    * Actualiza un comentario reportado con nueva información.
    *
    * @param idUsuario el ID del usuario que realiza la actualización
    * @param comentarioReportadoNuevo los nuevos detalles del comentario reportado
    * @return una respuesta HTTP indicando el resultado de la operación
    */
    @PutMapping("/editar/{idUsuario}")
    public ResponseEntity<?> editarComentarioReportado(@PathVariable int idUsuario,
                                                       @RequestBody ComentarioReportado comentarioReportadoNuevo) {

        // Obtener el comentario reportado existente
        ComentarioReportado comentarioReportado = reportadoRepositorio.findById(comentarioReportadoNuevo.getId()).orElse(null);
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);

        if (usuario == null || comentarioReportado == null) {
            return ResponseEntity.badRequest().build();
        }

        // Actualizar los campos del reporte
        comentarioReportado.setOfensivo(comentarioReportadoNuevo.getOfensivo());
        // Actualizar la fecha de actualización
        comentarioReportado.setAuditUpdated(LocalDateTime.now());
		comentarioReportado.setAuditUpdater(String.valueOf(usuario.getId()));

        // Guardar los cambios en la base de datos
        reportadoRepositorio.save(comentarioReportado);

        return ResponseEntity.ok("Comentario reportado actualizado correctamente");
    }


}
