package com.veronicaalvarez.api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veronicaalvarez.api.modelo.Biblioteca;
import com.veronicaalvarez.api.modelo.Usuario;

public interface BibliotecaRepositorio extends JpaRepository<Biblioteca, Integer> {
	//Usuario findByUserAndClave(String user, String clave);
	//List<Biblioteca> findByUsuario(int id_usuario);
	//Biblioteca findByNombre (String nombre);
}
