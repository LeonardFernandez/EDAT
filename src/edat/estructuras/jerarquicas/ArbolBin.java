/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edat.estructuras.jerarquicas;

/**
 *
 * @author Leo
 */
public class ArbolBin {
    private NodoArbol raiz;

    public ArbolBin() {
        raiz = null;
    }
    
    public boolean insertar(Object nuevoElem, Object elemPadre, char posicion) {
        boolean exito = false;
        //Si el arbol esta vacio, se inserta en la raiz
        if (esVacio()) {
            raiz = new NodoArbol(nuevoElem, null, null);
            exito = true;
        } else {
            //Buscamos el nodo padre indicado
            NodoArbol nPadre = obtenerNodo(raiz, elemPadre);
            //Si padre existe
            if (nPadre != null) {
                //Si el lugar no esta ocupado entonces inserta
                if (nPadre.getIzquierdo() == null && (posicion == 'I' || posicion == 'i')) {
                    nPadre.setIzquierdo(new NodoArbol(nuevoElem, null, null));
                    exito = true;
                } else if (nPadre.getDerecho() == null && (posicion == 'D' || posicion == 'd')) {
                    nPadre.setDerecho(new NodoArbol(nuevoElem, null, null));
                    exito = true;
                }
            }
        }
        return exito;
    }

    public boolean esVacio() {
        return raiz == null;
    }

    private NodoArbol obtenerNodo(NodoArbol n, Object elemBuscado) {
        //Busca un elemento, retorna el nodo que lo contiene
        NodoArbol resultado = null;
        //Caso base
        if (n != null) {
            if (n.getElem().equals(elemBuscado)) {
                resultado = n;
            } else {
                //Primero buscamos en el hijo izquierdo
                resultado = obtenerNodo(n.getIzquierdo(), elemBuscado);
                //Si no se encontro, se busca en el hijo derecho
                if (resultado == null) {
                    resultado = obtenerNodo(n.getDerecho(), elemBuscado);
                }
            }
        }
        return resultado;
    }

    public void vaciar() {
        raiz = null;
    }
    
    
}
