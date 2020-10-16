package com.bean;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;


import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;

import com.entidades.Deportista;
import com.entidades.Entrenador;
import com.entidades.Usuario;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.servicios.DeportistaEjb;
import com.servicios.EntrenadorEjb;
import com.servicios.UsuarioEjb;

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
	
	@EJB
	DeportistaEjb deportistaEjb;
	
	@EJB
	EntrenadorEjb entrenadorEjb;
	
	private Usuario usu;
	private Deportista deportista;
	private String perfil;
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
	private String titulo ="Inicie seccion en SGE";
	private String colorLinkTit="color:grey;";
	private String subTitulo=" Bienvenido, porfavor ingrese con su cuenta de google.";
	private Boolean bolSubTitulo=true;
	private Boolean btnRegistrar=true;
	private Boolean btnAcceso1= true;
	private Boolean btnAcceso2= false;
	private String colorLinkReg="color:#98BED7;";
	private String colorLinkLog="color:white;";
	private Boolean bolPanelReg=false;
	private Boolean bolPanelLog=true;
	private Boolean alert=false;

	private Boolean btnLogActiv=false;
	private Boolean btnRegActiv=false;

	private String mail;


public LoginUsu() {
		
	}


public String increment() throws FirebaseException, IOException   {
	
	
	
	System.out.println("entro incr");
	String pUsu=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pUsu");
//	fireBase(pUsu);
	System.out.println("entro "+pUsu);
	String str=null;
	List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
	
	System.out.println("entro 33"+firebaseApps.size());
	if(firebaseApps.size()==0) {
	FileInputStream serviceAccount = new FileInputStream("C:/Users/Administrador/Downloads/aug-bot-firebase-adminsdk-ooa9a-d0f75f6b4c.json");
	
		
		System.out.println("if build ");
		
		 FirebaseOptions builder = FirebaseOptions.builder()
		.setCredentials(GoogleCredentials.fromStream(serviceAccount))
		.setDatabaseUrl("https://aug-bot.firebaseio.com")
		.build();


	FirebaseApp.initializeApp(builder);
	}else {
		System.out.println("no if buid");
	}
	
//	idToken comes from the client app (shown above)
	FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(pUsu);

	String uid = decodedToken.getUid();
	System.out.println("token:  "+uid);
	UserRecord userRecord = FirebaseAuth.getInstance().getUser(uid);
	// See the UserRecord reference doc for the contents of userRecord.
	System.out.println("Successfully fetched user data: " + userRecord.getEmail());
	
	   mail= userRecord.getEmail();
	  if(mail.length()>0) {
		  
		  System.out.println("if mail");
		  btnRegistrar=false;
		  
	  }else {
		  btnRegistrar=true;
		  
	  }
	  if(btnLogActiv) {
		  System.out.println("btnLogActiv metodo incrm");
	try {
		str= usuarioEjb.login(mail);
		System.out.println(" Metodo login2" +str);
		if(str.equals("login2")) {
			
			System.out.println("Entro if login2");
			mostMsjGrowl("El usuario no esta registrado");
			alert=true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No se logeo correctamente. Vuelva a intentar", ""));
		e.printStackTrace();
	}
	  }else {
		
	  }
	  
	return str;
	
	
	}

public String btnRegistrar() {
	String str="login2";
	str=usuarioEjb.registarUsu(perfil, mail);
	
	return str;
	
}

  public void btnLogActiv() {
	  System.out.println("btnLogActiv");
	  btnLogActiv=true;
  }
  
  public void btnRegActiv() {
	  System.out.println("btnRegActiv");
	  btnRegActiv=true;
  }
//public String increment()  {
//	  
//	System.out.println("entro incr");
//	String pUsu=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pUsu");
////	fireBase(pUsu);
//	System.out.println("entro "+pUsu);
//	String str=null;
//	  
////	   return "deportista/homeDep.xhtml";
//	try {
//		str= usuarioEjb.login(pUsu);
//		System.out.println(" login2" +str);
//		if(str.equals("login2")) {
//			
//			System.out.println("Entro if login2");
//			mostMsjGrowl("No se encuentra registrado en la aplicacion");
//			mostMsj("No se encuentra registrado en la aplicacion");
//			val="No se encuentra registrado en la aplicacion";
//		}
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		
//		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No se logeo correctamente. Vuelva a intentar", ""));
//		e.printStackTrace();
//	}
//
//	return str;
//  }
  
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
	public void accesoApp() {
		
		System.out.println("entro incr");
		String pUsu=FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pUsu");
		System.out.println("entro "+pUsu);
		String str=null;
		
		
	}
  
	public void linkRegistro() {
		System.out.println("linkRegistro");
		
	
		bolPanelLog=false;
		bolPanelReg=true;
		bolSubTitulo=false;
		  titulo ="Registrece en SGE";
		  colorLinkTit="color:#20711f;";
		  subTitulo=" Bienvenido, porfavor ingrese con su cuenta de google y seleccione un perfil.";
		
		 
		 btnAcceso1= false;
		 btnAcceso2= true;
		colorLinkReg ="color:white;";
		colorLinkLog="color:#98BED7;";
	
	}
	
	public void linkLogin() {
		
		System.out.println("linkLogin");
		
		bolPanelLog=true;
		bolPanelReg=false;
		bolSubTitulo=true;
		 titulo ="Inicie seccion en SGE";
		 colorLinkTit="color:grey;";
		 subTitulo=" Bienvenido, porfavor ingrese con su cuenta de google.";
		 
		 btnAcceso1= true;
		 btnAcceso2= false;
		 colorLinkReg="color:#98BED7;";
		 colorLinkLog="color:white;";
	}
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
	

	public void mostMsjGrowl(String msj) {
		FacesContext.getCurrentInstance().addMessage("growl",
				new FacesMessage(FacesMessage.SEVERITY_INFO, msj, ""));
		
		
	}
	
	public void mostMsjIncorr() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario o Constraseña incorrecta", ""));
	}
	
	public void mostMsj(String msj) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, msj, ""));
		
		
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

	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
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


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getSubTitulo() {
		return subTitulo;
	}


	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}





	public Boolean getBtnRegistrar() {
		return btnRegistrar;
	}


	public void setBtnRegistrar(Boolean btnRegistrar) {
		this.btnRegistrar = btnRegistrar;
	}


	public Boolean getBtnAcceso2() {
		return btnAcceso2;
	}


	public void setBtnAcceso2(Boolean btnAcceso2) {
		this.btnAcceso2 = btnAcceso2;
	}


	public Boolean getBtnAcceso1() {
		return btnAcceso1;
	}


	public void setBtnAcceso1(Boolean btnAcceso1) {
		this.btnAcceso1 = btnAcceso1;
	}








	public String getColorLinkLog() {
		return colorLinkLog;
	}


	public void setColorLinkLog(String colorLinkLog) {
		this.colorLinkLog = colorLinkLog;
	}


	public String getColorLinkReg() {
		return colorLinkReg;
	}


	public void setColorLinkReg(String colorLinkReg) {
		this.colorLinkReg = colorLinkReg;
	}


	public String getColorLinkTit() {
		return colorLinkTit;
	}


	public void setColorLinkTit(String colorLinkTit) {
		this.colorLinkTit = colorLinkTit;
	}


	public Boolean getBolPanelReg() {
		return bolPanelReg;
	}


	public void setBolPanelReg(Boolean bolPanelReg) {
		this.bolPanelReg = bolPanelReg;
	}


	public Boolean getBolPanelLog() {
		return bolPanelLog;
	}


	public void setBolPanelLog(Boolean bolPanelLog) {
		this.bolPanelLog = bolPanelLog;
	}


	public String getPerfil() {
		return perfil;
	}


	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}


	public Boolean getBtnLogActiv() {
		return btnLogActiv;
	}


	public void setBtnLogActiv(Boolean btnLogActiv) {
		this.btnLogActiv = btnLogActiv;
	}


	public Boolean getBtnRegActiv() {
		return btnRegActiv;
	}


	public void setBtnRegActiv(Boolean btnRegActiv) {
		this.btnRegActiv = btnRegActiv;
	}


	public Boolean getBolSubTitulo() {
		return bolSubTitulo;
	}


	public void setBolSubTitulo(Boolean bolSubTitulo) {
		this.bolSubTitulo = bolSubTitulo;
	}


	public Boolean getAlert() {
		return alert;
	}


	public void setAlert(Boolean alert) {
		this.alert = alert;
	}

	
	
}

