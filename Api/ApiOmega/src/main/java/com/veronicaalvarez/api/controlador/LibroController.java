package com.veronicaalvarez.api.controlador;

import java.util.List;
import java.util.Optional;

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

	public LibroController(LibroRepositorio libroRepositorio) {
		this.libroRepositorio = libroRepositorio;
	}
	
	@GetMapping
	public ResponseEntity<?> obtenerLibros(){
		List<Libro> libros = libroRepositorio.findAll();
		
		if (libros.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(libros);
	}
	
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> obtenerLibrosPorId(@PathVariable int id){
		Libro libro = libroRepositorio.findById(id).orElse(null);
		
		if (libro ==null ) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(libro);
	}


	@GetMapping("/genero/{generoId}")
	public ResponseEntity<?> obtenerLibrosPorGenero(@PathVariable("generoId") int generoId) {
		List<Libro> librosPorGenero = libroRepositorio.findByGenero(generoId);

		if (librosPorGenero.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron libros para el género especificado.");
		} else {
			return ResponseEntity.ok(librosPorGenero);
		}
	}

	@PostMapping
	public ResponseEntity<?> crearLibro(@RequestBody Libro nuevoLibro) {
		try {
			Libro libroCreado = libroRepositorio.save(nuevoLibro);
			return ResponseEntity.status(HttpStatus.CREATED).body(libroCreado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el libro. Por favor, inténtalo de nuevo más tarde.");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editarLibro(@PathVariable("id") int id, @RequestBody Libro libroActualizado) {
		try {
			Optional<Libro> libroExistenteOptional = libroRepositorio.findById(id);

			if (!libroExistenteOptional.isPresent()) {
				return ResponseEntity.notFound().build();
			}

			Libro libroExistente = libroExistenteOptional.get();
			// Actualizar los campos del libro existente con los datos del libro actualizado
			libroExistente.setTitulo(libroActualizado.getTitulo());
			libroExistente.setAutor(libroActualizado.getAutor());
			libroExistente.setDescripcion(libroActualizado.getDescripcion());
			libroExistente.setGenero(libroActualizado.getGenero());
			libroExistente.setFechaPublicacion(libroActualizado.getFechaPublicacion());
			libroExistente.setPaginas(libroActualizado.getPaginas());
			libroExistente.setISBN(libroActualizado.getISBN());

			Libro libroEditado = libroRepositorio.save(libroExistente);

			return ResponseEntity.ok(libroEditado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al editar el libro. Por favor, inténtalo de nuevo más tarde.");
		}
	}

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
