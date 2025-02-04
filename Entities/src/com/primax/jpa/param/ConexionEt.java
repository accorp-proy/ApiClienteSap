package com.primax.jpa.param;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.primax.jpa.base.EntityBase;

@Entity
@Table(name = "CONEXION_ET")
@Audited
public class ConexionEt extends EntityBase {

	@Id
	@Column(name = "id_conexion")
	@GeneratedValue(generator = "sec_conexion", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "sec_conexion", sequenceName = "seq_conexion", allocationSize = 1, initialValue = 1)
	private Long idConexion;

	@Column(name = "ip")
	private String ip;

	@Column(name = "puerto")
	private String puerto;

	@Column(name = "nombre_base")
	private String nombreBase;

	@Column(name = "usuario")
	private String usuario;

	@Column(name = "contrasenia")
	private String contrasenia;

	public Long getIdConexion() {
		return idConexion;
	}

	public void setIdConexion(Long idConexion) {
		this.idConexion = idConexion;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPuerto() {
		return puerto;
	}

	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}

	public String getNombreBase() {
		return nombreBase;
	}

	public void setNombreBase(String nombreBase) {
		this.nombreBase = nombreBase;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ConexionEt) {
			ConexionEt other = (ConexionEt) obj;
			if (this.idConexion == null)
				return this == other;
			return this.idConexion.equals(other.idConexion);
		}
		return false;
	}
}
