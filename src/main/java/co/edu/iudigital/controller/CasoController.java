package co.edu.iudigital.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.iudigital.dto.CasoDTO;
import co.edu.iudigital.exception.RestException;
import co.edu.iudigital.service.ICasoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/casos")
@Api(value = "/casos", tags = {"Casos"})
@SwaggerDefinition(tags = {
		@Tag(name = "Casos", description = "Gestion API Casos")
})
public class CasoController {

	private static final Logger log = 
			LoggerFactory.getLogger(CasoController.class);
	
	@Autowired
	private ICasoService casoService;
	
	@ApiOperation(
			value = "Obtiene todos los casos",
			response = List.class, 
			produces = "application/json",
			httpMethod = "GET")
	@GetMapping
	public ResponseEntity<List<CasoDTO>> index(){
		try {
			return ResponseEntity.ok(casoService.findAll());
		} catch (RestException e) {
			log.error("Error consulta", e);
			return null;
		}
	}
	
	@ApiOperation(
			value = "Guarda un caso",
			produces = "application/json",
			httpMethod = "POST"
			)
	@PostMapping
	public ResponseEntity<CasoDTO> create(
				@RequestBody CasoDTO casoDTO
			) {
		try {
			return new ResponseEntity(
					casoService.save(casoDTO),
					HttpStatus.CREATED
			);
		} catch (RestException e) {
			log.error("Error al guardar", e);
			return null;
		}
	}
}
