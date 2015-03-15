package co.edu.uniandes.N1_I1.vos;

import java.util.ArrayList;

public class Producto {
	
	private long codigo;
	
	private String nombre;
	
	private int cantidad;
	
	private String descripcion;
	
	private double costo;
	
	private int estado;
	
	private int numEtapas;
	
	private Proceso proceso;
	
	private EtapaProduccion etapaProduccion;
	
	private ArrayList<Pedido> pedidos;
	
	public Producto()
	{
		pedidos = new ArrayList<Pedido>();
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public EtapaProduccion getEtapaProduccion() {
		return etapaProduccion;
	}

	public void setEtapaProduccion(EtapaProduccion etapaProduccion) {
		this.etapaProduccion = etapaProduccion;
	}

	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public int getNumEtapas() {
		return numEtapas;
	}

	public void setNumEtapas(int numEtapas) {
		this.numEtapas = numEtapas;
	}
	
	
	
}
