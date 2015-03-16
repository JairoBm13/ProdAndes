package co.edu.uniandes.N1_I1.vos;

import java.util.ArrayList;

public class Proveedor extends Usuario 
{
	private long codigo;
	
	private int tipoIdLegal;
	
	private long identificacion;
	
	private String nombreLegal;
	
	private ArrayList<Suministro> suministros;
	
	public Proveedor()
	{
		super();
		suministros = new ArrayList<Suministro>();
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public int getTipoIdLegal() {
		return tipoIdLegal;
	}

	public void setTipoIdLegal(int tipoIdLegal) {
		this.tipoIdLegal = tipoIdLegal;
	}

	public long getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(long identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombreLegal() {
		return nombreLegal;
	}

	public void setNombreLegal(String nombreLegal) {
		this.nombreLegal = nombreLegal;
	}

	public ArrayList<Suministro> getSuministros() {
		return suministros;
	}

	public void setSuministros(ArrayList<Suministro> suministros) {
		this.suministros = suministros;
	}
	
	
	
}
