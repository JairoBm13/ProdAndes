package co.edu.uniandes.N1_I1.vos;

public class Requiere {
	
	private int cantidad;
	
	private EstacionProduccion estacionProduccion;
	
	private Material material;
	
	public Requiere()
	{
		
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public EstacionProduccion getEstacionProduccion() {
		return estacionProduccion;
	}

	public void setEstacionProduccion(EstacionProduccion estacionProduccion) {
		this.estacionProduccion = estacionProduccion;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
	
	

}
