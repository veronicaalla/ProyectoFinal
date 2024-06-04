package com.veronicaalvarez.api.controlador;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veronicaalvarez.api.modelo.Genero;
import com.veronicaalvarez.api.repositorio.GeneroRepositorio;

@RestController
@RequestMapping("/omega/generos/")
public class GeneroController {

	private final GeneroRepositorio generoRepositorio;
	
	public GeneroController(GeneroRepositorio generoRepositorio) {
		this.generoRepositorio = generoRepositorio;
	}
	
	/**
	 * Obtiene todos los géneros.
	 * @return Lista de todos los géneros.
	 */
	@GetMapping
	public List<Genero> obtenerGeneros(){
		return generoRepositorio.findAll();
	}

	
	/**
	 * Obtiene un género por su ID.
	 * @param id El ID del género.
	 * @return ResponseEntity con el género encontrado o un 404 NOT FOUND si no se encuentra.
	 */
	@GetMapping("id/{id}")
	public ResponseEntity<?> obtenerGeneroPorId(@PathVariable int id){
		Genero genero = generoRepositorio.findById(id).orElse(null);

		if (genero == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(genero);
	}

	
	/**
	 * Obtiene el ID de un género por su nombre.
	 * @param nombre El nombre del género.
	 * @return ResponseEntity con el ID del género encontrado o un 404 NOT FOUND si no se encuentra.
	 */
	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<?> obtenerIdGeneroPorNombre(@PathVariable String nombre) {
		Genero genero = generoRepositorio.findByNombre(nombre);

		if (genero == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(genero.getId());
	}
}
