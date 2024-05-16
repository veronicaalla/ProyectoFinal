package com.veronicaalvarez.api.controlador;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.veronicaalvarez.api.ImplementacionSeguridad.Seguridad;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.veronicaalvarez.api.modelo.Usuario;
import com.veronicaalvarez.api.repositorio.UsuarioRepositorio;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/omega/usuarios")
public class UsuarioController {

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

	
	@GetMapping("usuario/{id}")
	public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable int id){
		Usuario usuario = usuarioRepositorio.findById(id).orElse(null);

		Seguridad seguridad = new Seguridad();

		if (usuario==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Credenciales inválidas");
		}

		Map<String, Object> map = new HashMap<>();
		map.put("usuario", usuario);

        String datosEncriptados = seguridad.encriptado(map);

		return ResponseEntity.ok(datosEncriptados);
	}

	/*@GetMapping("usuario/{id}")
	public ResponseEntity<String> obtenerUsuarioPorId(@PathVariable int id) throws JsonProcessingException {
		Usuario usuario = usuarioRepositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
		ObjectMapper objectMapper = new ObjectMapper();
		String usuarioJson = objectMapper.writeValueAsString(usuario);

		String encryptedData = new Seguridad().encriptado(usuarioJson);

		return ResponseEntity.ok(encryptedData);
	}*/

	@GetMapping("/usuarios/{user}")
	public ResponseEntity<Usuario> buscarUsuarioPorUser(@PathVariable String user) {
		Usuario usuario = usuarioRepositorio.findByUsername(user);
		if (usuario != null) {
			return ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/crearUsuario")
	public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
		//Asignamos la auditoria
		usuario.setAuditCreated(LocalDateTime.now());
		usuario.setAuditUpdated(LocalDateTime.now());
		usuario.setAuditCreator(String.valueOf(usuario.getId()));
		usuario.setAuditUpdater(String.valueOf(usuario.getId()));
		usuarioRepositorio.save(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado correctamente");
	}




	@PutMapping("modificar/{id}")
	public ResponseEntity<?> modificarUsuario(@PathVariable int id, @RequestBody Usuario usuarioModificado) {

		Usuario usuarioExistente = usuarioRepositorio.findById(usuarioModificado.getId()).orElse(null);

		if (usuarioExistente == null) {
			return ResponseEntity.notFound().build();
		}

		// Actualizar los campos del usuario existente con los datos del usuario modificado
		usuarioExistente.setUsername(usuarioModificado.getUsername());
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
		usuarioExistente.setAuditUpdater(String.valueOf(usuarioModificado.getId()));

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
		Usuario usuario = usuarioRepositorio.findByUsernameOrCorreo(usuarioOEmail, usuarioOEmail);

		// Verificar si se encontró un usuario
		if (usuario == null) {
			return ResponseEntity.notFound().build(); // Si no se encuentra el usuario, devolver una respuesta de estado 404 (no encontrado)
		}

		// Verificar si la contraseña proporcionada coincide con la contraseña del usuario
		if (!usuario.getClave().equals(clave)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas"); // Si las contraseñas no coinciden, devolver una respuesta de estado 401 (no autorizado)
		}

		// Devolver una respuesta exitosa con los detalles del usuario (excluyendo la contraseña)
		return ResponseEntity.ok(usuario);
	}

	/*Verigicar las credenciales
	@GetMapping("/login")
	public ResponseEntity<?> loginPorUser (@RequestParam String user, @RequestParam String clave){
		//Buscamos el usuario
		Usuario usuario = usuarioRepositorio.findByUserAndClave(user, clave);
		//Comprobamos que no sea nulo
		if (usuario != null) {
			return ResponseEntity.ok(usuario);
		}else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");
		}
	}
	
	
	@PostMapping
	public ResponseEntity<Usuario> nuevoUsuario (@RequestBody Usuario nuevoUsuario){
		Usuario nuevo = usuarioRepositorio.save(nuevoUsuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
	}
	
	@PutMapping("editar/{id}")
	public ResponseEntity<?> editarUsuario (@RequestBody Usuario editarUsuario, @PathVariable Integer id){
		Usuario usuario = usuarioRepositorio.findById(id).orElse(null);
		if (usuario==null) {
			return ResponseEntity.notFound().build();
		}
		
		usuario.setNombre(editarUsuario.getNombre());
		usuario.setApellidos(editarUsuario.getApellidos());
		usuario.setUser(editarUsuario.getUser());
		usuario.setCorreo(editarUsuario.getCorreo());
		usuario.setClave(editarUsuario.getClave());
		usuario.setTelefono(editarUsuario.getTelefono());
		
		//devolvemos el resultado
		return ResponseEntity.ok(usuarioRepositorio.save(usuario));
	}
	
	
	@DeleteMapping("borrar/{id}")
	public ResponseEntity<?> borrarUsuario (@PathVariable Integer id){
		usuarioRepositorio.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@GetMapping("/user/{user}")
	public ResponseEntity<?> obtenerUsuarioPorUser(@PathVariable String user){
		Usuario usuario = usuarioRepositorio.findByUser(user);
		
		if (usuario==null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(usuario);
	}
	
	@GetMapping("/correo/{correo}")
	public ResponseEntity<?> obtenerUsuarioPorCorreo(@PathVariable String correo){
		Usuario usuario = usuarioRepositorio.findByCorreo(correo);
		
		if (usuario==null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(usuario);
	}
	
	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<?> obtenerUsuarioPorNombre(@PathVariable String nombre){
		Usuario usuario = usuarioRepositorio.findByNombre(nombre);
		
		if (usuario==null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(usuario);
	}
	
	@GetMapping("/apellidos/{apellidos}")
	public ResponseEntity<?> obtenerUsuarioPorApellidos(@PathVariable String apellidos){
		Usuario usuario = usuarioRepositorio.findByApellidos(apellidos);
		
		if (usuario==null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(usuario);
	}
	
	
	@GetMapping("/telefono/{telefono}")
	public ResponseEntity<?> obtenerUsuarioPorTelefono(@PathVariable String telefono){
		Usuario usuario = usuarioRepositorio.findByTelefono(telefono);
		
		if (usuario==null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(usuario);
	}*/
}
