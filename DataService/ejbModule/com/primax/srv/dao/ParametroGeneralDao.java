package com.primax.srv.dao;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;

import com.primax.jpa.param.ParametrosGeneralesEt;
import com.primax.srv.dao.base.GenericDao;
import com.primax.srv.idao.IParametrolGeneralDao;
import com.primax.srv.util.QUL;

@Stateful
@StatefulTimeout(unit = TimeUnit.HOURS, value = 8)
public class ParametroGeneralDao extends GenericDao<ParametrosGeneralesEt, Long> implements IParametrolGeneralDao {

	public ParametroGeneralDao() {
		super(ParametrosGeneralesEt.class);
	}

	private StringBuilder sql;

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ParametrosGeneralesEt> getListaParametro(String condicion, Long nivel) {
		sql = new StringBuilder("from ParametrosGeneralesEt p ");
		sql.append("where p.nivel = :nivel ");

		if (condicion != null && !condicion.isEmpty()) {
			sql.append(" and (p.idParametroGeneral = :idCondicion or 0= :idCondicion) ");
			sql.append("and (p.nombreLista like :nomLista or ''= :nomLista) ");
		}

		TypedQuery<ParametrosGeneralesEt> query = em.createQuery(sql.toString(), ParametrosGeneralesEt.class);
		query.setParameter("nivel", nivel);
		if (condicion != null && !condicion.isEmpty()) {
			query.setParameter("idCondicion", QUL.toLong(condicion));
			query.setParameter("nomLista", "%" + QUL.getString(condicion).toUpperCase() + "%");

		}

		List<ParametrosGeneralesEt> result = query.getResultList();

		return result;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ParametrosGeneralesEt> getListaHIjos(ParametrosGeneralesEt par) {
		sql = new StringBuilder("from ParametrosGeneralesEt p ");
		sql.append("where p.nivel = 2 ");
		if (par != null) {
			sql.append(" and (p.parametroPadre = :idCondicion ) ");
		}
		sql.append(" and p.estado='ACT' ");
		sql.append("order by p.idParametroGeneral");
		TypedQuery<ParametrosGeneralesEt> query = em.createQuery(sql.toString(), ParametrosGeneralesEt.class);
		if (par != null) {
			query.setParameter("idCondicion", par);
		}
		List<ParametrosGeneralesEt> result = query.getResultList();
		return result;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ParametrosGeneralesEt getObjPadre(String condicion) {
		sql = new StringBuilder("from ParametrosGeneralesEt p ");
		sql.append("where p.nivel = 1 ");

		if (condicion != null && !condicion.isEmpty()) {
			sql.append(" and (p.idParametroGeneral = :idCondicion or 0= :idCondicion) ");
			sql.append("and (p.nombreLista like :nomLista or ''= :nomLista) ");
		}
		TypedQuery<ParametrosGeneralesEt> query = em.createQuery(sql.toString(), ParametrosGeneralesEt.class);
		if (condicion != null && !condicion.isEmpty()) {
			query.setParameter("idCondicion", QUL.toLong(condicion));
			query.setParameter("nomLista", "%" + QUL.getString(condicion).toUpperCase() + "%");
		}
		List<ParametrosGeneralesEt> result = query.getResultList();

		return getUnique(result);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ParametrosGeneralesEt getParametrosGeneralById(Long value) {
		sql = new StringBuilder("from ParametrosGeneralesEt p ");
		if (value != null) {
			sql.append("where (p.idParametroGeneral = :idCondicion or 0= :idCondicion) ");
		}
		TypedQuery<ParametrosGeneralesEt> query = em.createQuery(sql.toString(), ParametrosGeneralesEt.class);
		if (value != null) {
			query.setParameter("idCondicion", value);
		}
		List<ParametrosGeneralesEt> result = query.getResultList();
		return getUnique(result);

	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ParametrosGeneralesEt getTipoFiscalizador(String tipo) {
		List<ParametrosGeneralesEt> tipoFisc = getListaParametro(tipo, 1L);
		return getUnique(tipoFisc);
	}

	@Override
	public List<ParametrosGeneralesEt> getParametrosFechaActualizacion() {
		try {
			sql = new StringBuilder("from ParametrosGeneralesEt u ");
			sql.append("where u.estado = 'ACT' order by idParametroGeneral ");

			TypedQuery<ParametrosGeneralesEt> query = em.createQuery(sql.toString(), ParametrosGeneralesEt.class);

			List<ParametrosGeneralesEt> result = query.getResultList();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Remove
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void remove() {
		System.out.println("Finalizado Statefull Bean : " + this.getClass().getCanonicalName());
	}

	@PreDestroy
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void detached() {
		System.out.println("Terminado Statefull Bean : " + this.getClass().getCanonicalName());
	}

}
