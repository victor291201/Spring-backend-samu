package co.edu.iudigital.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import co.edu.iudigital.dto.CasoDTO;
import co.edu.iudigital.dto.DelitoDTO;
import co.edu.iudigital.dto.UsuarioDTO;
import co.edu.iudigital.model.Caso;
import co.edu.iudigital.model.Delito;
import co.edu.iudigital.model.Usuario;

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
		casoDTO.setUsuarioId(caso.getUsuario().getId());
		casoDTO.setNombre(caso.getUsuario().getNombre());
		casoDTO.setImage(caso.getUsuario().getImage());
		casoDTO.setDelitoId(caso.getDelito().getId());
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
	
	
	/**
     * Recibe por referencia una instancia de List para mapear su resultado al UsuarioDto
     */
    public static void setMapValuesClient(List<Usuario> usuarios, List<UsuarioDTO> usuariosDto){
    	usuarios.stream().map(usuario -> {
          UsuarioDTO cDto = getMapValuesClient(usuario);
          return cDto;
        }).forEach(cDto -> {
        	usuariosDto.add(cDto);
        });
    }

    public static UsuarioDTO getMapValuesClient(Usuario usuario){
    	UsuarioDTO uDto = new UsuarioDTO();
    	uDto.setId(usuario.getId());
    	uDto.setNombre(usuario.getNombre());
    	uDto.setApellido(usuario.getApellido());
    	uDto.setFechaNacimiento(usuario.getFechaNacimiento());
    	uDto.setImage(usuario.getImage());
    	uDto.setRoles(usuario.getRoles()
    			.stream().map(r -> r.getNombre())
    			.collect(Collectors.toList())
    			);
    	uDto.setUsername(usuario.getUsername());

    	return uDto;
    }
}
