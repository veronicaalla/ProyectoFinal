package com.veronicaalvarez.api.modelo;

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
@Table(name = "bibliotecas")
public class Biblioteca {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	//@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "id_usuario", nullable = false)
	private int id_usuario;
	
	
	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;
	
	
	@ColumnDefault("0")
    @Column(name = "publica", nullable = false)
	private Boolean publica; 



	//Métodos Setter y Getters
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUsuario() {
		return id_usuario;
	}


	public void setUsuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Boolean getPublica() {
		return publica;
	}


	public void setPublica(Boolean publica) {
		this.publica = publica;
	}
	
	
	
	
	
}
