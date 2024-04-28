package com.veronicaalvarez.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veronicaalvarez.api.modelo.Usuario;


//Es un integer, debido a que el id es un int
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
	Usuario findByUserAndClave(String user, String clave);
	Usuario findByCorreoAndClave(String correo, String clave);
	Usuario findByTelefonoAndClave(String telefono, String clave);
	//Para la aplicacion de escritorio
	Usuario findByUser(String user);
	Usuario findByCorreo (String correo);
	Usuario findByTelefono(String telefono);
	Usuario findByNombre(String nombre);
	Usuario findByApellidos(String apellidos);
}
