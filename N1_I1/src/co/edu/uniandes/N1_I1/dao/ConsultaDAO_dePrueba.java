/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ConsultaDAO.java,v 1.10 
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
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
import java.util.Properties;

/**
 * Clase ConsultaDAO, encargada de hacer las consultas b�sicas para el cliente
 */
public class ConsultaDAO_dePrueba {

	//----------------------------------------------------
	//Constantes
	//----------------------------------------------------
	/**
	 * ruta donde se encuentra el archivo de conexi�n.
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
	 * Consulta que devuelve isan, titulo, y a�o de los videos en orden alfabetico
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
	 * clave de conexi�n a la base de datos.
	 */
	private String clave;
	
	/**
	 * URL al cual se debe conectar para acceder a la base de datos.
	 */
	private String cadenaConexion;
	
	/**
	 * constructor de la clase. No inicializa ningun atributo.
	 */
	public ConsultaDAO_dePrueba() 
	{		
		
	}
	
	// -------------------------------------------------
    // M�todos
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
	 * M�todo que se encarga de crear la conexi�n con el Driver Manager
	 * a partir de los parametros recibidos.
	 * @param url direccion url de la base de datos a la cual se desea conectar
	 * @param usuario nombre del usuario que se va a conectar a la base de datos
	 * @param clave clave de acceso a la base de datos
	 * @throws SQLException si ocurre un error generando la conexi�n con la base de datos.
	 */
    private void establecerConexion(String url, String usuario, String clave) throws SQLException
    {
    	try
        {
			conexion = DriverManager.getConnection(url,usuario,clave);
        }
        catch( SQLException exception )
        {
            throw new SQLException( "ERROR: ConsultaDAO obteniendo una conexi�n." );
        }
    }
    
    /**
 	 *Cierra la conexi�n activa a la base de datos. Adem�s, con=null.
     * @param con objeto de conexi�n a la base de datos
     * @throws SistemaCinesException Si se presentan errores de conexi�n
     */
    public void closeConnection(Connection connection) throws Exception {        
		try {
			connection.close();
			connection = null;
		} catch (SQLException exception) {
			throw new Exception("ERROR: ConsultaDAO: closeConnection() = cerrando una conexi�n.");
		}
    } 
    
    // ---------------------------------------------------
    // M�todos asociados a los casos de uso: Consulta
    // ---------------------------------------------------
    
    /**
     * M�todo que se encarga de realizar la consulta en la base de datos
     * y retorna un ArrayList de elementos tipo VideosValue.
     * @return ArrayList lista que contiene elementos tipo VideosValue.
     * La lista contiene los videos ordenados alfabeticamente
     * @throws Exception se lanza una excepci�n si ocurre un error en
     * la conexi�n o en la consulta. 
     */
//    public ArrayList<VideosValue> darVideosDefault() throws Exception
//    {
//    	PreparedStatement prepStmt = null;
//    	
//    	ArrayList<VideosValue> videos = new ArrayList<VideosValue>();
//		VideosValue vidValue = new VideosValue();
//    	
//		try {
//			establecerConexion(cadenaConexion, usuario, clave);
//			prepStmt = conexion.prepareStatement(consultaVideosDefault);
//			
//			ResultSet rs = prepStmt.executeQuery();
//			
//			while(rs.next()){
//				String titVid = rs.getString(tituloVideo);
//				int anyoVid = rs.getInt(anyoVideo);
//				
//				vidValue.setTituloOriginal(titVid);
//				vidValue.setAnyo(anyoVid);	
//			
//				videos.add(vidValue);
//				vidValue = new VideosValue();
//							
//			}
//		
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println(consultaVideosDefault);
//			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
//		}finally 
//		{
//			if (prepStmt != null) 
//			{
//				try {
//					prepStmt.close();
//				} catch (SQLException exception) {
//					
//					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi�n.");
//				}
//			}
//			closeConnection(conexion);
//		}		
//		return videos;
//    }
    
    public void metodo1() throws Exception{
    	//////////////////
    	PreparedStatement prepStmt = null;

    	try {
    		establecerConexion(cadenaConexion, usuario, clave);
    		prepStmt = conexion.prepareStatement("SELECT * FROM PARRANDEROS.BEBEDORES");

    		ResultSet rs = prepStmt.executeQuery();

    		while(rs.next()){
    			String titVid = rs.getString("ID");
    			System.out.println(titVid);
    		}

    	} catch (SQLException e) {
    		e.printStackTrace();
    		System.out.println("SELECT * FROM PARRANDEROS.BEBEDORES");
    		throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
    	}finally 
    	{
    		if (prepStmt != null) 
    		{
    			try {
    				prepStmt.close();
    			} catch (SQLException exception) {

    				throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexi�n.");
    			}
    		}
    		closeConnection(conexion);
    	}	
    }


    public static void main(String[] args) {
    	ConsultaDAO_dePrueba c = new ConsultaDAO_dePrueba();
    	c.inicializar("C:/Users/Nelson/Documents/sistrans/n1_i2/N1_I1/WebContent/conexion.properties");
    	try {
			c.metodo1();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }

}
