package com.veronicaalvarez.api.controlador;

import com.veronicaalvarez.api.modelo.ValoracionLibro;
import com.veronicaalvarez.api.repositorio.ValoracionLibroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/omega/valoracionesLibros")
public class ValoracionLibroController {

    private final ValoracionLibroRepository valoracionLibroRepository;

    public ValoracionLibroController(ValoracionLibroRepository valoracionLibroRepository) {
        this.valoracionLibroRepository = valoracionLibroRepository;
    }

    @GetMapping
    public ResponseEntity<?> obtenerValoraciones() {
        List<ValoracionLibro> valoraciones = valoracionLibroRepository.findAll();

        if (valoraciones.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(valoraciones);
    }
}
