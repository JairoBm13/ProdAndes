package co.edu.uniandes.N1_I1.vos;

public class Operario extends Usuario
{
	private String cargo;
	
	private long codigo;
	
	private String nombre;
	
	private int participacionProces;
	
	private EstacionProduccion estacion;
	
	private Administrador administrador;
	
	public Operario()
	{
		super();
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
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

	public int getParticipacionProces() {
		return participacionProces;
	}

	public void setParticipacionProces(int participacionProces) {
		this.participacionProces = participacionProces;
	}

	public EstacionProduccion getEstacion() {
		return estacion;
	}

	public void setEstacion(EstacionProduccion estacion) {
		this.estacion = estacion;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}
	
	
	
}
