package com.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity

public class Usuario implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_USUARIO")
	
	private Long idUsuario;
	private String nombre;
	private String usuario;
	private String apellido;
	private String contrasena;
	private String perfil;

	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	private String telefono;
	private String mail;
	private String sexo;
	private String lugarNaciemiento;
	private String deporte;
	private String datosProf;
	private Long compPerf;
	
	@ManyToOne
	@JoinColumn(name="ID_DEPORTISTA")
	private Deportista deportista;


	public Usuario() {
		super();
	}


	public Usuario( String nombre, String usuario, String apellido, String contrasena, String perfil,
			Date fechaNacimiento, String telefono, String mail, String sexo, String lugarNaciemiento, String deporte,
			String datosProf, Long compPerf) {
		super();
		this.nombre = nombre;
		this.usuario = usuario;
		this.apellido = apellido;
		this.contrasena = contrasena;
		this.perfil = perfil;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.mail = mail;
		this.sexo = sexo;
		this.lugarNaciemiento = lugarNaciemiento;
		this.deporte = deporte;
		this.datosProf = datosProf;
		this.compPerf = compPerf;
	}


	public Usuario(String nombre, String usuario, String apellido, String contrasena, String perfil,
			Date fechaNacimiento, String telefono, String mail, String sexo, String lugarNaciemiento, String deporte,
			String datosProf, Long compPerf, Deportista deportista) {
		super();
		this.nombre = nombre;
		this.usuario = usuario;
		this.apellido = apellido;
		this.contrasena = contrasena;
		this.perfil = perfil;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.mail = mail;
		this.sexo = sexo;
		this.lugarNaciemiento = lugarNaciemiento;
		this.deporte = deporte;
		this.datosProf = datosProf;
		this.compPerf = compPerf;
		this.deportista = deportista;
	}


	public Long getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getContrasena() {
		return contrasena;
	}


	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}


	public String getPerfil() {
		return perfil;
	}


	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public String getLugarNaciemiento() {
		return lugarNaciemiento;
	}


	public void setLugarNaciemiento(String lugarNaciemiento) {
		this.lugarNaciemiento = lugarNaciemiento;
	}


	public String getDeporte() {
		return deporte;
	}


	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}



	public String getDatosProf() {
		return datosProf;
	}



	public void setDatosProf(String datosProf) {
		this.datosProf = datosProf;
	}



	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public Long getCompPerf() {
		return compPerf;
	}



	public void setCompPerf(Long compPerf) {
		this.compPerf = compPerf;
	}


	public Deportista getDeportista() {
		return deportista;
	}


	public void setDeportista(Deportista deportista) {
		this.deportista = deportista;
	}
	
   
}
