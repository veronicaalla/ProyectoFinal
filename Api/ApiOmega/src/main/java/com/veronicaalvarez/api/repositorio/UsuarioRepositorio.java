package com.veronicaalvarez.api.repositorio;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.veronicaalvarez.api.modelo.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;


//Es un integer, debido a que el id es un int
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
    Usuario findByAliasOrCorreo(String alias, String correo);
    Usuario findByAlias(String alias);
    Usuario findByCorreo(String correo);;


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuarios (audit_created, audit_creator, audit_updated, audit_updater, alias, nombre, apellidos, fecha_nacimiento, correo, clave, telefono, tipo, publico) " +
            "VALUES (:auditCreated, :auditCreator, :auditUpdated, :auditUpdater, :alias, :nombre, :apellidos, :fechaNacimiento, :correo, :clave, :telefono, :tipo, :publico)", nativeQuery = true)
    void insertUsuario(
            @Param("auditCreated") LocalDateTime auditCreated,
            @Param("auditCreator") String auditCreator,
            @Param("auditUpdated") LocalDateTime auditUpdated,
            @Param("auditUpdater") String auditUpdater,
            @Param("alias") String alias,
            @Param("nombre") String nombre,
            @Param("apellidos") String apellidos,
            @Param("fechaNacimiento") LocalDate fechaNacimiento,
            @Param("correo") String correo,
            @Param("clave") String clave,
            @Param("telefono") String telefono,
            @Param("tipo") int tipo,
            @Param("publico") Boolean publico
    );
}

