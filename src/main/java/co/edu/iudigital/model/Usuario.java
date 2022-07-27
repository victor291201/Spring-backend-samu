package co.edu.iudigital.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 9132267639491250466L;

	//id INT NOT NULL AUTO_INCREMENT
	@Id // PRIMARY KEY(id)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    //username VARCHAR(120) NOT NULL,
	@Column(name = "username", 
			length = 120, nullable = false, unique = true)
	private String username;
	
    //nombre VARCHAR(120) NOT NULL,
	@Column(name = "nombre", length = 120, nullable = false)
	private String nombre;
	
    //apellido VARCHAR(120) NULL,
	@Column(name = "apellido", length = 120)
	private String apellido;
    
	//password VARCHAR(250) NULL,
	@Column(name = "password", length = 250)
	private String password;
	
	//fecha_nacimiento DATE NULL,
	@Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
	
	//enabled TINYINT NULL DEFAULT 1
	//@Column(columnDefinition = "NULL DEFAULT 1")
    private Boolean enabled;
	
	//red_social TINYINT NULL DEFAULT 0,
	@Column(name = "red_social")
    private Boolean redSocial;
    
	//image TEXT NULL DEFAULT 'https://happytravel.viajes/wp-content/uploads/2020/04/146-1468479_my-profile-icon-blank-profile-picture-circle-hd.png',
	private String image;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "roles_usuarios", 
			  joinColumns = {
					  @JoinColumn(name = "usuarios_id")
			  },
			  inverseJoinColumns = {
					  @JoinColumn(name = "roles_id")
			  })
	private List<Role> roles;
	
	@PrePersist
	public void persist() {
		if(enabled == null) {
			enabled = true;
		}
		if(redSocial == null) {
			redSocial = false;
		}
		if(image == null || "".equals(image)) {
			image = "https://happytravel.viajes/wp-content/uploads/2020/04/146-1468479_my-profile-icon-blank-profile-picture-circle-hd.png";
		}
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getPassword() {
		return password;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public Boolean getRedSocial() {
		return redSocial;
	}

	public String getImage() {
		return image;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setRedSocial(Boolean redSocial) {
		this.redSocial = redSocial;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
