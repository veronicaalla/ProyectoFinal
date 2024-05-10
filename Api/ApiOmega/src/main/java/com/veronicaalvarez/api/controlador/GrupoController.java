package com.veronicaalvarez.api.controlador;


import com.veronicaalvarez.api.modelo.Grupo;
import com.veronicaalvarez.api.repositorio.GrupoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/omega/grupos")
public class GrupoController {

    private final GrupoRepository grupoRepository;

    public GrupoController(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    @GetMapping
    public ResponseEntity<?>  obtenerGrupos() {
        List<Grupo> grupos = grupoRepository.findAll();

        if (grupos.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(grupos);
    }
}
