package com.veronicaalvarez.api.controlador;

import java.util.List;

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
	
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerComentarioPorId(@PathVariable int id){
		Comentario comentario = comentarioRepositorio.findById(id).orElse(null);
		
		if (comentario==null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(comentario);
	}
	
	@GetMapping("/libro/{libro}")
	public ResponseEntity<?> obtenerComentariosPorLibro(@RequestBody Libro libro){
		List<Comentario> comentarios = comentarioRepositorio.findByLibro(libro);
		
		if (comentarios.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(comentarios);
	}
	
	@GetMapping("/usuario/{usuario}")
	public ResponseEntity<?> obtenerComentariosPorUsuario(@RequestBody Usuario usuario){
		List<Comentario> comentarios = comentarioRepositorio.findByUsuario(usuario);
		
		if (comentarios.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(comentarios);
	}
	
	
	@PostMapping
	public ResponseEntity<Comentario> nuevoComentario (@RequestBody Comentario comentario){
		Comentario nuevo = comentarioRepositorio.save(comentario);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
	}
	
	//Se crea la opc de editarComentario, pero su fincionalidad se aplicará en el futuro
	@PutMapping("/{id}")
	public ResponseEntity<?> editarComentario (@RequestBody Comentario editarComentario,@PathVariable Integer id ){
		Comentario comentario = comentarioRepositorio.findById(id).orElse(null);
		
		if (comentario == null) {
			return ResponseEntity.notFound().build();
		}
		
		//Modificamos 
		comentario.setComentario(editarComentario.getComentario());
		
		//Guardamos 
		return ResponseEntity.ok(comentarioRepositorio.save(comentario));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> borrarComentario (@PathVariable Integer id){
		comentarioRepositorio.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
