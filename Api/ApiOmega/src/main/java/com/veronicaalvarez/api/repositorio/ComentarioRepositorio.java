package com.veronicaalvarez.api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veronicaalvarez.api.modelo.Comentario;
import com.veronicaalvarez.api.modelo.Libro;
import com.veronicaalvarez.api.modelo.Usuario;

public interface ComentarioRepositorio extends JpaRepository<Comentario, Integer>  {

	/*List<Comentario> findByLibro (Libro libro);
	List<Comentario> findByUsuario (Usuario usuario);*/
}
