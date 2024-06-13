package com.primax.srv.idao;

import java.util.List;

import com.primax.jpa.param.ParametrosGeneralesEt;
import com.primax.srv.dao.base.IGenericDao;

public interface IParametrolGeneralDao extends IGenericDao<ParametrosGeneralesEt, Long> {

	public List<ParametrosGeneralesEt> getListaParametro(String condicion,Long nivel);

	public List<ParametrosGeneralesEt> getListaHIjos(ParametrosGeneralesEt par);

	public ParametrosGeneralesEt getObjPadre(String condiction);

	public ParametrosGeneralesEt getParametrosGeneralById(Long value);

	public ParametrosGeneralesEt getTipoFiscalizador(String tipo);

	public List<ParametrosGeneralesEt> getParametrosFechaActualizacion();

	public void remove();

}
