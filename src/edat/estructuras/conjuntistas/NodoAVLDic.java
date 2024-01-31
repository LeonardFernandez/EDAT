/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edat.estructuras.conjuntistas;

/**
 *
 * @author Leo
 */
public class NodoAVLDic {
     private Comparable clave;
    private Object dato;
    private int altura;
    private NodoAVLDic izquierdo;
    private NodoAVLDic derecho;

    public NodoAVLDic() {
        clave =null;
        dato = null;
        izquierdo = null;
        derecho = null;
        altura = 0;
    }

    public NodoAVLDic(Comparable unaClave, Object unDato, NodoAVLDic izq, NodoAVLDic der) {
        clave = unaClave;
        izquierdo = izq;
        derecho = der;
        altura = 0;
        dato = unDato;
    }

    public Comparable getClave() {
        return clave;
    }

    public void setClave(Comparable clave) {
        this.clave = clave;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public NodoAVLDic getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoAVLDic derecho) {
        this.derecho = derecho;
    }

    public NodoAVLDic getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoAVLDic izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void recalcularAltura() {

        int altIzq, altDer;
        // altura de HI o HD nulos es -1
        altIzq = -1;
        altDer = -1;
        if (this.izquierdo != null) { // Si tiene HI calculo su altura
            altIzq = (this.izquierdo).altura;
        }
        if (this.derecho != null) { // Si tiene HD calculo su altura
            altDer = (this.derecho).altura;
        }
        this.altura = Math.max(altIzq, altDer) + 1;
    }
}
