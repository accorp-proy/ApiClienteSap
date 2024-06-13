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

import com.primax.bean.util.EntidadNoEncontradaException;
import com.primax.bean.util.EntidadNoGrabadaException;
import com.primax.jpa.enm.ActionAuditedEnum;
import com.primax.jpa.enm.EstadoEnum;
import com.primax.jpa.gen.ClienteEt;
import com.primax.jpa.gen.PersonaEt;
import com.primax.jpa.sec.UsuarioEt;
import com.primax.srv.dao.base.GenericDao;
import com.primax.srv.idao.IClienteDao;

@Stateful
@StatefulTimeout(unit = TimeUnit.HOURS, value = 8)
public class ClienteDao extends GenericDao<ClienteEt, Long> implements IClienteDao {

	public ClienteDao() {
		super(ClienteEt.class);
	}

	private StringBuilder sql;

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void guardarCliente(ClienteEt cliente, UsuarioEt usuario) throws EntidadNoGrabadaException {
		if (cliente.getIdCliente() == null) {
			cliente.audit(usuario, ActionAuditedEnum.NEW);
			crear(cliente);
		} else {
			cliente.audit(usuario, ActionAuditedEnum.UPD);
			actualizar(cliente);
		}
		em.flush();
		em.clear();
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ClienteEt> getListCliente(String condicion) throws EntidadNoEncontradaException {
		sql = new StringBuilder("FROM ClienteEt o ");
		sql.append(" WHERE o.estado= :estado");
		if (condicion != null && !condicion.isEmpty()) {
			sql.append(" AND ( (UPPER(o.persona.nombreCompleto)|| UPPER(o.persona.nombreComercial)) LIKE :condicion ");
			sql.append(" OR  o.persona.identificacionPersona  = :condicion1 ) ");
			boolean isNumero = this.isNumeric(condicion);
			if (isNumero) {
				sql.append(" OR  o.idClienteJDE  = :condicion2 ) ");
			}
		}
		TypedQuery<ClienteEt> query = em.createQuery(sql.toString(), ClienteEt.class);
		query.setParameter("estado", EstadoEnum.ACT);

		if (condicion != null && !condicion.isEmpty()) {
			query.setParameter("condicion", "%" + condicion.toUpperCase() + "%");
			query.setParameter("condicion1", condicion);
			boolean isNumero = this.isNumeric(condicion);
			if (isNumero) {
				query.setParameter("condicion2", Long.parseLong(condicion));
			}
		} else {
			query.setMaxResults(100);
		}
		List<ClienteEt> result = query.getResultList();

		return result;
	}

	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ClienteEt getClientebyIdJDE(Long idClienteJDE) throws EntidadNoEncontradaException {
		sql = new StringBuilder("FROM ClienteEt o ");
		sql.append(" WHERE o.estado = :estado ");
		sql.append(" AND   o.idClienteJDE = :idClienteJDE ");
		TypedQuery<ClienteEt> query = em.createQuery(sql.toString(), ClienteEt.class);
		query.setParameter("estado", EstadoEnum.ACT);
		query.setParameter("idClienteJDE", idClienteJDE);
		List<ClienteEt> result = query.getResultList();
		return getUnique(result);
	}

	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ClienteEt getClientebyIdJDEUnique0(Long idClienteJDE, String negocio) throws EntidadNoEncontradaException {
		sql = new StringBuilder("FROM ClienteEt o ");
		sql.append(" WHERE o.estado = :estado ");
		sql.append(" AND   o.idClienteJDE = :idClienteJDE ");
		sql.append(" AND   o.companiaVirtual.descripcionAlterna like :negocio ");
		TypedQuery<ClienteEt> query = em.createQuery(sql.toString(), ClienteEt.class);
		query.setParameter("estado", EstadoEnum.ACT);
		query.setParameter("idClienteJDE", idClienteJDE);
		query.setParameter("negocio", "%" + negocio + "%");
		List<ClienteEt> result = query.getResultList();
		return getUnique(result);
	}

	

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ClienteEt> getClientebyIdPersona(PersonaEt persona) throws EntidadNoEncontradaException {
		sql = new StringBuilder("FROM ClienteEt o ");
		sql.append(" WHERE o.estado  = :estado    ");
		sql.append(" AND   o.persona = :persona   ");
		TypedQuery<ClienteEt> query = em.createQuery(sql.toString(), ClienteEt.class);
		query.setParameter("estado", EstadoEnum.ACT);
		query.setParameter("persona", persona);
		List<ClienteEt> result = query.getResultList();
		return result;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ClienteEt> getClientebyWebService(PersonaEt persona, String negocio) throws EntidadNoEncontradaException {
		sql = new StringBuilder("FROM ClienteEt o ");
		sql.append(" WHERE o.estado  = :estado    ");
		sql.append(" AND   o.persona = :persona   ");
		sql.append(" AND   o.companiaVirtual.descripcionAlterna like :negocio ");
		TypedQuery<ClienteEt> query = em.createQuery(sql.toString(), ClienteEt.class);
		query.setParameter("estado", EstadoEnum.ACT);
		query.setParameter("persona", persona);
		query.setParameter("negocio", "%" + negocio + "%");
		List<ClienteEt> result = query.getResultList();
		return result;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ClienteEt> getClientebyWebService(PersonaEt persona) throws EntidadNoEncontradaException {
		sql = new StringBuilder("FROM ClienteEt o ");
		sql.append(" WHERE o.estado  = :estado    ");
		sql.append(" AND   o.persona = :persona   ");
		TypedQuery<ClienteEt> query = em.createQuery(sql.toString(), ClienteEt.class);
		query.setParameter("estado", EstadoEnum.ACT);
		query.setParameter("persona", persona);
		List<ClienteEt> result = query.getResultList();
		return result;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ClienteEt> getClientebyWebService0(PersonaEt persona) throws EntidadNoEncontradaException {
		sql = new StringBuilder("FROM ClienteEt o ");
		sql.append(" WHERE o.estado  = :estado    ");
		sql.append(" AND   o.categoria21 is not null ");
		sql.append(" AND   o.persona = :persona   ");
		TypedQuery<ClienteEt> query = em.createQuery(sql.toString(), ClienteEt.class);
		query.setParameter("estado", EstadoEnum.ACT);
		query.setParameter("persona", persona);
		List<ClienteEt> result = query.getResultList();
		return result;
	}

	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ClienteEt> getListClienteAtrasados(ClienteEt cliente) throws EntidadNoEncontradaException {
		sql = new StringBuilder("SELECT o.cliente FROM GestionClienteCarteraEt o ");
		sql.append(" WHERE o.estado= :estado");
		if (cliente != null) {
			sql.append(" AND  o.cliente = :cliente ) ");
		}
		TypedQuery<ClienteEt> query = em.createQuery(sql.toString(), ClienteEt.class);
		query.setParameter("estado", EstadoEnum.ACT);
		if (cliente != null) {
			query.setParameter("cliente", cliente);
		} else {
			query.setMaxResults(100);
		}
		List<ClienteEt> result = query.getResultList();
		return result;
	}

	public boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	

	public void clear() {
		em.clear();
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
