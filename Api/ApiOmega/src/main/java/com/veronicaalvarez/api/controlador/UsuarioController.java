package com.veronicaalvarez.api.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.veronicaalvarez.api.modelo.Usuario;
import com.veronicaalvarez.api.repositorio.UsuarioRepositorio;



@RestController
@RequestMapping("/omega/usuarios")
public class UsuarioController {

	private final UsuarioRepositorio usuarioRepositorio;
	
	public UsuarioController(UsuarioRepositorio usuarioRepositorio) {
		this.usuarioRepositorio = usuarioRepositorio;
	}
	
	//Usamos un método GetMapping para obtener toda la lista de los usuarios
	/*@GetMapping
	public List<Usuario> obtenerUsuarios(){
		return usuarioRepositorio.findAll();
	}*/
	
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
		
		if (usuario==null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(usuario);
	}
	
	//Verigicar las credenciales 
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
	}
}
