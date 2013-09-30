package Arbol;


public class NodoBinario {
	private Comparable elem;
	private NodoBinario izq,der;
	
	NodoBinario(Comparable e, NodoBinario i, NodoBinario d){
		elem=e;
		izq=i;
		der=d;
	}
	NodoBinario(Comparable e){
		this(e,null,null);
	}
	public Comparable getElem() {
		return elem;
	}
	public void setElem(Comparable elem) {
		this.elem = elem;
	}
	public NodoBinario getIzq() {
		return izq;
	}
	public void setIzq(NodoBinario izq) {
		this.izq = izq;
	}
	public NodoBinario getDer() {
		return der;
	}
	public void setDer(NodoBinario der) {
		this.der = der;
	}
}