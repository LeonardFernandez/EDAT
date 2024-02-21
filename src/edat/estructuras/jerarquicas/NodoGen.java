/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edat.estructuras.jerarquicas;

/**
 *
 * @author Leo
 */
public class NodoGen {
     //Atributos
    private Object elem;
    private NodoGen hijoIzquierdo;
    private NodoGen hermanoDerecho;
    
    public NodoGen(Object elemNuevo, NodoGen HEI, NodoGen HD){
        elem=elemNuevo;
        hijoIzquierdo=HEI;
        hermanoDerecho=HD;
    }
    
    public Object getElem(){
        return elem;
    }
    
    public NodoGen getHijoIzquierdo(){
        return hijoIzquierdo;
    }
    
    public NodoGen getHermanoDerecho(){
        return hermanoDerecho;
    }
    
    public void setElem(Object elemNuevo){
        elem=elemNuevo;
    }
    
    public void setHijoIzquierdo(NodoGen HI){
        hijoIzquierdo=HI;
    }
    
    public void setHermanoDerecho(NodoGen HD){
        hermanoDerecho=HD;
    }

}
