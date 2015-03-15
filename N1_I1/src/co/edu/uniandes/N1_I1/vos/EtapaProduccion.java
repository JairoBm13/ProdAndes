package co.edu.uniandes.N1_I1.vos;

import java.sql.Date;
import java.util.ArrayList;

public class EtapaProduccion {

	private long codigo;
	
	private int etapa;
	
	private String nombre;
	
	private Date fechaInicio;
	
	private Date fechaFin;
	
	private long tiempoEjecuacion;
	
	private String descripcion;
	
	private ArrayList<Etapa> etapas;
	
	private ArrayList<Producto> productos;
	
	private ArrayList<EstacionProduccion> estacionProduccion;
	
	public EtapaProduccion()
	{
		etapas = new ArrayList<Etapa>();
		productos = new ArrayList<Producto>();
		estacionProduccion = new ArrayList<EstacionProduccion>();
	}
	
	public EtapaProduccion(long codigoT, int etapaT, String nombreT, Date fechaInicioT, Date fechaFinT, long tiempoEjecuacionT, String descripcionT)
	{
		codigo = codigoT;
		etapa=etapaT;
		nombre=nombreT;
		fechaInicio=fechaInicioT;
		fechaFin=fechaFinT;
		tiempoEjecuacion=tiempoEjecuacionT;
		descripcion=descripcionT;
		etapas = new ArrayList<Etapa>();
		productos = new ArrayList<Producto>();
		estacionProduccion = new ArrayList<EstacionProduccion>();
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public int getEtapa() {
		return etapa;
	}

	public void setEtapa(int etapa) {
		this.etapa = etapa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public long getTiempoEjecuacion() {
		return tiempoEjecuacion;
	}

	public void setTiempoEjecuacion(long tiempoEjecuacion) {
		this.tiempoEjecuacion = tiempoEjecuacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ArrayList<Etapa> getEtapas() {
		return etapas;
	}

	public void setEtapas(ArrayList<Etapa> etapas) {
		this.etapas = etapas;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}

	public ArrayList<EstacionProduccion> getEtapasProduccion() {
		return estacionProduccion;
	}

	public void setEtapasProduccion(ArrayList<EstacionProduccion> etapasProduccion) {
		this.estacionProduccion = etapasProduccion;
	}
	
	
	
	
}
