package co.edu.iudigital.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.iudigital.exception.ErrorDto;
import co.edu.iudigital.exception.InternalServerErrorException;
import co.edu.iudigital.exception.RestException;
import co.edu.iudigital.model.Role;
import co.edu.iudigital.model.Usuario;
import co.edu.iudigital.repository.IUsuarioRepository;
import co.edu.iudigital.service.IUsuarioService;
import co.edu.iudigital.util.ConstUtil;

@Service
public class UsuarioServiceImpl implements UserDetailsService, IUsuarioService{
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Override
	@Transactional(readOnly = true)// por ser consulta, readOnly
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username);
		if(usuario == null) {
			log.error("Error de login, no existe usuario: "+ usuario);
			throw new UsernameNotFoundException("Error de login, no existe usuario: "+ username);
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(Role role: usuario.getRoles()) {
			GrantedAuthority authority = new SimpleGrantedAuthority(role.getNombre());
			System.out.println("Rol: " + authority.getAuthority());
			authorities.add(authority);	
		}					
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,authorities);
	}

	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) throws RestException{
		return usuarioRepository.findById(id).get();
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) throws RestException{
    	if(usuario == null) {
    		throw new InternalServerErrorException(ErrorDto.getErrorDto(HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    ConstUtil.MESSAGE_ERROR_DATA,
                    HttpStatus.INTERNAL_SERVER_ERROR.value()));
    	}
    	if(usuario.getId() != null) {
    		Boolean exists = usuarioRepository.existsById(usuario.getId());
    		if(exists) {
    			return usuarioRepository.save(usuario);
    		}
    	}
    	List<Role> roles = new ArrayList<>();
    	Role role = new Role();
    	role.setId(2L);
    	roles.add(role);
    	usuario.setRoles(roles);
		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		return usuarioRepository.findByUsername(username);
	}
}
