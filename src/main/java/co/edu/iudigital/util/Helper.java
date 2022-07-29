package co.edu.iudigital.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import co.edu.iudigital.dto.CasoDTO;
import co.edu.iudigital.dto.DelitoDTO;
import co.edu.iudigital.model.Caso;
import co.edu.iudigital.model.Delito;

public interface Helper {

	public static DelitoDTO convertDelitoToDelitoDTO(Delito delito) {
		DelitoDTO delitoDTO = new DelitoDTO();
		BeanUtils.copyProperties(delito, delitoDTO);
		return delitoDTO;
	}
	
	public static Delito convertDelitoDTOToDelito(DelitoDTO delitoDTO) {
		Delito delito = new Delito();
		BeanUtils.copyProperties(delitoDTO, delito);
		return delito;
	}
	
	public static List<DelitoDTO> convertListDelitoDTO(
			List<Delito> delitos){
		return delitos
				.stream()
				.map(d -> {
					return convertDelitoToDelitoDTO(d);
				})
				.collect(Collectors.toList());
	}
	
	public static CasoDTO convertCasoToCasoDTO(Caso caso) {
		CasoDTO casoDTO = new CasoDTO();
		BeanUtils.copyProperties(caso, casoDTO);
		return casoDTO;
	}
	
	public static Caso convertCasoDTOToCaso(CasoDTO casoDTO) {
		Caso caso = new Caso();
		BeanUtils.copyProperties(casoDTO, caso);
		return caso;
	}
	
	
	public static List<CasoDTO> convertListCasoDTO(
			List<Caso> casos){
		return casos
				.stream()
				.map(c -> {
					return convertCasoToCasoDTO(c);
				})
				.collect(Collectors.toList());
	}
}
