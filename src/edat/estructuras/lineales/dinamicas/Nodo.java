/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edat.estructuras.lineales.dinamicas;

/**
 *
 * @author Leo
 */
public class Nodo {
     //Atributos
    private Object elem;
    private Nodo enlace;
    
    //Constructor
    public Nodo(Object elemento, Nodo nuevoN){
        elem=elemento;
        enlace=nuevoN;
    }
    
    //Modificadoras
    public void setElem(Object elemento){
        elem=elemento;
    }
    
    public void setEnlace(Nodo nuevoN){
        enlace=nuevoN;
    }
    
    //Observadoras
    public Object getElem(){
        return elem;
    }
    
    public Nodo getEnlace(){
        return enlace;
    }
}
