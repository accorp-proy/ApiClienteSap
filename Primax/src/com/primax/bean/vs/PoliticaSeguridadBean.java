package com.primax.bean.vs;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.primax.bean.util.EntidadNoGrabadaException;
import com.primax.bean.vs.base.BaseBean;
import com.primax.jpa.enm.EstadoEnum;
import com.primax.jpa.sec.PoliticaSeguridadEt;
import com.primax.srv.idao.IPoliticaSeguridadDao;

@Named("PoliticaSeguridadBn")
@ViewScoped
public class PoliticaSeguridadBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 349410903845516545L;

	private PoliticaSeguridadEt politicaSeguridad;
	private PoliticaSeguridadEt clonPoliticaSeguridad;

	@EJB
	private IPoliticaSeguridadDao iPolitica;

	@Override
	public void init() {
		politicaSeguridad = iPolitica.getpoliticaSeguridad();
		if (politicaSeguridad == null)
			politicaSeguridad = new PoliticaSeguridadEt();
	}

	public void getModificarPolitica(PoliticaSeguridadEt politica) {
		politicaSeguridad = politica;
	}

	public void cancelar() {
		init();
		showInfo("Notificaci�n", FacesMessage.SEVERITY_INFO, null, "Se cancelo la modificaci�n");
	}

	public void getGuardar() {
		try {
			clonPoliticaSeguridad = new PoliticaSeguridadEt(politicaSeguridad);
			if (politicaSeguridad.getIdPoliticaSeguridad() == null) {
				politicaSeguridad.setEstado(EstadoEnum.ACT);
				iPolitica.crear(politicaSeguridad);
			} else {
				politicaSeguridad.setEstado(EstadoEnum.INA);
				iPolitica.actualizar(politicaSeguridad);

				clonPoliticaSeguridad.setEstado(EstadoEnum.ACT);
				clonPoliticaSeguridad.setIdPoliticaSeguridad(null);
				iPolitica.crear(clonPoliticaSeguridad);
			}
			showInfo("Notificaci�n", FacesMessage.SEVERITY_INFO, null, "Se actualiz� la pol�tica de seguridad");
		} catch (EntidadNoGrabadaException e) {
			e.printStackTrace();
		}
	}

	public PoliticaSeguridadEt getPoliticaSeguridad() {
		return politicaSeguridad;
	}

	public void setPoliticaSeguridad(PoliticaSeguridadEt politicaSeguridad) {
		this.politicaSeguridad = politicaSeguridad;
	}

	@Override
	public void onDestroy() {
		iPolitica.remove();
	}

}
