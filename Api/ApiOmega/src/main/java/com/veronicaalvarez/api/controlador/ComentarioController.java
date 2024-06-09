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


	/**
	 * Obtiene todos los comentarios.
	 * @return ResponseEntity Devuelve la lista de comentarios o un 404 si no hay comentarios.
	 */
	@GetMapping
	public ResponseEntity<?> obtenerComentarios(){
		List<Comentario> comentarios = comentarioRepositorio.findAll();
		
		if (comentarios.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(comentarios);
	}


	/**
	 * Obtiene un comentario por su ID.
	 * @param id El ID del comentario.
	 * @return ResponseEntity Devuelve el comentario encontrado o un 404 si no se encuentra.
	 */
	@GetMapping("/comentario/{id}")
	public ResponseEntity<?> obtenerComentarioPorId(@PathVariable int id){
		Comentario comentario = comentarioRepositorio.findById(id).orElse(null);

		if (comentario == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(comentario);
	}


	/**
	 * Obtiene todos los comentarios asociados a un libro específico.
	 * @param idLibro El ID del libro.
	 * @return ResponseEntity Devuelve la lista de comentarios del libro espeficio o un 404 si no hay comentarios asociados.
	 */
	@GetMapping("/libro/{idLibro}")
	public ResponseEntity<?> obtenerComentariosPorLibro(@PathVariable("idLibro") int idLibro) {
		List<Comentario> comentarios = comentarioRepositorio.findByIdLibro(idLibro);

		if (comentarios.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(comentarios);
	}


	/**
	 * Crea un nuevo comentario.
	 * @param idLibro El ID del libro al que se asocia el comentario.
	 * @param idUsuario El ID del usuario que hace el comentario.
	 * @param comentario El contenido del comentario.
	 * @return ResponseEntity con el comentario creado.
	 */
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


	/**
	 * Modifica un comentario existente.
	 * @param id El ID del comentario a modificar.
	 * @param comentarioModificado La información del comentario modificada.
	 * @return ResponseEntity con el comentario actualizado o un 404 si no se encuentra.
	 */
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarComentario(@PathVariable int id, @RequestBody Comentario comentarioModificado) {
		Optional<Comentario> comentarioOptional = comentarioRepositorio.findById(id);

		if (comentarioOptional.isPresent()) {

			Comentario comentarioExistente = comentarioOptional.get();
			comentarioExistente.setComentario(comentarioModificado.getComentario());
			// Actualizar la fecha al modificar el comentario
			comentarioExistente.setFecha(LocalDateTime.now());

			Comentario comentarioActualizado = comentarioRepositorio.save(comentarioExistente);
			return ResponseEntity.ok(comentarioActualizado);

		} else {
			return ResponseEntity.notFound().build();
		}
	}


	/**
	 * Elimina un comentario por su ID.
	 * @param id El ID del comentario a eliminar.
	 * @return ResponseEntity con un 200 OK si se elimina correctamente o un 404 si no se encuentra.
	 */
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
