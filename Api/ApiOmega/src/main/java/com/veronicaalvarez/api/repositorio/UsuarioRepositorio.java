package com.veronicaalvarez.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veronicaalvarez.api.modelo.Usuario;


//Es un integer, debido a que el id es un int
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
    Usuario findByUsernameOrCorreo(String username, String correo);
    Usuario findByUsername(String username);

}
