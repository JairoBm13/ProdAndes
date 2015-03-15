/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ConsultaDAO.java,v 1.10 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 *
 * Ejercicio: VideoAndes
 * Autor: Juan Diego Toro - 1-Marzo-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package co.edu.uniandes.N1_I1.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import co.edu.uniandes.N1_I1.vos.Material;
import co.edu.uniandes.N1_I1.vos.Producto;
/**
 * Clase ConsultaDAO, encargada de hacer las consultas básicas para el cliente
 */
public class ConsultaDAO {

	//----------------------------------------------------
	//Constantes
	//----------------------------------------------------
	/**
	 * ruta donde se encuentra el archivo de conexión.
	 */
	private static final String ARCHIVO_CONEXION = "/../conexion.properties";

	/**
	 * nombre de la tabla videos
	 */
	private static final String tablaVideo = "videos";


	/**
	 * nombre de la columna titulo_original en la tabla videos.
	 */
	private static final String tituloVideo = "titulo_original";

	/**
	 * nombre de la columna anyo en la tabla videos.
	 */
	private static final String anyoVideo = "anyo";


	//----------------------------------------------------
	//Consultas
	//----------------------------------------------------

	/**
	 * Consulta que devuelve isan, titulo, y año de los videos en orden alfabetico
	 */
	private static final String consultaVideosDefault="SELECT *, FROM "+tablaVideo;


	//----------------------------------------------------
	//Atributos
	//----------------------------------------------------
	/**
	 * conexion con la base de datos
	 */
	public Connection conexion;

	/**
	 * nombre del usuario para conectarse a la base de datos.
	 */
	private String usuario;

	/**
	 * clave de conexión a la base de datos.
	 */
	private String clave;

	/**
	 * URL al cual se debe conectar para acceder a la base de datos.
	 */
	private String cadenaConexion;

	/**
	 * constructor de la clase. No inicializa ningun atributo.
	 */
	public ConsultaDAO() 
	{		

	}

	// -------------------------------------------------
	// Métodos
	// -------------------------------------------------

	/**
	 * obtiene ls datos necesarios para establecer una conexion
	 * Los datos se obtienen a partir de un archivo properties.
	 * @param path ruta donde se encuentra el archivo properties.
	 */
	public void inicializar(String path)
	{
		try
		{
			File arch= new File(path+ARCHIVO_CONEXION);
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream( arch );

			prop.load( in );
			in.close( );

			cadenaConexion = prop.getProperty("url");	// El url, el usuario y passwd deben estar en un archivo de propiedades.
			// url: "jdbc:oracle:thin:@chie.uniandes.edu.co:1521:chie10";
			usuario = prop.getProperty("usuario");	// "s2501aXX";
			clave = prop.getProperty("clave");	// "c2501XX";
			final String driver = prop.getProperty("driver");
			Class.forName(driver);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}

	/**
	 * Método que se encarga de crear la conexión con el Driver Manager
	 * a partir de los parametros recibidos.
	 * @param url direccion url de la base de datos a la cual se desea conectar
	 * @param usuario nombre del usuario que se va a conectar a la base de datos
	 * @param clave clave de acceso a la base de datos
	 * @throws SQLException si ocurre un error generando la conexión con la base de datos.
	 */
	private void establecerConexion(String url, String usuario, String clave) throws SQLException
	{
		try
		{
			conexion = DriverManager.getConnection(url,usuario,clave);
		}
		catch( SQLException exception )
		{
			throw new SQLException( "ERROR: ConsultaDAO obteniendo una conexi—n." );
		}
	}

	/**
	 *Cierra la conexión activa a la base de datos. Además, con=null.
	 * @param con objeto de conexión a la base de datos
	 * @throws SistemaCinesException Si se presentan errores de conexión
	 */
	public void closeConnection(Connection connection) throws Exception {        
		try {
			connection.close();
			connection = null;
		} catch (SQLException exception) {
			throw new Exception("ERROR: ConsultaDAO: closeConnection() = cerrando una conexión.");
		}
	} 

	// ---------------------------------------------------
	// Métodos asociados a los casos de uso: Consulta
	// ---------------------------------------------------

	//FIXME
	/**
	 * 
	 * @param codigo
	 * @param etapa
	 * @throws Exception
	 */
	public void registrarEjecucionEtapaDeProduccion(int codigo, int etapa) throws Exception{
		PreparedStatement statement = null;		

		try {
			String selectQuery = "select cantidad from producto where estado="+etapa+"codigo="+codigo+";";
			establecerConexion(cadenaConexion, usuario, clave);
			statement = conexion.prepareStatement(selectQuery);

			ResultSet rs = statement.executeQuery();
			int cantidad;
			rs.next();
			cantidad = rs.getInt("cantidad");
			String updateIncQuery = "update producto set cantidad=cantidad+"+cantidad+" where codigo="+codigo+" estado="+etapa+1+";";
			statement = conexion.prepareStatement(updateIncQuery);
			statement.executeUpdate();
			String updateDecQuery = "update producto set cantidad=0 where codigo="+codigo+" estado="+etapa+";";
			statement = conexion.prepareStatement(updateDecQuery);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally 
		{
			if (statement != null) 
			{
				try {
					statement.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
				}
			}
			closeConnection(conexion);
		}
	}

	//TODO
	/**
	 * 
	 * @param cantidad
	 * @param codigo
	 * @throws Exception
	 */
	public void registrarLlegadaDeInsumos(double cantidad, int codigo) throws Exception{
		PreparedStatement statement = null;		

		try {
			establecerConexion(cadenaConexion, usuario, clave);

			String updateDecQuery = "update producto set cantidad=cantidad+"+cantidad+" where codigo="+codigo+";";
			statement = conexion.prepareStatement(updateDecQuery);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally 
		{
			if (statement != null) 
			{
				try {
					statement.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
				}
			}
			closeConnection(conexion);
		}
	}

	public ArrayList consultarExistenciaDe(String tipo, String inventario, String etapa, String fechaEntrega, String fechaSolicitud, ArrayList<String> ordenes, ArrayList<String> grupos) throws Exception{
		ArrayList resultado = new ArrayList();
		if(tipo.equals("Producto")){
			resultado = consultarExistenciasDeProducto(inventario, etapa, fechaEntrega, fechaSolicitud, ordenes, grupos);
		}
		else if(tipo.equals("Materia Prima")){
			resultado = consultarExistenciasDeMateriaPrima(tipo, inventario, ordenes, grupos);
		}
		else if(tipo.equals("Componente")){
			resultado = consultarExistenciasDeComponente(tipo, inventario, ordenes, grupos);
		}
		return resultado;
	}

	private ArrayList<Material> consultarExistenciasDeMateriaPrima(String tipo, String inventario, ArrayList<String> ordenes, ArrayList<String> grupos) throws Exception {
		PreparedStatement statement= null;

		ArrayList<Material> materiales = new ArrayList<Material>();

		String selectingQuery = "Select cantidad, nombre, unidad from Materiales where tipo='"+tipo+"' ";
		if(inventario != null){selectingQuery += "AND cantidad between ";
		String[] inven = inventario.split("-");
		selectingQuery += inven[0] + " AND " + inven[1];
		}
		Iterator<String> iteraGrupos = ordenes.iterator();
		String agrupamiento = "";
		while(iteraGrupos.hasNext()){
			String grupo = iteraGrupos.next();
			if (iteraGrupos.hasNext()) {
				agrupamiento += grupo + ", ";
			}
			else{
				agrupamiento += grupo;
			}
		}
		if(!agrupamiento.isEmpty()){
			selectingQuery += "group by "+agrupamiento;
		}
		Iterator<String> iteraOrdenes= ordenes.iterator();
		String ordenamiento = "";
		while(iteraOrdenes.hasNext()){
			String orden = iteraOrdenes.next();
			if (iteraOrdenes.hasNext()) {
				agrupamiento += orden + ",";
			}
			else{
				agrupamiento += orden;
			}
		}
		if(!ordenamiento.isEmpty()){
			selectingQuery += "order by "+ordenamiento;
		}

		try {
			establecerConexion(cadenaConexion, usuario, clave);
			statement = conexion.prepareStatement(selectingQuery);

			ResultSet rs = statement.executeQuery();

			while(rs.next())
			{
				Material mate = new Material();
				String nombre = rs.getString("nombre");
				double cantidad = rs.getDouble("cantidad");
				String unidad = rs.getString("unidad");


				mate.setNombre(nombre);
				mate.setCantidad(cantidad);
				mate.setUnidad(unidad);
				materiales.add(mate);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally 
		{
			if (statement != null) 
			{
				try {
					statement.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
				}
			}
			closeConnection(conexion);
		}		
		return materiales;
	}

	private ArrayList<Material> consultarExistenciasDeComponente(String tipo, String inventario, ArrayList<String> ordenes, ArrayList<String> grupos) throws Exception {
		PreparedStatement statement= null;

		ArrayList<Material> materiales = new ArrayList<Material>();

		String selectingQuery = "Select cantidad, nombre, unidad from Materiales where tipo='"+tipo+"' ";
		Iterator<String> iteraGrupos = ordenes.iterator();
		String agrupamiento = "";

		if(inventario != null){selectingQuery += "AND cantidad between ";
		String[] inven = inventario.split("-");
		selectingQuery += inven[0] + " AND " + inven[1];
		}
		while(iteraGrupos.hasNext()){
			String grupo = iteraGrupos.next();
			if (iteraGrupos.hasNext()) {
				agrupamiento += grupo + ", ";
			}
			else{
				agrupamiento += grupo;
			}
		}
		if(!agrupamiento.isEmpty()){
			selectingQuery += "group by "+agrupamiento;
		}
		Iterator<String> iteraOrdenes= ordenes.iterator();
		String ordenamiento = "";
		while(iteraOrdenes.hasNext()){
			String orden = iteraOrdenes.next();
			if (iteraOrdenes.hasNext()) {
				agrupamiento += orden + ",";
			}
			else{
				agrupamiento += orden;
			}
		}
		if(!ordenamiento.isEmpty()){
			selectingQuery += "order by "+ordenamiento;
		}

		try {
			establecerConexion(cadenaConexion, usuario, clave);
			statement = conexion.prepareStatement(selectingQuery);

			ResultSet rs = statement.executeQuery();

			while(rs.next())
			{
				Material mate = new Material();
				String nombre = rs.getString("nombre");
				double cantidad = rs.getDouble("cantidad");


				mate.setNombre(nombre);
				mate.setCantidad(cantidad);
				materiales.add(mate);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally 
		{
			if (statement != null) 
			{
				try {
					statement.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
				}
			}
			closeConnection(conexion);
		}		
		return materiales;
	}

	private ArrayList<Producto> consultarExistenciasDeProducto(String inventario, String etapa, String fechaEntrega, String fechaSolicitud, ArrayList<String> ordenes, ArrayList<String> grupos) throws Exception {
		PreparedStatement statement = null;
		ArrayList<Producto> productos = new ArrayList<Producto>();
		String selectingQuery = "Select cantidad, nombre from PRODUCTO";
		Iterator<String> iteraGrupos = ordenes.iterator();
		String agrupamiento = "";
		if(inventario != null){selectingQuery += "AND cantidad between ";
		String[] inven = inventario.split("-");
		selectingQuery += inven[0] + " AND " + inven[1];
		}
		if(etapa != null){selectingQuery += "AND etapa='"+etapa+"'";}
		if(fechaEntrega != null){selectingQuery += "AND fechaEntrega='"+fechaEntrega+"'";}
		if(fechaSolicitud != null){selectingQuery += "AND fechaSolicitud='"+fechaSolicitud+"'";}
		while(iteraGrupos.hasNext()){
			String grupo = iteraGrupos.next();
			if (iteraGrupos.hasNext()) {
				agrupamiento += grupo + ", ";
			}
			else{
				agrupamiento += grupo;
			}
		}
		if(!agrupamiento.isEmpty()){
			selectingQuery += "group by "+agrupamiento;
		}
		Iterator<String> iteraOrdenes= ordenes.iterator();
		String ordenamiento = "";
		while(iteraOrdenes.hasNext()){
			String orden = iteraOrdenes.next();
			if (iteraOrdenes.hasNext()) {
				agrupamiento += orden + ",";
			}
			else{
				agrupamiento += orden;
			}
		}
		if(!ordenamiento.isEmpty()){
			selectingQuery += "order by "+ordenamiento;
		}
		
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			statement = conexion.prepareStatement(selectingQuery);

			ResultSet rs = statement.executeQuery();

			while(rs.next())
			{
				Producto produ = new Producto();
				String nombre = rs.getString("nombre");
				int cantidad = rs.getInt("cantidad");


				produ.setNombre(nombre);
				produ.setCantidadDisponible(cantidad);
				productos.add(produ);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally 
		{
			if (statement != null) 
			{
				try {
					statement.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
				}
			}
			closeConnection(conexion);
		}
		return productos;
	}
}
