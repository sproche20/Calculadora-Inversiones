package com.israel.tarea2.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.israel.tarea2.modelo.InversionModelo;

@Repository
public interface InversionRepositorio  extends JpaRepository<InversionModelo,Long>{
	public Optional<InversionModelo>findById(Long id);
}
