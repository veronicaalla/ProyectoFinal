package com.veronicaalvarez.api.controlador;

import com.veronicaalvarez.api.modelo.ValoracionLibro;
import com.veronicaalvarez.api.repositorio.ValoracionLibroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/valoracion/{idLibro}")
    public ResponseEntity<?> obtenerValoracionLibro(@PathVariable("idLibro") int idLibro) {
        Optional<ValoracionLibro> valoracionLibroOptional = valoracionLibroRepository.findByIdLibro(idLibro);

        if (valoracionLibroOptional.isPresent()) {
            Double valoracionTotal = valoracionLibroOptional.get().getValoracion_total();
            return ResponseEntity.ok(valoracionTotal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/valoracion/{idLibro}")
    public ResponseEntity<?> actualizarValoracionLibro(@PathVariable("idLibro") int idLibro, @RequestBody ValoracionLibro nuevaValoracion) {
        try {
            Optional<ValoracionLibro> valoracionLibroOptional = valoracionLibroRepository.findByIdLibro(idLibro);

            if (valoracionLibroOptional.isPresent()) {
                ValoracionLibro valoracionLibroExistente = valoracionLibroOptional.get();
                valoracionLibroExistente.setValoracion_total(nuevaValoracion.getValoracion_total());

                valoracionLibroRepository.save(valoracionLibroExistente);

                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la valoración del libro. Por favor, inténtalo de nuevo más tarde.");
        }
    }
}
