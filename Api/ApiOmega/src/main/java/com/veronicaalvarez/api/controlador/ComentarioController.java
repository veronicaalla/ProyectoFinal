package com.veronicaalvarez.api.controlador;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.veronicaalvarez.api.modelo.Comentario;
import com.veronicaalvarez.api.modelo.Libro;import com.veronicaalvarez.api.modelo.Usuario;
import com.veronicaalvarez.api.repositorio.ComentarioRepositorio;

@RestController
@RequestMapping("/omega/comentarios")
public class ComentarioController {

	private ComentarioRepositorio comentarioRepositorio;
	
	public ComentarioController (ComentarioRepositorio comentarioRepositorio) {
		this.comentarioRepositorio = comentarioRepositorio;
	}
	
	@GetMapping
	public ResponseEntity<?> obtenerComentarios(){
		List<Comentario> comentarios = comentarioRepositorio.findAll();
		
		if (comentarios.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(comentarios);
	}

	@GetMapping("/comentario/{id}")
	public ResponseEntity<?> obtenerComentarioPorId(@PathVariable int id){
		Comentario comentario = comentarioRepositorio.findById(id).orElse(null);

		if (comentario == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(comentario);
	}

	@GetMapping("/libro/{idLibro}")
	public ResponseEntity<?> obtenerComentariosPorLibro(@PathVariable("idLibro") int idLibro) {
		List<Comentario> comentarios = comentarioRepositorio.findByIdLibro(idLibro);

		if (comentarios.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(comentarios);
	}

	@PostMapping("/crear")
	public ResponseEntity<?> crearComentario(@RequestParam int idLibro, @RequestParam int idUsuario, @RequestParam String comentario) {
		Comentario nuevoComentario = new Comentario();
		nuevoComentario.setIdLibro(idLibro);
		nuevoComentario.setIdUsuario(idUsuario);
		nuevoComentario.setComentario(comentario);
		nuevoComentario.setFecha(LocalDateTime.now()); // Establecer la fecha actual

		Comentario comentarioGuardado = comentarioRepositorio.save(nuevoComentario);

		return ResponseEntity.ok(comentarioGuardado);
	}

	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarComentario(@PathVariable int id, @RequestBody Comentario comentarioModificado) {
		Optional<Comentario> comentarioOptional = comentarioRepositorio.findById(id);
		if (comentarioOptional.isPresent()) {
			Comentario comentarioExistente = comentarioOptional.get();
			comentarioExistente.setComentario(comentarioModificado.getComentario());
			comentarioExistente.setFecha(LocalDateTime.now()); // Actualizar la fecha al modificar el comentario

			Comentario comentarioActualizado = comentarioRepositorio.save(comentarioExistente);
			return ResponseEntity.ok(comentarioActualizado);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarComentario(@PathVariable int id) {
		Optional<Comentario> comentarioOptional = comentarioRepositorio.findById(id);
		if (comentarioOptional.isPresent()) {
			comentarioRepositorio.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
