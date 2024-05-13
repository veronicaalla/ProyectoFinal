package com.veronicaalvarez.api.controlador;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.veronicaalvarez.api.modelo.Comentario;
import com.veronicaalvarez.api.modelo.Usuario;
import com.veronicaalvarez.api.repositorio.ComentarioRepositorio;
import com.veronicaalvarez.api.repositorio.UsuarioRepositorio;
import org.springframework.http.HttpStatus;
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

	public ComentarioReportadoController (ComentarioReportadoRepositorio reportadoRepositorio, ComentarioRepositorio comentarioRepository, UsuarioRepositorio usuarioRepository) {
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

	@PostMapping("/crear")
	public ResponseEntity<ComentarioReportado> reportarComentario(@RequestParam int comentarioId, @RequestParam int usuarioId) {
		Comentario comentario = comentarioRepository.findById(comentarioId).orElse(null);
		Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

		if (comentario == null || usuario == null) {
			return ResponseEntity.badRequest().build(); // O cualquier otro manejo de error
		}

		ComentarioReportado nuevoReporte = new ComentarioReportado();
		nuevoReporte.setAuditCreated(LocalDateTime.now());
		nuevoReporte.setAuditUpdated(LocalDateTime.now());

		nuevoReporte.setAuditCreator(usuario.getUser());
		nuevoReporte.setAuditUpdater(usuario.getUser());

		nuevoReporte.setIdComentario(comentario.getId());
		nuevoReporte.setIdReportante(usuario.getId());

		reportadoRepositorio.save(nuevoReporte);
		return ResponseEntity.ok(nuevoReporte);
	}

	@PutMapping("/editar/{id}")
	public ResponseEntity<ComentarioReportado> editarComentarioReportado(@RequestParam int idComentarioReportado,
																		 @RequestParam int usuarioId,
																		 @RequestBody ComentarioReportado comentarioReportado) {

		Optional<ComentarioReportado> optionalReporte = reportadoRepositorio.findById(idComentarioReportado);
		if (!optionalReporte.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		ComentarioReportado reporte = optionalReporte.get();

		Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
		if (usuario == null) {
			return ResponseEntity.badRequest().build();
		}

		// Actualizar los campos del reporte
		reporte.setOfensivo(comentarioReportado.getOfensivo());
		reporte.setAuditUpdated(LocalDateTime.now()); // Actualizar la fecha de actualización
		reporte.setAuditUpdater(usuario.getUser());

		// Guardar los cambios en la base de datos
		reporte = reportadoRepositorio.save(reporte);

		return ResponseEntity.ok(reporte);
	}

}
