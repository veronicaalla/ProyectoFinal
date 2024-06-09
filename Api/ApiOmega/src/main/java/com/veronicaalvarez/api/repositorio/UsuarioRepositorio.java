package com.veronicaalvarez.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.veronicaalvarez.api.modelo.Usuario;

/**
 * Repositorio para la entidad Usuario.
 */
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    /**
     * Busca un usuario por su alias o correo electrónico.
     *
     * @param alias El alias del usuario a buscar.
     * @param correo El correo electrónico del usuario a buscar.
     * @return Usuario El usuario encontrado que coincida con el alias o correo electrónico especificado.
     */
    Usuario findByAliasOrCorreo(String alias, String correo);

    /**
     * Busca un usuario por su alias.
     *
     * @param alias El alias del usuario a buscar.
     * @return Usuario El usuario encontrado que coincida con el alias especificado.
     */
    Usuario findByAlias(String alias);

    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param correo El correo electrónico del usuario a buscar.
     * @return Usuario El usuario encontrado que coincida con el correo electrónico especificado.
     */
    Usuario findByCorreo(String correo);;

}

