package com.primax.web.service;

import java.util.ArrayList;

import javax.jws.HandlerChain;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.primax.ejb.lkp.BaseNaming;
import com.primax.jpa.gen.ClienteSap;
import com.primax.jpa.ws.ClienteAP;
import com.primax.jpa.ws.ClienteAPWrapper;
import com.primax.srv.msg.SrvConsultaSAP;

@WebService(serviceName = "wsClientC")
@HandlerChain(file = "handlers.xml")
public class wsClientC extends BaseNaming {

	public String clientByIdJDE(@WebParam(name = "idClienteJDE") String idClienteJDE) {

		ClienteAP clienteAP = null;
		ClienteAPWrapper clienteAPWrapper = null;

		try {
			if (idClienteJDE.isEmpty() || idClienteJDE == null) {
				clienteAPWrapper = new ClienteAPWrapper();
				clienteAPWrapper.setDni("NONE");
				clienteAPWrapper.setRazonSocial("NONE");
				clienteAPWrapper.setDescripcionErr("Por favor Ingrese Codigo Cliente");
			} else {
				ClienteSap clienteExiste = null;
				if (isNumeric(idClienteJDE)) {
					SrvConsultaSAP srvConsultaSAP = new SrvConsultaSAP();
					clienteExiste = srvConsultaSAP.consultarWsSAP(idClienteJDE);
				}
				if (clienteExiste == null) {
					clienteAPWrapper = new ClienteAPWrapper();
					clienteAPWrapper.setDni("NONE");
					clienteAPWrapper.setRazonSocial("NONE");
					clienteAPWrapper.setDescripcionErr("Cliente con codigo" + " " + idClienteJDE + " no Existe ");
				} else {
					clienteAPWrapper = new ClienteAPWrapper();
					clienteAP = new ClienteAP();
					clienteAPWrapper.setIdClienteJDE(idClienteJDE);
					clienteAPWrapper.setDni(clienteExiste.getRuc());
					clienteAPWrapper.setClientesAP(new ArrayList<ClienteAP>());
					clienteAPWrapper.setDescripcionErr("Consulta con Exito ");
					clienteAP.setDireccion("N/A");
					clienteAP.setNombreAlfa(clienteExiste.getRazonSocial());
					clienteAP.setClassOfMarket("N/A");
					clienteAP.setIdJDE(idClienteJDE);
					clienteAPWrapper.setRazonSocial(clienteExiste.getRazonSocial());
					clienteAP.setDeudaUno30("N/A");
					clienteAP.setDeudaMas30("N/A");
					clienteAP.setCategoria21("N/A");
					clienteAP.setCategoria26(clienteExiste.getPuntuacion());
					clienteAP.setCategoria27(clienteExiste.getCategoria());
					clienteAP.setSaldoVencido("N/A");
					clienteAP.setTipoCliente("N/A");
					clienteAP.setEstadoCuenta("N/A");
					clienteAP.setCorreo("N/A");
					clienteAP.setEjecutivoComercial("N/A");
					clienteAP.setFacturadorAsignado("N/A");
					clienteAP.setEstado("N/A");
					clienteAP.setProvincia("N/A");
					clienteAP.setCanton("N/A");
					clienteAPWrapper.getClientesAP().add(clienteAP);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error :Metodo clientByIdJDE " + " " + e.getMessage());
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
