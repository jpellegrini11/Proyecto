package com.servicios;

import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.dao.ResultadosEntoDao;
import com.entidades.ResultadosEnto;

@Stateless
@LocalBean
public class ResultadosEntoEjb {
	
	@EJB
	ResultadosEntoDao resultadosEntoDao;
	private boolean bool;
	
	
	public Boolean guardarResultadosEntoEJB(ResultadosEnto resultadosEnto) {
		bool=false;
	try {
		resultadosEntoDao.guardarResultadosEnto(resultadosEnto);
		
		bool=true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return bool;
	}
	
	
	
	public void mostMsjGrowl(String msj) {
		FacesContext.getCurrentInstance().addMessage("growl",
				new FacesMessage(FacesMessage.SEVERITY_INFO, msj, ""));
		
		
	}
}
