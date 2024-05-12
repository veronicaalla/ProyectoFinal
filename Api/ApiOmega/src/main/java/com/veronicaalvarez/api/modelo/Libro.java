package com.veronicaalvarez.api.modelo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="libros", uniqueConstraints = @UniqueConstraint(columnNames = {"ISBN"}))
public class Libro {

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
	    
	
	@Column(name = "ISBN", nullable = false, unique = true, length = 45)
	private String ISBN;

	@Column(name = "titulo", nullable = false, length = 45)
	private String titulo;

	@Column(name = "autor", nullable = false, length = 45)
	private String autor;

	@Column(name = "descripcion", nullable = false, length = 200)
	private String descripcion;

	@Column(name = "genero", nullable = false, length = 45)
	private int genero;

	@Column(name = "fecha_publicacion")
	private LocalDate fechaPublicacion;

	@Column(name = "paginas", nullable = false)
	private Integer paginas;



	//Métodos setter y getters
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

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }
}
