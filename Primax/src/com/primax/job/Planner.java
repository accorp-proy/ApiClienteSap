package com.primax.job;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

@Singleton
@Startup
public class Planner {

	@PostConstruct
	public void init() {

		Trigger triggerProcesarFac = TriggerBuilder.newTrigger().withIdentity("TRG-ACT-FAC", "GRP-ACT-PFAC")
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0/20 * 1/1 * ? *")).build();

		/**
		 * Ejecuta Estado Cuenta Cierre Diario PRIMAX
		 */
		JobDetail jobEstCuentaCierreP = JobBuilder.newJob(WorkerEstadoCuentaDiarioP.class)
				.withIdentity("JOB-EJE-CIE", "GRP-EJE-CIEP").build();
		Trigger triggerEstCuentaCierreP = TriggerBuilder.newTrigger().withIdentity("TRG-EJE-CIEP", "GRP-EJE-CIEP")
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0 0/6 1/1 * ? *")).build();

		/**
		 * Ejecuta Estado Cuenta Cierre Diario ATIMASA
		 */
		JobDetail jobEstCuentaCierreA = JobBuilder.newJob(WorkerEstadoCuentaDiarioA.class)
				.withIdentity("JOB-EJE-CIE", "GRP-EJE-CIEA").build();
		Trigger triggerEstCuentaCierreA = TriggerBuilder.newTrigger().withIdentity("TRG-EJE-CIEA", "GRP-EJE-CIEA")
				.withSchedule(CronScheduleBuilder.cronSchedule("0 0 1 1/1 * ? *")).build();

		// 0 0/45 * 1/1 * ? *
		// 0 0 3 1/1 * ? *

		

		/*
		 * indica el tiempo de suceso http://www.cronmaker.com/ 0 0/1 * 1/1 * ? 1 munuto
		 * 0 0 0/1 1/1 * ? * 1 hora
		 */
		Scheduler scheduler = null;
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			// scheduler.scheduleJob(jobEstCuentaCierreP,
			// triggerEstCuentaCierreP);//Comentar Temporal
			// scheduler.scheduleJob(jobEstCuentaCierreA,
			// triggerEstCuentaCierreA);//Comentar Temporal
			// scheduler.scheduleJob(job_alarm, trigger_alarm);
			// scheduler.scheduleJob(jobFacCon, triggerFacCon);

//			scheduler.scheduleJob(jobActEstFacCliente, triggerActEstFacCliente);
//			scheduler.scheduleJob(jobActNotaCredito, triggerActNotaCredito);
//			scheduler.scheduleJob(jobProcesarFac, triggerProcesarFac);
//			scheduler.scheduleJob(jobActPagoNC, triggerActPagoNC);
//			scheduler.scheduleJob(jobActFac, triggerActFac);

			// scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}

}
