/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edat.estructuras.conjuntistas.dinamicas;

/**
 *
 * @author Leo
 */
public class NodoAVL {
    private Comparable elemento;
    private NodoAVL izquierdo;
    private NodoAVL derecho;
    private int altura;
    
    public NodoAVL(Comparable nuevoElem, NodoAVL izq, NodoAVL der){
        elemento=nuevoElem;
        izquierdo=izq;
        derecho=der;
        altura=0;
    }
    
    public int getAltura(){
        return altura;
    }
    
    public Comparable getElem(){
        return elemento;
    }
    
    public NodoAVL getIzquierdo(){
        return izquierdo;
    }
    
    public NodoAVL getDerecho(){
        return derecho;
    }
    
    public void setElem(Comparable nuevoElem){
        elemento=nuevoElem;
    }
    
    public void setIzquierdo(NodoAVL nuevoNodo){
        izquierdo=nuevoNodo;
    }
    
    public void setDerecho(NodoAVL nuevoNodo){
        derecho=nuevoNodo;
    }
    
    public void recalcularAltura(){
        //Si su hijo(izq o der) es null entonces la altura de ese enlace es -1
        int altDer=-1;
        int altIzq=-1;
        if(derecho!=null){
            altDer=derecho.altura;
        }
        if(izquierdo!=null){
            altIzq=izquierdo.altura;
        }
        altura=Math.max(altIzq, altIzq)+1;
    }
}
