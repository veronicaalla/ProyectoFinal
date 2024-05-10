package com.veronicaalvarez.api.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.veronicaalvarez.api.modelo.ComentarioReportado;
import com.veronicaalvarez.api.repositorio.ComentarioReportadoRepositorio;

@RestController
@RequestMapping("/omega/comentarioreportado")
public class ComentarioReportadoController {

	public ComentarioReportadoRepositorio reportadoRepositorio;
	
	public ComentarioReportadoController (ComentarioReportadoRepositorio reportadoRepositorio) {
		this.reportadoRepositorio = reportadoRepositorio;
	}
	
	@GetMapping
	public ResponseEntity<?> obtenerReportados(){
		List<ComentarioReportado> reportados = reportadoRepositorio.findAll();
		
		if (reportados.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(reportados);
	}
	
	
	/*@GetMapping("/{id}")
	public ResponseEntity<?> obtenerReportadoPorId(@PathVariable int id){
		ComentarioReportado reportado = reportadoRepositorio.findById(id).orElse(null);
		
		if (reportado == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(reportado);
	}
	
	
	@GetMapping("/sinValorar")
    public ResponseEntity<?> obtenerComentariosSinValorar() {
		List<ComentarioReportado> reportados = reportadoRepositorio.findByOfensivoIsNull();
		
		if (reportados.isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(reportados);
    }
	
	
	@PostMapping
	public ResponseEntity<ComentarioReportado> nuevoReporte(@RequestBody ComentarioReportado reportado){
		ComentarioReportado nuevo = reportadoRepositorio.save(reportado);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
	}*/
	
}
