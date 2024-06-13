package com.primax.web.service;

import java.util.ArrayList;

import javax.jws.HandlerChain;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.primax.ejb.lkp.BaseNaming;
import com.primax.ejb.lkp.EnumNaming;
import com.primax.jpa.enm.EstadoEnum;
import com.primax.jpa.gen.ClienteEt;
import com.primax.jpa.ws.ClienteAP;
import com.primax.jpa.ws.ClienteAPWrapper;
import com.primax.srv.idao.IClienteDao;
import com.primax.srv.idao.IPersonaDao;

@WebService(serviceName = "wsClientL")
@HandlerChain(file = "handlers.xml")
public class wsClientL extends BaseNaming {

	public String clientByIdJDE(@WebParam(name = "idClienteJDE") String idClienteJDE) {

		//EmpresaEt empresa = null;
		ClienteAP clienteAP = null;
		ClienteAPWrapper clienteAPWrapper = null;
		IPersonaDao iPersonaDao = EJB(EnumNaming.IPersonaDao);
		IClienteDao iClienteDao = EJB(EnumNaming.IClienteDao);
		

		try {
			if (idClienteJDE.isEmpty() || idClienteJDE == null) {
				clienteAPWrapper = new ClienteAPWrapper();
				clienteAPWrapper.setDni("NONE");
				clienteAPWrapper.setRazonSocial("NONE");
				clienteAPWrapper.setDescripcionErr("Por favor Ingrese Codigo Cliente");
			} else {
				ClienteEt clienteExiste = null;
				if (isNumeric(idClienteJDE)) {
//					empresa = iEmpresaDao.getEmpresabyId(2L);
					clienteExiste = iClienteDao.getClientebyIdJDE(1L);
				}
				if (clienteExiste == null) {
					clienteAPWrapper = new ClienteAPWrapper();
					clienteAPWrapper.setDni("NONE");
					clienteAPWrapper.setRazonSocial("NONE");
					clienteAPWrapper.setDescripcionErr("Cliente con codigo" + " " + idClienteJDE + " " + " no Existe ");
				} else {
					//actualizarCliente(Long.parseLong(idClienteJDE), "LUBRICANTE", empresa);
					clienteAPWrapper = new ClienteAPWrapper();
					clienteAP = new ClienteAP();
					clienteAPWrapper.setIdClienteJDE(idClienteJDE);
					clienteAPWrapper.setDni(clienteExiste.getPersona().getIdentificacionPersona());
					clienteAPWrapper.setClientesAP(new ArrayList<ClienteAP>());
					clienteAPWrapper.setDescripcionErr("Consulta con Exito ");
					ClienteEt cliente = iClienteDao.getClientebyIdJDE(1L);
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
//					if (cliente.getVendedor() != null) {
//						clienteAP.setEjecutivoComercial(cliente.getVendedor().getPersona().getNombreCompleto());
//					} else {
//						clienteAP.setEjecutivoComercial("N/A");
//					}
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

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error :Metodo clientByIdJDE " + " " + e.getMessage());
		} finally {
			iPersonaDao.remove();
			iClienteDao.remove();
			
		}

		return clienteAPWrapper.parsetoXMl();
	}

	private boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
}
