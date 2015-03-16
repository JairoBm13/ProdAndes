package co.edu.uniandes.N1_I1.fachada;

import java.util.ArrayList;

import co.edu.uniandes.N1_I1.dao.ConsultaDAO;
import co.edu.uniandes.N1_I1.vos.Administrador;
import co.edu.uniandes.N1_I1.vos.Cliente;
import co.edu.uniandes.N1_I1.vos.Operario;
import co.edu.uniandes.N1_I1.vos.Proveedor;
import co.edu.uniandes.N1_I1.vos.Usuario;

/**
 * Clase VideoAndes, que representa la fachada de comunicación entre
 * la interfaz y la conexión con la base de datos. Atiende todas
 * las solicitudes.
 */
public class ProdAndes 
{
	
	private final static String ADMIN="sysadmin";
	/**
	 * Conexión con la clase que maneja la base de datos
	 */
	private ConsultaDAO dao;
	
	private int tipoUsuario;
	
	private Usuario usuarioVal;
	
	private Administrador adminVal;
	
	private Cliente clienteVal;
	
	private Proveedor proveedorVal;
	
	private Operario operarioVal;
    
    // -----------------------------------------------------------------
    // Singleton
    // -----------------------------------------------------------------


    /**
     * Instancia única de la clase
     */
    private static ProdAndes instancia;
    
    /**
     * Devuelve la instancia única de la clase
     * @return Instancia única de la clase
     */
    public static ProdAndes darInstancia( )
    {
        if( instancia == null )
        {
            instancia = new ProdAndes( );
        }
        return instancia;
    }
	
	/**
	 * contructor de la clase. Inicializa el atributo dao.
	 */
	private ProdAndes()
	{
		dao = new ConsultaDAO();
		tipoUsuario = 0;
	}
	
	/**
	 * inicializa el dao, dándole la ruta en donde debe encontrar
	 * el archivo properties.
	 * @param ruta ruta donde se encuentra el archivo properties
	 */
	public void inicializarRuta(String ruta)
	{
		dao.inicializar(ruta);
	}
	
	// ---------------------------------------------------
    // Métodos asociados autenticación
    // ---------------------------------------------------
	
	/**
	 * 
	 * @param usu
	 * @param correo
	 * @param contrasenia
	 * @return
	 */
    public boolean login(String usu, String correo, String contrasenia){
    	Object usuario = null;
    	try {
			usuario = dao.inicioSesion(usu, correo, contrasenia);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	if(usuario==null) return false;
    	else if(usuario.getClass().getSimpleName().equals("Administrador")){
    		tipoUsuario=1;
    		adminVal = (Administrador) usuario;
    	}
    	else if(usuario.getClass().getSimpleName().equals("Proveedor")){
    		tipoUsuario=2;
    		proveedorVal = (Proveedor) usuario;
    	}
    	else if(usuario.getClass().getSimpleName().equals("Cliente")){
    		tipoUsuario=3;
    		clienteVal = (Cliente) usuario;
    	}
    	else if(usuario.getClass().getSimpleName().equals("Operario")){
    		tipoUsuario=4;
    		operarioVal = (Operario) usuario;
    	}
    	return true;
    }
    
    /**
     * 
     */
    public void logout(){
		tipoUsuario=0;
		adminVal=null;
		clienteVal=null;
		operarioVal=null;
		proveedorVal=null;
	}
    
    //-------------------------------------------------------
    // Metodos asociados al registro de información
    //-------------------------------------------------------
	
    /**
     * 
     * @param codigo
     * @param etapa
     * @throws Exception
     */
    public void registrarEjecucionEtapa(int codigo, int etapa) throws Exception{
    	dao.registrarEjecucionEtapaDeProduccion(codigo, etapa);
    }
    
    /**
     * 
     * @param cantidad
     * @param codigo
     * @throws Exception
     */
    public void registrarInsumos(double cantidad, int codigo) throws Exception {
    	dao.registrarLlegadaDeInsumos(cantidad, codigo);
    }
    
    /**
     * 
     * @param pedido
     * @throws Exception
     */
    public void registrarPedidoCompletado(String pedido) throws Exception{
    	dao.registrarEntregaPedido(pedido);
    }
    
    // ---------------------------------------------------
    // Métodos asociados a los casos de uso: Consulta
    // ---------------------------------------------------
    
    /**
     * 
     * @param tipo
     * @param inventario
     * @param etapa
     * @param fechaEntrega
     * @param fechaSolicitud
     * @param ordenes
     * @param grupos
     * @return
     * @throws Exception
     */
    public ArrayList consultarExistencia(String tipo, String inventario, String etapa, String fechaEntrega, String fechaSolicitud, ArrayList<String> ordenes, ArrayList<String> grupos) throws Exception{
    	return dao.consultarExistenciaDe(tipo, inventario, etapa, fechaEntrega, fechaSolicitud, ordenes, grupos);
    }
    
    public ArrayList consultarEtapaProduccion() throws Exception{
    	return dao.seleccionarTodosLasEtapa();
    }
}
