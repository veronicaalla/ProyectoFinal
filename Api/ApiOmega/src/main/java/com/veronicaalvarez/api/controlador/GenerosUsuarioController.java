package com.veronicaalvarez.api.controlador;

import com.veronicaalvarez.api.modelo.GeneroUsuario;
import com.veronicaalvarez.api.repositorio.GenerosUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
