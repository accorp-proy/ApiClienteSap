package com.primax.bean.vs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.primax.bean.ss.AppMain;
import com.primax.bean.util.EntidadNoEncontradaException;
import com.primax.bean.util.EntidadNoGrabadaException;
import com.primax.bean.vs.base.BaseBean;
import com.primax.jpa.enm.EstadoEnum;
import com.primax.jpa.param.ParametrosGeneralesEt;
import com.primax.srv.idao.IParametrolGeneralDao;


@Named("parametroGeneralBn")
@ViewScoped
public class ParametroGeneralBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 2246949885696398626L;

	@EJB
	private IParametrolGeneralDao iparametroGeneral;
	
	private Long nivel = 1L;
	private ParametrosGeneralesEt parametroGeneralPadreSeleccionado = new ParametrosGeneralesEt();
	private ParametrosGeneralesEt parametroGeneralPadreEliminar = new ParametrosGeneralesEt();
	private ParametrosGeneralesEt parametroGeneralHijoSeleccionado = new ParametrosGeneralesEt();
	private List<ParametrosGeneralesEt> listaParametrosGeneralesHijosEliminar = new ArrayList<>();
	private List<ParametrosGeneralesEt> listaParametrosGenerales = new ArrayList<>();
	private String txtbuscar = "";

	@Inject
	private AppMain appMain;

	public void init() {
		listaParametrosGenerales = iparametroGeneral.getListaParametro(null, nivel);
	}

	public Long getNivel() {
		return nivel;
	}

	public void setNivel(Long nivel) {
		this.nivel = nivel;
	}

	public void buscarParametros() {
		listaParametrosGenerales = iparametroGeneral.getListaParametro(txtbuscar, nivel);
	}

	public void nuevoParametro() {
		parametroGeneralPadreSeleccionado = new ParametrosGeneralesEt();
	}

	public void cargarParametroPadreSeleccionado(ParametrosGeneralesEt par) {
		try {
			iparametroGeneral.clear();
			parametroGeneralPadreSeleccionado = iparametroGeneral.recuperar(par.getIdParametroGeneral());
		} catch (EntidadNoEncontradaException e) {
			e.printStackTrace();
		}
		listaParametrosGeneralesHijosEliminar.clear();
	}

	public void cargarParametroHijoSeleccionado(ParametrosGeneralesEt par) {
		parametroGeneralHijoSeleccionado = par;
	}

	public void agregarHijo() {
		if (parametroGeneralHijoSeleccionado != null && parametroGeneralHijoSeleccionado.getNombreLista() != null
				&& !parametroGeneralHijoSeleccionado.getNombreLista().isEmpty()) {
			parametroGeneralHijoSeleccionado.setParametroPadre(parametroGeneralPadreSeleccionado);
			parametroGeneralHijoSeleccionado.setNivel(2L);
			parametroGeneralPadreSeleccionado.getParametros().remove(parametroGeneralHijoSeleccionado);
			parametroGeneralPadreSeleccionado.getParametros().add(parametroGeneralHijoSeleccionado);
			parametroGeneralHijoSeleccionado = new ParametrosGeneralesEt();
		} else {
			showInfo("Debe ingresar una descripcion para agregar parametro", FacesMessage.SEVERITY_INFO);
		}
	}

	public void guardar() {
		if (listaParametrosGeneralesHijosEliminar.size() > 0) {
			for (ParametrosGeneralesEt parEli : listaParametrosGeneralesHijosEliminar) {
				parametroGeneralPadreSeleccionado.getParametros().add(parEli);
			}
			listaParametrosGeneralesHijosEliminar.clear();
		}
		try {
			if (parametroGeneralPadreSeleccionado.getIdParametroGeneral() == null) {
				parametroGeneralPadreSeleccionado.setUsuarioRegistra(appMain.getUsuario());
				parametroGeneralPadreSeleccionado.setNivel(1L);
				iparametroGeneral.crear(parametroGeneralPadreSeleccionado);
			} else {
				parametroGeneralPadreSeleccionado.setFechaModificacion(new Date());
				parametroGeneralPadreSeleccionado.setUsuarioRegistra(appMain.getUsuario());
				iparametroGeneral.actualizar(parametroGeneralPadreSeleccionado);
			}
			cargarParametroPadreSeleccionado(parametroGeneralPadreSeleccionado);
			buscarParametros();
			showInfo("Dato Guardado", FacesMessage.SEVERITY_INFO);
		} catch (EntidadNoGrabadaException e) {
			e.printStackTrace();
		}
	}

	public void cargarParametroPadreEliminar(ParametrosGeneralesEt par) {
		parametroGeneralPadreEliminar = par;
	}

	public void eliminarParametroPadre() {
		parametroGeneralPadreEliminar.setEstado(EstadoEnum.ELI);
		parametroGeneralPadreEliminar.setFechaModificacion(new Date());
		parametroGeneralPadreEliminar.setUsuarioRegistra(appMain.getUsuario());
		for (ParametrosGeneralesEt parhijo : parametroGeneralPadreEliminar.getParametros()) {
			parhijo.setEstado(EstadoEnum.ELI);
			parhijo.setFechaModificacion(new Date());
			parhijo.setUsuarioRegistra(appMain.getUsuario());
		}
		try {
			iparametroGeneral.actualizar(parametroGeneralPadreEliminar);
		} catch (EntidadNoGrabadaException e) {
			e.printStackTrace();
		}
		parametroGeneralPadreEliminar = new ParametrosGeneralesEt();
		buscarParametros();
		showInfo("Parametro Eliminado", FacesMessage.SEVERITY_INFO);
	}

	public void eliminarParametroHijo(ParametrosGeneralesEt par) {
		parametroGeneralPadreSeleccionado.getParametros().remove(par);
		if (par.getIdParametroGeneral() != null) {
			par.setEstado(EstadoEnum.ELI);
			par.setFechaModificacion(new Date());
			par.setUsuarioRegistra(appMain.getUsuario());
			listaParametrosGeneralesHijosEliminar.remove(par);
			listaParametrosGeneralesHijosEliminar.add(par);
			parametroGeneralPadreSeleccionado.getParametros().remove(par);
		}
	}
	
	public SelectItem[] getEstadosActIna() {
		SelectItem[] items = new SelectItem[2];
		items[0] = new SelectItem(EstadoEnum.ACT, EstadoEnum.ACT.getDescripcion());
		items[1] = new SelectItem(EstadoEnum.INA, EstadoEnum.INA.getDescripcion());
		return items;
	}

	public ParametrosGeneralesEt getParametroGeneralPadreSeleccionado() {
		return parametroGeneralPadreSeleccionado;
	}

	public void setParametroGeneralPadreSeleccionado(ParametrosGeneralesEt parametroGeneralPadreSeleccionado) {
		this.parametroGeneralPadreSeleccionado = parametroGeneralPadreSeleccionado;
	}

	public ParametrosGeneralesEt getParametroGeneralHijoSeleccionado() {
		return parametroGeneralHijoSeleccionado;
	}

	public void setParametroGeneralHijoSeleccionado(ParametrosGeneralesEt parametroGeneralHijoSeleccionado) {
		this.parametroGeneralHijoSeleccionado = parametroGeneralHijoSeleccionado;
	}

	public List<ParametrosGeneralesEt> getListaParametrosGenerales() {
		return listaParametrosGenerales;
	}

	public void setListaParametrosGenerales(List<ParametrosGeneralesEt> listaParametrosGenerales) {
		this.listaParametrosGenerales = listaParametrosGenerales;
	}

	public String getTxtbuscar() {
		return txtbuscar;
	}

	public void setTxtbuscar(String txtbuscar) {
		this.txtbuscar = txtbuscar;
	}

	public ParametrosGeneralesEt getParametroGeneralPadreEliminar() {
		return parametroGeneralPadreEliminar;
	}

	public void setParametroGeneralPadreEliminar(ParametrosGeneralesEt parametroGeneralPadreEliminar) {
		this.parametroGeneralPadreEliminar = parametroGeneralPadreEliminar;
	}

	public List<ParametrosGeneralesEt> getListaParametrosGeneralesHijosEliminar() {
		return listaParametrosGeneralesHijosEliminar;
	}

	public void setListaParametrosGeneralesHijosEliminar(List<ParametrosGeneralesEt> listaParametrosGeneralesHijosEliminar) {
		this.listaParametrosGeneralesHijosEliminar = listaParametrosGeneralesHijosEliminar;
	}

	public AppMain getAppMain() {
		return appMain;
	}

	public void setAppMain(AppMain appMain) {
		this.appMain = appMain;
	}

	@Override
	public void onDestroy() {
		iparametroGeneral.remove();
	}
}
