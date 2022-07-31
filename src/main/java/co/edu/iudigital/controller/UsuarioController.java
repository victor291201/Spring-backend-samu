package co.edu.iudigital.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.edu.iudigital.dto.UsuarioDTO;
import co.edu.iudigital.exception.BadRequestException;
import co.edu.iudigital.exception.ErrorDto;
import co.edu.iudigital.exception.InternalServerErrorException;
import co.edu.iudigital.exception.NotFoundException;
import co.edu.iudigital.exception.RestException;
import co.edu.iudigital.model.Usuario;
import co.edu.iudigital.service.IEmailService;
import co.edu.iudigital.service.IUsuarioService;
import co.edu.iudigital.util.ConstUtil;
import co.edu.iudigital.util.Helper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@RestController
@RequestMapping("/usuarios")
@Api(value = "/usuarios", tags = {"Usuarios"})
@SwaggerDefinition(tags = {
		@Tag(name = "Usuarios", description = "Gestion API Usuarios")
})
public class UsuarioController {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IEmailService emailService;
	
    @ApiOperation(value = "Realiza la creación de un nuevo usuario en el sistema",
            produces = "application/json",
            httpMethod = "POST")
    @PostMapping("/signup")
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) throws RestException{
        try {
        	Usuario usuarioFind = usuarioService.findByUsername(usuario.getUsername());
        	if(usuarioFind != null) {
                throw new BadRequestException(ErrorDto.getErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                        ConstUtil.MESSAGE_ALREADY,
                        HttpStatus.INTERNAL_SERVER_ERROR.value()));
        	}
        	String mensaje = "Su usuario: "+usuario.getUsername()+"; password: "+usuario.getPassword();
        	String asunto = "Registro en HelmeIUD";
        	if(usuario.getPassword() != null) {
        		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        	}
        	Usuario usuarioSaved = usuarioService.save(usuario);
        	if(usuarioSaved != null) {
        		emailService.sendEmail(mensaje, usuario.getUsername(), asunto);
        	}
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSaved);
        }catch (BadRequestException ex){
            throw ex;
        }catch (Exception ex){
            log.error("Error Creando", ex);
            throw new InternalServerErrorException(ErrorDto.getErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                    ConstUtil.MESSAGE_GENERAL,
                    HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }
    
	@ApiOperation(value = "Obtiene usuario por id",
			response = UsuarioDTO.class,
			produces = "application/json",
			httpMethod = "GET")
	@GetMapping("/usuario/{id}")
	//@ResponseStatus(code = HttpStatus.OK)
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<UsuarioDTO> show(@PathVariable Long id) throws RestException {
		Usuario usuario =  usuarioService.findById(id);
		UsuarioDTO usuarioDto = new UsuarioDTO();
		usuarioDto = Helper.getMapValuesClient(usuario);
		
		return ResponseEntity.ok().body(usuarioDto);
	}
	
	@ApiOperation(value = "Obtiene usuarios",
			produces = "application/json",
			httpMethod = "GET")
	@GetMapping
	//@ResponseStatus(code = HttpStatus.OK)
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<List<UsuarioDTO>> index() throws RestException {
		List<Usuario> usuarios =  usuarioService.findAll();
		List<UsuarioDTO> usuariosDto = new ArrayList<>();
		Helper.setMapValuesClient(usuarios, usuariosDto);
		return ResponseEntity.ok().body(usuariosDto);
	}
    
    @ApiOperation(value = "Consulta el usuario autenticado actual",
    		response = UsuarioDTO.class,
            produces = "application/json",
            httpMethod = "GET")
	@GetMapping("/usuario")
	public ResponseEntity<UsuarioDTO> userInfo(Authentication authentication) throws RestException{
		if(!authentication.isAuthenticated()) {
			throw new RestException(ErrorDto.getErrorDto(HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                    ConstUtil.MESSAGE_NOT_AUTHORIZED,
                    HttpStatus.UNAUTHORIZED.value()));
		}
		String userName = authentication.getName();
		Usuario usuario = usuarioService.findByUsername(userName);
		if(usuario==null) {
			throw new RestException(ErrorDto.getErrorDto(HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                    ConstUtil.MESSAGE_NOT_AUTHORIZED,
                    HttpStatus.UNAUTHORIZED.value()));
		}
		UsuarioDTO usuarioDto = new UsuarioDTO();
		usuarioDto = Helper.getMapValuesClient(usuario);
		
		return ResponseEntity.ok().body(usuarioDto);
	}
	
    /*
     * Sube la imagen de un usuario
     */
	@PostMapping("/upload")
	public ResponseEntity<?> upload(
			@RequestParam("image") MultipartFile image, 
			Authentication authentication) 
			throws RestException{
		Map<String, Object> response = new HashMap<>();
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		if(!image.isEmpty()) {
			String nombreImage = 
					UUID.randomUUID().toString()+"_"+image.getOriginalFilename().replace(" ", "");
			Path path = Paths.get("uploads").resolve(nombreImage).toAbsolutePath();
			try {
				Files.copy(image.getInputStream(), path);
			}catch (IOException e) {
				response.put("Error", e.getMessage().concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String oldImage = usuario.getImage();
			
			if(oldImage != null && oldImage.length() > 0 && !oldImage.startsWith("http")) {
				Path oldPath = Paths.get("uploads").resolve(oldImage).toAbsolutePath();
				File oldFileImage = oldPath.toFile();
				if(oldFileImage.exists() && oldFileImage.canRead()) {
					oldFileImage.delete();
				}
			}
			
			usuario.setImage(nombreImage);
			usuarioService.save(usuario);
			response.put("usuario", usuario);
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Actualiza un usuario",
			response = Usuario.class,
			produces = "application/json",
			httpMethod = "PUT")
	@PutMapping("/usuario")
	public ResponseEntity<Usuario> update(Authentication authentication, @RequestBody Usuario usuario) throws RestException {
        try {
    		if(!authentication.isAuthenticated()) {
    			throw new RestException(ErrorDto.getErrorDto(HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                        ConstUtil.MESSAGE_NOT_AUTHORIZED,
                        HttpStatus.UNAUTHORIZED.value()));
    		}
        	Usuario usuarioFind = usuarioService.findByUsername(authentication.getName());
        	if(usuarioFind == null) {
                throw new NotFoundException(ErrorDto.getErrorDto(HttpStatus.NOT_FOUND.getReasonPhrase(),
                        ConstUtil.MESSAGE_NOT_FOUND,
                        HttpStatus.NOT_FOUND.value()));
        	}
        	usuarioFind.setNombre(usuario.getNombre());
        	usuarioFind.setApellido(usuario.getApellido());
        	usuarioFind.setFechaNacimiento(usuario.getFechaNacimiento());
        	if(usuario.getPassword() != null) {
        		usuarioFind.setPassword(passwordEncoder.encode(usuario.getPassword()));
        	}
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioFind));
        }catch (BadRequestException ex){
            throw ex;
        }catch (Exception ex){
            log.error("Error {}", ex);
            throw new InternalServerErrorException(ErrorDto.getErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                    ConstUtil.MESSAGE_GENERAL,
                    HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
	}
	
	
	@ApiOperation(value = "Deshabilita usuario por id")
	@PutMapping("/usuario/{id}")
	//@ResponseStatus(code = HttpStatus.OK)
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<?> delete(@PathVariable Long id) throws RestException {
		Usuario usuario =  usuarioService.findById(id);
		if(usuario == null) {
			throw new NotFoundException(ErrorDto.getErrorDto(HttpStatus.NOT_FOUND.getReasonPhrase(),
                    ConstUtil.MESSAGE_NOT_FOUND,
                    HttpStatus.NOT_FOUND.value()));
		}
		usuario.setEnabled(false);
		usuarioService.save(usuario);
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Disabled");
		return ResponseEntity.ok().body(response);
	}
	
	/**
	 * Obtiene la imagen de un usuario
	 * @param name
	 * @return
	 */
	//@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/uploads/img/{name:.+}")
	public ResponseEntity<Resource> getImage(@PathVariable String name){
		Path path = Paths.get("uploads").resolve(name).toAbsolutePath();
		Resource resource = null;
		try {
			resource = new UrlResource(path.toUri());
			if(!resource.exists()) {
				try {
					path = Paths.get("uploads").resolve("default.png").toAbsolutePath();
					resource = new UrlResource(path.toUri());
				}catch(MalformedURLException ex) {
					log.error("error {}", ex);
				}
			}
		}catch (MalformedURLException e) {
			log.error("error {}", e);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ resource.getFilename() + "\"");
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
}
