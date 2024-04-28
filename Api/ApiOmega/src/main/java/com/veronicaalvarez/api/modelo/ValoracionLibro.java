package com.veronicaalvarez.api.modelo;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="valoracion_libros")
public class ValoracionLibro {

	@Id
    @Column(name = "id_libro", nullable = false)
    private Integer id;
	
	//@Id
	@MapsId
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_libro", nullable = false)
	@PrimaryKeyJoinColumn
	private Libro libro;
	
	@ColumnDefault("0")
	@Column(name = "valoracion_total", nullable = false)
    private Double valoracion_total;

	
	//Constructor
	public ValoracionLibro() {
		
	}


	public ValoracionLibro(Libro libro, double valoracion_total) {
		super();
		this.libro = libro;
		this.valoracion_total = valoracion_total;
	}


	//Métodos setters y getters
	public Libro getLibro() {
		return libro;
	}


	public void setLibro(Libro libro) {
		this.libro = libro;
	}


	public double getValoracion_total() {
		return valoracion_total;
	}


	public void setValoracion_total(double valoracion_total) {
		this.valoracion_total = valoracion_total;
	}
	
	
}
