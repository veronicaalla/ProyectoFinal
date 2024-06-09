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


    /**
     * Asocia uno o más géneros a un usuario.
     *
     * @param idUsuario El ID del usuario al que se van a asociar los géneros.
     * @param idGeneros La lista de IDs de los géneros que se van a asociar al usuario.
     * @return ResponseEntity<Void> Un ResponseEntity que indica el resultado de la operación.
     */
    @PostMapping("/asociarGeneros")
    public ResponseEntity<Void> asociarGenerosAUsuario(@RequestParam("idUsuario") int idUsuario, @RequestParam("idGeneros") List<Integer> idGeneros) {

        for (Integer idGenero : idGeneros) {
            GeneroUsuario generoUsuario = new GeneroUsuario();
            generoUsuario.setIdUsuario(idUsuario);
            generoUsuario.setIdGenero(idGenero);
            generosUsuarioRepository.save(generoUsuario);
        }

        return ResponseEntity.ok().build();
    }
}
