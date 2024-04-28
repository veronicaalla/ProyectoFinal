package com.veronicaalvarez.api.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "perfil_usuario")
public class PerfilUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idperfil", nullable = false)
    private Integer id;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "audit_created", nullable = false)
    private LocalDateTime auditCreated;

    @ColumnDefault("admi")
    @Column(name = "audit_creator", nullable = false, length = 45)
    private String auditCreator;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "audit_updated", nullable = false)
    private LocalDateTime auditUpdated;

    @ColumnDefault("admi")
    @Column(name = "audit_updater", nullable = false, length = 45)
    private String auditUpdater;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "informacion", length = 100)
    private String informacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @Column(name = "username", nullable = false, length = 45)
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getAuditCreated() {
        return auditCreated;
    }

    public void setAuditCreated(LocalDateTime auditCreated) {
        this.auditCreated = auditCreated;
    }

    public String getAuditCreator() {
        return auditCreator;
    }

    public void setAuditCreator(String auditCreator) {
        this.auditCreator = auditCreator;
    }

    public LocalDateTime getAuditUpdated() {
        return auditUpdated;
    }

    public void setAuditUpdated(LocalDateTime auditUpdated) {
        this.auditUpdated = auditUpdated;
    }

    public String getAuditUpdater() {
        return auditUpdater;
    }

    public void setAuditUpdater(String auditUpdater) {
        this.auditUpdater = auditUpdater;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public Usuario getIdUsuario() {
        return usuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.usuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}