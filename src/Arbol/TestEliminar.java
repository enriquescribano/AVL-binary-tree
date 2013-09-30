package Arbol;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestEliminar {

	@Test
//	Elimina un nodo sin hijos
	public void testEliminaSinHijos() {
		try{
			ArbolBinario arbol = new ArbolBinario();
		
			NodoBinario a1 = new NodoBinario(1);
			NodoBinario a2 = new NodoBinario(2);
			NodoBinario a3 = new NodoBinario(3);
			
			arbol.insertaNodo(a1);
			arbol.insertaNodo(a2);
			arbol.insertaNodo(a3);
			
			arbol.eliminar(3);
			assertEquals(a2.getDer(), null);
			
		}
		catch (Exception e) {
			fail("Exception");
		}
	}

	@Test
//	Elimina un nodo con un hijo
	public void testEliminaConHijo() {
		try{
			
			NodoBinario a1 = new NodoBinario(1);
			NodoBinario a3 = new NodoBinario(3);
			NodoBinario a2 = new NodoBinario(2);
			NodoBinario a4 = new NodoBinario(4);
			NodoBinario a5 = new NodoBinario(5);
			NodoBinario a6 = new NodoBinario(6);
			NodoBinario a7 = new NodoBinario(7);
			NodoBinario a8 = new NodoBinario(8);
			NodoBinario a9 = new NodoBinario(9);
			NodoBinario a10 = new NodoBinario(10);
			
			
			ArbolBinario arbol = new ArbolBinario();
			
			arbol.insertaNodo(a6);
			arbol.insertaNodo(a4);
			arbol.insertaNodo(a8);
			arbol.insertaNodo(a2);
			arbol.insertaNodo(a5);
			arbol.insertaNodo(a7);
			arbol.insertaNodo(a9);
			arbol.insertaNodo(a1);
			arbol.insertaNodo(a3);
			arbol.insertaNodo(a10);
			
			arbol.eliminar(9);
			assertEquals(a8.getDer(), a10);
			assertEquals(a10.getDer(), null);
			assertEquals(a10.getIzq(), null);
			
		
	
		}
		catch (Exception e) {
			fail("Exception");
		}
	}
	
	@Test
//	Elimina un nodo con dos hijos
	public void testEliminaConHijos() {
		try{
			
			NodoBinario a1 = new NodoBinario(1);
			NodoBinario a3 = new NodoBinario(3);
			NodoBinario a2 = new NodoBinario(2);
			NodoBinario a4 = new NodoBinario(4);
			NodoBinario a5 = new NodoBinario(5);
			NodoBinario a6 = new NodoBinario(6);
			NodoBinario a7 = new NodoBinario(7);
			NodoBinario a8 = new NodoBinario(8);
			NodoBinario a9 = new NodoBinario(9);
			NodoBinario a10 = new NodoBinario(10);
			
			
			ArbolBinario arbol = new ArbolBinario();
			
			arbol.insertaNodo(a6);
			arbol.insertaNodo(a4);
			arbol.insertaNodo(a8);
			arbol.insertaNodo(a2);
			arbol.insertaNodo(a5);
			arbol.insertaNodo(a7);
			arbol.insertaNodo(a9);
			arbol.insertaNodo(a1);
			arbol.insertaNodo(a3);
			arbol.insertaNodo(a10);
			
			assertEquals(a4.getIzq(), a2);
			assertEquals(a2.getElem(), 2);
			
			arbol.eliminar(2);
		
			assertEquals(a2.getElem(), 1);
		
		}
		catch (Exception e) {
			fail("Exception");
		}
	}
	
	@Test
//	Elimina un nodo rotando, al quedar el arbol desequilibrado
	public void testEliminavarios() {
		try{
			
			NodoBinario a1 = new NodoBinario(1);
			NodoBinario a3 = new NodoBinario(3);
			NodoBinario a2 = new NodoBinario(2);
			NodoBinario a4 = new NodoBinario(4);
			NodoBinario a5 = new NodoBinario(5);
			NodoBinario a6 = new NodoBinario(6);
			NodoBinario a7 = new NodoBinario(7);
			NodoBinario a8 = new NodoBinario(8);
			NodoBinario a9 = new NodoBinario(9);
			NodoBinario a10 = new NodoBinario(10);
			
			
			ArbolBinario arbol = new ArbolBinario();
			
			arbol.insertaNodo(a6);
			arbol.insertaNodo(a4);
			arbol.insertaNodo(a8);
			arbol.insertaNodo(a2);
			arbol.insertaNodo(a5);
			arbol.insertaNodo(a7);
			arbol.insertaNodo(a9);
			arbol.insertaNodo(a1);
			arbol.insertaNodo(a3);
			arbol.insertaNodo(a10);
			
			assertEquals(a6.getIzq().getElem(), 4);
			
			assertEquals(a4.getDer(), a5);
			assertEquals(a5.getElem(), 5);
			
			arbol.eliminar(5);
			
			
			assertEquals(arbol.raiz, a6);
			assertEquals(a6.getIzq().getElem(), 2);
			assertEquals(a6.getIzq().getDer().getElem(), 4);
			
			assertEquals(a4.getDer(), null);
			assertEquals(a5.getDer(), null);
			assertEquals(a5.getIzq(), null);
		}
		catch (Exception e) {
			fail("Exception");
		}
	}
	
	@Test
//	Elimina varios nodos en un orden indeterminado
//	Usaremos un arbol de 16 nodos
	public void testEliminaVarios() {
		try{
			
			NodoBinario n=null;
			ArbolBinario arbol = new ArbolBinario();
			
			NodoBinario a1 = new NodoBinario(1);
			NodoBinario a3 = new NodoBinario(3);
			NodoBinario a2 = new NodoBinario(2);
			NodoBinario a4 = new NodoBinario(4);
			NodoBinario a5 = new NodoBinario(5);
			NodoBinario a6 = new NodoBinario(6);
			NodoBinario a7 = new NodoBinario(7);
			NodoBinario a8 = new NodoBinario(8);
			NodoBinario a9 = new NodoBinario(9);
			NodoBinario a10 = new NodoBinario(10);
			NodoBinario a11 = new NodoBinario(11);
			NodoBinario a12 = new NodoBinario(12);
			NodoBinario a13 = new NodoBinario(13);
			NodoBinario a14 = new NodoBinario(14);
			NodoBinario a15 = new NodoBinario(15);
			NodoBinario a16 = new NodoBinario(16);
			
			arbol.insertaNodo(a1);
			arbol.insertaNodo(a2);
			arbol.insertaNodo(a3);
			arbol.insertaNodo(a4);
			arbol.insertaNodo(a5);
			arbol.insertaNodo(a6);
			arbol.insertaNodo(a7);
			arbol.insertaNodo(a8);
			arbol.insertaNodo(a9);
			arbol.insertaNodo(a10);
			arbol.insertaNodo(a11);
			arbol.insertaNodo(a12);
			arbol.insertaNodo(a13);
			arbol.insertaNodo(a14);
			arbol.insertaNodo(a15);
			arbol.insertaNodo(a16);
			
			
			assertEquals(a2.getIzq().getElem(), 1);
			assertEquals(a15.getDer().getElem(), 16);
			assertEquals(a6.getDer().getElem(), 7);
			
			
			arbol.eliminar(1);
			arbol.eliminar(16);
			arbol.eliminar(7);
			

			
			assertEquals(a2.getIzq(), null);
			assertEquals(a15.getDer(), null);
			assertEquals(a6.getDer(), null);
			
		}
		catch (Exception e) {
			fail("Exception");
		}
	}
		
	
}
