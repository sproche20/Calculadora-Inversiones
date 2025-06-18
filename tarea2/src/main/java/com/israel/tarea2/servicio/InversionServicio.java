package com.israel.tarea2.servicio;

import java.util.List;

import com.israel.tarea2.modelo.InversionModelo;


public interface InversionServicio {
	public void insertar(InversionModelo nuevaInversion);
	public List<InversionModelo>listar();
	public InversionModelo buscarPorId(Long id);
	public void eliminar(Long id);
}
