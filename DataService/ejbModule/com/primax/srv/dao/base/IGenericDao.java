package com.primax.srv.dao.base;

import java.io.Serializable;
import java.util.List;

import com.primax.bean.util.EntidadNoBorradaException;
import com.primax.bean.util.EntidadNoEncontradaException;
import com.primax.bean.util.EntidadNoGrabadaException;
import com.primax.jpa.enm.EstadoEnum;

public interface IGenericDao<T, P extends Serializable> {

	public void crearObject(Object o) throws EntidadNoGrabadaException;

	public void actualizarObject(Object o) throws EntidadNoGrabadaException;

	public void crear(T o) throws EntidadNoGrabadaException;

	public void actualizar(T o) throws EntidadNoGrabadaException;

	public void eliminar(T o) throws EntidadNoBorradaException;

	public void eliminarObject(Object o) throws EntidadNoBorradaException;

	public T recuperar(P o) throws EntidadNoEncontradaException;

	public List<T> obtenerTodos();

	public int contar(EstadoEnum estado);

	public void clear();
}
