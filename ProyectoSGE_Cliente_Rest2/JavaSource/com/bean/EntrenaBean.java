package com.bean;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.entidades.Entrena;
import com.entidades.Entrenador;
import com.servicios.EntrenaEjb;
import com.servicios.EntrenadorEjb;
import com.utils.SessionUtils;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named("entrena")
@ManagedBean
@SessionScoped
public class EntrenaBean implements Serializable {


	private static final long serialVersionUID = 1L;

	
	
		@EJB
		EntrenaEjb entrenaEjb;
		@EJB
		EntrenadorEjb entrenadorEjb;
		
		
		private UIData entSelect;
		private Entrenador selectEntrenador;
		private List<Entrena> listEntrena;
		

		public void seleccionarEntBean() {
		
			Boolean bolEnt= false;
			try {
				System.out.println("entro try entrena");
				String usuario = (String) SessionUtils.getRequest().getSession(false).getAttribute("usuario");
				
				listEntrena= selectEntrenador.getListEntrena();
				for (Entrena entrena : listEntrena) {
					
					if(entrena.getDeportista().getUsuario().equals(usuario)) {
						
						bolEnt=true;
					}
				}
			if(bolEnt) {
				mostMsjGrowl("No puede vincularce mas de 1 vez, con un entrenador ");
			}else {
				
				entrenaEjb.guardarEntrenaEjb(selectEntrenador);
				mostMsjGrowl("Felicitaciones tiene un entrenador nuevo");
			}
				
				
				} catch (SQLException e) {
				mostMsj("No se logro selccionar entrenador. Intente mas tarde nuevamente");
				e.printStackTrace();
			}
			
		}
		public void mostMsjGrowl(String msj) {
			FacesContext.getCurrentInstance().addMessage("growl",
					new FacesMessage(FacesMessage.SEVERITY_INFO, msj, ""));
			
			
		}
		
		public void mostMsj(String msj) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msj, ""));
	}
		
		public UIData getEntSelect() {
			return entSelect;
		}
		public void setEntSelect(UIData entSelect) {
			this.entSelect = entSelect;
		}
		public Entrenador getSelectEntrenador() {
			return selectEntrenador;
		}
		public void setSelectEntrenador(Entrenador selectEntrenador) {
			this.selectEntrenador = selectEntrenador;
		}
		public List<Entrena> getListEntrena() {
			return listEntrena;
		}
		public void setListEntrena(List<Entrena> listEntrena) {
			this.listEntrena = listEntrena;
		}
}
