package com.veronicaalvarez.api.modelo;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="libros_biblioteca")
public class LibrosBiblioteca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "id_biblioteca", referencedColumnName = "id", foreignKey = @ForeignKey(name = "libros_biblioteca_ibfk_1"))
	private int idBiblioteca;
	
	//@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "id_libro", referencedColumnName = "id", foreignKey = @ForeignKey(name="libros_biblioteca_ibfk_2"))
	private int idLibro;
	
	
	@ColumnDefault("0")
    @Column(name = "valorado", nullable = false)
	private Boolean valorado;

	
	//Métodos setters y getters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public boolean isValorado() {
		return valorado;
	}

	public void setValorado(boolean valorado) {
		this.valorado = valorado;
	}

	public int getIdBiblioteca() {
		return idBiblioteca;
	}

	public void setIdBiblioteca(int idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}

	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public Boolean getValorado() {
		return valorado;
	}

	public void setValorado(Boolean valorado) {
		this.valorado = valorado;
	}
}
