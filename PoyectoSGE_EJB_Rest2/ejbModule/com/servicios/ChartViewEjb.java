package com.servicios;

import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.dao.DeportistaDao;
import com.dao.EntrenaDao;
import com.dao.EntrenadorDao;
import com.entidades.Entrenador;

@Stateless
@LocalBean
public class ChartViewEjb {

	@EJB
	EntrenaDao entrenaDao;
	@EJB
	EntrenadorDao entrenadorDao;
	@EJB
	DeportistaDao deportistaDao;
	
	public void guardarEntrenaEjb(Entrenador selectEntrenador) throws SQLException {
		
		System.out.println("Ejb guardar entrena");
		
	}
}
