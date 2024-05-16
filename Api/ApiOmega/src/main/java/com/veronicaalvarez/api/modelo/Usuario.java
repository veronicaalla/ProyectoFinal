package com.veronicaalvarez.api.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

	@Column(name = "username", nullable = false, length = 45)
	private String username;

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

	@JoinColumn(name = "tipo", nullable = false)
	private int tipo;

	@Column(name = "publico")
	private Boolean publico;

	
	/*Tabla terciaria
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="generos_usuario", joinColumns = @JoinColumn(name="id_usuario"), inverseJoinColumns = @JoinColumn(name="id_genero") )
	private Set<Genero> generosFavoritos = new HashSet<>();*/


    public int getId() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Boolean getPublico() {
        return publico;
    }

    public void setPublico(Boolean publico) {
        this.publico = publico;
    }

  /*  @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", auditCreated=" + auditCreated +
                ", auditCreator='" + auditCreator + '\'' +
                ", auditUpdated=" + auditUpdated +
                ", auditUpdater='" + auditUpdater + '\'' +
                ", username='" + username + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", correo='" + correo + '\'' +
                ", clave='" + clave + '\'' +
                ", telefono='" + telefono + '\'' +
                ", tipo=" + tipo +
                ", publico=" + publico +
                '}';
    }*/
}
