package test;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class TestDao {


	//----------------------------------------------------
	//Constantes
	//----------------------------------------------------
	
	/**
	 * ruta donde se encuentra el archivo de conexion.
	 */
	private static final String ARCHIVO_CONEXION = "WebContent/conexion.properties";
	
	

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
	 * clave de conexion a la base de datos.
	 */
	private String clave;

	/**
	 * URL al cual se debe conectar para acceder a la base de datos.
	 */
	private String cadenaConexion;

	/**
	 * constructor de la clase. No inicializa ningun atributo.
	 */
	public TestDao() 
	{		

	}

	// -------------------------------------------------
	// Metodos
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

			cadenaConexion = prop.getProperty("url");
			// El url, el usuario y passwd deben estar en un archivo de propiedades.
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
	 * Metodo que se encarga de crear la conexion con el Driver Manager
	 * a partir de los parametros recibidos.
	 * @param url direccion url de la base de datos a la cual se desea conectar
	 * @param usuario nombre del usuario que se va a conectar a la base de datos
	 * @param clave clave de acceso a la base de datos
	 * @throws SQLException si ocurre un error generando la conexion con la base de datos.
	 */
	private void establecerConexion(String url, String usuario, String clave) throws SQLException
	{
		try
		{
			conexion = DriverManager.getConnection(url,usuario,clave);
		}
		catch( SQLException exception )
		{
			System.out.println(exception.getLocalizedMessage());
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			throw new SQLException( "ERROR: ConsultaDAO obteniendo una conexion." );
		}
	}

	/**
	 *Cierra la conexion activa a la base de datos. Ademas, con=null.
	 * @param con objeto de conexion a la base de datos
	 * @throws SistemaCinesException Si se presentan errores de conexion
	 */
	public void closeConnection(Connection connection) throws Exception 
	{        
		try 
		{
			connection.close();
			connection = null;
		} 
		catch (SQLException exception)
		{
			
			throw new Exception("ERROR: ConsultaDAO: closeConnection() = cerrando una conexion.");
		}
	} 
	
	public void agregarMaterialPrueba(int cantidad, long codigo, String tipo, String unidad, String nombre, Date ultimo) throws Exception
	{
		PreparedStatement prepStmt = null;

		try 
		{
//			String cambio =  " INSERT INTO material ( cantidad , codigo , tipo, unidad, nombre, ultimoAbastecimiento) "
//			  +" Values( '"+ cantidad +"' , '"+ codigo +"'  , '"+ tipo +"'  , '"+ unidad +"'  , '"+ nombre +"'  , '"+ ultimo +"' )";
			
//			String cambio =  " INSERT INTO material ( cantidad , codigo , tipo, unidad, nombre, ultimoAbastecimiento) "
//					  +" Values( "+ cantidad +" , "+ codigo +"  , '"+ tipo +"'  , '"+ unidad +"'  , '"+ nombre +"'  , "+ ultimo +" )";
			
			String cambio =  " INSERT INTO material ( cantidad , codigo , tipo, unidad, nombre, ultimoAbastecimiento) "
					  +" Values( "+ cantidad +" , "+ codigo +"  , '"+ tipo +"'  , '"+ unidad +"'  , '"+ nombre +"'  , TO_DATE('2015-02-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS') )";
			
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement(cambio);

			prepStmt.executeQuery();
		} 
		catch (SQLException e) 
		{
			//e.printStackTrace();
			System.out.println(e.getSQLState());
			throw e;
		}
		finally 
		{
			String borrar = "delete from material where codigo = "+codigo+"";
			
			System.out.println("st: " + borrar);
			
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement(borrar);

			prepStmt.executeQuery();
			
			
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: RegistroDePaquetesDAO: loadRow() =  cerrando una conexion.");
				}
			}
			closeConnection(conexion);
		}
	}

	public void agregarMaterialSinEliminar(int cantidad, long codigo, String tipo, String unidad, String nombre, Date ultimo) throws Exception
	{
		PreparedStatement prepStmt = null;

		try 
		{
//			String cambio =  " INSERT INTO material ( cantidad , codigo , tipo, unidad, nombre, ultimoAbastecimiento) "
//					  +" Values( '"+ cantidad +"' , '"+ codigo +"'  , '"+ tipo +"'  , '"+ unidad +"'  , '"+ nombre +"'  , '"+ ultimo +"' )";
			
//			String cambio =  " INSERT INTO material ( cantidad , codigo , tipo, unidad, nombre, ultimoAbastecimiento) "
//					  +" Values( "+ cantidad +" , "+ codigo +"  , '"+ tipo +"'  , '"+ unidad +"'  , '"+ nombre +"'  , "+ ultimo +" )";
			
			String cambio =  " INSERT INTO material ( cantidad , codigo , tipo, unidad, nombre, ultimoAbastecimiento) "
					  +" Values( "+ cantidad +" , "+ codigo +"  , '"+ tipo +"'  , '"+ unidad +"'  , '"+ nombre +"'  , TO_DATE('2015-02-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS') )";
					
					establecerConexion(cadenaConexion, usuario, clave);
					
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement(cambio);

			prepStmt.executeQuery();
		} 
		catch (SQLException e) 
		{
			//e.printStackTrace();
			System.out.println(e.getSQLState());
			throw e;
		}
		finally 
		{

			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: RegistroDePaquetesDAO: loadRow() =  cerrando una conexion.");
				}
			}
			closeConnection(conexion);
		}

	}
	
	public void eliminarMaterial(long codigo) throws Exception
	{
		PreparedStatement prepStmt = null;
		
		String borrar = "delete from material where codigo = "+codigo+"";
		
		System.out.println("st: " + borrar);
		
		establecerConexion(cadenaConexion, usuario, clave);
		prepStmt = conexion.prepareStatement(borrar);

		prepStmt.executeQuery();
		
		
		if (prepStmt != null) 
		{
			try {
				prepStmt.close();
			} catch (SQLException exception) {

				throw new Exception("ERROR: RegistroDePaquetesDAO: loadRow() =  cerrando una conexion.");
			}
		}
		closeConnection(conexion);
		
	}
	
	public void agregarProductoPrueba(long codigo, String nombre, int cantidad, String descripcion, double costo, int numEtapas) throws Exception
	{
		PreparedStatement prepStmt = null;

		try 
		{
			String cambio =  " INSERT INTO producto ( codigo , nombre , cantidad, descripcion, costo, numEtapas) "
					  +" Values( "+ codigo +" , '"+ nombre +"'  , "+ cantidad +"  , '"+ descripcion +"'  , "+ costo +"  , "+ numEtapas +" )";
			
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement(cambio);

			prepStmt.executeQuery();
		} 
		catch (SQLException e) 
		{
			//e.printStackTrace();
			System.out.println(e.getSQLState());
			throw e;
		}
		finally 
		{
			String borrar = "delete from producto where codigo = "+codigo;
			
			System.out.println("st: " + borrar);
			
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement(borrar);

			prepStmt.executeQuery();
			
			
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: RegistroDePaquetesDAO: loadRow() =  cerrando una conexion.");
				}
			}
			closeConnection(conexion);
		}
	}
	
	public void agregarProductoSinEliminar(long codigo, String nombre, int cantidad, String descripcion, double costo,  int numEtapas) throws Exception
	{
		PreparedStatement prepStmt = null;

		try 
		{
			String cambio =  " INSERT INTO producto ( codigo , nombre , cantidad, descripcion, costo, numEtapas) "
					  +" Values( "+ codigo +" , '"+ nombre +"'  , "+ cantidad +"  , '"+ descripcion +"'  , "+ costo +"  , "+ numEtapas +" )";
			
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement(cambio);

			prepStmt.executeQuery();
		} 
		catch (SQLException e) 
		{
			//e.printStackTrace();
			System.out.println(e.getSQLState());
			throw e;
		}
		finally 
		{
						
			
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: RegistroDePaquetesDAO: loadRow() =  cerrando una conexion.");
				}
			}
			closeConnection(conexion);
		}
	}
	
	public void eliminarProducto(long codigo) throws Exception
	{
		PreparedStatement prepStmt = null;
		
		String borrar = "delete from producto where codigo = "+codigo;
		
		System.out.println("st: " + borrar);
		
		establecerConexion(cadenaConexion, usuario, clave);
		prepStmt = conexion.prepareStatement(borrar);

		prepStmt.executeQuery();
		
		
		if (prepStmt != null) 
		{
			try {
				prepStmt.close();
			} catch (SQLException exception) {

				throw new Exception("ERROR: RegistroDePaquetesDAO: loadRow() =  cerrando una conexion.");
			}
		}
		closeConnection(conexion);
		
	}
	
	public void agregarProcesoPrueba(long codigo, String nombre, long tiempoEjecucion, String descripcion, long codigoProducto,int estado) throws Exception
	{
		PreparedStatement prepStmt = null;

		try 
		{
			String cambio =  " INSERT INTO proceso ( codigo , nombre , tiempoEjecucion, descripcion, codigoProducto,estado) "
			  +" Values( "+ codigo +" , '"+ nombre +"'  , "+ tiempoEjecucion +"  , '"+ descripcion +"'  , "+ codigoProducto +","+estado+" )";
			
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement(cambio);

			prepStmt.executeQuery();
		} 
		catch (SQLException e) 
		{
			//e.printStackTrace();
			System.out.println(e.getSQLState());
			throw e;
		}
		finally 
		{
			String borrar = "delete from proceso where codigo = "+codigo+" and codigoProducto="+codigoProducto;
			
			System.out.println("st: " + borrar);
			
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement(borrar);

			prepStmt.executeQuery();
			
			
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: RegistroDePaquetesDAO: loadRow() =  cerrando una conexion.");
				}
			}
			closeConnection(conexion);
		}
	}
	
	public void agregarProcesoSinEliminacion(long codigo, String nombre, long tiempoEjecucion, String descripcion, long codigoProducto, int estado) throws Exception
	{
		PreparedStatement prepStmt = null;

		try 
		{
			String cambio =  " INSERT INTO proceso ( codigo , nombre , tiempoEjecucion, descripcion, codigoProducto,estado) "
					  +" Values( "+ codigo +" , '"+ nombre +"'  , "+ tiempoEjecucion +"  , '"+ descripcion +"'  , "+ codigoProducto +","+estado+" )";
			
			establecerConexion(cadenaConexion, usuario, clave);
			prepStmt = conexion.prepareStatement(cambio);

			prepStmt.executeQuery();
		} 
		catch (SQLException e) 
		{
			//e.printStackTrace();
			System.out.println(e.getSQLState());
//			throw new Exception("ERROR = RegistroDePaquetesDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
			throw e;
		}
		finally 
		{
			
			if (prepStmt != null) 
			{
				try {
					prepStmt.close();
				} catch (SQLException exception) {

					throw new Exception("ERROR: RegistroDePaquetesDAO: loadRow() =  cerrando una conexion.");
				}
			}
			closeConnection(conexion);
		}
	}

	
	public void eliminarProceso(long codigo, long codigoProducto) throws Exception
	{
		PreparedStatement prepStmt = null;
		
		String borrar = "delete from proceso where codigo = "+codigo+" and codigoProducto="+codigoProducto;
		
		System.out.println("st: " + borrar);
		
		establecerConexion(cadenaConexion, usuario, clave);
		prepStmt = conexion.prepareStatement(borrar);

		prepStmt.executeQuery();
		
		
		if (prepStmt != null) 
		{
			try {
				prepStmt.close();
			} catch (SQLException exception) {

				throw new Exception("ERROR: RegistroDePaquetesDAO: loadRow() =  cerrando una conexion.");
			}
		}
		closeConnection(conexion);
	}

}
