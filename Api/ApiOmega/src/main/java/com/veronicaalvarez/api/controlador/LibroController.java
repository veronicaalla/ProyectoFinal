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
import org.springframework.web.bind.annotation.RestController;

import com.veronicaalvarez.api.modelo.Libro;
import com.veronicaalvarez.api.repositorio.LibroRepositorio;

@RestController
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
	
	
	@GetMapping
	public ResponseEntity<?> obtenerLibrosPorId(@PathVariable int id){
		Libro libro = libroRepositorio.findById(id).orElse(null);
		
		if (libro ==null ) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(libro);
	}
	
	
	@GetMapping
	public ResponseEntity<?> obtenerLibrosPorGenero(@PathVariable String genero){
		List <Libro> libros = libroRepositorio.findByGenero(genero);
		
		if (libros.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(libros);
	}
	
	
	@GetMapping
	public ResponseEntity<?> obtenerLibrosPorISBN(@PathVariable String ISBN){
		List<Libro> libros = libroRepositorio.findByISBN(ISBN);
		
		if (libros.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(libros);
	}
	
	@GetMapping
	public ResponseEntity<?> obtenerLibrosPorTitulo (@PathVariable String titulo){
		List <Libro> libros = libroRepositorio.findByTitulo(titulo);
		
		if (libros.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(libros);
	}
	
	@GetMapping
	public ResponseEntity<?> obtenerLibrosPorAutor (@PathVariable String autor){
		List <Libro> libros = libroRepositorio.findByAutor(autor);
		
		if (libros.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(libros);
	}
	
	
	// Método para obtener una lista de libros cuyas páginas estén dentro de un rango dado
    @GetMapping("/librosPorPaginas/{paginaMin}/{paginaMax}")
    public List<Libro> obtenerLibrosPorRangoDePaginas(@PathVariable int paginaMin, @PathVariable int paginaMax) {
        return libroRepositorio.findByPaginasEntre(paginaMin, paginaMax);
    }

    // Método para obtener libros por valoración
    @GetMapping("/librosPorValoracion/{valoracion}")
    public List<Libro> obtenerLibrosPorValoracion(@PathVariable int valoracion) {
        return libroRepositorio.findByValoracion(valoracion);
    }

    // Método para obtener los libros mejor valorados
    @GetMapping("/librosMejorValorados")
    public List<Libro> obtenerLibrosMejorValorados() {
        return libroRepositorio.findTop20ByOrderByValoracionDesc();
    }
    
    
    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro nuevoLibro){
    	Libro nuevo = libroRepositorio.save(nuevoLibro);
    	
    	return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> editarLibro (@RequestBody Libro editarLibro, @PathVariable Integer id){
    	Libro libro = libroRepositorio.findById(id).orElse(null);
    	
    	if (libro == null)
    		return ResponseEntity.notFound().build();
    	
    	
    	libro.setISBN(editarLibro.getISBN());
    	libro.setTitulo(editarLibro.getTitulo());
    	libro.setAutor(editarLibro.getAutor());
    	libro.setDescripcion(editarLibro.getDescripcion());
    	libro.setGenero(editarLibro.getGenero());
    	libro.setFecha_publicacion(editarLibro.getFecha_publicacion());
    	libro.setPaginas(editarLibro.getPaginas());
    	
    	return ResponseEntity.ok(libroRepositorio.save(libro));
    }
    
	
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarLibro(@PathVariable Integer id){
    	libroRepositorio.deleteById(id);
    	return ResponseEntity.noContent().build();
    }
	
}
