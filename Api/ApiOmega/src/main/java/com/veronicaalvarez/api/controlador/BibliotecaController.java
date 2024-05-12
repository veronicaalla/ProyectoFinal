package com.veronicaalvarez.api.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.veronicaalvarez.api.modelo.Biblioteca;
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
	
	
	/*@GetMapping("/{id}")
	public ResponseEntity<?> obtenerBibliotecaPorId (@PathVariable int id) {
		Biblioteca biblioteca = bibliotecaRepositorio.findById(id).orElse(null);
		
		if (biblioteca==null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(biblioteca);
	}*/



	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<?> getBibliotecasByUsuario(@PathVariable int usuarioId) {

		List <Biblioteca> bibliotecas =  bibliotecaRepositorio.findByUsuario_Id(usuarioId);

		if (bibliotecas.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(bibliotecas);
	}

	@PostMapping
	public ResponseEntity<Biblioteca> crearBiblioteca(@RequestBody Biblioteca biblioteca) {
		Biblioteca guardado = bibliotecaRepositorio.save(biblioteca);
		return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
	}

	
	@PutMapping("/editar/{id}")
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
	
	
	@DeleteMapping("/borrar/{id}")
	public ResponseEntity<?> borrarBiblioteca (@PathVariable int id){
		bibliotecaRepositorio.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
