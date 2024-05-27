package com.veronicaalvarez.api.modelo;

import jakarta.persistence.*;

@Entity
@IdClass(GenerosUsuarioId.class)
@Table(name="generos_usuario")
public class GeneroUsuario {
    @Id
    @Column(name = "id_genero")
    private int idGenero;

    @Id
    @Column(name = "id_usuario")
    private int idUsuario;

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
