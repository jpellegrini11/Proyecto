package com.servicios;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.dao.EntEjerDao;
import com.entidades.EntEjer;

@Stateless
@LocalBean
public class EntEjerEjb {
	

@EJB
EntEjerDao entEjerDao;

public  EntEjerEjb() {
	
}
public List<EntEjer> obtenerTodosEntEjerEjb(Long idEjer) throws SQLException {
	
	return entEjerDao.obtenerPorIdEjer(idEjer);
	
}

public void guardarEntEjerEjb(EntEjer entEjer) throws SQLException {
	
	entEjerDao.guardarEntEjer(entEjer);
	}
}
