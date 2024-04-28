package com.veronicaalvarez.api.modelo;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_comentario", nullable = false)
	private Comentario comentario;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_reportante", nullable = false)
	private Usuario reportante;
	
	@Column(name = "ofensivo")
	private boolean ofensivo;
	
	//Constructor 
	public ComentarioReportado() {
		super();
	}

	public ComentarioReportado(Comentario comentario, Usuario reportante) {
		super();
		this.comentario = comentario;
		this.reportante = reportante;
	}

	public ComentarioReportado(int id, Comentario comentario, Usuario reportante, boolean ofensivo) {
		super();
		this.id = id;
		this.comentario = comentario;
		this.reportante = reportante;
		this.ofensivo = ofensivo;
	}

	
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


	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}

	public Usuario getReportante() {
		return reportante;
	}

	public void setReportante(Usuario reportante) {
		this.reportante = reportante;
	}

	public boolean isOfensivo() {
		return ofensivo;
	}

	public void setOfensivo(boolean ofensivo) {
		this.ofensivo = ofensivo;
	}
	
	
}
