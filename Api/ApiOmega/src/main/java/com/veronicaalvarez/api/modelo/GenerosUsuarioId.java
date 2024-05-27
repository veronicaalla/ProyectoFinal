package com.veronicaalvarez.api.modelo;

import java.io.Serializable;
import java.util.Objects;

public class GenerosUsuarioId implements Serializable {
    private int idGenero;
    private int idUsuario;

    // Constructores, getters y setters
    // Es importante que generes correctamente los métodos equals() y hashCode()

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenerosUsuarioId that = (GenerosUsuarioId) o;
        return idGenero == that.idGenero &&
                idUsuario == that.idUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGenero, idUsuario);
    }
}