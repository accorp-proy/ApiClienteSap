package com.primax.srv.imsg;

import javax.ejb.Local;
import javax.mail.MessagingException;

import com.primax.jpa.enm.MessageType;

@Local
public interface ISrvNotificacion {

	public void enviarMensaje(MessageType Messenger, String destinatario, String asunto, String Mensaje)
			throws MessagingException;

}
