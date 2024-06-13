package com.primax.jpa.enm;

public enum ParametrosGeneralesEnum {

	
	TIPO_IDENTIFICACION("8"),
	TIPO_ACUERDO_PAGO("21"),
	TIPO_CREDITO("18"),
	TIPO_CLIENTE("13"),
	FORMAPAGO("1"),
	CONFIGURACION_CORREOS("59");
	

	private String descripcion;

	private ParametrosGeneralesEnum(final String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Long getValue() {
		return Long.parseLong(descripcion);
	}

}
