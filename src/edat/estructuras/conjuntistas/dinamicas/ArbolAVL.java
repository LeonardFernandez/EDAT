/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edat.estructuras.conjuntistas.dinamicas;

/**
 *
 * @author Leo
 */
public class ArbolAVL {
    private NodoAVL raiz;
    
    public ArbolAVL(){
        raiz=null;
    }
    
    private NodoAVL rotarIzq(NodoAVL pivote){
        NodoAVL h = pivote.getDerecho();
        NodoAVL temp = null;
        if(h.getIzquierdo()!=null){
            temp=h.getIzquierdo();
        }
        h.setIzquierdo(pivote); //h pasa a ser padre de pivote(su padre)
        pivote.setDerecho(temp); //temp pasa a ser HD de pivote
        //Recalculamos las alturas
        pivote.recalcularAltura();
        h.recalcularAltura();
        //Retornamos el nuevo padre(h) de este subarbol
        return h;
    }
    
    private NodoAVL rotarDer(NodoAVL pivote){
        NodoAVL h = pivote.getIzquierdo();
        NodoAVL temp = null;
        if(h.getIzquierdo()!=null){
            temp=h.getDerecho();
        }
        h.setDerecho(pivote); 
        pivote.setIzquierdo(temp); 
        //Recalculamos las alturas
        pivote.recalcularAltura();
        h.recalcularAltura();
        //Retornamos el nuevo padre(h) de este subarbol
        return h;
    }
    
     private NodoAVL rotarIzDer(NodoAVL padre) {
        //Rotamos hacia la izq el hijoIzq
        NodoAVL hijoIzq = rotarIzq(padre.getIzquierdo());
        //Enganchamos el nuevo hijoIzq a su padre
        padre.setIzquierdo(hijoIzq);
        //Rotamos todo el arbol hacia la derecha y retornamos este nuevo padre
        NodoAVL nuevoPadre = rotarDer(padre);
        //Recalculamos alturas
        hijoIzq.recalcularAltura();
        padre.recalcularAltura();
        return nuevoPadre;
    }

    private NodoAVL rotarDerIz(NodoAVL padre) {
        //Rotamos hacia la der el hijo derecho
        NodoAVL hijoDer = rotarDer(padre.getDerecho());
        //Engachamos este nuevo hijo a derecho a su padre
        padre.setDerecho(hijoDer);
        //Rotamos todo el arbol hacia la izquierda y retornamos este nuevo padre
        NodoAVL nuevoPadre = rotarIzq(padre);
        hijoDer.recalcularAltura();
        padre.recalcularAltura();
        return nuevoPadre;
    }
    
    private int balance(NodoAVL nodo) {
        //Devuelve el balance de un nodo
        int balance = -1;
        if (nodo != null) {
            //Si los hijos son nulos los valores seran -1
            int altIzq = -1;
            int altDer = -1;
            if (nodo.getIzquierdo() != null) {
                altIzq = nodo.getIzquierdo().getAltura();
            }
            if (nodo.getDerecho() != null) {
                altDer = nodo.getDerecho().getAltura();
            }
            balance = altIzq - altDer;
        }
        return balance;
    }
    
    
}
