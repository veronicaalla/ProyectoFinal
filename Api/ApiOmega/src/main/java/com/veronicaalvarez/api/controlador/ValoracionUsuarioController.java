package com.veronicaalvarez.api.controlador;

import com.veronicaalvarez.api.modelo.ValoracionUsuario;
import com.veronicaalvarez.api.repositorio.ValoracionUsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/omega/valoracionesUsuarios")
public class ValoracionUsuarioController {

    private final ValoracionUsuarioRepository valoracionUsuarioRepository;

    public ValoracionUsuarioController(ValoracionUsuarioRepository valoracionUsuarioRepository) {
        this.valoracionUsuarioRepository = valoracionUsuarioRepository;
    }

    @GetMapping
    public ResponseEntity<?> obtenerValoracionUsuarios() {
        List<ValoracionUsuario> valoracionUsuarios = valoracionUsuarioRepository.findAll();
        if (valoracionUsuarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(valoracionUsuarios);
    }
}
