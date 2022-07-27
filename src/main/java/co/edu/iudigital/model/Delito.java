package co.edu.iudigital.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "delitos")
public class Delito implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 6225803848687364814L;

	//id INT NOT NULL AUTO_INCREMENT
	//PRIMARY KEY (id)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    //nombre VARCHAR(45) NOT NULL
	@Column(name = "nombre", length = 45, nullable = false)
	private String nombre;
	
    //descripcion TEXT NULL
	private String descripcion;
    	
	
	//usuarios_id INT NOT NULL
    //FOREIGN KEY (usuarios_id) REFERENCES usuarios(id)
	@ManyToOne(cascade = CascadeType.ALL) // por default: fetch = EAGER
	@JoinColumn(name = "usuarios_id")
	private Usuario usuario;

	public Long getId() {
		return id;
	}



	public String getNombre() {
		return nombre;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
