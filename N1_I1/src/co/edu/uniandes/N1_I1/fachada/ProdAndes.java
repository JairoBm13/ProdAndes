package co.edu.uniandes.N1_I1.fachada;

import java.util.ArrayList;

import co.edu.uniandes.N1_I1.dao.ConsultaDAO;
import co.edu.uniandes.N1_I1.vos.Cliente;
import co.edu.uniandes.N1_I1.vos.Operario;
import co.edu.uniandes.N1_I1.vos.Proveedor;
import co.edu.uniandes.N1_I1.vos.Usuario;

/**
 * Clase VideoAndes, que representa la fachada de comunicaci�n entre
 * la interfaz y la conexi�n con la base de datos. Atiende todas
 * las solicitudes.
 */
public class ProdAndes 
{
	
	private final static String ADMIN="sysadmin";
	/**
	 * Conexi�n con la clase que maneja la base de datos
	 */
	private ConsultaDAO dao;
	
	private int tipoUsuario;
	
	private Usuario usuarioVal;
	
	private Cliente clienteVal;
	
	private Proveedor proveedorVal;
	
	private Operario operarioVal;
    
    // -----------------------------------------------------------------
    // Singleton
    // -----------------------------------------------------------------


    /**
     * Instancia �nica de la clase
     */
    private static ProdAndes instancia;
    
    /**
     * Devuelve la instancia �nica de la clase
     * @return Instancia �nica de la clase
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
	 * inicializa el dao, d�ndole la ruta en donde debe encontrar
	 * el archivo properties.
	 * @param ruta ruta donde se encuentra el archivo properties
	 */
	public void inicializarRuta(String ruta)
	{
		dao.inicializar(ruta);
	}
	
	
	// ---------------------------------------------------
    // M�todos asociados autenticaci�n
    // ---------------------------------------------------
    public boolean loggin(String correo, String contrasenia){
    	Object usuario = null;
    }
	
    // ---------------------------------------------------
    // M�todos asociados a los casos de uso: Consulta
    // ---------------------------------------------------
    
//	/**
//	 * m�todo que retorna los videos en orden alfab�tico.
//	 * invoca al DAO para obtener los resultados.
//	 * @return ArrayList lista con los videos ordenados alfabeticamente.
//	 * @throws Exception pasa la excepci�n generada por el DAO
//	 */
//	public ArrayList<VideosValue> darVideosDefault() throws Exception
//	{
//	    return dao.darVideosDefault();
//	}
	
}
