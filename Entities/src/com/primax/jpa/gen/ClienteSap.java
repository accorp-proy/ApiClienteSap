package com.primax.jpa.gen;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.primax.jpa.enm.EstadoCategoria;

@JsonPropertyOrder({ "RUC", "RAZON_SOCIAL", "CATEGORIA_CLI", "CATEGORIA_CLI", "SCORING" })
public class ClienteSap implements Serializable {

	/**
	 * ACCORP S.A
	 */
	private static final long serialVersionUID = 1L;

	private String ruc;

	private String razonSocial;

	private String categoria;

	private String puntuacion;

	private EstadoCategoria categoria27;

	public ClienteSap() {
		this.ruc = "";
		this.categoria = "";
		this.puntuacion = "";
		this.razonSocial = "";
		this.categoria27 = EstadoCategoria.N;
	}

	@JsonProperty("RUC")
	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	@JsonProperty("RAZON_SOCIAL")
	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	@JsonProperty("CATEGORIA_CLI")
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@JsonProperty("SCORING")
	public String getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(String puntuacion) {
		this.puntuacion = puntuacion;
	}

	@JsonIgnore
	public EstadoCategoria getCategoria27() {
		return categoria27;
	}

	
	public void setCategoria27(EstadoCategoria categoria27) {
		this.categoria27 = categoria27;
	}

}
