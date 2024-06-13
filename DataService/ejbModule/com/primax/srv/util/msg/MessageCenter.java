package com.primax.srv.util.msg;

import java.util.List;
import java.util.Map;

import com.primax.jpa.param.ParametrosGeneralesEt;

public interface MessageCenter {

	public void setMessage(String message);

	public void setRecipient(String receiver);

	public void setSubject(String subject);

	public void setFrom(String from);

	public boolean sendMessage();
	
	public boolean sendMessageNoConfig();

	public void setConfig(List<ParametrosGeneralesEt> config);

	void setAttachtment(Map<String, byte[]> filebyte);

}
