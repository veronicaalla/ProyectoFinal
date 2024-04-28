package com.veronicaalvarez.api.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veronicaalvarez.api.modelo.Genero;
import com.veronicaalvarez.api.repositorio.GeneroRepositorio;

@RestController
public class GeneroController {

	private final GeneroRepositorio generoRepositorio;
	
	public GeneroController(GeneroRepositorio generoRepositorio) {
		this.generoRepositorio = generoRepositorio;
	}
	
	//Usamoe el método GetMapping para obtener toda la lista de usuarios 
	@GetMapping("/generos")
	public List<Genero> obtenerGeneros(){
		return generoRepositorio.findAll();
	}
	
}
