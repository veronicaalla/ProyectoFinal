package com.veronicaalvarez.api.modelo;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="libros_biblioteca")
public class LibrosBiblioteca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "id_biblioteca", referencedColumnName = "id", foreignKey = @ForeignKey(name = "libros_biblioteca_ibfk_1"))
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Biblioteca biblioteca;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "id_libro", referencedColumnName = "id", foreignKey = @ForeignKey(name="libros_biblioteca_ibfk_2"))
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Libro libro;


	
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
}
