package com.israel.tarea2.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.israel.tarea2.modelo.ClienteModelo;

@Repository
public interface ClienteRepositorio  extends JpaRepository<ClienteModelo,Long>{

}
