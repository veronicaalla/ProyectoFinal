package com.veronicaalvarez.api.modelo;

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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="generos")
public class Genero {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

	
	//Metodos getter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
