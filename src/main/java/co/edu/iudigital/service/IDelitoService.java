package co.edu.iudigital.service;

import java.util.List;

import co.edu.iudigital.dto.DelitoDTO;

public interface IDelitoService {

	//el nombre de los métodos puede ser "cualquier"
	List<DelitoDTO> findAll();
	
	DelitoDTO findById(Long id);
	
	DelitoDTO save(DelitoDTO delitoDTO);
	
	void delete(Long id);
}
