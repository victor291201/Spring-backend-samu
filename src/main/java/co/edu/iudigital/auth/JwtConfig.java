package co.edu.iudigital.auth;

public class JwtConfig {
	
	 // se debe asignar una llave secreta para firmar y verificar la integridad del JWT
	
	// sino hay un código o llave secreto se crea uno por defecto (se puede ver en JwtAccessTokenConverter)
	
	//Para generar estas llaves se usa openssl y se colocan en AuthorizationServer
	
	// usamos el algoritmo RSA
	// con la privada se firma el token
	// con la llave pública se verifica que el token sea válido
	// y no se haya alterado o modificado
	
	// Hay varias herramientas pero usamos openssl
	// openssl genrsa -out jwt.pem -> para generar
    // openssl rsa -in jwt.pem -> para mostrar
	// openssl rsa -in jwt.pem -pubout -> para mostrar llave pública
	
	// estas llaves cifran y verifican el token
	
	// en jwt.io podemos leer el token
	
	public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoM1WizAe1hDjwYvGAT+T\r\n" + 
			"47QxeNxIaw3m7Tay/aD2qFgHV93jDwAWqsd1jzSyemBmt7zP00TElNTbZthm+p5T\r\n" + 
			"vYAs5O7YC+X9I5S12O/8WPNBJXXZwH25X27ARxocHEfq82a+5P9WSK7T3XToaeGf\r\n" + 
			"K2N9OgkV5IZDZKJ5BqwVAJ5NjKROAKSuuNBH5/uDfTETUqepbvFZv5ABzej1j4eY\r\n" + 
			"ubk7xpQ7BAezjGu68zu3Rl2SqXcgE+4hkwEJPrKfhft2IZ+yj6E1JDXfv+v9Cyfn\r\n" + 
			"9oxvdC/YYkMdjYI6JQb0ImYwEjw0pPxWqIVkCTv+xU25idHqSoQgGqGOBTxAGPFP\r\n" + 
			"4wIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";
	
	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpQIBAAKCAQEAoM1WizAe1hDjwYvGAT+T47QxeNxIaw3m7Tay/aD2qFgHV93j\r\n" + 
			"DwAWqsd1jzSyemBmt7zP00TElNTbZthm+p5TvYAs5O7YC+X9I5S12O/8WPNBJXXZ\r\n" + 
			"wH25X27ARxocHEfq82a+5P9WSK7T3XToaeGfK2N9OgkV5IZDZKJ5BqwVAJ5NjKRO\r\n" + 
			"AKSuuNBH5/uDfTETUqepbvFZv5ABzej1j4eYubk7xpQ7BAezjGu68zu3Rl2SqXcg\r\n" + 
			"E+4hkwEJPrKfhft2IZ+yj6E1JDXfv+v9Cyfn9oxvdC/YYkMdjYI6JQb0ImYwEjw0\r\n" + 
			"pPxWqIVkCTv+xU25idHqSoQgGqGOBTxAGPFP4wIDAQABAoIBABD3eZE4I5jMsWtj\r\n" + 
			"lyHclnspMSsiexWootwWsG4ohL4gg63WwLV9eebNIu9YTRiygUQTzQ3qrJ9Tkk7Y\r\n" + 
			"uIzrFcTh+FqzhOJEgyA0+bcJI2QzToVoWPCBk4SpR+p4q8ykHftOtQeulWkX4Uz1\r\n" + 
			"n+xEHSpT1gvAl6nELOo6ZnC4yYKGBqYqORWG/PiqwrumMc09Vnnd3rrBuAJj3+Kq\r\n" + 
			"NCT/lL8u79FutMSbjcUQoDbAyxAw0P7DVDULEy0jOt5gLw2yhtgJ3spZkL/waVTT\r\n" + 
			"RlmT/SEUrw0TjbkNMnbMCXq2kB+ucxw8QfpmE2vz7zLGekU8Untjk07yGvFv/6fB\r\n" + 
			"bo60aDECgYEA1HIsAOZlMz114UteX6wDo8K7YsIlEukvczbee11lpvqrMhfrtt56\r\n" + 
			"IvKH5PnX6XcRm1gKDWTcjjllQUn/vgBMAtemRUpaZrMwnVnUgQhRbSCJDNzLeAVV\r\n" + 
			"knggJvU5XiZp/YwSxqEzXOUmtgx0OFq5wZBalknRobqO39RZ49NLUukCgYEAwcS7\r\n" + 
			"7eTS9JcXfTdms9AGUK3cL9jMnlnXypS+CTzEjMMHHWaYlvqPSnXkFaxZVXSGmbUm\r\n" + 
			"B+Wgit1G7cTUJZuw/DVmuBTzd/EbbMdvN7Z879tQa76TLais/yT8xn0Qc6gmAHJE\r\n" + 
			"mGEmYt1YauEQTCWMibMfyOVJUjnoENxmLjaCFOsCgYEAvbMVR9hzFUYUNJCwPI3E\r\n" + 
			"0j3YRV9G65P9Iswj7dSAKJI8vAZQHIKcXQhkADp4aahAuI3sq/kcWIM3vwzsqCRr\r\n" + 
			"uz4E4mp6PqpTBq5n6rAaxW7/8dRWe12IA7nwO9lM2J1El3q0aVkYHCMiZfzI6mlY\r\n" + 
			"5VaZDQntfpSvb36WpbDO2cECgYEAknfm4p1chPyNCd7N6E/v5h512UkU6wHYp04m\r\n" + 
			"FA92NzzY7PV3lHCTzeItZeCgqoLpl9ihhciiwTUgf2yQPFliwFKRccaMf3aml2Rt\r\n" + 
			"gnyUFi7+rGMjF5lwI3FPDa7Nt8874ox9o32UKZlNU4UK77RWdmQLO61t7hTNgalI\r\n" + 
			"J9iWHb8CgYEAnfkthLf8Kb+Fl2q0cwBY5+6bTH81r8jvC5Dvc/EI3z2M+ZW7H7sU\r\n" + 
			"ATq6YEIeA5FLYswoXI8cIjp5ShdNJdin/efdR3XMMh4H3rrmxU+UiSrCf/mq9V0v\r\n" + 
			"XxzEooRzUvnDLsNiuK4uu63zDAPw3LTsIszK5Z9ecHKmUgXePHI3q6c=\r\n" + 
			"-----END RSA PRIVATE KEY-----";
}
