package com.veronicaalvarez.api.modelo;

import java.time.LocalDateTime;

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
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "id_libro", referencedColumnName = "id", foreignKey = @ForeignKey(name="valoraciones_usuarios_ibfk_2"))
	private Libro libro;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "id_usuario", referencedColumnName = "id", foreignKey = @ForeignKey(name = "valoraciones_usuarios_ibfk_2"))
	private Usuario usuario;
	
	
	@Column(name = "playList", length = 100)
    private String playList;

    @Column(name = "personaje_fav", length = 45)
    private String personajeFav;

    @Column(name = "personaje_odiado", length = 45)
    private String personajeOdiado;

    @ColumnDefault("1")
    @Column(name = "recomendacion", nullable = false)
    private Boolean recomendacion;

    @Column(name = "frase_iconica", length = 45)
    private String fraseIconica;

    @Column(name = "opinion", length = 100)
    private String opinion;

    @Column(name = "puntuacion", nullable = false)
    private Double puntuacion;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fechaValoracion")
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

	public String getPersonaje_fav() {
		return personajeFav;
	}

	public void setPersonaje_fav(String personaje_fav) {
		this.personajeFav = personaje_fav;
	}

	public String getPersonaje_odiado() {
		return personajeOdiado;
	}

	public void setPersonaje_odiado(String personaje_odiado) {
		this.personajeOdiado = personaje_odiado;
	}

	public Boolean getRecomendacion() {
		return recomendacion;
	}

	public void setRecomendacion(Boolean recomendacion) {
		this.recomendacion = recomendacion;
	}

	public String getFrase_iconica() {
		return fraseIconica;
	}

	public void setFrase_iconica(String fraseIconica) {
		this.fraseIconica = fraseIconica;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}

	public LocalDateTime getFechaValoracion() {
		return fechaValoracion;
	}

	public void setFechaValoracion(LocalDateTime fechaValoracion) {
		this.fechaValoracion = fechaValoracion;
	}
	
	
}
