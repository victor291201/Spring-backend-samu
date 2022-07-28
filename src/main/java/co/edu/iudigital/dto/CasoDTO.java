package co.edu.iudigital.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CasoDTO implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -4779886773654447822L;

	private Long id;

	private LocalDateTime fechaHora;

	private Float latitud;

	private Float longitud;

	private Float altitud;
  
	private Boolean visible;
  
	private String descripcion;

	private String urlMap;
	
	private String rmiUrl;

	private Long usuarioId;
	
	private String nombre;
	
	private String image;

	public Long getId() {
		return id;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public Float getLatitud() {
		return latitud;
	}

	public Float getLongitud() {
		return longitud;
	}

	public Float getAltitud() {
		return altitud;
	}

	public Boolean getVisible() {
		return visible;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getUrlMap() {
		return urlMap;
	}

	public String getRmiUrl() {
		return rmiUrl;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public String getNombre() {
		return nombre;
	}

	public String getImage() {
		return image;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public void setLatitud(Float latitud) {
		this.latitud = latitud;
	}

	public void setLongitud(Float longitud) {
		this.longitud = longitud;
	}

	public void setAltitud(Float altitud) {
		this.altitud = altitud;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setUrlMap(String urlMap) {
		this.urlMap = urlMap;
	}

	public void setRmiUrl(String rmiUrl) {
		this.rmiUrl = rmiUrl;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
