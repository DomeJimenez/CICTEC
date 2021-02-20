package com.espe.cicte.ws.utils;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {

	public void generaClave(String correo, String password) {
		try {
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

			Session session = Session.getDefaultInstance(props);
			String clave = password;
			String correoRemitente = "cicte.espe@gmail.com";
			String passwordRemitente = "Cicte2018*";
			String correoReceptor = correo;
			String asunto = "CICTE Simulador de Vuelo";
			String mensaje = "Bienvenido, <br> Por favor ingresar al sitio web con su correo electrónico y la siguiente clave generada:  <b>"
					+ clave + "</b><br><br>Por <b>Saludos cordiales</b>";

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(correoRemitente));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
			message.setSubject(asunto);
			message.setText(mensaje, "ISO-8859-1", "html");

			Transport t = session.getTransport("smtp");
			t.connect(correoRemitente, passwordRemitente);
			t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			t.close();

		} catch (AddressException ex) {
			Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
		} catch (MessagingException ex) {
			Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void enviarAdjunto(String correo) {
		try {
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

			Session session = Session.getDefaultInstance(props);

			String correoRemitente = "cicte.espe@gmail.com";
			String passwordRemitente = "Cicte2018*";
			String correoReceptor = correo;
			String asunto = "CICTE Simulador de Vuelo";
			String mensaje = "Hola<br>Este es el contenido de la evaluación realizada el <b>"
					+ Utils.getInstance().getDateTime() + "  </b><br><br>Por: <b> El simulador de vuelo  </b>";

			BodyPart texto = new MimeBodyPart();
			texto.setContent(mensaje, "text/html");

			BodyPart adjunto = new MimeBodyPart();
			adjunto.setDataHandler(new DataHandler(new FileDataSource("C:/CICTE/evaluacion.pdf")));
			adjunto.setFileName("Image.png");

			MimeMultipart miltiParte = new MimeMultipart();
			miltiParte.addBodyPart(texto);
			miltiParte.addBodyPart(adjunto);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(correoRemitente));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
			message.setSubject(asunto);
			message.setContent(miltiParte);

			Transport t = session.getTransport("smtp");
			t.connect(correoRemitente, passwordRemitente);
			t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			t.close();

		} catch (AddressException ex) {
			Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
		} catch (MessagingException ex) {
			Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
