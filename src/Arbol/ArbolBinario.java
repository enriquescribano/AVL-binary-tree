package Arbol;

import java.util.*;

public class ArbolBinario {
	protected NodoBinario raiz;

	public ArbolBinario() {
		raiz = null;
	}

	public NodoBinario buscar(Comparable e) {
		if (raiz == null)
			return null;
		else {
			return buscar(e, raiz);
		}
	}

	public NodoBinario buscar(Comparable e, NodoBinario a) {
		while (a != null) {
			if (e.compareTo(a.getElem()) < 0)
				a = a.getIzq();
			else if (e.compareTo(a.getElem()) > 0)
				a = a.getDer();
			else
				return a; // encontrado
		}
		return null; // no encontrado
	}

	public NodoBinario minimo() {
		if (raiz == null)
			return null;
		else
			return minimo(raiz);
	}

	// Método interno para buscar
	private NodoBinario minimo(NodoBinario a) {
		while (a.getIzq() != null) {
			a = a.getIzq();
		}
		return a;
	}

	public int altura() {
		return altura(raiz);
	}

	// Método interno para buscar
	private int altura(NodoBinario a) {
		if (a == null)
			return -1;
		else
			return Math.max(altura(a.getIzq()), altura(a.getDer())) + 1;
	}
	
	private boolean equilibrado(NodoBinario a) {
		boolean res;
		if (a == null) {
			return true;
		} else if (Math.abs(altura(a.getIzq()) - altura(a.getDer())) <= 1) {
			res = true;
		} else {
			res = false;
		}
		return res;
	}

	public ArrayList<NodoBinario> camino(NodoBinario fin) {
		ArrayList<NodoBinario> camino = new ArrayList<NodoBinario>();
		NodoBinario a = this.raiz;
		while (a.getElem() != fin.getElem()) {
			camino.add(a);
			if (fin.getElem().compareTo(a.getElem()) < 0) {
				a = a.getIzq();
			} else {
				a = a.getDer();
			}
		}
		camino.add(a);
		return camino;
	}

	public void insertaNodo(NodoBinario insertado) throws Exception {
		this.inserta(insertado);
		ArrayList<NodoBinario> camino = this.camino(insertado);
		this.corrigeDesequilibrios(camino);
	}

	private void inserta(NodoBinario insert) throws Exception {
		if (raiz == null) {
			this.raiz = insert;
		} else {
			NodoBinario a = this.raiz;
			NodoBinario b;
			while (a != null) {
				if (insert.getElem().compareTo(a.getElem()) < 0) {
					b = a.getIzq();
					if (b == null) {
						a.setIzq(insert);
						return;
					}
					a = a.getIzq();
				} else if (insert.getElem().compareTo(a.getElem()) > 0) {
					b = a.getDer();
					if (b == null) {
						a.setDer(insert);
						return;
					}
					a = a.getDer();
				}
				// encontrado, no hace falta insertar
			}
			throw new Exception(); // no encontrado
		}
	}

	private void corrigeDesequilibrios(ArrayList<NodoBinario> camino)
			throws Exception {
		int size = camino.size();
		if (size > 0) {
			NodoBinario ultimo = camino.get(size - 1);
			while (size > 0) {
				NodoBinario desequilibrado = camino.remove(size - 1);
				size--;
				if (!equilibrado(desequilibrado)) {
					System.out.println("ultimo"+ultimo.getElem());
					System.out.println("desequilibrado"+desequilibrado.getElem());
					System.out.println("corrigiendo");
					if (size < 1) {
						corrigeDesequilibrio(ultimo, desequilibrado, null);// es
																			// la
																			// raiz
					} else {
						corrigeDesequilibrio(ultimo, desequilibrado,
								camino.get(camino.size() - 1));
					}
				}
			}
		}
	}

	private void corrigeDesequilibrio(NodoBinario ultimo, NodoBinario des,
			NodoBinario padreDes) throws Exception {
		if (des == null) {
			throw new Exception("corrigeEquilibrio >>  des=null");
		}
		if(des.getElem().equals(ultimo.getElem())){// eliminamos un hijo unico y provoca desequilibrio
			if(ultimo.getIzq()!=null){
				while(ultimo.getIzq()!=null){
					ultimo=ultimo.getIzq();
				}
			}
			else{
				while(ultimo.getDer()!=null){
					ultimo=ultimo.getDer();
				}
			}
		}
		int caso = casoRotacion(ultimo, des);
		NodoBinario rot = null;
		System.out.println("caso "+caso);
		System.out.println("ultimo "+ultimo.getElem()+" des "+des.getElem());
		switch (caso) {
		case 1:
			rot = conHijoIzquierdo(des);
			break;
		case 2:
			rot = dobleConHijoIzquierdo(des);
			break;
		case 3:
			rot = dobleConHijoDerecho(des);
			break;
		case 4:
			rot = conHijoDerecho(des);
			break;
		default:
			throw new Exception("corrigeEquilibrio >> sin caso");
		}
		if (padreDes == null) {
			this.raiz = rot;
		} else {
			int hijo = des.getElem().compareTo(padreDes.getElem());
			if (hijo > 0) {// a la dereceha del padre
				padreDes.setDer(rot);
			} else if (hijo < 0) {// a la izquierda del padre
				padreDes.setIzq(rot);
			} else {
				throw new Exception(
						"corrigeEquilibrio >> desequilibrado = padre");
			}
		}
	}

	private int casoRotacion(NodoBinario ultimo, NodoBinario des) {
		int son = ultimo.getElem().compareTo(des.getElem());
		if (son < 0) {// hijo izquierdo
			NodoBinario hijoIzq = des.getIzq();
			int nieto = ultimo.getElem().compareTo(hijoIzq.getElem());
			if (nieto < 0) {
				return 1;// caso1
			} else {
				return 2; // caso2
			}
		} else {// hijo derecho
			NodoBinario hijoDer = des.getDer();
			int nieto = ultimo.getElem().compareTo(hijoDer.getElem());
			if (nieto < 0) {
				return 3;// caso3
			} else {
				return 4;// caso4
			}
		}
	}

	private NodoBinario conHijoIzquierdo(NodoBinario k2) { // Caso 1
		NodoBinario k1 = k2.getIzq();
		k2.setIzq(k1.getDer());
		k1.setDer(k2);
		return k1;
	}

	private NodoBinario conHijoDerecho(NodoBinario k1) {// caso 4
		NodoBinario k2 = k1.getDer();
		k1.setDer(k2.getIzq());
		k2.setIzq(k1);
		return k2;
	}

	private NodoBinario dobleConHijoIzquierdo(NodoBinario k3) {// caso 2
		k3.setIzq(conHijoDerecho(k3.getIzq()));
		return conHijoIzquierdo(k3);
	}

	private NodoBinario dobleConHijoDerecho(NodoBinario k1) {// caso 3
		k1.setDer(conHijoIzquierdo(k1.getDer()));
		return conHijoDerecho(k1);
	}

	public void eliminar(Comparable e) throws Exception {
		eliminar(this.buscar(e));
	}

	public void eliminar(NodoBinario eliminado) throws Exception {
		ArrayList<NodoBinario> camino = this.eliminaNodo(eliminado);
		this.corrigeDesequilibrios(camino);
	}

	private ArrayList<NodoBinario> eliminaNodo(NodoBinario eliminado) {
		ArrayList<NodoBinario> camino = camino(eliminado);
		if (camino.size() == 1) {
			camino = this.eliminaRaiz(eliminado);
		} else {
			NodoBinario padreElim = camino.get(camino.size() - 2);
			if ((eliminado.getIzq() == null) && (eliminado.getDer() == null)) {// no
																				// tiene
																				// hijos
				camino.remove(camino.size() - 1);
				if (padreElim.getElem().compareTo(eliminado.getElem()) < 0) {
					padreElim.setDer(null);
				} else {
					padreElim.setIzq(null);
				}
			} else if ((eliminado.getIzq() != null)
					&& (eliminado.getDer() == null)) {
				camino.remove(camino.size() - 1);
				if (padreElim.getElem().compareTo(eliminado.getElem()) < 0) {
					padreElim.setDer(eliminado.getIzq());
					camino.add(eliminado.getIzq());
				} else {
					padreElim.setIzq(eliminado.getIzq());
					camino.add(eliminado.getIzq());
				}
			} else if ((eliminado.getIzq() == null)
					&& (eliminado.getDer() != null)) {
				camino.remove(camino.size() - 1);
				if (padreElim.getElem().compareTo(eliminado.getElem()) < 0) {
					padreElim.setDer(eliminado.getDer());
					camino.add(eliminado.getDer());
				} else {
					padreElim.setIzq(eliminado.getDer());
					camino.add(eliminado.getDer());
				}
			} else if ((eliminado.getIzq() != null)
					&& (eliminado.getDer() != null)) {
				camino = eliminaNodoComplejo(eliminado, camino);
			}
		}
		if(eliminado.getElem().compareTo(22)==0){
			for(int i=0;i<camino.size();i++){
				System.out.println(camino.get(i).getElem());
			}
		}
		return camino;
	}

	private ArrayList<NodoBinario> eliminaRaiz(NodoBinario raiz) {
		ArrayList<NodoBinario> camino = camino(raiz);
		if ((raiz.getIzq() == null) && (raiz.getDer() == null)) {// no tiene
																	// hijos
			camino.remove(camino.size() - 1);
			this.raiz = null;
		} else if ((raiz.getIzq() != null) && (raiz.getDer() == null)) {
			camino.remove(camino.size() - 1);
			camino.add(raiz.getIzq());
			this.raiz = raiz.getIzq();
		} else if ((raiz.getIzq() == null) && (raiz.getDer() != null)) {
			camino.remove(camino.size() - 1);
			camino.add(raiz.getDer());
			this.raiz = raiz.getDer();
		} else if ((raiz.getIzq() != null) && (raiz.getDer() != null)) {
			camino = eliminaNodoComplejoRaiz(raiz, camino);
		}
		return camino;
	}

	private ArrayList<NodoBinario> eliminaNodoComplejoRaiz(NodoBinario raiz,
			ArrayList<NodoBinario> camino) {
		NodoBinario derecho = raiz.getDer();
		NodoBinario izquierdo = raiz.getIzq();
		int alturaIzq = altura(izquierdo);
		int alturaDer = altura(derecho);
		if (alturaDer == 0 && alturaIzq == 0) {
			raiz.setElem(izquierdo.getElem());
			raiz.setIzq(null);
		} else {
			if (alturaDer > alturaIzq) {
				camino.add(derecho);
				NodoBinario ultimo = derecho;
				while (ultimo.getIzq() != null) {
					ultimo = ultimo.getIzq();
					camino.add(ultimo);
				}
				NodoBinario movido = camino.remove(camino.size() - 1);
				raiz.setElem(movido.getElem());
				NodoBinario padreMovido = camino.get(camino.size() - 1);
				if (movido.getDer() == null) {
					padreMovido.setIzq(null);
				} else {
					padreMovido.setIzq(movido.getDer());
				}
			} else {
				camino.add(izquierdo);
				while (izquierdo.getDer() != null) {
					izquierdo = izquierdo.getDer();
					camino.add(izquierdo);
				}
				NodoBinario movido = camino.remove(camino.size() - 1);
				raiz.setElem(movido.getElem());
				NodoBinario padreMovido = camino.get(camino.size() - 1);
				if (movido.getIzq() == null) {
					padreMovido.setDer(null);
				} else {
					padreMovido.setDer(movido.getIzq());
				}
			}
		}
		return camino;
	}

	private ArrayList<NodoBinario> eliminaNodoComplejo(NodoBinario eliminado,
			ArrayList<NodoBinario> camino) {
		NodoBinario derecho = eliminado.getDer();
		NodoBinario izquierdo = eliminado.getIzq();
		int alturaIzq = altura(izquierdo);
		int alturaDer = altura(derecho);
		if (alturaDer == 0 && alturaIzq == 0) {
			eliminado.setElem(izquierdo.getElem());
			eliminado.setIzq(null);
		} else {
			if (alturaDer > alturaIzq) {
				camino.add(derecho);
				NodoBinario ultimo = derecho;
				while (ultimo.getIzq() != null) {
					ultimo = ultimo.getIzq();
					camino.add(ultimo);
				}
				NodoBinario movido = camino.remove(camino.size() - 1);
				eliminado.setElem(movido.getElem());
				NodoBinario padreMovido = camino.get(camino.size() - 1);
				if (movido.getDer() == null) {
					padreMovido.setIzq(null);
				} else {
					padreMovido.setIzq(movido.getDer());
				}
			} else {
				camino.add(izquierdo);
				while (izquierdo.getDer() != null) {
					izquierdo = izquierdo.getDer();
					camino.add(izquierdo);
				}
				NodoBinario movido = camino.remove(camino.size() - 1);
				eliminado.setElem(movido.getElem());
				NodoBinario padreMovido = camino.get(camino.size() - 1);
				if (movido.getIzq() == null) {
					padreMovido.setDer(null);
				} else {
					padreMovido.setDer(movido.getIzq());
				}
			}
		}
		return camino;
	}

	public void imprimeArbol() {
		int altura =this.altura();
		ArrayList<ArrayList<NodoBinario>> alturas = new ArrayList<ArrayList<NodoBinario>>();
		for(int i =0;i<=altura;i++){
			alturas.add(new ArrayList<NodoBinario>());
		}
		anadeNodo(0, this.raiz,alturas);
		imprimeAlturas(alturas);
	}

	private void anadeNodo(int alt, NodoBinario a,ArrayList<ArrayList<NodoBinario>> alturas) {
		if (a == null) {
			return;
		}
		ArrayList<NodoBinario> array=alturas.get(alt);
		if (!array.contains(a)) {
			array.add(a);
			anadeNodo(alt + 1, a.getIzq(),alturas);
			anadeNodo(alt + 1, a.getDer(), alturas);
		}
	}

	private void imprimeAlturas(ArrayList<ArrayList<NodoBinario>> alturas) {
		while(alturas.size()!=0){
			ArrayList<NodoBinario> a =alturas.remove(0);
			while (a.size() != 0) {
				NodoBinario nodo = a.remove(0);
				if (nodo.getIzq() != null) {
					System.out.print("(" + nodo.getIzq().getElem() + "/");
				}
				System.out.print(nodo.getElem());
				if (nodo.getDer() != null) {
					System.out.print("\\" + nodo.getDer().getElem() + ")");
				}
				System.out.print("    ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
