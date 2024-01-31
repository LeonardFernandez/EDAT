/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edat.estructuras.test;

import edat.estructuras.grafos.Grafo;
import edat.estructuras.lineales.dinamicas.Lista;

/**
 *
 * @author Leo
 */
public class Test {
    public static void main(String[] args) {
        //
        Grafo grafo = new Grafo();
        grafo.insertarVertice("Retiro");
        grafo.insertarVertice("Plottier");
        grafo.insertarVertice("Las grutas");
        grafo.insertarVertice("Neuquen");
        System.out.println(grafo.toString());
        grafo.insertarArco("Retiro", "Neuquen", 32);
        grafo.insertarArco("Retiro", "Las grutas", 345);
        grafo.insertarArco("Neuquen", "Plottier", 45);
        grafo.insertarArco("Plottier", "Retiro", 500);
        grafo.insertarArco("Neuquen", "Las grutas", 39);
        System.out.println(grafo.toString());
        System.out.println("Post Eliminacion");
        //grafo.eliminarArco("Retiro", "Neuquen");
        grafo.eliminarVertice("Retiro");
        System.out.println(grafo.toString());
    }
}
