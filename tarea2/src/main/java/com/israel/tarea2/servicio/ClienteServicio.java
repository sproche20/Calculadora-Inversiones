package com.israel.tarea2.servicio;

import java.util.List;

import com.israel.tarea2.modelo.ClienteModelo;

public interface ClienteServicio {
	public void insertar(ClienteModelo Cliente );
	public List<ClienteModelo>listar();
	public ClienteModelo buscarPorId(Long id);
	public void eliminar(Long id);
}
