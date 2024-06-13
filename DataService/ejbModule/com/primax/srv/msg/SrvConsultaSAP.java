package com.primax.srv.msg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.hornetq.utils.json.JSONException;
import org.hornetq.utils.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.primax.jpa.enm.EstadoCategoria;
import com.primax.jpa.gen.ClienteSap;

public class SrvConsultaSAP {

	public ClienteSap consultarWsSAP(String codCliente) {
		ClienteSap clienteSap = null;
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("CODCLIENTE", codCliente);
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
					if (!acumuladorRespuesta.equals("")) {
						respuesta.append(acumuladorRespuesta.trim());
					}
				}
				if (isValid(respuesta.toString())) {
					ObjectMapper objectMapper = new ObjectMapper();
					clienteSap = objectMapper.readValue(respuesta.toString(), ClienteSap.class);
					clienteSap.setPuntuacion(getEstadoCategoria(clienteSap.getPuntuacion()).getDescripcion());
					System.out.println(respuesta.toString());
					System.out.println("RUC_______________" + " " + clienteSap.getRuc());
					System.out.println("Razón Social______" + " " + clienteSap.getRazonSocial());
					System.out.println("Categoría_________" + " " + clienteSap.getCategoria());
					System.out.println("Puntuación________" + " " + clienteSap.getPuntuacion());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error :Método consultarWsSAP " + " " + e.getMessage());
		}
		return clienteSap;
	}
	
	private EstadoCategoria getEstadoCategoria(String estadoJde) {
		EstadoCategoria estadoCategoria = null;
		try {
			switch (estadoJde) {
			case "A":
				estadoCategoria = EstadoCategoria.A;
				break;
			case "B":
				estadoCategoria = EstadoCategoria.B;
				break;
			case "C":
				estadoCategoria = EstadoCategoria.C;
				break;
			case "D":
				estadoCategoria = EstadoCategoria.D;
				break;
			case "E":
				estadoCategoria = EstadoCategoria.E;
				break;
			case "R":
				estadoCategoria = EstadoCategoria.R;
				break;
			case "P":
				estadoCategoria = EstadoCategoria.P;
				break;
			case "AAA":
				estadoCategoria = EstadoCategoria.AAA;
				break;
			default:
				estadoCategoria = EstadoCategoria.N;
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error :Método getEstadoCategoria " + " " + e.getMessage());
		}
		return estadoCategoria;
	}
	public boolean isValid(String json) {
	    try {
	        new JSONObject(json);
	    } catch (JSONException e) {
	        return false;
	    }
	    return true;
	}

}
