package com.veronicaalvarez.api.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veronicaalvarez.api.modelo.Genero;

import java.util.Optional;

public interface GeneroRepositorio extends JpaRepository<Genero, Integer>{
    Genero findByNombre(String nombre);
}
