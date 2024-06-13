package com.primax.job;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.primax.jpa.enm.RutaFileEnum;

public class WorkerEstadoCuentaDiarioP extends BaseJobs implements Job {

	private static final Logger log = Logger.getLogger(WorkerEstadoCuentaDiarioP.class.getName());

	public void execute(JobExecutionContext context) throws JobExecutionException {

		DateFormat formatter = null;
		String pathScriptP = "";

		synchronized (lockAuth) {
			try {
				Date fechaCorte = new Date();
				formatter = new SimpleDateFormat("dd/MM/yyyy");
				String fechaCorteS = formatter.format(fechaCorte);
				System.out.println("Inicio JOB: Ejecuci�n Estado Cuenta Cierre PRIMAX " + " " + fechaCorteS);
				pathScriptP = RutaFileEnum.RUTA_SCRIPT_EJECUTA_CIERRE_DIARIO_P.getDescripcion() + " " + "&";
				Runtime.getRuntime().exec("/bin/sh " + pathScriptP);
				System.out.println("Fin JOB: Ejecuci�n Estado Cuenta Cierre PRIMAX " + " " + fechaCorteS);
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getMessage() + " ERROR EST CUENTA CIERRE DIARIO :WorkerEstadoCuentaDiarioP");
			} finally {

			}
		}
	}
}
