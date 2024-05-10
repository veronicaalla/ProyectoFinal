package com.veronicaalvarez.api.modelo;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="generos")
public class Genero {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
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
    
	
    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;



	
	//Metodos getter
	public int getId() {
		return id;
	}

	public void setId(int id) {
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
