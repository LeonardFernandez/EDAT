/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edat.estructuras.test;

import edat.estructuras.grafos.Camino;
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
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarVertice("C");
        grafo.insertarVertice("D");
        grafo.insertarVertice("E");
        grafo.insertarVertice("F");
        grafo.insertarVertice("X");
        System.out.println(grafo.toString());
        grafo.insertarArco("A", "E", 8);
        grafo.insertarArco("A", "D", 3);
        grafo.insertarArco("A", "C", 7);
        grafo.insertarArco("C", "B", 21);
        grafo.insertarArco("E", "F", 3);
        grafo.insertarArco("E", "X", 2);
        grafo.insertarArco("D", "X", 2);
        grafo.insertarArco("D", "C", 4);
        grafo.insertarArco("X", "F", 3);
        grafo.insertarArco("F", "B", 6);
        grafo.insertarArco("X", "B", 10);
        System.out.println(grafo.toString());
        System.out.println("Recorrido menor");
//        Lista camino = new Lista();
//        camino = grafo.caminoSinVertice("A", "B", "X");
//        for (int i = 1; i <= camino.longitud(); i++) {
//            System.out.println(camino.recuperar(i).toString());
//        }
        Camino camino = new Camino();
        camino = grafo.caminMaxDistancia("A", "B", 12);
        System.out.println(camino.toString());
        }
    }

