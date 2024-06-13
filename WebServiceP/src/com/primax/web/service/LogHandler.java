package com.primax.web.service;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class LogHandler implements SOAPHandler<SOAPMessageContext> {

	public Set<QName> getHeaders() {
		return null;
	}

	public boolean handleFault(SOAPMessageContext context) {
		logToSystemOut(context);
		return true;
	}

	public boolean handleMessage(SOAPMessageContext context) {
		logToSystemOut(context);
		return true;
	}

	private void logToSystemOut(SOAPMessageContext smc) {
		Boolean outboundProperty = (Boolean) smc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		if (outboundProperty.booleanValue()) {
			System.out.println("\nOutgoing message:");
		} else {
			System.out.println("\nIncoming message:");
		}

		SOAPMessage message = smc.getMessage();
		try {
			message.writeTo(System.out);
		} catch (Exception e) {
			System.out.println("Exception in handler: " + e);
		}
	}

	public void close(MessageContext context) {
		// TODO Auto-generated method stub

	}
}
