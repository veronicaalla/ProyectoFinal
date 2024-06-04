package com.veronicaalvarez.api.controlador;

import com.veronicaalvarez.api.modelo.PerfilUsuario;
import com.veronicaalvarez.api.modelo.Usuario;
import com.veronicaalvarez.api.repositorio.PerfilUsuarioRepository;
import com.veronicaalvarez.api.repositorio.UsuarioRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/omega/perfiles")
public class PerfilUsuarioController {

    private final PerfilUsuarioRepository perfilUsuarioRepository;
    private final UsuarioRepositorio usuarioRepositorio;

    public PerfilUsuarioController(PerfilUsuarioRepository perfilUsuarioRepository, UsuarioRepositorio usuarioRepositorio) {
        this.perfilUsuarioRepository = perfilUsuarioRepository;
        this.usuarioRepositorio = usuarioRepositorio;
    }

   /* @GetMapping
    public ResponseEntity<?> obtenerPerfilesUsuarios() {
        List<PerfilUsuario> perfiles = perfilUsuarioRepository.findAll();

        if (perfiles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(perfiles);
    }*/


    /**
     * Obtiene el perfil de un usuario por su ID.
     * @param idUsuario El ID del usuario.
     * @return ResponseEntity con el perfil del usuario o un 404 NOT FOUND si no se encuentra.
     */
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> obtenerPerfilPorIdUsuario(@PathVariable int idUsuario) {
        PerfilUsuario perfil = perfilUsuarioRepository.findByIdUsuario(idUsuario);

        if (perfil == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(perfil);
    }


    /**
     * Modifica el perfil de un usuario.
     * @param idUsuario El ID del usuario.
     * @param perfilUsuarioNuevo El perfil actualizado del usuario.
     * @return ResponseEntity con un mensaje de éxito o un 404 NOT FOUND si el usuario o el perfil no existen.
     */
    @PutMapping("modificar/{idUsuario}")
    public ResponseEntity<?> modificarPerfil(@PathVariable int idUsuario, @RequestBody PerfilUsuario perfilUsuarioNuevo) {

        //Buscamos el usuario
        Usuario usuario = usuarioRepositorio.findById(idUsuario).orElse(null);
        PerfilUsuario perfilExistente = perfilUsuarioRepository.findByIdUsuario(idUsuario);

        if (usuario == null || perfilExistente == null) {
            return ResponseEntity.notFound().build();
        }


        //Modificamos la auditoria
        perfilExistente.setAuditUpdated(LocalDateTime.now());
        perfilExistente.setAuditUpdater(String.valueOf(usuario.getId()));

        perfilExistente.setNombre(perfilUsuarioNuevo.getNombre());
        perfilExistente.setInformacion(perfilUsuarioNuevo.getInformacion());


        perfilUsuarioRepository.save(perfilExistente);
        return ResponseEntity.ok("Perfil actualizado correctamente");
    }

}
