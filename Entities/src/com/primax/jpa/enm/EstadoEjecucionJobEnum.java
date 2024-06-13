package com.primax.jpa.enm;

public enum EstadoEjecucionJobEnum {

	EJECUTANDO("EJECUTANDO"), FINALIZO("FINALIZO");

	private String estado;

	EstadoEjecucionJobEnum(String estadoDesc) {
		this.estado = estadoDesc;
	}

	public String getDescripcion() {
		return this.estado;
	}

}
