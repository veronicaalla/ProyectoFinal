package com.veronicaalvarez.api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veronicaalvarez.api.modelo.Comentario;
import com.veronicaalvarez.api.modelo.Libro;
import com.veronicaalvarez.api.modelo.Usuario;

public interface ComentarioRepositorio extends JpaRepository<Comentario, Integer>  {

     /**
     * Busca los comentarios asociados a un libro determinado
     * 
     * @param idLibro El id del libro
     * @return Lista de comentarios asociados
     */
    List<Comentario> findByIdLibro(int idLibro);
}
