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

import com.veronicaalvarez.api.modelo.Biblioteca;
import com.veronicaalvarez.api.modelo.Usuario;
import com.veronicaalvarez.api.repositorio.BibliotecaRepositorio;

@RestController
@RequestMapping("/omega/bibliotecas")
public class BibliotecaController {

	private BibliotecaRepositorio bibliotecaRepositorio;
	
	public BibliotecaController (BibliotecaRepositorio bibliotecaRepositorio) {
		this.bibliotecaRepositorio = bibliotecaRepositorio;
	}
	
	
	@GetMapping
	public ResponseEntity<?> obtenerBibliotecas(){
		List <Biblioteca> bibliotecas = bibliotecaRepositorio.findAll();
		
		if (bibliotecas.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(bibliotecas);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerBibliotecaPorId (@PathVariable int id) {
		Biblioteca biblioteca = bibliotecaRepositorio.findById(id).orElse(null);
		
		if (biblioteca==null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(biblioteca);
	}
	
	
	
	/*@GetMapping("/usuario/{id_usuario}")
	public ResponseEntity<?> obtenerBibliotecasPorUsuario(@RequestBody int id_usuario){
		List<Biblioteca> bibliotecasUsuario = bibliotecaRepositorio.findByUsuario(id_usuario);
		
		if (bibliotecasUsuario.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(bibliotecasUsuario);
	}*/
	
	/*@GetMapping("nombre/{nombre}")
	public ResponseEntity<?> obtenerBibliotecaPorNombre (@PathVariable String nombre){
		Biblioteca biblioteca = bibliotecaRepositorio.findByNombre(nombre);
		
		if (biblioteca == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(biblioteca);
	}
	
	
	//Obtener bibliotecas por User, pero solo aquellas que son publicas
	
	
	
	
	
	@PostMapping
	public ResponseEntity<Biblioteca> nuevaBiblioteca (@RequestBody Biblioteca nuevaBibliocata){
		Biblioteca nueva = bibliotecaRepositorio.save(nuevaBibliocata);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editarBiblioteca (@RequestBody Biblioteca editarBiblioteca, @PathVariable Integer id){
		Biblioteca biblioteca = bibliotecaRepositorio.findById(id).orElse(null);
		
		if (biblioteca == null) {
			return ResponseEntity.notFound().build();
		}
		
		//Modificamos
		biblioteca.setNombre(editarBiblioteca.getNombre());
		biblioteca.setPublica(editarBiblioteca.getPublica());
		
		//Guardamos
		return ResponseEntity.ok(bibliotecaRepositorio.save(biblioteca));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> borrarBiblioteca (@PathVariable Integer id){
		bibliotecaRepositorio.deleteById(id);
		return ResponseEntity.noContent().build();
	}*/
	
}
