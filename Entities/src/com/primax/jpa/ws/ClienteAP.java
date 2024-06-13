package com.primax.jpa.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.primax.bean.util.XmlBind;

@XmlRootElement(name = "clientAP")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class ClienteAP extends XmlBind {

	private String idJDE;

	private String nombreAlfa;

	private String estado;

	private String direccion;

	private String provincia;

	private String canton;

	private String ejecutivoComercial;

	private String facturadorAsignado;

	private String correo;

	private String estadoCuenta;

	private String tipoCliente;

	private String categoria21;

	private String categoria26;

	private String categoria27;

	private String classOfMarket;

	private String saldoVencido;

	private String deudaUno30;

	private String deudaMas30;

	public String getIdJDE() {
		return idJDE;
	}

	@XmlElement(name = "codigoCliente")
	public void setIdJDE(String idJDE) {
		this.idJDE = idJDE;
	}

	public String getNombreAlfa() {
		return nombreAlfa;
	}

	@XmlElement(name = "nombreAlfa")
	public void setNombreAlfa(String nombreAlfa) {
		this.nombreAlfa = nombreAlfa;
	}

	public String getEstado() {
		return estado;
	}

	@XmlElement(name = "estado")
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDireccion() {
		return direccion;
	}

	@XmlElement(name = "direccion")
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getProvincia() {
		return provincia;
	}

	@XmlElement(name = "provincia")
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCanton() {
		return canton;
	}

	@XmlElement(name = "canton")
	public void setCanton(String canton) {
		this.canton = canton;
	}

	public String getEjecutivoComercial() {
		return ejecutivoComercial;
	}

	@XmlElement(name = "ejecutivoComercial")
	public void setEjecutivoComercial(String ejecutivoComercial) {
		this.ejecutivoComercial = ejecutivoComercial;
	}

	public String getFacturadorAsignado() {
		return facturadorAsignado;
	}

	@XmlElement(name = "facturadorAsignado")
	public void setFacturadorAsignado(String facturadorAsignado) {
		this.facturadorAsignado = facturadorAsignado;
	}

	public String getCorreo() {
		return correo;
	}

	@XmlElement(name = "correo")
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getEstadoCuenta() {
		return estadoCuenta;
	}

	@XmlElement(name = "estadoCuenta")
	public void setEstadoCuenta(String estadoCuenta) {
		this.estadoCuenta = estadoCuenta;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	@XmlElement(name = "tipoCliente")
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getCategoria21() {
		return categoria21;
	}

	@XmlElement(name = "categoria21")
	public void setCategoria21(String categoria21) {
		this.categoria21 = categoria21;
	}

	public String getCategoria26() {
		return categoria26;
	}

	@XmlElement(name = "categoria26")
	public void setCategoria26(String categoria26) {
		this.categoria26 = categoria26;
	}

	public String getCategoria27() {
		return categoria27;
	}

	@XmlElement(name = "categoria27")
	public void setCategoria27(String categoria27) {
		this.categoria27 = categoria27;
	}

	public String getClassOfMarket() {
		return classOfMarket;
	}

	@XmlElement(name = "classOfMarket")
	public void setClassOfMarket(String classOfMarket) {
		this.classOfMarket = classOfMarket;
	}

	public String getSaldoVencido() {
		return saldoVencido;
	}

	@XmlElement(name = "saldoVencido")
	public void setSaldoVencido(String saldoVencido) {
		this.saldoVencido = saldoVencido;
	}

	public String getDeudaUno30() {
		return deudaUno30;
	}

	@XmlElement(name = "deudaUno30")
	public void setDeudaUno30(String deudaUno30) {
		this.deudaUno30 = deudaUno30;
	}

	public String getDeudaMas30() {
		return deudaMas30;
	}

	@XmlElement(name = "deudaMas30")
	public void setDeudaMas30(String deudaMas30) {
		this.deudaMas30 = deudaMas30;
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
