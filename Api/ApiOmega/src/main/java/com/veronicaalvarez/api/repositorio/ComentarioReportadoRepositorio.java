package com.veronicaalvarez.api.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.veronicaalvarez.api.modelo.ComentarioReportado;

public interface ComentarioReportadoRepositorio extends JpaRepository <ComentarioReportado, Integer>{
	List<ComentarioReportado> findByOfensivoIsNull();

}
