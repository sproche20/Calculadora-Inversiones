package com.israel.tarea2.servicio.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.israel.tarea2.modelo.InversionModelo;
import com.israel.tarea2.repositorio.InversionRepositorio;
import com.israel.tarea2.servicio.InversionServicio;

import jakarta.transaction.Transactional;

@Service
public class InversionServiceImpl implements InversionServicio{
	@Autowired
	public InversionRepositorio inversionRep;
	@Override
	public void insertar(InversionModelo nuevaInversion) {
		try {
			inversionRep.save(nuevaInversion);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	@Override
	@Transactional
	public List<InversionModelo> listar() {
		List<InversionModelo>inversion=inversionRep.findAll();
		for (InversionModelo inversiones:inversion) {
			if (inversiones.getFkCliente()!=null) {
				Hibernate.initialize(inversiones.getFkCliente());
			}
		}
		return inversion;
	}
	@Override
	public InversionModelo buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return inversionRep.findById(id).get();
	}
	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		try {
			inversionRep.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace(); 
		}
	}
}
