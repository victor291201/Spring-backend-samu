package co.edu.iudigital.service;

import java.util.List;

import co.edu.iudigital.exception.RestException;
import co.edu.iudigital.model.Usuario;

public interface IUsuarioService {

	public List<Usuario> findAll() throws RestException;
	
	public Usuario findById(Long id) throws RestException;
	
	public Usuario save(Usuario usuario) throws RestException;
	
	public Usuario findByUsername(String username);
}
