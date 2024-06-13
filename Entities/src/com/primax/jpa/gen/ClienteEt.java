package com.primax.jpa.gen;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

import com.primax.jpa.base.EntityBase;
import com.primax.jpa.enm.ActionAuditedEnum;
import com.primax.jpa.enm.EstadoCategoria;
import com.primax.jpa.enm.EstadoEnum;
import com.primax.jpa.param.ParametrosGeneralesEt;
import com.primax.jpa.sec.UsuarioEt;

@Entity
@Table(name = "CLIENTE_ET")
@Audited
public class ClienteEt extends EntityBase implements Serializable {

	/**
	 * Primax ,Desarrollador Jefferson Maji
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_cliente")
	@SequenceGenerator(name = "sec_cliente", allocationSize = 1, initialValue = 1, sequenceName = "seq_cliente")
	@GeneratedValue(generator = "sec_cliente", strategy = GenerationType.SEQUENCE)
	private Long idCliente;

	@ManyToOne
	@JoinColumn(name = "id_persona")
	private PersonaEt persona;

	@Column(name = "limite_credito")
	private Double limiteCredito;

	@ManyToOne
	@JoinColumn(name = "par_tipo_cliente")
	private ParametrosGeneralesEt parTipoCliente;

	@ManyToOne
	@JoinColumn(name = "par_tipo_credito")
	private ParametrosGeneralesEt parTipoCredito;

	@Column(name = "codigo_pago", length = 10)
	private String codigoPago;

	@Column(name = "id_cliente_jde")
	private Long idClienteJDE;

	@Column(name = "nombre_comercial", length = 300)
	private String nombreComercial;

	@Column(name = "nombre_alfa", length = 300)
	private String nombreAlfa;

	@Column(name = "direccion", length = 300)
	private String direccion;

	@Column(name = "correo", length = 100)
	private String correo;

	@Column(name = "telefono", length = 50)
	private String telefono;

	@Column(name = "celular", length = 50)
	private String celular;

	@Column(name = "dia_plazo")
	private Long diaPlazo;

	@Column(name = "fecha_utima_actualizacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaUltimaActualizacion;

	@Column(name = "fecha_utimo_corte")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaUltimoCorte;

	@Column(name = "codigo_facturador")
	private Long codigoFacturador;

	@Column(name = "nombre_facturador")
	private String nombreFacturador;

	@Column(name = "estado_jde", length = 10)
	private String estadoJDE;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado_cuenta")
	protected EstadoEnum estadoCuenta;

	@Column(name = "provincia", length = 100)
	private String provincia;

	@Column(name = "canton", length = 100)
	private String canton;

	@Enumerated(EnumType.STRING)
	@Column(name = "categoria_21", length = 15)
	private EstadoCategoria categoria21;

	@Enumerated(EnumType.STRING)
	@Column(name = "categoria_26", length = 15)
	private EstadoCategoria categoria26;

	@Enumerated(EnumType.STRING)
	@Column(name = "categoria_27", length = 15)
	private EstadoCategoria categoria27;

	@Column(name = "class_of_market", length = 50)
	private String classOfMarket;

	@Column(name = "saldo_vencido")
	private Double saldoVencido;

	@Column(name = "deuda_1_30")
	private Double deudaUno30;

	@Column(name = "deuda_mas_30")
	private Double deudaMas30;

	public ClienteEt() {
		canton = "";
		provincia = "";
		deudaUno30 = 0.0D;
		deudaMas30 = 0.0D;
		classOfMarket = "";
		saldoVencido = 0.0D;
		estadoCuenta = null;
		categoria21 = EstadoCategoria.N;
		categoria26 = EstadoCategoria.N;
		categoria27 = EstadoCategoria.N;

	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Date getFechaUltimaActualizacion() {
		return fechaUltimaActualizacion;
	}

	public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) {
		this.fechaUltimaActualizacion = fechaUltimaActualizacion;
	}

	public PersonaEt getPersona() {
		return persona;
	}

	public void setPersona(PersonaEt persona) {
		this.persona = persona;
	}

	public Double getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(Double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public ParametrosGeneralesEt getParTipoCliente() {
		return parTipoCliente;
	}

	public void setParTipoCliente(ParametrosGeneralesEt parTipoCliente) {
		this.parTipoCliente = parTipoCliente;
	}

	public ParametrosGeneralesEt getParTipoCredito() {
		return parTipoCredito;
	}

	public void setParTipoCredito(ParametrosGeneralesEt parTipoCredito) {
		this.parTipoCredito = parTipoCredito;
	}

	public String getCodigoPago() {
		return codigoPago;
	}

	public void setCodigoPago(String codigoPago) {
		this.codigoPago = codigoPago;
	}

	public Long getIdClienteJDE() {
		return idClienteJDE;
	}

	public void setIdClienteJDE(Long idClienteJDE) {
		this.idClienteJDE = idClienteJDE;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getDiaPlazo() {
		return diaPlazo;
	}

	public void setDiaPlazo(Long diaPlazo) {
		this.diaPlazo = diaPlazo;
	}

	public String getNombreAlfa() {
		return nombreAlfa;
	}

	public void setNombreAlfa(String nombreAlfa) {
		this.nombreAlfa = nombreAlfa;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Date getFechaUltimoCorte() {
		return fechaUltimoCorte;
	}

	public void setFechaUltimoCorte(Date fechaUltimoCorte) {
		this.fechaUltimoCorte = fechaUltimoCorte;
	}

	public Long getCodigoFacturador() {
		return codigoFacturador;
	}

	public void setCodigoFacturador(Long codigoFacturador) {
		this.codigoFacturador = codigoFacturador;
	}

	public String getNombreFacturador() {
		return nombreFacturador;
	}

	public void setNombreFacturador(String nombreFacturador) {
		this.nombreFacturador = nombreFacturador;
	}

	public String getEstadoJDE() {
		return estadoJDE;
	}

	public void setEstadoJDE(String estadoJDE) {
		this.estadoJDE = estadoJDE;
	}

	public EstadoEnum getEstadoCuenta() {
		return estadoCuenta;
	}

	public void setEstadoCuenta(EstadoEnum estadoCuenta) {
		this.estadoCuenta = estadoCuenta;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCanton() {
		return canton;
	}

	public void setCanton(String canton) {
		this.canton = canton;
	}

	public EstadoCategoria getCategoria21() {
		return categoria21;
	}

	public void setCategoria21(EstadoCategoria categoria21) {
		this.categoria21 = categoria21;
	}

	public EstadoCategoria getCategoria26() {
		return categoria26;
	}

	public void setCategoria26(EstadoCategoria categoria26) {
		this.categoria26 = categoria26;
	}

	public EstadoCategoria getCategoria27() {
		return categoria27;
	}

	public void setCategoria27(EstadoCategoria categoria27) {
		this.categoria27 = categoria27;
	}

	public String getClassOfMarket() {
		return classOfMarket;
	}

	public void setClassOfMarket(String classOfMarket) {
		this.classOfMarket = classOfMarket;
	}

	public Double getSaldoVencido() {
		return saldoVencido;
	}

	public void setSaldoVencido(Double saldoVencido) {
		this.saldoVencido = saldoVencido;
	}

	public Double getDeudaUno30() {
		return deudaUno30;
	}

	public void setDeudaUno30(Double deudaUno30) {
		this.deudaUno30 = deudaUno30;
	}

	public Double getDeudaMas30() {
		return deudaMas30;
	}

	public void setDeudaMas30(Double deudaMas30) {
		this.deudaMas30 = deudaMas30;
	}

	@Override
	public <T> void audit(UsuarioEt user, ActionAuditedEnum act) {
		super.audit(user, act);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ClienteEt) {

			ClienteEt other = (ClienteEt) obj;

			if (this.idCliente == null)

				return this == other;

			return this.idCliente.equals(other.idCliente);

		} else

			return false;
	}

}
