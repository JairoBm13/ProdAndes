

package co.edu.uniandes.N1_I1.vos;

import java.sql.Date;
import java.util.ArrayList;

public class Material {
	
	private double cantidad;
	
	private long codigo;
	
	private String tipo;
	
	private String unidad;
	
	private String nombre;
	
	private Date ultimoAbastecimiento;
	
	private ArrayList<Requiere> requiere;
	
	private ArrayList<Suministro> suministro;
	
	public Material()
	{
		requiere = new ArrayList<Requiere>();
		
		suministro = new ArrayList<Suministro>();
	}
	
	public Material(double cantidadT, long codigoT, String tipoT, String unidadT, String nombreT, Date ultimoAbastecimientoT)
	{
		cantidad=cantidadT;
		codigo=codigoT;
		tipo=tipoT;
		unidad=unidadT;
		nombre=nombreT;
		ultimoAbastecimiento=ultimoAbastecimientoT;
		
		requiere = new ArrayList<Requiere>();
		
		suministro = new ArrayList<Suministro>();
	}
	
	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getUltimoAbastecimiento() {
		return ultimoAbastecimiento;
	}

	public void setUltimoAbastecimiento(Date ultimoAbastecimiento) {
		this.ultimoAbastecimiento = ultimoAbastecimiento;
	}

	public ArrayList<Requiere> getRequiere() {
		return requiere;
	}

	public void setRequiere(ArrayList<Requiere> requiere) {
		this.requiere = requiere;
	}

	public ArrayList<Suministro> getSuministro() {
		return suministro;
	}

	public void setSuministro(ArrayList<Suministro> suministro) {
		this.suministro = suministro;
	}

	
	
	
}

