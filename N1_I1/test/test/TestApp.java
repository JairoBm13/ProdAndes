package test;

import java.sql.Date;

import junit.framework.TestCase;

public class TestApp extends TestCase
{
	private TestDao dao;
	
	public TestApp()
	{
		dao = new TestDao();
		dao.inicializar();
	}
	
	public void testAgregarPK()
	{
		try 
		{
			dao.agregarMaterialSinEliminar(1, 10020, "Materia Prima", "libras", "matera", new Date(System.currentTimeMillis()));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("Fallo crear");
		}
		
		try 
		{
			dao.agregarMaterialPrueba(20, 10020, "Componente", "libras", "litio", new Date(System.currentTimeMillis()));
			fail("Creo uno que no debia");
		}
		catch (Exception e) 
		{
			//e.printStackTrace();
			
		}
		
		try 
		{
			dao.eliminarMaterial(10020);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No pudo eliminar");
		}
	}
	
	public void testAgregarFK()
	{
		try 
		{
			dao.agregarProductoSinEliminar(100000, "TV", 10, "TV de prueba", 10, 0, 5, 5);
			dao.agregarProcesoSinEliminacion(10020, "proce1", 10, "prueba1", 100000);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("Fallo crear");
		}
		try 
		{
			dao.agregarProcesoSinEliminacion(10022, "proce1", 10, "prueba1", 150000);
			fail("No debio crear este");
		}
		catch (Exception e) 
		{
			//e.printStackTrace();
			
		}
		try 
		{
			dao.eliminarProducto(100000, 0);
			fail("Elimino un producto que no debia");
		}
		catch (Exception e) 
		{
			//e.printStackTrace();
			
		}
		try 
		{
			dao.eliminarProceso(10020, 100000);
			dao.eliminarProducto(100000, 0);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("No elimino las pruebas que debia eliminar");
		}
		
	}
	
	public void testAgregarCK()
	{
		try 
		{
			dao.agregarMaterialPrueba(1, 10020, "Materia Prima", "libras", "matera", new Date(System.currentTimeMillis()));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			fail("Fallo crear");
		}
		
		try 
		{
			dao.agregarMaterialPrueba(-5, 10020, "lalalala", "libras", "litio", new Date(System.currentTimeMillis()));
			fail("Creo uno que no debia");
		}
		catch (Exception e) 
		{
			//e.printStackTrace();
			
		}
	}
}
