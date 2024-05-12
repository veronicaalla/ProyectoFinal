package com.veronicaalvarez.api.repositorio;

import com.veronicaalvarez.api.modelo.ValoracionLibro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ValoracionLibroRepository extends JpaRepository<ValoracionLibro, Integer> {
    Optional<ValoracionLibro> findByIdLibro(int idLibro);
}
