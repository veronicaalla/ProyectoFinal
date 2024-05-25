package com.veronicaalvarez.api.repositorio;

import com.veronicaalvarez.api.modelo.Genero;
import com.veronicaalvarez.api.modelo.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Grupo, Integer> {

}
