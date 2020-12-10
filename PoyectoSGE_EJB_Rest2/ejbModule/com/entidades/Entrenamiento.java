package com.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.utils.StringListConverter;


@Entity

public class Entrenamiento implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_ENTRENAMIENTO")
	
	private Long idEntrenamiento;
	private String nombre;
	private String	deporte;
	
	private Long tiempoRealizar;
	
	@Convert(converter = StringListConverter.class)
	private List<String> etiquetas;
	
	@OneToMany (mappedBy = "entrenamiento")
	private List<Ejercicios> listEjer;
	
	@ManyToOne
	@JoinColumn(name="ID_ENTRENADOR")
	private Entrenador entrenador;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@ManyToOne
	@JoinColumn(name="ID_ASIGNARENTO")
	private AsignarEnto asignarEnto;
	
	@OneToMany (mappedBy = "entrenamiento")
	private List<EntEjer> listEntEjer;
	
	
	
	public Entrenamiento(){
		
	}
		
	
	public Entrenamiento(String nombre, String deporte, Long tiempoRealizar, List<String> etiquetas,
			Entrenador entrenador, Date fecha) {
		super();
		this.nombre = nombre;
		this.deporte = deporte;
		this.tiempoRealizar = tiempoRealizar;
		this.etiquetas = etiquetas;
	
		this.entrenador = entrenador;
		this.fecha = fecha;
	}


	public Long getIdEntrenamiento() {
		return idEntrenamiento;
	}

	public void setIdEntrenamiento(Long idEntrenamiento) {
		this.idEntrenamiento = idEntrenamiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDeporte() {
		return deporte;
	}

	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}

	public Long getTiempoRealizar() {
		return tiempoRealizar;
	}

	public void setTiempoRealizar(Long tiempoRealizar) {
		this.tiempoRealizar = tiempoRealizar;
	}





	public List<Ejercicios> getListEjer() {
		return listEjer;
	}

	public void setListEjer(List<Ejercicios> listEjer) {
		this.listEjer = listEjer;
	}

	public Entrenador getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}

	public List<String> getEtiquetas() {
		return etiquetas;
	}

	public void setEtiquetas(List<String> etiquetas) {
		this.etiquetas = etiquetas;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public AsignarEnto getAsignarEnto() {
		return asignarEnto;
	}


	public void setAsignarEnto(AsignarEnto asignarEnto) {
		this.asignarEnto = asignarEnto;
	}


	public List<EntEjer> getListEntEjer() {
		return listEntEjer;
	}


	public void setListEntEjer(List<EntEjer> listEntEjer) {
		this.listEntEjer = listEntEjer;
	}

	

   
}
