package co.edu.iudigital.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {
	
	@Value("${spring.mail.host}")
	private String host;

	@Value("${spring.mail.port}")
	private int port;
	
	@Value("${spring.mail.username}")
	private String username;
	
	@Value("${spring.mail.password}")
	private String password;
	
	@Value("${spring.mail.properties.mail.smtp.auth}")
	private String auth;
	
	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private String startls;
	
	@Bean
	public JavaMailSender sender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost(host);
	    mailSender.setPort(port);
	    
	    mailSender.setUsername(username);
	    mailSender.setPassword(password);
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", auth);
	    props.put("mail.smtp.starttls.enable", startls);
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}
}
