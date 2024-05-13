package com.veronicaalvarez.api.modelo;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="comentarios_reportados")
public class ComentarioReportado {

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
	
	
	
	//@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_comentario", nullable = false)
	private int idComentario;
	

	//@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_reportante", nullable = false)
	private int idReportante;
	
	@Column(name = "ofensivo")
	private Boolean ofensivo;


	
	//Métodos setter y getts


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

	public int getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(int idComentario) {
		this.idComentario = idComentario;
	}

	public int getIdReportante() {
		return idReportante;
	}

	public void setIdReportante(int idReportante) {
		this.idReportante = idReportante;
	}

	public Boolean getOfensivo() {
		return ofensivo;
	}

	public void setOfensivo(Boolean ofensivo) {
		this.ofensivo = ofensivo;
	}
}
