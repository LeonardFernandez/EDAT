/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edat.estructuras.grafos;

import edat.estructuras.lineales.dinamicas.Lista;

/**
 *
 * @author Leo
 */
public class Camino {
    //El proposito de esta clase es facilitar los algoritmos de busqueda de camino de grafos
    private int distancia;
    private Lista recorrido;
    
    public Camino(){
        distancia=0;
        recorrido = new Lista();
    }
    
    public void insertarVertice(Object elem, int dist){
        distancia=distancia+dist;
        recorrido.insertar(elem, recorrido.longitud()+1);
    }

    public int getDistancia() {
        return distancia;
    }

    public Lista getRecorrido() {
        return recorrido;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public void setRecorrido(Lista recorrido) {
        this.recorrido = recorrido;
    }
    
    public boolean esVacio(){
        return recorrido.esVacia();
    }
    
    public String toString(){
        String camino;
        camino = "Vertices: " + recorrido.toString() + " , Distancia: " + distancia;
        return camino;
    }
}
