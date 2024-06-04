package com.veronicaalvarez.api.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.veronicaalvarez.api.modelo.Biblioteca;

public interface BibliotecaRepositorio extends JpaRepository<Biblioteca, Integer> {

    /**
     * Busca bibliotecas asociadas con un usuario específico por su ID.
     * 
     * @param usuarioId ID del usuario asociado a las bibliotecas
     * @return Lista de bibliotecas asociadas con el usuario
     */
    List<Biblioteca> findByUsuario_Id(int usuarioId);
}
