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

	public ComentarioReportadoController (ComentarioReportadoRepositorio reportadoRepositorio, ComentarioRepositorio comentarioRepository, UsuarioRepositorio usuarioRepository, ComentarioReportadoRepositorio comentarioReportadoRepositorio) {
		this.reportadoRepositorio = reportadoRepositorio;
		this.comentarioRepository = comentarioRepository;
		this.usuarioRepository = usuarioRepository;
	}

	@GetMapping
	public ResponseEntity<?> obtenerComentariosReportados() {
		List<ComentarioReportado> comentariosReportados = reportadoRepositorio.findAll();
		if (comentariosReportados.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(comentariosReportados);

	}

	@GetMapping("/sin-resolver")
	public ResponseEntity<?> obtenerComentariosReportadosSinResolver() {

		List<ComentarioReportado> librosErroneosSinResolver = reportadoRepositorio.findByOfensivoIsNull();
		if (librosErroneosSinResolver.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(librosErroneosSinResolver);
	}

	@PostMapping("/crear")
	public ResponseEntity<?> reportarComentario(@RequestParam int comentarioId, @RequestParam int usuarioId) {

		Comentario comentario = comentarioRepository.findById(comentarioId).orElse(null);
		Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

		if (comentario == null || usuario == null) {
			return ResponseEntity.badRequest().build(); // O cualquier otro manejo de error
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

	@PutMapping("/editar/{id}")
	public ResponseEntity<?> editarComentarioReportado(@RequestParam int idComentarioReportado,
																		 @RequestParam int usuarioId,
																		 @RequestBody ComentarioReportado comentarioReportadoNuevo) {

		ComentarioReportado comentarioReportado = reportadoRepositorio.findById(comentarioReportadoNuevo.getId()).orElse(null);
		Comentario comentario = comentarioRepository.findById(idComentarioReportado).orElse(null);
		Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

		if (comentario == null || usuario == null) {
			return ResponseEntity.badRequest().build();
		}


		// Actualizar los campos del reporte
		comentarioReportado.setOfensivo(comentarioReportadoNuevo.getOfensivo());
		comentarioReportado.setAuditUpdated(LocalDateTime.now()); // Actualizar la fecha de actualización
		comentarioReportado.setAuditUpdater(usuario.getAlias());

		// Guardar los cambios en la base de datos
		reportadoRepositorio.save(comentarioReportado);

		return ResponseEntity.ok("Comentario reportado actualizado correctamente");
	}

}
