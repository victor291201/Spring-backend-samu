package co.edu.iudigital.service;

import java.util.List;

import co.edu.iudigital.dto.CasoDTO;
import co.edu.iudigital.exception.RestException;

public interface ICasoService {

	// consulta todos los casos
	List<CasoDTO> findAll() throws RestException ;
	
	// crear un caso
	CasoDTO save(CasoDTO caso) throws RestException ;
	
	// inactivar el caso
	Boolean visible(Boolean visible, Long id);
	
	// consultar caso por Id
	CasoDTO findById(Long id) throws RestException ;
}
