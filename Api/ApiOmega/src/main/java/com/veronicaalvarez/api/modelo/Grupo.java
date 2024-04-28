package com.veronicaalvarez.api.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "grupos")
public class Grupo {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "audit_created")
    private LocalDateTime auditCreated;

    @Column(name = "audit_creator", nullable = false, length = 45)
    private String auditCreator;

    @Column(name = "audit_updated")
    private LocalDateTime auditUpdated;

    @Column(name = "audit_updater", nullable = false, length = 45)
    private String auditUpdater;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

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

}