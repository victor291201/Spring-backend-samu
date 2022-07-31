package co.edu.iudigital.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import co.edu.iudigital.model.Usuario;
import co.edu.iudigital.service.IUsuarioService;

/**
 * Adicionar info del usuario al token
 * o cualquier otra
 * luego se registra en AuthorizationServerConfig
 * @author JULIOCESARMARTINEZ
 *
 */
@Component
public class TokenMoreInfo implements TokenEnhancer{

	@Autowired
	private IUsuarioService usuarioService;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		Map<String, Object> info = new HashMap<>();
		info.put("id_usuario", ""+usuario.getId());
		info.put("nombre", usuario.getNombre());
		info.put("image", usuario.getImage());
		info.put("fecha_nacimiento", ""+usuario.getFechaNacimiento());
		((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
