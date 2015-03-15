package co.edu.uniandes.N1_I1.vos;

import java.util.ArrayList;

public class Proceso {
	
	private long codigo;
	
	private String descripcion;
	
	private String nombre;
	
	private long tiempoEjecucion;
	
	private Administrador admin;
	
	private Producto producto;
	
	private ArrayList<Etapa> etapas;
	
	public Proceso()
	{
		etapas = new ArrayList<Etapa>();
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getTiempoEjecucion() {
		return tiempoEjecucion;
	}

	public void setTiempoEjecucion(long tiempoEjecucion) {
		this.tiempoEjecucion = tiempoEjecucion;
	}

	public Administrador getAdmin() {
		return admin;
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public ArrayList<Etapa> getEtapas() {
		return etapas;
	}

	public void setEtapas(ArrayList<Etapa> etapas) {
		this.etapas = etapas;
	}
	
	
	
}
