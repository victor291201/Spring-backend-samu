package co.edu.iudigital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.iudigital.model.Role;
import co.edu.iudigital.repository.IRoleRepository;
import co.edu.iudigital.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{

	@Autowired // inyección de dependencias
	private IRoleRepository roleRepository;
	
	@Override
	public List<Role> getAll() {
		return roleRepository.findAll();
	}

}
