package co.edu.iudigital.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "delitos")
public class Delito implements Serializable{

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
	// TODO: colocar la asociación & getteres y setteres
}
