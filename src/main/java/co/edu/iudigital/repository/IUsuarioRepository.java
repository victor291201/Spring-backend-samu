package co.edu.iudigital.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.iudigital.model.Usuario;

@Repository
public interface IUsuarioRepository 
	extends CrudRepository<Usuario, Long>{
	
	//@Query("SELECT u FROM Usuario u WHERE u.username = ?1")
	// con @Query es otra forma para hacer la siguiente
	// consulta
	Usuario findByUsername(String username);
}
