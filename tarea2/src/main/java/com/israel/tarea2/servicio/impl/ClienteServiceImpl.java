package com.israel.tarea2.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.israel.tarea2.modelo.ClienteModelo;
import com.israel.tarea2.repositorio.ClienteRepositorio;
import com.israel.tarea2.servicio.ClienteServicio;

@Service
public class ClienteServiceImpl implements ClienteServicio{
	@Autowired
	private ClienteRepositorio clienteRep;

	@Override
	public void insertar(ClienteModelo Cliente) {
		// TODO Auto-generated method stub
		try {
			clienteRep.save(Cliente);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public List<ClienteModelo> listar() {
		// TODO Auto-generated method stub
		return clienteRep.findAll();
	}

	@Override
	public ClienteModelo buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return clienteRep.findById(id).get();
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		try {
			clienteRep.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
			
		}
		
	}

}
