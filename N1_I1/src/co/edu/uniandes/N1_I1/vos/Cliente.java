package co.edu.uniandes.N1_I1.vos;

import java.util.ArrayList;

public class Cliente extends Usuario
{
	private long idLegal;
	
	private long codigo; 
	
	private String nombreLegal;
	
	private String resgistroSINV;
	
	private long tipoIdLegal;
	
	private ArrayList<Pedido> pedidos;
	
	public Cliente(){
		super();
		pedidos = new ArrayList<Pedido>();
	}

	public long getIdLegal() {
		return idLegal;
	}

	public void setIdLegal(long idLegal) {
		this.idLegal = idLegal;
	}

	public String getNombreLegal() {
		return nombreLegal;
	}

	public void setNombreLegal(String nombreLegal) {
		this.nombreLegal = nombreLegal;
	}

	public String getResgistroSINV() {
		return resgistroSINV;
	}

	public void setResgistroSINV(String resgistroSINV) {
		this.resgistroSINV = resgistroSINV;
	}

	public long getTipoIdLegal() {
		return tipoIdLegal;
	}

	public void setTipoIdLegal(long tipoIdLegal) {
		this.tipoIdLegal = tipoIdLegal;
	}

	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	/**
	 * @return the codigo
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
	
	
}
