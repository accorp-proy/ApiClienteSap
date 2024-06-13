package com.primax.jpa.enm;

public enum TipoIdentificacionEnum {

	CED("C�dula", 1, "05"),
	RUC("Ruc", 2, "04"),
	PAS("Pasaporte", 3, "06"),
	CF("Consumidor Final", 4, "07"),
	IE("Identificaci�n del Exterior", 5, "08"),
	PLC("Placa", 6, "09");

	private String descripcion, codigoSri;
	private long codigo;

	TipoIdentificacionEnum(String desc, long codigo, String codigoSri) {
		this.descripcion = desc;
		this.codigo = codigo;
		this.codigoSri = codigoSri;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public long getCodigo() {
		return codigo;
	}

	public String getCodigoSri() {
		return codigoSri;
	}

}
