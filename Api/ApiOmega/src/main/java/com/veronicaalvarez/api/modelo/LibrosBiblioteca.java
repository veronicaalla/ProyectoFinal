package com.veronicaalvarez.api.modelo;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="libros_biblioteca")
public class LibrosBiblioteca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "id_biblioteca", referencedColumnName = "id", foreignKey = @ForeignKey(name = "libros_biblioteca_ibfk_1"))
	private Biblioteca biblioteca;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "id_libro", referencedColumnName = "id", foreignKey = @ForeignKey(name="libros_biblioteca_ibfk_2"))
	private Libro libro;
	
	
	@ColumnDefault("0")
    @Column(name = "valorado", nullable = false)
	private Boolean valorado;
	
	//Constructor
	public LibrosBiblioteca() {
		super();
	}

	public LibrosBiblioteca(Biblioteca biblioteca, Libro libro) {
		super();
		this.biblioteca = biblioteca;
		this.libro = libro;
	}

	public LibrosBiblioteca(Biblioteca biblioteca, Libro libro, boolean valorado) {
		super();
		this.biblioteca = biblioteca;
		this.libro = libro;
		this.valorado = valorado;
	}

	public LibrosBiblioteca(int id, Biblioteca biblioteca, Libro libro, boolean valorado) {
		super();
		this.id = id;
		this.biblioteca = biblioteca;
		this.libro = libro;
		this.valorado = valorado;
	}

	
	//Métodos setters y getters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public boolean isValorado() {
		return valorado;
	}

	public void setValorado(boolean valorado) {
		this.valorado = valorado;
	}
	
	
	
	
}
