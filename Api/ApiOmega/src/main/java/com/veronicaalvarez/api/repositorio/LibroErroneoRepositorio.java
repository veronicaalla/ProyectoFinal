package com.veronicaalvarez.api.repositorio;

import com.veronicaalvarez.api.modelo.LibroErroneo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroErroneoRepositorio extends JpaRepository<LibroErroneo, Integer> {
    List<LibroErroneo> findByResueltoIsNull();
}
