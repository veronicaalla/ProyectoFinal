package com.veronicaalvarez.api.controlador;

import java.util.List;

import com.veronicaalvarez.api.modelo.Usuario;
import com.veronicaalvarez.api.repositorio.UsuarioRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.veronicaalvarez.api.modelo.Biblioteca;
import com.veronicaalvarez.api.repositorio.BibliotecaRepositorio;

@RestController
@RequestMapping("/omega/bibliotecas")
public class BibliotecaController {

	private BibliotecaRepositorio bibliotecaRepositorio;
	private UsuarioRepositorio usuarioRepositorio;
	
	public BibliotecaController (BibliotecaRepositorio bibliotecaRepositorio, UsuarioRepositorio usuarioRepositorio) {
		this.bibliotecaRepositorio = bibliotecaRepositorio;
		this.usuarioRepositorio = usuarioRepositorio;
	}
	

	/**
	 * Obtiene todas las bibliotecas.
	 * @return ResponseEntity Devuelve la lista de bibliotecas o un 404 si no hay bibliotecas.
	 */
	@GetMapping
	public ResponseEntity<?> obtenerBibliotecas(){
		List <Biblioteca> bibliotecas = bibliotecaRepositorio.findAll();
		
		if (bibliotecas.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(bibliotecas);
	}
	
	/**
	 * Obtiene una biblioteca por su ID.
	 * @param id El ID de la biblioteca.
	 * @return ResponseEntity Devuelve la biblioteca encontrada o un 404 si no se encuentra.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerBibliotecaPorId (@PathVariable int id) {
		Biblioteca biblioteca = bibliotecaRepositorio.findById(id).orElse(null);
		
		if (biblioteca==null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(biblioteca);
	}


	/**
	 * Obtiene todas las bibliotecas asociadas a un usuario específico.
	 * @param usuarioId El ID del usuario.
	 * @return ResponseEntity Devuelve la lista de bibliotecas o un 404 si el usuario no tiene ninguna biblioteca asociada.
	 */
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<?> getBibliotecasByUsuario(@PathVariable int usuarioId) {

		List <Biblioteca> bibliotecas =  bibliotecaRepositorio.findByUsuario_Id(usuarioId);

		if (bibliotecas.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(bibliotecas);
	}

	/**
	 * Crea una nueva biblioteca.
	 * @param biblioteca La biblioteca a crear.
	 * @return ResponseEntity Devuelve la biblioteca creada y un 201 que indica su creación CREATED.
	 */
	@PostMapping("/usuario/{usuarioId}/biblioteca")
	public ResponseEntity<Biblioteca> crearBiblioteca(@PathVariable Integer usuarioId, @RequestBody Biblioteca biblioteca) {
		//Comprobamos que el usuario existe
		Usuario usuario = usuarioRepositorio.findById(usuarioId).orElse(null);

		if (usuario == null){
			return ResponseEntity.notFound().build();
		}

		//Creamos la biblioteca
		Biblioteca bibliotecaNueva = biblioteca;
		bibliotecaNueva.setUsuario(usuario);
		bibliotecaNueva.setNombre(biblioteca.getNombre());

		 bibliotecaRepositorio.save(bibliotecaNueva);
		return ResponseEntity.status(HttpStatus.CREATED).body(bibliotecaNueva);
	}

	/**
	 * Edita una biblioteca existente.
	 * @param editarBiblioteca La biblioteca con la información ya modificada.
	 * @param id El ID de la biblioteca a editar.
	 * @return ResponseEntity Devuelve la biblioteca editada o un 404 si no se encuentra.
	 */
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
	
	/**
	 * Borra una biblioteca por su ID.
	 * @param id El ID de la biblioteca a borrar.
	 * @return ResponseEntity Devuelve un 204 NO CONTENT.
	 */
	@DeleteMapping("/borrar/{id}")
	public ResponseEntity<?> borrarBiblioteca (@PathVariable int id){
		bibliotecaRepositorio.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
