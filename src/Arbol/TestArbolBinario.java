package Arbol;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestArbolBinario {
	
	NodoBinario a1 = new NodoBinario(1);
	NodoBinario a2 = new NodoBinario(2);
	NodoBinario a3 = new NodoBinario(3);
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

	@Test
	public void testInsertaNormal() {
		try{
			ArbolBinario arbol = new ArbolBinario();
			arbol.insertaNodo(a1);
			assertEquals(arbol.raiz,a1);
		}
		catch (Exception e) {
			fail("Exception");
		}
	}
	
	@Test
	public void testInsertaNormal2() {
		try{
			ArbolBinario arbol = new ArbolBinario();
			arbol.insertaNodo(a2);
			arbol.insertaNodo(a1);
			arbol.insertaNodo(a3);
			assertEquals(arbol.raiz,a2);
			assertEquals(arbol.raiz.getIzq(),a1);
			assertEquals(arbol.raiz.getDer(),a3);
		}
		catch (Exception e) {
			fail("Exception");
		}
	}
	
	@Test
	public void testInsertaConRotacion() {
		try{
			ArbolBinario arbol = new ArbolBinario();
			arbol.insertaNodo(a1);
			arbol.insertaNodo(a2);
			arbol.insertaNodo(a3);
			assertEquals(arbol.raiz,a2);
			assertEquals(arbol.raiz.getIzq(),a1);
			assertEquals(arbol.raiz.getDer(),a3);
		}
		catch (Exception e) {
			fail("Exception");
		}
	}
	
	@Test
	public void testInsertaConRotacion2() {
		try{
			ArbolBinario arbol = new ArbolBinario();
			arbol.insertaNodo(a12);
			arbol.insertaNodo(a8);
			arbol.insertaNodo(a16);
			arbol.insertaNodo(a14);
			arbol.insertaNodo(a10);
			arbol.insertaNodo(a4);
			arbol.insertaNodo(a2);
			arbol.insertaNodo(a6);
			arbol.insertaNodo(a5);
			assertEquals(arbol.raiz,a12);
			assertEquals(arbol.raiz.getIzq(),a6);
			assertEquals(arbol.raiz.getIzq().getIzq(),a4);
			assertEquals(arbol.raiz.getIzq().getDer(),a8);
		}
		catch (Exception e) {
			fail("Exception");
		} 
	}
	
	@Test
	public void testInsertaConRotacion3() {
		try{
			ArbolBinario arbol = new ArbolBinario();
			arbol.insertaNodo(a3);
			arbol.insertaNodo(a1);
			arbol.insertaNodo(a5);
			arbol.insertaNodo(a2);
			arbol.insertaNodo(a4);
			arbol.insertaNodo(a9);
			arbol.insertaNodo(a8);
			arbol.insertaNodo(a10);
			arbol.insertaNodo(a7);
			assertEquals(arbol.raiz,a3);
			assertEquals(arbol.raiz.getIzq(),a1);
			assertEquals(arbol.raiz.getDer(),a8);
			assertEquals(arbol.raiz.getDer().getIzq(),a5);
			assertEquals(arbol.raiz.getDer().getDer(),a9);
		}
		catch (Exception e) {
			fail("Exception");
		} 
	}
	
	@Test
	public void testInsertaConRotacion4() {
		try{
			ArbolBinario arbol = new ArbolBinario();
			arbol.insertaNodo(a1);
			arbol.insertaNodo(a3);
			arbol.insertaNodo(a5);
			assertEquals(arbol.raiz,a3);
			assertEquals(arbol.raiz.getIzq(),a1);
			assertEquals(arbol.raiz.getDer(),a5);
			arbol.imprimeArbol();
		}
		catch (Exception e) {
			fail("Exception");
		}
	}
	
	@Test
	public void testInsertaConRotacion5() {
		try{
			ArbolBinario arbol = new ArbolBinario();
			arbol.insertaNodo(a3);
			arbol.insertaNodo(a2);
			arbol.insertaNodo(a6);
			arbol.insertaNodo(a5);
			arbol.insertaNodo(a7);
			arbol.insertaNodo(a8);
			assertEquals(arbol.raiz,a6);
			assertEquals(arbol.raiz.getIzq(),a3);
			assertEquals(arbol.raiz.getDer(),a7);
			assertEquals(arbol.raiz.getIzq().getIzq(),a2);
			assertEquals(arbol.raiz.getIzq().getDer(),a5);
			assertEquals(arbol.raiz.getDer().getDer(),a8);
		}
		catch (Exception e) {
			fail("Exception");
		}
	}
	
	@Test
	public void testInsertaMuchos() {
		try{
			ArbolBinario arbol = new ArbolBinario();
			for(int i=1;i<500;i++){
				NodoBinario a =new NodoBinario(i);
				arbol.insertaNodo(a);
			}
			assertEquals(arbol.raiz.getElem(),256);
			assertEquals(arbol.raiz.getIzq().getElem(),128);
			assertEquals(arbol.raiz.getDer().getElem(),384);
			assertEquals(arbol.raiz.getIzq().getIzq().getElem(),64);
			assertEquals(arbol.raiz.getIzq().getDer().getElem(),192);
			assertEquals(arbol.raiz.getDer().getDer().getElem(),448);
			assertEquals(arbol.raiz.getDer().getIzq().getElem(),320);
		}
		catch (Exception e) {
			fail("Exception");
		}
	}
	
	@Test
	public void testInsertaMuchosInverso() {
		try{
			ArbolBinario arbol = new ArbolBinario();
			for(int i=500;i>1;i--){
				NodoBinario a =new NodoBinario(i);
				arbol.insertaNodo(a);
			}
			assertEquals(arbol.raiz.getElem(),245);
			assertEquals(arbol.raiz.getIzq().getElem(),117);
			assertEquals(arbol.raiz.getDer().getElem(),373);
			assertEquals(arbol.raiz.getIzq().getIzq().getElem(),53);
			assertEquals(arbol.raiz.getIzq().getDer().getElem(),181);
			assertEquals(arbol.raiz.getDer().getDer().getElem(),437);
			assertEquals(arbol.raiz.getDer().getIzq().getElem(),309);
		}
		
		catch (Exception e) {
			fail("Exception");
		}
	}
	


}
