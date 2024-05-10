package com.veronicaalvarez.api.modelo;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="valoracion_libros")
public class ValoracionLibro {

	@Id
    @Column(name = "id_libro", nullable = false)
    private int idLibro;

	
	@ColumnDefault("0")
	@Column(name = "valoracion_total", nullable = false)
    private Double valoracion_total;

	//Métodos setters y getters
	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public Double getValoracion_total() {
		return valoracion_total;
	}

	public void setValoracion_total(Double valoracion_total) {
		this.valoracion_total = valoracion_total;
	}
}
