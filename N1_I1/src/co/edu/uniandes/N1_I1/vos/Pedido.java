package co.edu.uniandes.N1_I1.vos;

import java.sql.Date;

public class Pedido {

	private long codigo;
	
	private int estado;
	
	private long cantidad;
	
	private Date fechaPedido;
	
	private Date fechaEsperada;
	
	private Date fechaEntrega;
	
	private Administrador admin;
	
	private Cliente cliente;
	
	private Producto producto;
	
	public Pedido()
	{
		
	}
	
	public Pedido(long codigoT , int estadoT, long cantidadT, Date fechaPedidoT, Date fechaEsperadaT, Date fechaEntregaT)
	{
		codigo=codigoT;
		estado=estadoT;
		cantidad=cantidadT;
		fechaPedido=fechaPedidoT;
		fechaEsperada=fechaEsperadaT;
		fechaEntrega=fechaEntregaT;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public long getCantidad() {
		return cantidad;
	}

	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
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

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Administrador getAdmin() {
		return admin;
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
	
}
