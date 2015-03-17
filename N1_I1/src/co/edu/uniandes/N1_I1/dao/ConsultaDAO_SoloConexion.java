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


















import co.edu.uniandes.N1_I1.vos.EstacionProduccion;
import co.edu.uniandes.N1_I1.vos.EtapaProduccion;
import co.edu.uniandes.N1_I1.vos.Material;
import co.edu.uniandes.N1_I1.vos.Pedido;
import co.edu.uniandes.N1_I1.vos.PedidoMaterial;
import co.edu.uniandes.N1_I1.vos.Producto;
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
//	private static final String ARCHIVO_CONEXION = "WebContent/conexion.properties";
//	private static final String ARCHIVO_CONEXION = "/../WebContent/conexion.properties";
	private static final String ARCHIVO_CONEXION = "/conexion.properties";
	
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
	
	public static final String CONSULTA_PRODUCTO = "Producto";
	
	public static final String CONSULTA_MATERIAL = "Material";
	
	public static final String CONSULTA_ETAPA_PROD = "Etapa Produccion";
	
	public static final String TIPO_MATERIAL_MATERIA_PRIMA = "Materia Prima";
	
	public static final String TIPO_MATERIAL_COMPONENTE = "Componente";
	

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
//			File arch= new File(ARCHIVO_CONEXION);
//			Properties prop = new Properties();
//			FileInputStream in = new FileInputStream( arch );
//
//	        prop.load( in );
//	        in.close( );
//
//			cadenaConexion = prop.getProperty("url");	// El url, el usuario y passwd deben estar en un archivo de propiedades.
//												// url: "jdbc:oracle:thin:@chie.uniandes.edu.co:1521:chie10";
//			usuario = prop.getProperty("usuario");	// "s2501aXX";
//			clave = prop.getProperty("clave");	// "c2501XX";
	        cadenaConexion = "jdbc:oracle:thin:@prod.oracle.virtual.uniandes.edu.co:1531:prod";

	        usuario = "ISIS2304051510";
	        clave = "dmariafifth";
//			final String driver = prop.getProperty("driver");
	        final String driver = "oracle.jdbc.driver.OracleDriver";
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
            //throw new SQLException( "ERROR: ConsultaDAO obteniendo una conexi—n." );
        	throw exception;
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
    
//    /**
//     * Método que se encarga de realizar la consulta en la base de datos
//     * y retorna un ArrayList de elementos tipo VideosValue.
//     * @return ArrayList lista que contiene elementos tipo VideosValue.
//     * La lista contiene los videos ordenados alfabeticamente
//     * @throws Exception se lanza una excepción si ocurre un error en
//     * la conexión o en la consulta. 
//     */
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
//					throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
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
    		prepStmt = conexion.prepareStatement("SELECT * FROM USUARIO");

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

    	
    	PreparedStatement pSRequeridosNum = null;

    	try {
    		establecerConexion(cadenaConexion, usuario, clave);
    		
    		//Rectifica si hay cantidad suficiente
    		
    		    		
    		PreparedStatement  prcantidadDisponible = conexion.prepareStatement("SELECT cantidad from Producto where Proceso.codigoProducto="+idProceso+" and etapa=0");
    		ResultSet rscantidadDisponible = prcantidadDisponible.executeQuery();
    		prcantidadDisponible.close();
    		
    		int cantidadDisponible=0;
    		if(rscantidadDisponible.next())
    			cantidadDisponible = rscantidadDisponible.getInt("cantidad");
    		
    		if(cantidadDisponible>=cantidad)
    		{
    			//crea y despacha el pedido
    			
    			int nuevo = cantidadDisponible-cantidad;
    			PreparedStatement  psaactualizarDisponibles1 = conexion.prepareStatement("update Productos set cantidad="+nuevo+" where Proceso.codigoProducto="+idProceso+" and etapa=0");
    			PreparedStatement  psaactualizarDisponibles2 = conexion.prepareStatement("update Productos set cantidad=cantidad+"+cantidad+" where Proceso.codigoProducto="+idProceso+" and etapa=-1");
    			psaactualizarDisponibles1.executeUpdate();
    			psaactualizarDisponibles2.executeUpdate();
    			psaactualizarDisponibles1.close();
    			psaactualizarDisponibles2.close();
    			
    			//Codigo del admin y crea pedido
    			pSRequeridosNum = conexion.prepareStatement("select codigo from Administrador");
    			ResultSet admin = pSRequeridosNum.executeQuery();
    			int adminID =0;
    			if(admin.next())
    				adminID=admin.getInt("codigo");
    			
    			pSRequeridosNum =conexion.prepareStatement("insert into Pedidos (codigo, estado,cantidad,fechaPedido, fechaEsperada,  codioProducto ,  codigoAdmin, codigoCliente)"
    					+ "values (incremento_id_Pedido.NextVal,'listo',"+cantidad+", NOW(),"+fechaEspera+","+idProceso+","+adminID+",'"+loginCLiente+"' )");
    			pSRequeridosNum.executeUpdate();
    			
    		}
    		else
    		{
    			
    			//establece si se puede reservar o no
    			
    			//Primero obtiene la cantidad de material que requiere un producto
        		
    			
    			pSRequeridosNum =conexion.prepareStatement("Create View consulta as (SELECT * FROM PROCESO, ETAPA, ETAPAPRODUCCION, ESTACIONPRODUCCION, REQUIERE "
        				+ "where Proceso.codigoProducto="+idProceso+" and etapa.codigoProceso=proceso.codigo and etapa.codigoEtapa=etapaProduccion.codigo "
						+ " and etapaProduccion.codigo=estacionProduccion.codigoEtapa and requiere.codigoEstacion=estacionProduccion.codigo) ");
    			pSRequeridosNum.executeUpdate();
    			
        		pSRequeridosNum = conexion.prepareStatement("select count(*) as cuenta from consulta");

        		
        		
        		ResultSet rsRequeridos = pSRequeridosNum.executeQuery();
        		
        		int cantidadRequerido = 0;
        		
        		if(rsRequeridos.next())
        			cantidadRequerido=rsRequeridos.getInt("cuenta");
        		
        		pSRequeridosNum = conexion.prepareStatement("Create View matDisp as (SELECT * FROM consulta INNER JOIN Materiales mat ON consulta.codigoMaterial= mat.codigo where consulta.cantidad*"+(cantidad-cantidadDisponible)+" <= mat.cantidad )");
        		pSRequeridosNum.executeUpdate();
        		
        		pSRequeridosNum = conexion.prepareStatement("select count(*) as cuenta from matDisp");
        		pSRequeridosNum.executeUpdate();
        		
        		ResultSet rsDisponibleMat = pSRequeridosNum.executeQuery();
        		
        		int cantidadDisponibleMat = 0;
        		
        		if(rsDisponibleMat.next())
        			cantidadDisponibleMat=rsDisponibleMat.getInt("cuenta");
        		
        		if(cantidadDisponibleMat==cantidadRequerido)
        		{
        			//Actualiza los productos si se puede fabricar
        			
        			PreparedStatement  psaactualizarDisponibles1 = conexion.prepareStatement("update Productos set cantidad="+0+" where Proceso.codigoProducto="+idProceso+" and etapa=0");
        			PreparedStatement  psaactualizarDisponibles2 = conexion.prepareStatement("update Productos set cantidad=cantidad+"+cantidadDisponible+" where Proceso.codigoProducto="+idProceso+" and etapa=-1");
        			psaactualizarDisponibles1.executeUpdate();
        			psaactualizarDisponibles2.executeUpdate();
        			psaactualizarDisponibles1.close();
        			psaactualizarDisponibles2.close();
        			
        			pSRequeridosNum.close();
        			pSRequeridosNum = conexion.prepareStatement("select * from matDisp");
        			ResultSet materialesReservar = pSRequeridosNum.executeQuery();
        			
        			PreparedStatement actualizar=null;
        			
        			while(materialesReservar.next()){
        				
        				String cod = materialesReservar.getString("mat.codigo");
        				int resta = materialesReservar.getInt("consulta.cantidad")*cantidad;
        				
        				actualizar=conexion.prepareStatement("update Material set cantidad=cantidad-"+resta+" where codigo="+cod);	
        				actualizar.executeUpdate();
        			}
        			
        			if(actualizar!=null)
        				actualizar.close();
        			//Codigo del admin y crea pedido
        			pSRequeridosNum = conexion.prepareStatement("select codigo from Administrador");
        			ResultSet admin = pSRequeridosNum.executeQuery();
        			int adminID =0;
        			if(admin.next())
        				adminID=admin.getInt("codigo");
        			
        			pSRequeridosNum = conexion.prepareStatement("insert into Pedidos (codigo, estado,cantidad,fechaPedido, fechaEsperada,  codioProducto ,  codigoAdmin, codigoCliente)"
        					+ "values (incremento_id_Pedido.NextVal,'enProduccion',"+cantidad+", NOW(),"+fechaEspera+","+idProceso+","+adminID+",'"+loginCLiente+"' )");
        			pSRequeridosNum.executeUpdate();
        			
        			
        			
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
        			
        			pSRequeridosNum = conexion.prepareStatement("insert into Pedidos (codigo, estado,cantidad,fechaPedido, fechaEsperada,  codioProducto ,  codigoAdmin, codigoCliente)"
        					+ "values (incremento_id_Pedido.NextVal,'enEspera',"+cantidad+", NOW(),"+fechaEspera+","+idProceso+","+adminID+",'"+loginCLiente+"' )");
        			pSRequeridosNum.executeUpdate();
        			
        			
        		}

    		}
    		
    		
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    		System.out.println("metodo1");
    		fallo = true;
    		throw new Exception("ERROR = ConsultaDAO: loadRowsBy(..) Agregando parametros y executando el statement!!!");
    		
    	}finally 
    	{
    		
    		if (pSRequeridosNum != null) 
    		{
    			try {
    				pSRequeridosNum.close();
    			} catch (SQLException exception) {

    				throw new Exception("ERROR: ConsultaDAO: loadRow() =  cerrando una conexión.");
    			}
    		}
    		closeConnection(conexion);
    	}
    	return fallo?false:true;
    }
    
    
    public Object[] consultarMaterial(Long idMaterial, String tipoPedido, String tipo, Integer[] volumen, Date[] fechas, Double[] costo, ArrayList<String> ordenes, ArrayList<String> grupos) throws Exception
    {
    	if(tipoPedido.equals(CONSULTA_PRODUCTO))
    		return consultarMaterialProducto(idMaterial, fechas, costo, ordenes, grupos);
    	else
    		return consultarMaterialMaterial(idMaterial, tipoPedido, tipo, volumen, ordenes, grupos);
    	
    }
    
    public Object[] consultarMaterialProducto(Long idProducto, Date[] fechas, Double[] costo, ArrayList<String> ordenes, ArrayList<String> grupos) throws Exception
    {
    	PreparedStatement prepStmt = null;
    	Producto producto = new Producto();
    	ArrayList<EtapaProduccion> etapasProduc = new ArrayList<EtapaProduccion>();
    	ArrayList<Material> materiales = new ArrayList<Material>();
    	ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

    	try {
    		establecerConexion(cadenaConexion, usuario, clave);
    		
    		
    		String sentencia ="SELECT * from Producto where Producto.codigo="+idProducto+" and etapa=0";
    		
    		if(costo!=null)
    			sentencia = sentencia + " and costo between "+costo[0]+" and "+costo[1];
    		
    		Iterator<String> iteraGrupos = grupos.iterator();
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
    			sentencia += "group by "+agrupamiento;
    		}
    		Iterator<String> iteraOrdenes= ordenes.iterator();
    		String ordenamiento = "";
    		while(iteraOrdenes.hasNext()){
    			String orden = iteraOrdenes.next();
    			if (iteraOrdenes.hasNext()) {
    				ordenamiento += orden + ",";
    			}
    			else{
    				ordenamiento += orden;
    			}
    		}
    		if(!ordenamiento.isEmpty()){
    			sentencia += "order by "+ordenamiento;
    		}
    		
    		
    		prepStmt = conexion.prepareStatement(sentencia);
    		
    		ResultSet rsProducto = prepStmt.executeQuery();
    		
    		prepStmt = conexion.prepareStatement("SELECT * from Producto where Producto.codigo="+idProducto+" and etapa=-1");
    		
    		ResultSet rsProducto1 = prepStmt.executeQuery();
    		
    		
    		while(rsProducto.next())
    		{
    			rsProducto1.next();
    			producto = new Producto(rsProducto.getLong("codigo"), rsProducto.getString("nombre"), rsProducto.getInt("cantidad"), rsProducto1.getInt("cantidad"), rsProducto.getString("descripcion"), rsProducto.getDouble("costo"), rsProducto.getInt("estado"), rsProducto.getInt("numEtapas"));
    			
    			
    		}
    		
    		prepStmt = conexion.prepareStatement("SELECT * from Proceso, etapa, etapaProduccion etaProd "
    				+ "where Proceso.codigoProducto="+idProducto+" and etapa.codigoProceso=proceso.codigo and etapa.codigoEtapa=etaProd.codigo");
    		
    		ResultSet rsEtapaProd = prepStmt.executeQuery();
    		
    		while(rsEtapaProd.next())
    		{
    			etapasProduc.add(new EtapaProduccion(rsEtapaProd.getLong("etaProd.codigo"), rsEtapaProd.getInt("etaProd.etapa"), rsEtapaProd.getString("etaProd.nombre"), rsEtapaProd.getDate("etaProd.fechaInicio"), rsEtapaProd.getDate("etaProd.fechaFin"), rsEtapaProd.getLong("etaProd.tiempoEjecucion"), rsEtapaProd.getString("etaProd.descripcion")));
    			
    			prepStmt = conexion.prepareStatement("SELECT * FROM (ETAPAPRODUCCION, ESTACIONPRODUCCION, REQUIERE "
        				+ "where "+rsEtapaProd.getLong("etaProd.codigo")+"=etapaProduccion.codigo "
						+ " and etapaProduccion.codigo=estacionProduccion.codigoEtapa and requiere.codigoEstacion=estacionProduccion.codigo) consulta"
						+ "INNER JOIN Materiales mat ON consulta.codigoMaterial= mat.codigo");
    		
    			ResultSet rsMateriales = prepStmt.executeQuery();
    			
    			while(rsMateriales.next())
        		{
        			materiales.add(new Material(rsMateriales.getDouble("cantidad"), rsMateriales.getLong("codigo"), rsMateriales.getString("tipo"), rsMateriales.getString("unidad"), rsMateriales.getString("nombre"), rsMateriales.getDate("ultimoAbastecimiento")));
        			   			
        			
        		}
    			 
    			
    		}
    		
    		
    		prepStmt = conexion.prepareStatement("SELECT * FROM PEDIDO where PEDIDO.codigoProducto="+idProducto);
    		
    		ResultSet rsPedido = prepStmt.executeQuery();
    		
    		while(rsPedido.next())
    		{
    			pedidos.add(new Pedido(rsPedido.getLong("codigo"), rsPedido.getInt("estado"), rsPedido.getLong("number"), rsPedido.getDate("fechaPedido"), rsPedido.getDate("fechaEsperada"), rsPedido.getDate("fechaEntrega")));
    			   			   			
    			
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
    	return new Object[]{producto,etapasProduc,materiales,pedidos};
    }

    public Object[] consultarMaterialMaterial(Long idMaterial, String tipoPedido, String tipo, Integer[] volumen, ArrayList<String> ordenes, ArrayList<String> grupos) throws Exception
    {
    	PreparedStatement prepStmt = null;
    	Material material = new Material();
    	ArrayList<EtapaProduccion> etapasProduc = new ArrayList<EtapaProduccion>();
    	ArrayList<Producto> productos = new ArrayList<Producto>();
    	ArrayList<PedidoMaterial> pedidosMaterial = new ArrayList<PedidoMaterial>();
    	
    	try {
    		establecerConexion(cadenaConexion, usuario, clave);
    		
    		String sentencia = "SELECT * from Material where codigo="+idMaterial+"";
    		if(tipo!=null)
    			sentencia  = sentencia+" tipo="+tipo;
    		if(volumen!=null)
    			sentencia = sentencia + " and cantidad between "+volumen[0]+" and "+volumen[1];

    		Iterator<String> iteraGrupos = grupos.iterator();
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
    			sentencia += "group by "+agrupamiento;
    		}
    		Iterator<String> iteraOrdenes= ordenes.iterator();
    		String ordenamiento = "";
    		while(iteraOrdenes.hasNext()){
    			String orden = iteraOrdenes.next();
    			if (iteraOrdenes.hasNext()) {
    				ordenamiento += orden + ",";
    			}
    			else{
    				ordenamiento += orden;
    			}
    		}
    		if(!ordenamiento.isEmpty()){
    			sentencia += "order by "+ordenamiento;
    		}


    		
    		prepStmt = conexion.prepareStatement(sentencia);
    		
    		ResultSet rsMaterial = prepStmt.executeQuery();
    		
    		
    		while(rsMaterial.next())
    		{
    			material = new Material(rsMaterial.getDouble("cantidad"), rsMaterial.getLong("codigo"), rsMaterial.getString("tipo"), rsMaterial.getString("unidad"), rsMaterial.getString("nombre"), rsMaterial.getDate("ultimoAbastecimiento"));
    			
    			
    		}
    		
    		
    		prepStmt = conexion.prepareStatement("SELECT * FROM (Materiales, ESTACIONPRODUCCION, REQUIERE "
    				+ "where "+idMaterial+"=Materiales.codigo "
					+ " and requiere.codigoMaterial= Materiales.codigo and requiere.codigoEstacion=estacionProduccion.codigo) consulta"
					+ "INNER JOIN ETAPAPRODUCCION etaProd ON consulta.codigoEtapa= etapaProd.codigo");
    		
    		
    		
    		ResultSet rsEtapaProd = prepStmt.executeQuery();
    		
    		while(rsEtapaProd.next())
    		{
    			etapasProduc.add(new EtapaProduccion(rsEtapaProd.getLong("etaProd.codigo"), rsEtapaProd.getInt("etaProd.etapa"), rsEtapaProd.getString("etaProd.nombre"), rsEtapaProd.getDate("etaProd.fechaInicio"), rsEtapaProd.getDate("etaProd.fechaFin"), rsEtapaProd.getLong("etaProd.tiempoEjecucion"), rsEtapaProd.getString("etaProd.descripcion")));
    			
    			prepStmt = conexion.prepareStatement("SELECT * etapaProduccion ep inner join producto pr "
        				+ "on pr.codigo=ep.codigoProducto and pr.codigo="+rsEtapaProd.getLong("etaProd.codigo")+" and etapa=0");
        		
    		
    			ResultSet rsProductos = prepStmt.executeQuery();
    			
    			while(rsProductos.next())
        		{
    				productos.add(new Producto(rsProductos.getLong("pr.codigo"), rsProductos.getString("pr.nombre"), rsProductos.getInt("pr.cantidad"), 0, rsProductos.getString("pr.descripcion"), rsProductos.getDouble("pr.costo"), rsProductos.getInt("pr.estado"), rsProductos.getInt("pr.numEtapas")));
        			   			
        			
        		}
    			 
    			
    		}
    		
    		
    		prepStmt = conexion.prepareStatement("SELECT * FROM PEDIDOMATERIAL where PEDIDOMATERIAL.codioMaterial="+idMaterial);
    		
    		ResultSet rsPedido = prepStmt.executeQuery();
    		
    		while(rsPedido.next())
    		{
    			pedidosMaterial.add(new PedidoMaterial(rsPedido.getLong("codigo"), rsPedido.getInt("cantidadPedida"), rsPedido.getDate("fechaPedido"), rsPedido.getDate("fechaEsperada"), rsPedido.getDouble("costo")));
    			   			   			
    			
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
    	return new Object[]{material,etapasProduc,productos,pedidosMaterial};
    }
    
    public ArrayList<Material> darTodosMaterialesCodigoNombreTipo() throws Exception
    {
    	PreparedStatement prepStmt = null;
    	ArrayList<Material> resp = new ArrayList<Material>();
    	
    	try {
    		establecerConexion(cadenaConexion, usuario, clave);
    		
    		String sentencia = "SELECT codigo, nombre, tipo from Material order by tipo";
    		
    		
    		prepStmt = conexion.prepareStatement(sentencia);
    		
    		ResultSet rsMaterial = prepStmt.executeQuery();
    		
    		
    		while(rsMaterial.next())
    		{
    			Material material = new Material();
    			material.setCodigo(rsMaterial.getLong("codigo"));
    			material.setNombre(rsMaterial.getString("nombre"));
    			material.setTipo(rsMaterial.getString("tipo"));
    			
    			resp.add(material);
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
    	return resp;
    }
    
    
    public ArrayList<Producto> darTodosProductosCodigoNombre() throws Exception
    {
    	PreparedStatement prepStmt = null;
    	ArrayList<Producto> resp = new ArrayList<Producto>();
    	
    	try {
    		establecerConexion(cadenaConexion, usuario, clave);
    		
    		String sentencia = "SELECT codigo, nombre from Producto where etapa=0";
    		
    		
    		prepStmt = conexion.prepareStatement(sentencia);
    		
    		ResultSet rsProducto = prepStmt.executeQuery();
    		
    		
    		while(rsProducto.next())
    		{
    			Producto producto = new Producto();
    			producto.setCodigo(rsProducto.getLong("codigo"));
    			producto.setNombre(rsProducto.getString("nombre"));
    			
    			resp.add(producto);
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
    	return resp;
    }
    
    
    
    public static void main(String[] args) {
    	ConsultaDAO_SoloConexion c = new ConsultaDAO_SoloConexion();
    	c.inicializar();
    	try {
			c.metodo1();
			c.darTodosMaterialesCodigoNombreTipo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }

}
