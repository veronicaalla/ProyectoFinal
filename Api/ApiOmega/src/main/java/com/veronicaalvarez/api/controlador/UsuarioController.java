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

	/**
	 * Obtiene todos los usuarios.
	 * @return ResponseEntity con la lista de usuarios o un 404 NOT FOUND si no se encuentran usuarios.
	 */
	@GetMapping
	public ResponseEntity<?> obtenerUsuario(){
		List <Usuario> usuarios = usuarioRepositorio.findAll();
		if (usuarios.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuarios);
	}


	/**
	 * Obtiene un usuario por su ID.
	 * @param id El ID del usuario.
	 * @return ResponseEntity con el usuario o un 404 NOT FOUND si no se encuentra.
	 */
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



	/**
	 * Busca un usuario por su nombre de usuario.
	 * @param user El nombre de usuario a buscar.
	 * @return ResponseEntity con el usuario o un 404 NOT FOUND si no se encuentra.
	 */
	@GetMapping("/usuarios/{user}")
	public ResponseEntity<Usuario> buscarUsuarioPorUser(@PathVariable String user) {
		Usuario usuario = usuarioRepositorio.findByAlias(user);
		if (usuario != null) {
			return ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}


	@GetMapping("/usuarios/correo/{correo}")
	public ResponseEntity<Usuario> buscarUsuarioPorCorreo(@PathVariable String correo) {
		Usuario usuario = usuarioRepositorio.findByCorreo(correo);
		if (usuario != null) {
			return ResponseEntity.ok(usuario);
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Crea un nuevo usuario.
	 * @param usuario El usuario a crear.
	 * @return ResponseEntity con un mensaje de éxito.
	 */
	@PostMapping("/crearUsuario")
	public ResponseEntity<String> createUsuario(@RequestBody Usuario usuario) {

		//Debemos comprobar que el correo no esta usandose
		Usuario usuarioExistente = usuarioRepositorio.findByCorreo(usuario.getCorreo());
		Usuario usuarioExistente2 = usuarioRepositorio.findByAlias(usuario.getAlias());

		if (usuarioExistente2 != null && usuarioExistente != null){
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario ya existe");
		}

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


	/**
	 * Modifica un usuario existente.
	 * @param id El ID del usuario a modificar.
	 * @param usuarioModificado El usuario modificado.
	 * @return ResponseEntity con el usuario modificado o un 404 NOT FOUND si el usuario no existe.
	 */
	@PutMapping("/modificar/{id}")
	public ResponseEntity<?> modificarUsuario(@PathVariable int id, @RequestBody Usuario usuarioModificado) {

		Usuario usuarioExistente = usuarioRepositorio.findById(usuarioModificado.getId()).orElse(null);

		//Necesitamos ver si el usuario que ha realizado la modificacion existe
		Usuario usuModif = usuarioRepositorio.findById(id).orElse(null);
		if (usuarioExistente == null || usuModif == null) {
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
		usuarioExistente.setAuditUpdater(String.valueOf(id));

		// Guardar los cambios en la base de datos
		usuarioRepositorio.save(usuarioExistente);

		return ResponseEntity.ok(usuarioExistente);
	}


	/**
	 * Elimina un usuario por su ID.
	 * @param id El ID del usuario a eliminar.
	 * @return ResponseEntity con un mensaje de éxito o un 404 NOT FOUND si el usuario no existe.
	 */
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


	/**
	 * Inicia sesión de un usuario.
	 * @param usuarioOEmail El nombre de usuario o correo electrónico del usuario.
	 * @param clave La contraseña del usuario.
	 * @return ResponseEntity con el usuario si la autenticación es exitosa o un 401 UNAUTHORIZED si la contraseña es incorrecta.
	 */
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

	@PutMapping("privacidad/{idUsuario}")
	public ResponseEntity<?> modificarPrivacidad(@PathVariable("idUsuario") int id, @RequestParam("publico") boolean publico) {
		Usuario usuario = usuarioRepositorio.findById(id).orElse(null);

		if (usuario == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no se ha encontrado");
		}

		usuario.setPublico(publico);
		usuarioRepositorio.save(usuario);
		return ResponseEntity.ok(usuario);
	}

}
