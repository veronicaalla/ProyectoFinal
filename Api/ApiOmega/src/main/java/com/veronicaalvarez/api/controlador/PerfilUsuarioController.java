package com.veronicaalvarez.api.controlador;

import com.veronicaalvarez.api.modelo.PerfilUsuario;
import com.veronicaalvarez.api.repositorio.PerfilUsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> obtenerPerfilPorIdUsuario(@PathVariable int idUsuario) {

        PerfilUsuario perfil = perfilUsuarioRepository.findByIdUsuario(idUsuario);

        if (perfil == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(perfil);
    }


    @PutMapping("modificar/{idUsuario}")
    public ResponseEntity<?> modificarPerfil(@PathVariable int idUsuario, @RequestBody PerfilUsuario perfilUsuario) {
        PerfilUsuario perfilExistente = perfilUsuarioRepository.findByIdUsuario(idUsuario);

        if (perfilExistente == null) {
            return ResponseEntity.notFound().build();
        }

        perfilExistente.setNombre(perfilUsuario.getNombre());
        perfilExistente.setInformacion(perfilUsuario.getInformacion());


        perfilExistente.setNombre(perfilUsuario.getNombre());
        PerfilUsuario perfilActualizado = perfilUsuarioRepository.save(perfilExistente);
        return ResponseEntity.ok(perfilActualizado);
    }

}
