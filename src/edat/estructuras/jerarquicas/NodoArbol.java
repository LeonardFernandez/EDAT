/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edat.estructuras.jerarquicas;

/**
 *
 * @author Leo
 */
public class NodoArbol {
     //Atributos
    private Object elem;
    private NodoArbol izquierdo;
    private NodoArbol derecho;
    
    public NodoArbol(Object elemento, NodoArbol izq, NodoArbol der){
        elem=elemento;
        izquierdo=izq;
        derecho=der;
    }
    
    public Object getElem(){
        return elem;
    }
    
    public NodoArbol getIzquierdo(){
        return izquierdo;
    }
    
    public NodoArbol getDerecho(){
        return derecho;
    }
    
    public void setElem(Object nuevoElem){
        elem=nuevoElem;
    }
    
    public void setIzquierdo(NodoArbol nuevoNodo){
        izquierdo=nuevoNodo;
    }
    
    public void setDerecho(NodoArbol nuevoNodo){
        derecho=nuevoNodo;
    }
    
}
