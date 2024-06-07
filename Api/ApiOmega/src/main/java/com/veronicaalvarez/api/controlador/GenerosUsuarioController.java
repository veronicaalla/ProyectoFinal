package com.veronicaalvarez.api.controlador;

import com.veronicaalvarez.api.modelo.GeneroUsuario;
import com.veronicaalvarez.api.repositorio.GenerosUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/omega/generosUsuario")
public class GenerosUsuarioController {

    @Autowired
    private GenerosUsuarioRepository generosUsuarioRepository;

    /**
     * Obtiene todos los géneros asociados a un usuario específico.
     * @param idUsuario El ID del usuario.
     * @return ResponseEntity con la lista de géneros del usuario o un 404 NOT FOUND si no hay géneros asociados.
     */
    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<GeneroUsuario>> obtenerGenerosPorUsuario(@PathVariable int idUsuario) {
        List<GeneroUsuario> generosUsuario = generosUsuarioRepository.findByIdUsuario(idUsuario);

        if (generosUsuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(generosUsuario);
    }

    // Nuevo método para asociar géneros a un usuario
    @PostMapping("/asociarGeneros")
    public ResponseEntity<Void> asociarGenerosAUsuario(@RequestBody Map<String, Object> payload) {
        int idUsuario = (Integer) payload.get("idUsuario");
        List<Integer> idGeneros = (List<Integer>) payload.get("idGeneros");

        for (Integer idGenero : idGeneros) {
            GeneroUsuario generoUsuario = new GeneroUsuario();
            generoUsuario.setIdUsuario(idUsuario);
            generoUsuario.setIdGenero(idGenero);
            generosUsuarioRepository.save(generoUsuario);
        }

        return ResponseEntity.ok().build();
    }
}
