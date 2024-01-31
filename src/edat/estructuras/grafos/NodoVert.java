/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edat.estructuras.grafos;

/**
 *
 * @author Leo
 */
public class NodoVert {
    //Atributos
    private Object elem;
    private NodoVert sigVertice; //Lista de nodos vertice
    private NodoAdy primerAdy; //Lista de adyacentes
    
    public NodoVert(Object unElem, NodoVert siguienteVertice, NodoAdy adyacente){
        elem=unElem;
        sigVertice=siguienteVertice;
        primerAdy=adyacente;
    }
    
    public Object getElem(){
        return elem;
    }
    
    public NodoVert getSigVertice(){
        return sigVertice;
    }
    
    public NodoAdy getPrimerAdy(){
        return primerAdy;
    }
    
    public void setElem(Object unElem){
        elem=unElem;
    }
    
    public void setSigVertice(NodoVert siguienteVertice){
        sigVertice=siguienteVertice;
    }
    
    public void setPrimerAdy(NodoAdy adyacente){
        primerAdy=adyacente;
    }
}
