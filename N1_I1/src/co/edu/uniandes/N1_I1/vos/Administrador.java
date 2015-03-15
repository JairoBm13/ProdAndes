package co.edu.uniandes.N1_I1.vos;

import java.util.ArrayList;

public class Administrador extends Usuario 
{
	private long codigo;
	
	private String nombre;
	
	private ArrayList<Operario> operarios;
	
	private ArrayList<Pedido> pedidos;
	
	private ArrayList<Proceso> procesos;
	
	public Administrador()
	{
		super();
		operarios = new ArrayList<Operario>();
		pedidos = new ArrayList<Pedido>();
		procesos = new ArrayList<Proceso>();
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

	public ArrayList<Operario> getOperarios() {
		return operarios;
	}

	public void setOperarios(ArrayList<Operario> operarios) {
		this.operarios = operarios;
	}

	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public ArrayList<Proceso> getProcesos() {
		return procesos;
	}

	public void setProcesos(ArrayList<Proceso> procesos) {
		this.procesos = procesos;
	}
	
	
	
	
}
