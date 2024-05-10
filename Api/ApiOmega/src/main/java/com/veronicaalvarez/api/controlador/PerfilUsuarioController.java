package com.veronicaalvarez.api.controlador;

import com.veronicaalvarez.api.modelo.PerfilUsuario;
import com.veronicaalvarez.api.repositorio.PerfilUsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/omega/perfiles")
public class PerfilUsuarioController {

    private final PerfilUsuarioRepository perfilUsuarioRepository;

    public PerfilUsuarioController(PerfilUsuarioRepository perfilUsuarioRepository) {
        this.perfilUsuarioRepository = perfilUsuarioRepository;
    }

    @GetMapping
    public ResponseEntity<?> obtenerPerfilesUsuarios() {
        List<PerfilUsuario> perfiles = perfilUsuarioRepository.findAll();

        if (perfiles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(perfiles);
    }
}
