package com.veronicaalvarez.api.controlador;


import com.veronicaalvarez.api.modelo.Grupo;
import com.veronicaalvarez.api.repositorio.GrupoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * Obtiene todos los grupos.
     * @return ResponseEntity con la lista de todos los grupos o un 404 NOT FOUND si no hay grupos.
     */
    @GetMapping
    public ResponseEntity<?>  obtenerGrupos() {
        List<Grupo> grupos = grupoRepository.findAll();

        if (grupos.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(grupos);
    }

    /**
     * Obtiene un grupo por su ID.
     * @param id El ID del grupo.
     * @return ResponseEntity con el grupo encontrado o un 404 NOT FOUND si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?>  obtenerGrupo(@PathVariable Integer id) {
        Grupo grupo = grupoRepository.findById(id).orElse(null);
        if (grupo == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(grupo);
    }
}
