package co.edu.iudigital.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Registrar la clase de UsuarioService
 * @author JULIOCESARMARTINEZ
 *
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)//para usar el @Secured
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService usuarioService;
	
	// registramos el objeto como componente de spring
	// esta es la forma de registrar objetos que retornan métodos
	@Bean 
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/*
	 * registramos el UserDatailService y paramos el atributo
	 * y configuramos el passwordencoder y pasamos una instancia de 
	 * la implementación de un encriptador de contraseña para brindar
	 * más seguridad a las contraseñas, usamos el que creamos de Spring security
	 * LE pasamos el método creado: passwordEnconder()
	 * */
	@Override
	@Autowired // para inyectar vía argumento el auth
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder());
	}
	
	/**
	 * Lo coloicamos como beans para usarlo en el servidor de autorización
	 * */
	@Bean("authenticationManager")// le colocamos esto para que el qyue inyectemos sea de este y no de otro
	// osea para el qualifier
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	
	
	////--------------------------------------2 ---------------------------
	// protección del lado de spring
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("**/swagger-ui.html").permitAll()
			.antMatchers("**/api/v1/webjars/*").permitAll()
			.anyRequest().authenticated()
			.and()
			.csrf().disable()//sin proteccion de forms ataques cross por estar en capa react separado del back
			
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//
			
			//.and()
			//.httpBasic().realmName("HelmeIUD")
			;	
	}
	
	/*swagger*/
	 @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers("/v2/api-docs",
	                                   "/configuration/ui",
	                                   "/swagger-resources/**",
	                                   "/configuration/security",
	                                   "/swagger-ui.html",
	                                   "/webjars/**");
	  }

}
