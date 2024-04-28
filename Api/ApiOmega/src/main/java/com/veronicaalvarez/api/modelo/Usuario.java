package com.veronicaalvarez.api.modelo;

import java.sql.Date;
import java.time.LocalDate;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

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

	@Column(name = "user", nullable = false, length = 45)
	private String user;

	@Column(name = "nombre", nullable = false, length = 45)
	private String nombre;

	@Column(name = "apellidos", nullable = false, length = 45)
	private String apellidos;

	@Column(name = "fecha_nacimiento", nullable = false)
	private LocalDate fechaNacimiento;

	@Column(name = "correo", nullable = false, length = 100)
	private String correo;

	@Column(name = "clave", nullable = false, length = 45)
	private String clave;

	@Column(name = "telefono", length = 45)
	    private String telefono;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@ColumnDefault("3")
	@JoinColumn(name = "tipo", nullable = false)
	private Grupo tipo;

	@ColumnDefault("0")
	@Column(name = "publico")
	private Boolean publico;

	
	//Tabla terciaria
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="generos_usuario", joinColumns = @JoinColumn(name="id_usuario"), inverseJoinColumns = @JoinColumn(name="id_genero") )
	private Set<Genero> generosFavoritos = new HashSet<>();
	
	// Constructor
	public Usuario() {
		super();
	}

	

	// Métodos setters & getters
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Grupo getTipo() {
        return tipo;
    }

    public void setTipo(Grupo tipo) {
        this.tipo = tipo;
    }

    public Boolean getPublico() {
        return publico;
    }

    public void setPublico(Boolean publico) {
        this.publico = publico;
    }



}
