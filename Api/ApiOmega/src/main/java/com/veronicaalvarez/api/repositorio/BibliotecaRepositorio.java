package com.veronicaalvarez.api.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.veronicaalvarez.api.modelo.Biblioteca;

public interface BibliotecaRepositorio extends JpaRepository<Biblioteca, Integer> {
    List<Biblioteca> findByUsuario_Id(int usuarioId);
}
