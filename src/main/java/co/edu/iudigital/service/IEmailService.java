package co.edu.iudigital.service;

public interface IEmailService {

	boolean sendEmail(String mensaje, 
						String email, String asunto);
}
