package co.edu.iudigital.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -2928234787085757170L;

	//id INT NOT NULL AUTO_INCREMENT
	@Id // PRIMARY KEY(id)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    //nombre VARCHAR(45) NOT NULL
	@Column(name = "nombre", length = 45, nullable = false)
    private String nombre;
	
	//descripcion TEXT NULL
    private String descripcion;
    
    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios;

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
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

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
}
