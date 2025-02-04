package com.primax.jpa.enm;

import java.io.File;

public enum RutaFileEnum {

	RUTA_PROYECTO_DEPLOYED(getPathProyecto(File.listRoots()[0].getAbsolutePath() + File.separator + "IMG-SYS")),
	RUTA_SCRIPT_EJECUTA_COMPARATIVO_P( File.separator + "app/wildfly-10.1.0.Final/standalone/jar" + File.separator + "ejecucionComparativoP.sh "),
	RUTA_SCRIPT_EJECUTA_CIERRE_P( File.separator + "app/wildfly-10.1.0.Final/standalone/jar" + File.separator + "ejecucionCierreP.sh "),
	RUTA_SCRIPT_EJECUTA_CIERRE_A( File.separator + "app/wildfly-10.1.0.Final/standalone/jar" + File.separator + "ejecucionCierreA.sh "),
	RUTA_SCRIPT_EJECUTA_CIERRE_DIARIO_P( File.separator + "app/wildfly-10.1.0.Final/standalone/jar" + File.separator + "ejecucionCierreDiarioP.sh "),
	RUTA_SCRIPT_EJECUTA_CIERRE_DIARIO_A( File.separator + "app/wildfly-10.1.0.Final/standalone/jar" + File.separator + "ejecucionCierreDiarioA.sh "),
	RUTA_SCRIPT_EJECUTA_CIERRE_DIARIO_I( File.separator + "app/wildfly-10.1.0.Final/standalone/jar" + File.separator + "ejecucionCierreDiarioI.sh "),
	RUTA_IMAGEN_TEMPORAL("TMP"),
	RUTA_IMAGEN_TEMP("");
	
	private RutaFileEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	private String descripcion;

	public String getDescripcion() {

		return descripcion;
	}

	public static String getPath(String... path) {
		StringBuilder fileDir = new StringBuilder(File.listRoots()[0].getAbsolutePath());
		for (String fl : path) {
			fileDir.append(File.separator + fl);
		}
		return fileDir.toString() + File.separator;
	}

	public static String getPathProyecto(String... path) {
		if (path != null && path.length > 0) {
			String ruta = path[0];
			System.out.println("RUTA POR PATH: " + ruta);
			return ruta;
		} else {
			String ruta = System.getenv("JBOSS_HOME");
			System.out.println("RUTA DEL SERVIDOR: " + ruta);
			return ruta + File.separator + "standalone" + File.separator + "deployments" + File.separator
					+ "PrimaxEAR.ear" + File.separator + "Primax.war";
		}
	}
}
