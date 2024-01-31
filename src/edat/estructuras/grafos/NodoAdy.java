/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edat.estructuras.grafos;

/**
 *
 * @author Leo
 */
public class NodoAdy {
    private NodoVert vertice; //Referencia al vertice
    private NodoAdy sigAdyacente; //Lista de adyacentes
    private int etiqueta; //Etiqueta tipo int
    
    public NodoAdy(NodoVert unVert, NodoAdy siguiente, int etiq){
        vertice = unVert;
        sigAdyacente = siguiente;
        etiqueta=etiq;
    }
    
    public NodoVert getVertice(){
        return vertice;
    }
    
    public NodoAdy getSigAdyacente(){
        return sigAdyacente;
    }
    
    public int getEtiqueta(){
        return etiqueta;
    }
    
    public void setVertice(NodoVert unVert){
        vertice=unVert;
    }
    
    public void setSigAdyacente(NodoAdy sig){
        sigAdyacente=sig;
    }
    
    public void setEtiqueta(int etiq){
        etiqueta=etiq;
    }
    
}
