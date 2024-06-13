package com.primax.srv.idao;

import java.util.List;

import com.primax.bean.util.EntidadNoEncontradaException;
import com.primax.bean.util.EntidadNoGrabadaException;
import com.primax.jpa.gen.ClienteEt;
import com.primax.jpa.gen.PersonaEt;
import com.primax.jpa.sec.UsuarioEt;
import com.primax.srv.dao.base.IGenericDao;

public interface IClienteDao extends IGenericDao<ClienteEt, Long> {

	public void remove();

	public void clear();

	public ClienteEt getClientebyIdJDE(Long idClienteJDE) throws EntidadNoEncontradaException;

	public List<ClienteEt> getListCliente(String condicion) throws EntidadNoEncontradaException;

	public void guardarCliente(ClienteEt cliente, UsuarioEt usuario) throws EntidadNoGrabadaException;

	public List<ClienteEt> getClientebyIdPersona(PersonaEt persona) throws EntidadNoEncontradaException;

	public List<ClienteEt> getClientebyWebService(PersonaEt persona) throws EntidadNoEncontradaException;

	public List<ClienteEt> getListClienteAtrasados(ClienteEt cliente) throws EntidadNoEncontradaException;

	public List<ClienteEt> getClientebyWebService0(PersonaEt persona) throws EntidadNoEncontradaException;

	public ClienteEt getClientebyIdJDEUnique0(Long idClienteJDE, String negocio) throws EntidadNoEncontradaException;

	public List<ClienteEt> getClientebyWebService(PersonaEt persona, String negocio) throws EntidadNoEncontradaException;

}
