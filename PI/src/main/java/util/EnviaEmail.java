package util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EnviaEmail {
	
	public void sendEmail(String emailDestino, String nome, String mensagem, String assunto) throws EmailException {
	    
		   SimpleEmail email = new SimpleEmail();
		   //Utilize o hostname do seu provedor de email
		   //System.out.println("alterando hostname...");
		   email.setHostName("smtp.gmail.com");
		   //Quando a porta utilizada n�o � a padr�o (gmail = 465)
		   email.setSmtpPort(465);
		   //Adicione os destinat�rios
		   email.addTo(emailDestino, nome);
		   //Configure o seu email do qual enviar�
		   email.setFrom("evento.tads@gmail.com", "Sistema de Eventos");
		   //Adicione um assunto
		   email.setSubject(assunto);
		   //Adicione a mensagem do email
		   email.setMsg(mensagem);
		   //Para autenticar no servidor � necess�rio chamar os dois m�todos abaixo
		   //System.out.println("autenticando...");
		   email.setSSL(true);
		   email.setAuthentication("evento.tads@gmail.com", "tads2016");
		   //System.out.println("enviando...");
		   email.send();
		   //System.out.println("Email enviado!");
		}
}
