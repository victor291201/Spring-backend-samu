package co.edu.iudigital.service;

import java.util.List;

import co.edu.iudigital.dto.CasoDTO;

public interface ICasoService {

	// consulta todos los casos
	List<CasoDTO> findAll();
	
	// crear un caso
	CasoDTO save(CasoDTO caso);
	
	// inactivar el caso
	Boolean visible(Boolean visible, Long id);
	
	// consultar caso por Id
	CasoDTO findById(Long id);
}
