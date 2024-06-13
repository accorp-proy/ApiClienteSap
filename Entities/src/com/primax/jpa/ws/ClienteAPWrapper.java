package com.primax.jpa.ws;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.primax.bean.util.XmlBind;

@XmlRootElement(name = "clienteAPWrapper")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class ClienteAPWrapper extends XmlBind {

	private String dni;

	private String idClienteJDE;

	private String razonSocial;

	private String comercialAsignado;

	private String facturadorAsignado;

	private List<ClienteAP> clientesAP;

	private String descripcionErr;

	public List<ClienteAP> getClientesAP() {
		return clientesAP;
	}

	@XmlElementWrapper(name = "clientesAP")
	@XmlElement(name = "clientAP")
	public void setClientesAP(List<ClienteAP> clientesAP) {
		this.clientesAP = clientesAP;
	}

	public String getDni() {
		return dni;
	}

	@XmlElement(name = "dni")
	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	@XmlElement(name = "razonSocial")
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getComercialAsignado() {
		return comercialAsignado;
	}

	@XmlElement(name = "comercialAsignado")
	public void setComercialAsignado(String comercialAsignado) {
		this.comercialAsignado = comercialAsignado;
	}

	public String getFacturadorAsignado() {
		return facturadorAsignado;
	}

	@XmlElement(name = "facturadorAsignado")
	public void setFacturadorAsignado(String facturadorAsignado) {
		this.facturadorAsignado = facturadorAsignado;
	}

	public String getDescripcionErr() {
		return descripcionErr;
	}

	@XmlElement
	public void setDescripcionErr(String descripcionErr) {
		this.descripcionErr = descripcionErr;
	}

	public String getIdClienteJDE() {
		return idClienteJDE;
	}

	@XmlElement(name = "idClienteJDE")
	public void setIdClienteJDE(String idClienteJDE) {
		this.idClienteJDE = idClienteJDE;
	}

	@Override
	public <T> String parsetoXMl() {
		return super.parsetoXMl();
	}

	@Override
	public <T> T parsetoObject(String xml) {
		return super.parsetoObject(xml);
	}

}
