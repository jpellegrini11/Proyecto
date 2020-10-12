package com.bean;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

//import org.primefaces.PrimeFaces;

import com.entidades.Deportista;
import com.entidades.Usuario;
import com.servicios.UsuarioEjb;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


@Named("login")
@ManagedBean
@SessionScoped

public class LoginUsu implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UsuarioEjb usuarioEjb;
	
	private Usuario usu;
	private Deportista deportista;
	private String visibleStr;
	private String msj;
	private String usuarioL;
	private String contrasenaL;
	private String visStr1;
	private String visStr2;
	private String visStr3;
	private List<UsuarioEjb> usuListL;
	private Long compForm;
	private String val;
	


public LoginUsu() {
		
	}
  
  public String increment()   {
	System.out.println("entro incr");
	String pUsu=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pUsu");
	System.out.println("entro "+pUsu);
	String str=null;
	  
//	   return "deportista/homeDep.xhtml";
	try {
		str= usuarioEjb.login(pUsu);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No se logeo correctamente. Vuelva a intentar", ""));
		e.printStackTrace();
	}
	return str;
  }
  
  public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}
  
//  public String loginUsu() throws Exception {
//	 String u=usuarioL;
//	 String  c=contrasenaL;
////	 PrimeFaces.current().executeScript("alert('peek-a-boo');");
//	
//	 System.out.println(FacesContext.getCurrentInstance().getPartialViewContext().toString());
//
//	  return usuarioEjb.login(u,c);
//  }

  
	public void perfilUsu(String perfilUsu) {
		
		if(perfilUsu.equals("DEPORTISTA")) {
			System.out.println("entro DEP");
			setVisStr1("none");
			setVisStr2("");
			setVisStr3("none");
			
			
		}
		if(perfilUsu.equals("ENTRENADOR")) {
			System.out.println("entro ENTR");
			visStr1="";
			visStr2="";
			visStr3="";
		}

	}
	
//	public void buscarU() throws  Exception,  IOException  {
//		System.out.println("entro incr");
//	
//	    
//		
//	    String usuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pUsu");
//	    String perfil = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pBuscar"); 
//	    
//
//	    
////		Map<String, String> params=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
////		
////		String perfil=params.get("buscar");
////		String usuario=params.get("usu");
//		System.out.println("entro "+perfil);
//		System.out.println("entro "+usuario);
//					 
//		 if(usuarioEjb.login(usuario,perfil)){
//			 FacesContext.getCurrentInstance().getExternalContext().setResponseHeader("phUsu", "1");
//		 }
//			  
//	}
	
	public void  signOut() {
		System.out.println("login singOut ");
		try {
			usuarioEjb.logout();
		} catch (IOException e) {
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No se realizo correctamente el logout. Vuelva a intentar", ""));
			e.printStackTrace();
		}
		
	}
	

	
	
	public Usuario getUsu() {
	return usu;
}

public void setUsu(Usuario usu) {
	this.usu = usu;
}

	public Deportista getDeportista() {
	return deportista;
}

public void setDeportista(Deportista deportista) {
	this.deportista = deportista;
}

	public String getVisibleStr() {
		return visibleStr;
	}

	public void setVisibleStr(String visibleStr) {
		this.visibleStr = visibleStr;
	}

	public String getMsj() {
		return msj;
	}

	public void setMsj(String msj) {
		this.msj = msj;
	}


	public String getUsuarioL() {
		return usuarioL;
	}

	public void setUsuarioL(String usuarioL) {
		this.usuarioL = usuarioL;
	}

	public String getContrasenaL() {
		return contrasenaL;
	}

	public void setContrasenaL(String contrasenaL) {
		this.contrasenaL = contrasenaL;
	}

	public String getVisStr1() {
		return visStr1;
	}

	public void setVisStr1(String visStr1) {
		this.visStr1 = visStr1;
	}

	public String getVisStr2() {
		return visStr2;
	}

	public void setVisStr2(String visStr2) {
		this.visStr2 = visStr2;
	}

	public String getVisStr3() {
		return visStr3;
	}

	public void setVisStr3(String visStr3) {
		this.visStr3 = visStr3;
	}

	public List<UsuarioEjb> getUsuListL() {
		return usuListL;
	}

	public void setUsuListL(List<UsuarioEjb> usuListL) {
		this.usuListL = usuListL;
	}

	public Long getCompForm() {
		return compForm;
	}

	public void setCompForm(Long compForm) {
		this.compForm = compForm;
	}

	
	
}

