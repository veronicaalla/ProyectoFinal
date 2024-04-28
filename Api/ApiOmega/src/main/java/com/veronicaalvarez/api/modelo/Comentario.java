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
@Table(name="comentarios")
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "id_libro", nullable = false)
	private Libro libro;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;
	
	@Column(name = "comentario", nullable = false)
	private String comentario;
	
	@ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha")
	private LocalDateTime fecha; //private Instant fecha; se le asigna de forma automatica

	
	//Constructores
	public Comentario() {
		super();
	}

	public Comentario(Libro libro, Usuario usuario, String comentario) {
		super();
		this.libro = libro;
		this.usuario = usuario;
		this.comentario = comentario;
	}

	public Comentario(int id, Libro libro, Usuario usuario, String comentario, LocalDateTime fecha) {
		super();
		this.id = id;
		this.libro = libro;
		this.usuario = usuario;
		this.comentario = comentario;
		this.fecha = fecha;
	}

	
	//Métodos setters y getters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	
}
