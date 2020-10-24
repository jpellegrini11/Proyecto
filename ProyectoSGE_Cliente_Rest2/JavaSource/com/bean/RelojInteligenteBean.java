package com.bean;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.servicios.RelojInteligenteEJB;

import java.io.Serializable;

@Named("reloj")
@ViewScoped
public class RelojInteligente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	RelojInteligenteEJB relojInteligenteEJB;
	
	
	public void sincronizarRelojBean() {
		
		relojInteligenteEJB.sincronizarRelojEJB();
	}

}
