package com.primax.jpa.enm;

public enum TipoPersonaEnum {

	NOTYPE(""),
	USUARIO("Usuario"),
	CLIENTE("Cliente"),
	VENDEDOR("Vendedor"),
	CONTACTO("Contacto");

	private String label;

	TipoPersonaEnum(String id) {
		label = id;
	}

	public String getLabel() {
		return this.label;
	}

}
