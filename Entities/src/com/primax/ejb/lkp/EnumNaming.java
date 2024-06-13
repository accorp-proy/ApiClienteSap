package com.primax.ejb.lkp;

public enum EnumNaming {

	IPoliticaSeguridadDao("PoliticaSeguridadDao"),
	IUsuarioHistorialDao("UsuarioHistorialDao"),
	IParametroDao("ParametroGeneralDao"),
	IRolMenuAccesoDao("RolMenuAccesoDao"),
	IRolUsuarioDao("RolUsuarioDao"),
	IProvinciaDao("ProvinciaDao"),
	IServidorDao("ServidorDao"),
	IOficinaDao("OficinaDao"),
	IEmpresaDao("EmpresaDao"),
	IUsuarioDao("UsuarioDao"),
	IPersonaDao("PersonaDao"),
	IClienteDao("ClienteDao"),
	IDbHandler("DbHandler"),
	ICantonDao("CantonDao"),
	IRegionDao("RegionDao"),
	IMenuEtDao("MenuEtDao"),
	IRolDao("RolEtDao");

	private String naming;
	private String ruta = "java:global/PrimaxEAR/DataService/";

	EnumNaming(String name) {
		this.naming = ruta + name;
	}

	public String getNaming() {
		return naming;
	}

}
