package com.servicios;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.component.UIData;
import javax.faces.context.ExternalContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import com.dao.DeportistaDao;
import com.dao.EntrenaDao;
import com.dao.EntrenadorDao;
import com.entidades.Deportista;
import com.entidades.Entrena;
import com.entidades.Entrenador;
import com.utils.SessionUtils;

@Stateless
@LocalBean
public class EntrenaEjb {

	@EJB
	EntrenaDao entrenaDao;
	@EJB
	EntrenadorDao entrenadorDao;
	@EJB
	DeportistaDao deportistaDao;

	
	public void guardarEntrenaEjb(Entrenador selectEntrenador) throws SQLException {
		
		System.out.println("Ejb guardar entrena");
			
//		Entrenador entrenador= (Entrenador) ent.getRowData();
//		Entrenador entrenador = entrenadorDao.obtenerEntrenadorIgual(ent);
		
		String usuario = (String) SessionUtils.getRequest().getSession(false).getAttribute("usuario");
		
		Deportista deportista = deportistaDao.obtenerDeportistaIgual(usuario);
		Date fecha = new Date();
		Long v1= (long) 0;
		Long v2=(long) 0;
		Entrena e= new Entrena(selectEntrenador, deportista, fecha,v1,v2);
		selectEntrenador.getListEntrena().add(e);
		this.entrenaDao.guardarEntrena(e);
		
		

	}

	public void actualizarEntrena(Entrena entrena) throws SQLException {
		this.entrenaDao.actualizarEntrena(entrena);
	}

	public void borrarEntrena(Long idEntrena) throws SQLException {
		entrenaDao.borrarEntrena(idEntrena);
	}

	public Entrena obtenerEntrena(Long idEntrena) {
		return this.entrenaDao.obtenerEntrena(idEntrena);
	}

	public List<Entrena> obternerTodos() {
		return entrenaDao.obternerTodos();
	}
}
