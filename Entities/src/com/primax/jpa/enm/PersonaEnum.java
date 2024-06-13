package com.primax.jpa.enm;

public enum PersonaEnum {

	USUARIO("USUARIO");

	private PersonaEnum(final String descripcion) {
		this.descripcion = descripcion;
	}

	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

}
