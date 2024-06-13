package com.primax.bean.vs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.primefaces.context.RequestContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.primax.bean.ss.AppMain;
import com.primax.bean.vs.base.BaseBean;
import com.primax.jpa.enm.ParametrosGeneralesEnum;
import com.primax.jpa.enm.TipoPersonaEnum;
import com.primax.jpa.gen.ClienteEt;
import com.primax.jpa.gen.ClienteSap;
import com.primax.jpa.gen.PersonaEt;
import com.primax.jpa.param.ParametrosGeneralesEt;
import com.primax.jpa.sec.UsuarioEt;
import com.primax.srv.idao.IClienteDao;
import com.primax.srv.idao.IParametrolGeneralDao;
import com.primax.srv.idao.IPersonaDao;

@Named("clienteBn")
@ViewScoped
public class ClienteBean extends BaseBean implements Serializable {

	/**
	 * Primax ,Desarrollador Jefferson Maji
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private IClienteDao iClienteDao;
	@EJB
	private IPersonaDao iPersonaDao;
	@EJB
	private IParametrolGeneralDao iParametrolGeneralDao;

	@Inject
	private AppMain appMain;

	private PersonaEt personaSeleccionada;

	private String condicion;
	private String condicionVendedor;
	private Double limiteCredito = 0.0D;

	private List<PersonaEt> personas;
	private List<ClienteEt> clientes;

	@Override
	protected void init() {
		inicializarObj();
		// buscar();
		consultarWsSAP(condicion);

	}

	public void inicializarObj() {
		personaSeleccionada = new PersonaEt();
		personaSeleccionada.setClientes(new ArrayList<>());
		condicion = "";
	}

	public ClienteSap consultarWsSAP(String codCliente) {
		ClienteSap clienteSap = null;
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("CODCLIENTE", "1041839");
			System.out.println(jsonObject.toString());
			String user = "EC_PI_INCONCERT";
			String password = "V2*&zcGv6^A4";
			String auth = user + ":" + password;
			byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
			String authHeaderValue = "Basic " + new String(encodedAuth);
			URL object = new URL("http://10.55.40.220:50000/RESTAdapter/INCONCERT/EC/Formulario");
			HttpURLConnection con = (HttpURLConnection) object.openConnection();
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("Authorization", authHeaderValue);
			try (OutputStream os = con.getOutputStream()) {
				byte[] input = jsonObject.toString().getBytes("utf-8");
				os.write(input, 0, input.length);
			}
			try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
				StringBuilder respuesta = new StringBuilder();
				String acumuladorRespuesta = null;
				while ((acumuladorRespuesta = br.readLine()) != null) {
					respuesta.append(acumuladorRespuesta.trim());
				}
				if (!respuesta.toString().equals("")) {
					ObjectMapper objectMapper = new ObjectMapper();
					clienteSap = objectMapper.readValue(respuesta.toString(), ClienteSap.class);
					System.out.println(respuesta.toString());
					System.out.println("RUC_______________" + " " + clienteSap.getRuc());
					System.out.println("Razón Social______" + " " + clienteSap.getRazonSocial());
					System.out.println("Categoría_________" + " " + clienteSap.getCategoria());
					System.out.println("Puntuación________" + " " + clienteSap.getPuntuacion());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error :Método enviarJson " + " " + e.getMessage());
		}
		return clienteSap;
	}

	public void buscar() {
		try {
			personas = iPersonaDao.getListPersonaCliente(TipoPersonaEnum.CLIENTE, condicion);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error :M�todo buscar " + " " + e.getMessage());
		}

	}

	public void guardar() {
		try {
			UsuarioEt usuario = appMain.getUsuario();
			personaSeleccionada.setPrimerNombre("");
			personaSeleccionada.setPrimerApellido("");
			personaSeleccionada.setFechaNacimiento(new Date());
			personaSeleccionada.setCodTipPersona(TipoPersonaEnum.CLIENTE.ordinal());
			iPersonaDao.guardarPersona(personaSeleccionada, usuario);
			showInfo("Dato Guardado", FacesMessage.SEVERITY_INFO, "mensajes", null);
			RequestContext.getCurrentInstance().execute("PF('dlg_car_002').hide()");
			buscar();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error :M�todo guardar " + " " + e.getMessage());
		}
	}

	public List<ParametrosGeneralesEt> getListaTipoIdentificacion() {
		ParametrosGeneralesEt parametro = new ParametrosGeneralesEt();
		try {
			parametro = iParametrolGeneralDao.getObjPadre(ParametrosGeneralesEnum.TIPO_IDENTIFICACION.getDescripcion());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error :M�todo getListaTipoIdentificacion " + " " + e.getMessage());
		}
		return parametro.getParametros();
	}

	public List<ParametrosGeneralesEt> getListaTipoCliente() {
		ParametrosGeneralesEt parametro = new ParametrosGeneralesEt();
		try {
			parametro = iParametrolGeneralDao.getObjPadre(ParametrosGeneralesEnum.TIPO_CLIENTE.getDescripcion());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error :M�todo getListaTipoCliente " + " " + e.getMessage());
		}
		return parametro.getParametros();
	}

	public List<ParametrosGeneralesEt> getListaTipoCredito() {
		ParametrosGeneralesEt parametro = new ParametrosGeneralesEt();
		try {
			parametro = iParametrolGeneralDao.getObjPadre(ParametrosGeneralesEnum.TIPO_CREDITO.getDescripcion());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error :M�todo getListaTipoCredito " + " " + e.getMessage());
		}
		return parametro.getParametros();
	}

	public AppMain getAppMain() {
		return appMain;
	}

	public void setAppMain(AppMain appMain) {
		this.appMain = appMain;
	}

	public List<PersonaEt> getPersonas() {
		return personas;
	}

	public void setPersonas(List<PersonaEt> personas) {
		this.personas = personas;
	}

	public List<ClienteEt> getClientes() {
		return clientes;
	}

	public void setClientes(List<ClienteEt> clientes) {
		this.clientes = clientes;
	}

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public String getCondicionVendedor() {
		return condicionVendedor;
	}

	public void setCondicionVendedor(String condicionVendedor) {
		this.condicionVendedor = condicionVendedor;
	}

	public PersonaEt getPersonaSeleccionada() {
		return personaSeleccionada;
	}

	public void setPersonaSeleccionada(PersonaEt personaSeleccionada) {
		this.personaSeleccionada = personaSeleccionada;
	}

	public Double getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(Double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	@Override
	protected void onDestroy() {
		iClienteDao.remove();
		iPersonaDao.remove();
		iParametrolGeneralDao.remove();
	}

}
