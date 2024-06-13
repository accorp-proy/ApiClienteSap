package com.primax.srv.util.msg;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.primax.jpa.param.ParametrosGeneralesEt;
import javax.mail.util.ByteArrayDataSource;

public class MailSender implements MessageCenter {

	private String _mensaje, _recipiente, _asunto, _de;

	private String _clave;

	private Map<String, byte[]> archivos;

	private List<ParametrosGeneralesEt> config;

	@Override
	public void setMessage(String message) {
		this._mensaje = message;
	}

	@Override
	public void setRecipient(String receiver) {
		this._recipiente = receiver;
	}

	@Override
	public void setSubject(String subject) {
		this._asunto = subject;
	}

	@Override
	public void setFrom(String from) {
		this._de = from;
	}

	@Override
	public void setAttachtment(Map<String, byte[]> filebyte) {
		this.archivos = filebyte;
	}

	@Override
	public boolean sendMessageNoConfig() {
		Properties props = new Properties();
		// props.put("mail.smtp.host", "172.18.120.240");
		props.put("mail.smtp.host", "172.18.120.20");
		props.put("mail.smtp.starttls.enable", "false");
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.auth", "true");

		try {
			Session session = Session.getDefaultInstance(props);
			session.setDebug(true);
			MimeMultipart container = new MimeMultipart();
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText(_mensaje, "UTF-8");
			textBodyPart.setHeader("Content-Type", "text/html");
			container.addBodyPart(textBodyPart);
			if (archivos != null && !archivos.isEmpty()) {
				for (Map.Entry<String, byte[]> file : archivos.entrySet()) {
					DataSource ds;
					try {
						ds = new ByteArrayDataSource(new ByteArrayInputStream(file.getValue()), "application/octet-stream");
						MimeBodyPart attachment = new MimeBodyPart();
						attachment.setFileName(file.getKey());
						attachment.setDataHandler(new DataHandler(ds));
						container.addBodyPart(attachment);
					} catch (IOException e) {
						System.err.print("ERROR EN ENVIO DE MAIL, Cause by :" + e.getMessage());
					}
				}
			}

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(_de));
			message.addRecipients(RecipientType.TO, InternetAddress.parse(_recipiente));
			message.setSubject(_asunto);
			message.setContent(container);

			Transport t = session.getTransport("smtp");
			// t.connect("giftcard", "giftcard01");
			t.connect("Jeffdak", "rans1hyoc");
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			return true;
		} catch (MessagingException e) {
			System.err.print("ERROR EN ENVIO MAIL, CAUSED BY : " + e.getMessage());
			return false;
		}
	}

	private void makeTest() {
		/*
		 * Servidor de Correo Cambio 10.72.1.77
		 * 
		 */
		Properties props = new Properties();
		props.put("mail.smtp.host", "10.72.1.77");
		props.put("mail.smtp.starttls.enable", "false");
		props.put("mail.smtp.port", "25");
		props.put("mail.smtp.auth", "true");

		try {
			Session session = Session.getDefaultInstance(props);
			session.setDebug(true);
			MimeMultipart container = new MimeMultipart();
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText(_mensaje, "UTF-8");
			textBodyPart.setHeader("Content-Type", "text/html");
			container.addBodyPart(textBodyPart);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(_de));
			message.addRecipients(RecipientType.TO, InternetAddress.parse(_recipiente));
			message.setSubject(_asunto);
			message.setContent(container);

			Transport t = session.getTransport("smtp");
			t.connect("Jeffdak", "rans1hyoc");
			t.sendMessage(message, message.getAllRecipients());
			t.close();

		} catch (MessagingException e) {
			System.err.print("ERROR EN ENVIO MAIL, CAUSED BY : " + e.getMessage());
		}
	}

	public static void main(String... arg) {

		MailSender send = new MailSender();
		send.setFrom("giftcard@primax.com.ec");
		send.setMessage("Test msg");
		send.setSubject("subject");
		send.setRecipient("jeffersonmaji@hotmail.com");
		send.makeTest();
		System.out.println("Mail enviado");

	}

	@Override
	public boolean sendMessage() {

		try {
			Session session = obtenerSession(config);
			session.setDebug(true);
			MimeMultipart container = new MimeMultipart();
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText(_mensaje, "UTF-8");
			textBodyPart.setHeader("Content-Type", "text/html");
			container.addBodyPart(textBodyPart);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(_de));
			message.addRecipients(RecipientType.TO, InternetAddress.parse(_recipiente));
			message.setSubject(_asunto);
			message.setContent(container);

			Transport t = session.getTransport("smtp");
			t.connect(_de, _clave);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			return true;
		} catch (MessagingException e) {
			System.err.print("ERROR EN ENVIO MAIL, CAUSED BY : " + e.getMessage());
			return false;
		}

	}

	private Session obtenerSession(List<ParametrosGeneralesEt> parametrosMail) {
		Properties props = new Properties();

		for (ParametrosGeneralesEt par : parametrosMail) {
			if (par.getNombreLista().equals("MAIL-HOST")) {
				props.put("mail.smtp.host", par.getValorLista());
			} else if (par.getNombreLista().equals("EMAIL-TLS")) {
				props.put("mail.smtp.starttls.enable", par.getValorLista());
			} else if (par.getNombreLista().equals("EMAIL-PORT")) {
				props.put("mail.smtp.port", par.getValorLista());
			} else if (par.getNombreLista().equals("EMAIL-AUTH")) {
				props.put("mail.smtp.auth", par.getValorLista());
			} else if (par.getNombreLista().equals("EMAIL-SMTP-PWD")) {
				_clave = par.getValorLista();
			} else if (par.getNombreLista().equals("EMAIL-FROM")) {
				_de = par.getValorLista();
				props.put("user", par.getValorLista());
			}
		}

		Session session = Session.getDefaultInstance(props);
		return session;
	}

	public List<ParametrosGeneralesEt> getConfig() {
		return config;
	}

	public void setConfig(List<ParametrosGeneralesEt> config) {
		this.config = config;
	}

}
