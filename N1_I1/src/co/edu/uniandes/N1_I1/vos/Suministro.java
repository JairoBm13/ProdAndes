package co.edu.uniandes.N1_I1.vos;

public class Suministro {
	
	private long maximaCantidad;
	
	private long tiempoEntrega;
	
	private Material material;
	
	private Proveedor proveedor;
	
	public Suministro()
	{
		
	}

	public long getMaximaCantidad() {
		return maximaCantidad;
	}

	public void setMaximaCantidad(long maximaCantidad) {
		this.maximaCantidad = maximaCantidad;
	}

	public long getTiempoEntrega() {
		return tiempoEntrega;
	}

	public void setTiempoEntrega(long tiempoEntrega) {
		this.tiempoEntrega = tiempoEntrega;
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
	
	

}
