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
import java.util.Properties;






import co.edu.uniandes.N1_I1.vos.VideosValue;

/**
 * Clase ConsultaDAO, encargada de hacer las consultas básicas para el cliente
 */
public class ConsultaDAO_SoloConexion {

	//----------------------------------------------------
	//Constantes
	//----------------------------------------------------
	/**
	 * ruta donde se encuentra el archivo de conexión.
	 */
	private static final String ARCHIVO_CONEXION = "WebContent/conexion.properties";
	
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
	public ConsultaDAO_SoloConexion() 
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
	//public void inicializar(String path)
	public void inicializar()
	{
		try
		{
			File arch= new File(ARCHIVO_CONEXION);
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
    
    /**
     * Método que se encarga de realizar la consulta en la base de datos
     * y retorna un ArrayList de elementos tipo VideosValue.
     * @return ArrayList lista que contiene elementos tipo VideosValue.
     * La lista contiene los videos ordenados alfabeticamente
     * @throws Exception se lanza una excepción si ocurre un error en
     * la conexión o en la consulta. 
     */
    public ArrayList<VideosValue> darVideosDefault() throws Exception
    {
    	PreparedStatement prepStmt = null;
    	
    	ArrayList<VideosValue> videos = new ArrayList<VideosValue>();
		VideosValue vidValue = new VideosValue();
    	
		try {
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement(consultaVideosDefault);
			
			ResultSet rs = prepStmt.executeQuery();
			
			while(rs.next()){
				String titVid = rs.getString(tituloVideo);
				int anyoVid = rs.getInt(anyoVideo);
				
				vidValue.setTituloOriginal(titVid);
				vidValue.setAnyo(anyoVid);	
			
				videos.add(vidValue);
				vidValue = new VideosValue();
							
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(consultaVideosDefault);
			throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
		}finally 
		{
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {
					
					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
				}
			}
			closeConnection(conexion);
		}		
		return videos;
    }
    
    public void metodo1() throws Exception{
    	//////////////////
    	PreparedStatement prepStmt = null;

    	try {
    		establecerConexion(cadenaConexion, usuario, clave);
    		prepStmt = conexion.prepareStatement("SELECT * USUARIO");

    		ResultSet rs = prepStmt.executeQuery();

    		while(rs.next()){
    			String titVid = rs.getString("login");
    			System.out.println(titVid);
    		}

    	} catch (SQLException e) {
    		e.printStackTrace();
    		System.out.println("metodo1");
    		throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
    	}finally 
    	{
    		if (prepStmt != null) 
    		{
    			try {
    				prepStmt.close();
    			} catch (SQLException exception) {

    				throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
    			}
    		}
    		closeConnection(conexion);
    	}	
    }
    
    
    public boolean registrarPedidoProducto(Long idProceso, String loginCLiente, int cantidad, Date fechaEspera) throws Exception
    {
    	
    	boolean fallo = false; 

    	PreparedStatement prepStmt = null;
    	PreparedStatement pSRequeridosNum = null;

    	try {
    		establecerConexion(cadenaConexion, usuario, clave);
    		
    		//Rectifica si hay cantidad suficiente
    		
    		    		
    		PreparedStatement  prcantidadDisponible = conexion.prepareStatement("SELECT cantidad from Producto where Proceso.codigoProducto="+idProceso+"etapa=0");
    		ResultSet rscantidadDisponible = prcantidadDisponible.executeQuery();
    		
    		int cantidadDisponible=0;
    		if(rscantidadDisponible.next())
    			cantidadDisponible = rscantidadDisponible.getInt("cantidad");
    		
    		if(cantidadDisponible>=cantidad)
    		{
    			//crea y despacha el pedido
    			
    			int nuevo = cantidadDisponible-cantidad;
    			PreparedStatement  psaactualizarDisponibles1 = conexion.prepareStatement("update Productos set cantidad="+nuevo+"where Proceso.codigoProducto="+idProceso+"etapa=0");
    			PreparedStatement  psaactualizarDisponibles2 = conexion.prepareStatement("update Productos set cantidad=cantidad+"+cantidad+"where Proceso.codigoProducto="+idProceso+"etapa=-1");
    			psaactualizarDisponibles1.executeUpdate();
    			psaactualizarDisponibles2.executeUpdate();
    			
    			//Codigo del admin y crea pedido
    			pSRequeridosNum = conexion.prepareStatement("select codigo from Administrador");
    			ResultSet admin = pSRequeridosNum.executeQuery();
    			int adminID =0;
    			if(admin.next())
    				adminID=admin.getInt("codigo");
    			
    			conexion.prepareStatement("insert into Pedidos (codigo, estado,cantidad,fechaPedido, fechaEsperada,  codioProducto ,  codigoAdmin, codigoCliente)"
    					+ "values (incremento_id_Pedido.NextVal,listo,"+cantidad+", NOW(),"+fechaEspera+","+idProceso+","+adminID+","+loginCLiente+" )");
    			
    		}
    		else
    		{
    			
    			//establece si se puede reservar o no
    			
    			//Primero obtiene la cantidad de material que requiere un producto
        		
    			conexion.prepareStatement("Create View consulta as (SELECT * FROM PROCESO, ETAPA, ETAPAPRODUCCION, ESTACIONPRODUCCION, REQUIERE "
        				+ "where Proceso.codigoProducto="+idProceso+" and etapa.codigoProceso=proceso.codigo and etapa.codigoEtapa=etapaProduccion.codigo "
						+ " and etapaProduccion.codigo=estacionProduccion.codigoEtapa and requiere.codioEstacion=estacionProduccion.codigo) ").executeQuery();
    			
        		pSRequeridosNum = conexion.prepareStatement("select count(*) as cuenta from consulta");

        		
        		
        		ResultSet rsRequeridos = pSRequeridosNum.executeQuery();
        		
        		int cantidadRequerido = 0;
        		
        		if(rsRequeridos.next())
        			cantidadRequerido=rsRequeridos.getInt("cuenta");
        		
        		conexion.prepareStatement("Create View matDisp as (SELECT * FROM consulta INNER JOIN Materiales mat ON req.codigoMaterial= mat.codigo where consulta.cantidad*"+cantidad+" <= mat.cantidad )").executeQuery();
        		
        		pSRequeridosNum = conexion.prepareStatement("select count(*) as cuenta from matDisp");
        		
        		ResultSet rsDisponibleMat = pSRequeridosNum.executeQuery();
        		
        		int cantidadDisponibleMat = 0;
        		
        		if(rsDisponibleMat.next())
        			cantidadDisponibleMat=rsDisponibleMat.getInt("cuenta");
        		
        		if(cantidadDisponibleMat==cantidadRequerido)
        		{
        			//Actualiza los productos si se puede fabricar
        			
        			PreparedStatement  psaactualizarDisponibles1 = conexion.prepareStatement("update Productos set cantidad="+0+"where Proceso.codigoProducto="+idProceso+"etapa=0");
        			PreparedStatement  psaactualizarDisponibles2 = conexion.prepareStatement("update Productos set cantidad=cantidad+"+cantidadDisponible+"where Proceso.codigoProducto="+idProceso+"etapa=-1");
        			psaactualizarDisponibles1.executeUpdate();
        			psaactualizarDisponibles2.executeUpdate();
        			
        			pSRequeridosNum = conexion.prepareStatement("select * from matDisp");
        			ResultSet materialesReservar = pSRequeridosNum.executeQuery();
        			
        			PreparedStatement actualizar;
        			
        			while(materialesReservar.next()){
        				
        				String cod = materialesReservar.getString("mat.codigo");
        				int resta = materialesReservar.getInt("req.cantidad")*cantidad;
        				
        				actualizar=conexion.prepareStatement("update Material set cantidad=cantidad-"+resta+"where codigo="+cod);		
        			}
        			
        			//Codigo del admin y crea pedido
        			pSRequeridosNum = conexion.prepareStatement("select codigo from Administrador");
        			ResultSet admin = pSRequeridosNum.executeQuery();
        			int adminID =0;
        			if(admin.next())
        				adminID=admin.getInt("codigo");
        			
        			conexion.prepareStatement("insert into Pedidos (codigo, estado,cantidad,fechaPedido, fechaEsperada,  codioProducto ,  codigoAdmin, codigoCliente)"
        					+ "values (incremento_id_Pedido.NextVal,enProduccion,"+cantidad+", NOW(),"+fechaEspera+","+idProceso+","+adminID+","+loginCLiente+" )");
        			
        			
        			
        		}
        		else
        		{
        			//Deja el pedido en pendiente
        			//Codigo del admin y crea pedido
        			pSRequeridosNum = conexion.prepareStatement("select codigo from Administrador");
        			ResultSet admin = pSRequeridosNum.executeQuery();
        			int adminID =0;
        			if(admin.next())
        				adminID=admin.getInt("codigo");
        			
        			conexion.prepareStatement("insert into Pedidos (codigo, estado,cantidad,fechaPedido, fechaEsperada,  codioProducto ,  codigoAdmin, codigoCliente)"
        					+ "values (incremento_id_Pedido.NextVal,enEspera,"+cantidad+", NOW(),"+fechaEspera+","+idProceso+","+adminID+","+loginCLiente+" )");
        			
        			
        		}

    		}
    		
    		
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    		System.out.println("metodo1");
    		fallo = true;
    		throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
    		
    	}finally 
    	{
    		if (prepStmt != null) 
    		{
    			try {
    				prepStmt.close();
    			} catch (SQLException exception) {

    				throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
    			}
    		}
    		closeConnection(conexion);
    		return fallo?false:true;
    	}
    }


    public static void main(String[] args) {
    	ConsultaDAO_SoloConexion c = new ConsultaDAO_SoloConexion();
    	c.inicializar();
    	try {
			c.metodo1();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }

}
