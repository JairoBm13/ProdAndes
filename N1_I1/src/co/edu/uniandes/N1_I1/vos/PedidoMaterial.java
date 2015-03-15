package co.edu.uniandes.N1_I1.vos;

import java.sql.Date;

public class PedidoMaterial {

	private long codigo;
	
	private int cantidadPedida;
	
	private Date fechaPedido;
	
	private Date fechaEsperada;
	
	private Material material;
	
	private Proveedor proveedor;
	
	private double costo;
	
	public PedidoMaterial()
	{
		
	}
	
	public PedidoMaterial(long codigoT, int cantidadPedidaT, Date fechaPedidoT, Date fechaEsperadaT,double costoT)
	{
		codigo=codigoT;
		cantidadPedida=cantidadPedidaT;
		fechaPedido=fechaPedidoT;
		fechaEsperada=fechaEsperadaT;
		costo = costoT;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public int getCantidadPedida() {
		return cantidadPedida;
	}

	public void setCantidadPedida(int cantidadPedida) {
		this.cantidadPedida = cantidadPedida;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Date getFechaEsperada() {
		return fechaEsperada;
	}

	public void setFechaEsperada(Date fechaEsperada) {
		this.fechaEsperada = fechaEsperada;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	
	
}
