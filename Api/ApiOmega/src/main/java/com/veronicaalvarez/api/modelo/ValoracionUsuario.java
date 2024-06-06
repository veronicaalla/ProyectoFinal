package com.veronicaalvarez.api.modelo;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name="valoraciones_usuarios")
public class ValoracionUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "id_libro", referencedColumnName = "id", foreignKey = @ForeignKey(name = "valoraciones_usuarios_ibfk_2"))
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Libro libro;


	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "id_usuario", referencedColumnName = "id", foreignKey = @ForeignKey(name = "valoraciones_usuarios_ibfk_2"))
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Usuario usuario;


	@Column(name = "play_list", length = 100)
	private String playList;

	@Column(name = "personaje_fav", length = 45)
	private String personajeFav;

	@Column(name = "personaje_odiado", length = 45)
	private String personajeOdiado;


	@Column(name = "recomendacion", nullable = false)
	private Boolean recomendacion;

	@Column(name = "frase_iconica", length = 200)
	private String fraseIconica;

	@Column(name = "opinion", length = 100)
	private String opinion;

	@Column(name = "puntuacion", nullable = false)
	private Double puntuacion;

	//@ColumnDefault("CURRENT_TIMESTAMP")
	@Column(name = "fecha_valoracion")
	private LocalDateTime fechaValoracion;



	//Métodos Setters y Getters
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

	public String getPlayList() {
		return playList;
	}

	public void setPlayList(String playList) {
		this.playList = playList;
	}

	public String getPersonajeFav() {
		return personajeFav;
	}

	public void setPersonajeFav(String personajeFav) {
		this.personajeFav = personajeFav;
	}

	public String getPersonajeOdiado() {
		return personajeOdiado;
	}

	public void setPersonajeOdiado(String personajeOdiado) {
		this.personajeOdiado = personajeOdiado;
	}

	public Boolean getRecomendacion() {
		return recomendacion;
	}

	public void setRecomendacion(Boolean recomendacion) {
		this.recomendacion = recomendacion;
	}

	public String getFraseIconica() {
		return fraseIconica;
	}

	public void setFraseIconica(String fraseIconica) {
		this.fraseIconica = fraseIconica;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Double puntuacion) {
		this.puntuacion = puntuacion;
	}

	public LocalDateTime getFechaValoracion() {
		return fechaValoracion;
	}

	public void setFechaValoracion(LocalDateTime fechaValoracion) {
		this.fechaValoracion = fechaValoracion;
	}
}
