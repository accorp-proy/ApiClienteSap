package com.primax.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.HandlerChain;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.primax.ejb.lkp.BaseNaming;
import com.primax.ejb.lkp.EnumNaming;
import com.primax.jpa.enm.EstadoEnum;
import com.primax.jpa.enm.TipoPersonaEnum;
import com.primax.jpa.gen.ClienteEt;
import com.primax.jpa.gen.PersonaEt;
import com.primax.jpa.ws.ClienteAP;
import com.primax.jpa.ws.ClienteAPWrapper;
import com.primax.srv.idao.IClienteDao;
import com.primax.srv.idao.IPersonaDao;

@WebService(serviceName = "wsClientAP")
@HandlerChain(file = "handlers.xml")
public class wsClientAP extends BaseNaming {

	public String clientbyDNI(@WebParam(name = "dni") String dni) {

		IPersonaDao iPersonaDao = EJB(EnumNaming.IPersonaDao);
		IClienteDao iClienteDao = EJB(EnumNaming.IClienteDao);
		ClienteAPWrapper clienteAPWrapper = null;
		ClienteAP clienteAP = null;
		try {
			if (dni.isEmpty() || dni == null) {
				clienteAPWrapper = new ClienteAPWrapper();
				clienteAPWrapper.setDni("NONE");
				clienteAPWrapper.setRazonSocial("NONE");
				clienteAPWrapper.setDescripcionErr("Por favor Ingrese DNI");
			} else {
				PersonaEt persona = iPersonaDao.getPersonaById(1L);
				if (persona == null) {
					clienteAPWrapper = new ClienteAPWrapper();
					clienteAPWrapper.setDni("NONE");
					clienteAPWrapper.setRazonSocial("NONE");
					clienteAPWrapper.setDescripcionErr("Cliente con DNI" + " " + dni + " " + " no Existe ");
				} else {
					//actualizarPersona(persona);
					clienteAPWrapper = new ClienteAPWrapper();
					clienteAP = new ClienteAP();
					clienteAPWrapper.setDni(persona.getIdentificacionPersona());
					clienteAPWrapper.setClientesAP(new ArrayList<ClienteAP>());
					clienteAPWrapper.setDescripcionErr("Consulta con �xito ");
					List<ClienteEt> clientes = iClienteDao.getClientebyWebService0(persona);
					for (ClienteEt cliente : clientes) {
						clienteAP = new ClienteAP();
						clienteAP.setDireccion(cliente.getDireccion());
						clienteAP.setNombreAlfa(cliente.getNombreAlfa());
						clienteAP.setClassOfMarket(cliente.getClassOfMarket());
						clienteAP.setIdJDE(cliente.getIdClienteJDE().toString());
						clienteAPWrapper.setRazonSocial(cliente.getNombreComercial());
						clienteAP.setDeudaUno30(String.valueOf(cliente.getDeudaUno30()));
						clienteAP.setDeudaMas30(String.valueOf(cliente.getDeudaMas30()));
						clienteAP.setCategoria21(cliente.getCategoria21().getDescripcion());
						clienteAP.setCategoria26(cliente.getCategoria26().getDescripcion());
						clienteAP.setCategoria27(cliente.getCategoria27().getDescripcion());
						clienteAP.setSaldoVencido(String.valueOf(cliente.getSaldoVencido()));
						if (cliente.getEstadoCuenta().equals(EstadoEnum.ACT)) {
							clienteAP.setEstadoCuenta("ACTIVO");
							if (cliente.getParTipoCredito().getIdParametroGeneral() == 19L) {
								clienteAP.setTipoCliente("CREDITO");
							} else {
								clienteAP.setTipoCliente("PREPAGO");
							}
						} else {
							clienteAP.setEstadoCuenta("BLOQUEADO");
							clienteAP.setTipoCliente("INACTIVO");
						}

						if (cliente.getCorreo() == null || cliente.getCorreo().equals("")) {
							clienteAP.setCorreo("N/A");
						} else {
							clienteAP.setCorreo(cliente.getCorreo());
						}
//						if (cliente.getVendedor() != null) {
//							clienteAP.setEjecutivoComercial(cliente.getVendedor().getPersona().getNombreCompleto());
//						} else {
//							clienteAP.setEjecutivoComercial("N/A");
//						}
						if (cliente.getCodigoFacturador() != 0L) {
							clienteAP.setFacturadorAsignado(cliente.getNombreFacturador());
						} else {
							clienteAP.setFacturadorAsignado("N/A");
						}
						clienteAP.setEstado(cliente.getEstado().getDescripcion());
						if (cliente.getProvincia() != null) {
							clienteAP.setProvincia(cliente.getProvincia() == null ? "NONE" : cliente.getProvincia());
							clienteAP.setCanton(cliente.getCanton() == null ? "NONE" : cliente.getCanton());
						}
						clienteAPWrapper.getClientesAP().add(clienteAP);
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error :M�todo clientbyDNI " + " " + e.getMessage());
		} finally {
			iPersonaDao.remove();
			iClienteDao.remove();
		}

		return clienteAPWrapper.parsetoXMl();
	}

	

}
