package com.primax.jpa.enm;

public enum NombreJobEnum {

	JOB_EST_INFO_CLI("JOB_EST_INFO_CLI"),
	JOB_EST_MAESTRO("JOB_EST_MAESTRO"),
	JOB_EST_CUENTA("JOB_EST_CUENTA"),
	JOB_FACTURA_P("JOB_FACTRURA_P"),
	JOB_FACTURA("JOB_FACTRURA"),
	JOB_PAGO_NC("JOB_PAGO_NC"),
	JOB_NC("JOB_NC");

	private String estado;

	NombreJobEnum(String estadoDesc) {
		this.estado = estadoDesc;
	}

	public String getDescripcion() {
		return this.estado;
	}

}
