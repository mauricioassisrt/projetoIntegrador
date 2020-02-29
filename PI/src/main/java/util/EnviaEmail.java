package util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EnviaEmail {
	
	public void sendEmail(String emailDestino, String nome, String mensagem, String assunto) throws EmailException {
	    
		   SimpleEmail email = new SimpleEmail();
		   //Utilize o hostname do seu provedor de email
		   //System.out.println("alterando hostname...");
		   email.setHostName("smtp.gmail.com");
		   //Quando a porta utilizada não é a padrão (gmail = 465)
		   email.setSmtpPort(465);
		   //Adicione os destinatários
		   email.addTo(emailDestino, nome);
		   //Configure o seu email do qual enviará
		   email.setFrom("evento.tads@gmail.com", "Sistema de Eventos");
		   //Adicione um assunto
		   email.setSubject(assunto);
		   //Adicione a mensagem do email
		   email.setMsg(mensagem);
		   //Para autenticar no servidor é necessário chamar os dois métodos abaixo
		   //System.out.println("autenticando...");
		   email.setSSL(true);
		   email.setAuthentication("evento.tads@gmail.com", "tads2016");
		   //System.out.println("enviando...");
		   email.send();
		   //System.out.println("Email enviado!");
		}
}
