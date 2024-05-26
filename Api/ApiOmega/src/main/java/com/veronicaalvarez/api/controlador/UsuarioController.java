package com.veronicaalvarez.api.controlador;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.veronicaalvarez.api.ImplementacionSeguridad.Seguridad;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.veronicaalvarez.api.modelo.Usuario;
import com.veronicaalvarez.api.repositorio.UsuarioRepositorio;


@RestController
@RequestMapping("/omega/usuarios")
public class UsuarioController {

	@PersistenceContext
	private EntityManager entityManager;

	private final UsuarioRepositorio usuarioRepositorio;

	public UsuarioController(UsuarioRepositorio usuarioRepositorio) {
		this.usuarioRepositorio = usuarioRepositorio;
	}


	@GetMapping
	public ResponseEntity<?> obtenerUsuario(){
		List <Usuario> usuarios = usuarioRepositorio.findAll();
		if (usuarios.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuarios);
	}

	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable int id){
		Usuario usuario = usuarioRepositorio.findById(id).orElse(null);

		if (usuario==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Credenciales inválidas");
		}

		/*
		Seguridad seguridad = new Seguridad();
		Map<String, Object> map = new HashMap<>();
		map.put("usuario", usuario);

        String datosEncriptados = seguridad.encriptado(map);*/

		return ResponseEntity.ok(usuario);
	}



	@GetMapping("/usuarios/{user}")
	public ResponseEntity<Usuario> buscarUsuarioPorUser(@PathVariable String user) {
		Usuario usuario = usuarioRepositorio.findByAlias(user);
		if (usuario != null) {
			return ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}



	@PostMapping
	public ResponseEntity<String> createUsuario(@RequestBody Usuario usuario) {

		usuario.setAuditCreated(LocalDateTime.now());
		usuario.setAuditCreator("admi");

		usuario.setAuditUpdated(LocalDateTime.now());
		usuario.setAuditUpdater("admi");

		//encriptamos la contraseña
		// Generar una contraseña encriptada
		String hashedPassword = BCrypt.hashpw(usuario.getClave(), BCrypt.gensalt());
		//se la asignamos al usuario
		usuario.setClave(hashedPassword);

		usuarioRepositorio.save(usuario);

		return ResponseEntity.ok("Usuario created successfully");
	}


	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarUsuario(@PathVariable int id, @RequestBody Usuario usuarioModificado) {

		Usuario usuarioExistente = usuarioRepositorio.findById(id).orElse(null);

		if (usuarioExistente == null) {
			return ResponseEntity.notFound().build();
		}

		// Actualizar los campos del usuario existente con los datos del usuario modificado
		usuarioExistente.setAlias(usuarioModificado.getAlias());
		usuarioExistente.setNombre(usuarioModificado.getNombre());
		usuarioExistente.setApellidos(usuarioModificado.getApellidos());
		usuarioExistente.setFechaNacimiento(usuarioModificado.getFechaNacimiento());
		usuarioExistente.setCorreo(usuarioModificado.getCorreo());
		usuarioExistente.setClave(usuarioModificado.getClave());
		usuarioExistente.setTelefono(usuarioModificado.getTelefono());
		usuarioExistente.setTipo(usuarioModificado.getTipo());
		usuarioExistente.setPublico(usuarioModificado.getPublico());

		//Actualizamos los campos de auditoria
		usuarioExistente.setAuditUpdated(LocalDateTime.now());
		//Se debe adjudicar al hacerse la mod -> usuarioExistente.setAuditUpdater(String.valueOf(usuarioModificado.getId()));

		// Guardar los cambios en la base de datos
		usuarioRepositorio.save(usuarioExistente);

		return ResponseEntity.ok(usuarioExistente);
	}

	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<?> eliminarUsuario(@PathVariable int id) {
		// Verificar si el usuario existe en la base de datos
		boolean existeUsuario = usuarioRepositorio.existsById(id);

		if (!existeUsuario) {
			return ResponseEntity.notFound().build(); // Si el usuario no existe, devolver una respuesta de estado 404 (no encontrado)
		}

		// Eliminar el usuario de la base de datos
		usuarioRepositorio.deleteById(id);

		// Devolver una respuesta con un mensaje de éxito
		return ResponseEntity.ok("Usuario eliminado correctamente");
	}


	@PostMapping("/login")
	public ResponseEntity<?> loginUsuario(@RequestParam String usuarioOEmail, @RequestParam String clave) {
		// Buscar al usuario por nombre de usuario o correo electrónico
		Usuario usuario = usuarioRepositorio.findByAliasOrCorreo(usuarioOEmail, usuarioOEmail);

		// Verificar si se encontró un usuario
		if (usuario == null) {
			return ResponseEntity.notFound().build(); // Si no se encuentra el usuario, devolver una respuesta de estado 404 (no encontrado)
		}

		// Verificar la contraseña
		boolean isPasswordCorrect = BCrypt.checkpw(clave, usuario.getClave());

		if (!isPasswordCorrect) {
			return ResponseEntity.status(401).body("Contraseña incorrecta");
		}

		// Devolver una respuesta exitosa con los detalles del usuario (excluyendo la contraseña)
		return ResponseEntity.ok(usuario);
	}
}
