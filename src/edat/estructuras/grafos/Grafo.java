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
public class Grafo {

    private NodoVert inicio;

    public Grafo() {
        inicio = null;
    }

    public boolean esVacio() {
        return (inicio == null);
    }

    private NodoVert ubicarVertice(Object elem) {
        NodoVert aux = inicio;
        while (aux != null && !(aux.getElem().equals(elem))) {
            //Mientras nodo no sea nulo y no encontremos el elemento
            aux = aux.getSigVertice(); //Avanzamos siguiente nodo de la lista
        }
        return aux;
    }

    public boolean insertarVertice(Object elem) {
        boolean exito = false;
        NodoVert aux = null;
        aux = ubicarVertice(elem);
        if (aux == null) {
            inicio = new NodoVert(elem, inicio, null);
            exito = true;
        }
        return exito;
    }

    public boolean insertarArco(Object origen, Object destino, int etiq) {
        boolean exito = false;
        NodoVert aux = inicio; //aux recorre los nodosVertice buscando Origen y destino
        NodoVert nodoOrigen = null;
        NodoVert nodoDestino = null;
        while (aux != null && (nodoOrigen == null || nodoDestino == null)) {
            if (aux.getElem().equals(origen)) {
                nodoOrigen = aux;
            }
            if (aux.getElem().equals(destino)) {
                nodoDestino = aux;
            }
            aux = aux.getSigVertice();
        }
        if (nodoOrigen != null && nodoDestino != null) { //Si los nodos existen
            //Engancho como primer ady y el resto de adys por delante(si hubieran)
            nodoOrigen.setPrimerAdy(new NodoAdy(nodoDestino, nodoOrigen.getPrimerAdy(), etiq));
            //Como se trata de un grafo se une el arco en ambos sentidos
            nodoDestino.setPrimerAdy(new NodoAdy(nodoOrigen, nodoDestino.getPrimerAdy(), etiq));
            exito = true;
        }
        return exito;
    }

    public boolean eliminarArco(Object origen, Object destino) {
        boolean exito = false;
        NodoVert nodoOrigen = ubicarVertice(origen);
        if (nodoOrigen != null) { //Si nodo origen existe
            /*Busca y elimina el arco vinculado a nodo destino y lo retorna(en caso de que exista) 
            para poder elimnar el arco en ambos sentidos, si no existe retorna null*/
            NodoVert nodoDestino = eliminarAdyacente(nodoOrigen, destino);
            if (nodoDestino != null) { //Si existe un arco con el nodo destino
                eliminarAdyacente(nodoDestino, origen); //elimina el arco en la otra direccion
                exito = true;
            }
        }
        return exito;
    }

    private NodoVert eliminarAdyacente(NodoVert vertice, Object adyacente) {
        /*Este metodo elimina el adyacente indicado y ademas retorna el vertice del adyacente eliminado 
        para poder eliminar el arco en ambos sentidos para el caso de grafos*/
        NodoAdy aux = vertice.getPrimerAdy();
        NodoVert nodoDestino = null;
        if (aux.getVertice().getElem().equals(adyacente)) { //Caso especial primer adyacemte
            nodoDestino = aux.getVertice(); //Guardo el vertice
            vertice.setPrimerAdy(aux.getSigAdyacente()); //Elimino el correspondiente vertice
        } else { //Caso para el resto de adyacentes
            while (!(aux.getSigAdyacente().getVertice().getElem().equals(adyacente)) && aux != null) {
                aux = aux.getSigAdyacente();
            }
            if (aux != null) { //Si no corto por nulo
                nodoDestino = aux.getSigAdyacente().getVertice(); //Guardo el vertice
                aux.setSigAdyacente(aux.getSigAdyacente().getSigAdyacente()); //Elimino el correspondiente adyacente
            }
        }
        return nodoDestino;
    }

    public boolean eliminarVertice(Object elem) {
        boolean exito = false;
        NodoVert nodoEliminar = null;
        if (!esVacio()) {
            if (inicio.getElem().equals(elem)) {
                nodoEliminar=inicio;
                inicio = inicio.getSigVertice();
            } else {
                NodoVert aux = inicio;
                while (aux.getSigVertice() != null && !(aux.getSigVertice().getElem().equals(elem))) {
                    //Mientras nodo no sea nulo y no encontremos el elemento
                    aux = aux.getSigVertice(); //Avanzamos siguiente nodo de la lista
                }
                nodoEliminar = aux.getSigVertice(); //Guardo el puntero al nodo a eliminar
                aux.setSigVertice(aux.getSigVertice().getSigVertice());//Enlazamos saltando el nodo a eliminar
            }
            if (nodoEliminar != null) { //Si existe tal nodo
                NodoAdy adyacente = nodoEliminar.getPrimerAdy();
                while (adyacente != null) {
                    /*Eliminamos el vertice(nodoEliminar) en todos los vertices
            de su lista de adyacentes*/
                    eliminarAdyacente(adyacente.getVertice(), elem);
                    adyacente = adyacente.getSigAdyacente();
                }
            }
            exito = true;
        }
        return exito;
    }

    public String toString() {
        String cadena = "";
        NodoVert vertice;
        NodoAdy adyacente;
        vertice = inicio;
        while (vertice != null) {
            cadena = cadena + "#" + vertice.getElem();
            adyacente = vertice.getPrimerAdy();
            while (adyacente != null) {
                cadena = cadena + "\n     ";
                cadena = cadena + "--";
                cadena = cadena + "-" + adyacente.getEtiqueta() + "->" + adyacente.getVertice().getElem();
                adyacente = adyacente.getSigAdyacente();
            }
            vertice = vertice.getSigVertice();
            cadena = cadena + "\n";
        }
        return cadena;
    }

    public Lista caminoMenosVertices(Object origen, Object destino) {
        //Este metodo devuelve el recorrido que menos vertices visita
        Lista caminoMenor = new Lista();
        NodoVert vertO = null;
        NodoVert vertD = null;
        NodoVert aux = inicio;
        while (aux != null && (vertO == null || vertD == null)) {
            if (aux.getElem().equals(origen)) {
                vertO = aux;
            }
            if (aux.getElem().equals(destino)) {
                vertD = aux;
            }
            aux = aux.getSigVertice();
        }

        if (vertO != null && vertD != null) { //Si ambos vertices existen en el grafo
            Lista visitados = new Lista(); //Lista para control de visitados
            caminoMenor = caminoMenosVerticesR(vertO, destino, visitados, caminoMenor);
        }
        return caminoMenor;
    }

    private Lista caminoMenosVerticesR(NodoVert vert, Object destino, Lista visitados, Lista caminoMenor) {
        if (vert != null) {
            visitados.insertar(vert.getElem(), visitados.longitud() + 1); //Marcamos nodo como visitado
            if (vert.getElem().equals(destino)) { //Se encontro un camino
                if (visitados.longitud() < caminoMenor.longitud() || caminoMenor.esVacia()) {
                    caminoMenor = visitados.clone();
                }
            } else {
                NodoAdy ady = vert.getPrimerAdy();
                while (ady != null) { //Recorremos todos los adyacentes
                    if (visitados.localizar(ady.getVertice().getElem()) < 0) { //Si el adyacente no ha sido visitado
                        if (visitados.longitud() + 1 < caminoMenor.longitud() || caminoMenor.esVacia()) {
                            /*Si la longitud del caminoMenor es mayor que el recorrido actual entonces
                            seguimos buscando, caso contrario cortamos la busqueda*/
                            caminoMenor = caminoMenosVerticesR(ady.getVertice(), destino, visitados, caminoMenor);
                        }
                    }
                    ady = ady.getSigAdyacente();
                }
            }
            visitados.eliminar(visitados.longitud()); //Se elimina oara continuar buscando otros caminos
        }
        return caminoMenor;
    }

    public Camino caminoMenorDistancia(Object origen, Object destino) {
        //Este metodo devuelve el recorrido que menos vertices visita
        Camino caminoMenor = new Camino();
        NodoVert vertO = null;
        NodoVert vertD = null;
        NodoVert aux = inicio;
        while (aux != null && (vertO == null || vertD == null)) {
            if (aux.getElem().equals(origen)) {
                vertO = aux;
            }
            if (aux.getElem().equals(destino)) {
                vertD = aux;
            }
            aux = aux.getSigVertice();
        }

        if (vertO != null && vertD != null) { //Si ambos vertices existen en el grafo
            Lista visitados = new Lista(); //Lista para control de visitados
            caminoMenor = caminoMenorDistanciaR(vertO, destino, visitados, 0, caminoMenor);
        }
        return caminoMenor;
    }

    private Camino caminoMenorDistanciaR(NodoVert vert, Object destino, Lista visitados, int distanciaRecorrida, Camino caminoMenor) {
        if (vert != null) {
            visitados.insertar(vert.getElem(), visitados.longitud() + 1); //Marcamos nodo como visitado
            if (vert.getElem().equals(destino)) { //Se encontro un camino
                if (distanciaRecorrida < caminoMenor.getDistancia() || caminoMenor.esVacio()) {
                    caminoMenor.setDistancia(distanciaRecorrida);
                    caminoMenor.setRecorrido(visitados.clone());
                }
            } else { //Si no es el nodo buscado
                NodoAdy ady = vert.getPrimerAdy();
                while (ady != null) {
                    if (visitados.localizar(ady.getVertice().getElem()) < 0) { //Si el adyacente no ha sido visitado
                        if ((distanciaRecorrida + ady.getEtiqueta()) < caminoMenor.getDistancia() || caminoMenor.esVacio()) {
                            caminoMenor = caminoMenorDistanciaR(ady.getVertice(), destino, visitados, distanciaRecorrida + (ady.getEtiqueta()), caminoMenor);
                        }
                    }
                    ady = ady.getSigAdyacente();
                }
            }
            visitados.eliminar(visitados.longitud());
        }
        return caminoMenor;
    }

    public Lista caminoSinVertice(Object A, Object B, Object C) {
        //Caminos entre A y B sin pasar por vertice C
        Lista caminos = new Lista();
        NodoVert vertA = null;
        NodoVert vertB = null;
        NodoVert vertC = null;
        NodoVert aux = inicio;
        while (aux != null && (vertA == null || vertB == null || vertC == null)) {
            if (aux.getElem().equals(A)) {
                vertA = aux;
            }
            if (aux.getElem().equals(B)) {
                vertB = aux;
            }
            if (aux.getElem().equals(C)) {
                vertC = aux;
            }
            aux = aux.getSigVertice();
        }
        if (vertA != null && vertB != null && vertC != null) {
            Lista visitados = new Lista();
            caminoSinVerticeR(vertA, B, C, visitados, caminos);
        }
        return caminos;
    }

    private void caminoSinVerticeR(NodoVert vertA, Object B, Object C, Lista visitados, Lista caminos) {
        if (vertA != null) {
            visitados.insertar(vertA.getElem(), visitados.longitud() + 1); //Marcamos nodo como visitado
            if (vertA.getElem().equals(B)) { //Se encuentra un camino
                Lista caminoNuevo = visitados.clone();
                caminos.insertar(caminoNuevo, caminos.longitud() + 1);
            } else {
                NodoAdy ady = vertA.getPrimerAdy();
                while (ady != null) {
                    if (visitados.localizar(ady.getVertice().getElem()) < 0 && !ady.getVertice().getElem().equals(C)) {
                        //Si el adyacente no ha sido visitado y tampoco se trata del vertice C
                        caminoSinVerticeR(ady.getVertice(), B, C, visitados, caminos);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
            visitados.eliminar(visitados.longitud());
        }
    }
    
    public Camino caminMaxDistancia(Object origen, Object destino, int distanciaMax){
        // Verifica si es posible llegar de A a B recorriendo como máximo una cantidad X de kilómetros
        Camino camino = null;
        NodoVert vertO = null;
        NodoVert vertD = null;
        NodoVert aux = inicio;
        while (aux != null && (vertO == null || vertD == null)) {
            if (aux.getElem().equals(origen)) {
                vertO = aux;
            }
            if (aux.getElem().equals(destino)) {
                vertD = aux;
            }
            aux = aux.getSigVertice();
        }

        if (vertO != null && vertD != null) {
            Lista visitados = new Lista();
            camino=caminoMaxDistanciaR(vertO, destino, visitados, distanciaMax, 0, camino);
        }
        return camino;
    }
    
    private Camino caminoMaxDistanciaR(NodoVert vert, Object destino, Lista visitados, int distanciaMax, int distanciaRecorrida, Camino caminoMaxDistancia){
        //Metodo recursivo 
        if(vert!=null){
            visitados.insertar(vert.getElem(), visitados.longitud()+1);
            if(vert.getElem().equals(destino)){
                caminoMaxDistancia = new Camino();
                caminoMaxDistancia.setRecorrido(visitados.clone());
                caminoMaxDistancia.setDistancia(distanciaRecorrida);
            }else{
                NodoAdy ady = vert.getPrimerAdy();
                while(ady!=null && caminoMaxDistancia==null){
                    if(visitados.localizar(ady.getVertice().getElem())<0  && distanciaRecorrida+ady.getEtiqueta()<=distanciaMax){
                        //Si no ha sido visitado AND no se hallo camino AND distanciaRecorrida sigue siendo menor que distanciaMax
                        caminoMaxDistancia = caminoMaxDistanciaR(ady.getVertice(), destino, visitados, distanciaMax, distanciaRecorrida+ady.getEtiqueta(), caminoMaxDistancia);
                    }
                    ady = ady.getSigAdyacente();
                }
            }
            visitados.eliminar(visitados.longitud());
        }
        return caminoMaxDistancia;
    }
    
    //***METODOS PRACTICA PARCIAL***
    private Lista caminoPesoMenor(NodoVert vert, Object destino, int pesoActual, int pesoMax, Lista visitados, Lista caminoMenor){
        if(vert!=null){
            visitados.insertar(vert.getElem(), visitados.longitud()+1);
            if(vert.getElem().equals(destino) && pesoActual<=pesoMax){
                caminoMenor=visitados.clone();
            }else{
                NodoAdy ady = vert.getPrimerAdy();
                while(ady!=null && caminoMenor.esVacia()){
                    if(visitados.localizar(ady.getVertice().getElem())<0){
                        if(pesoActual+ady.getEtiqueta()<=pesoMax){
                            caminoMenor=caminoPesoMenor(ady.getVertice(),destino,pesoActual+ady.getEtiqueta(),pesoMax, visitados, caminoMenor);
                        }
                    }
                    ady=ady.getSigAdyacente();
                }
            }
            visitados.eliminar(visitados.longitud());
        }
        return caminoMenor;
    }
    
    public Lista caminoDePesoMenor(Object origen, Object destino, int pesoMax){
        NodoVert auxO=null;
        NodoVert auxD=null;
        NodoVert aux=inicio;
        Lista camino=new Lista();
        while((auxO==null || auxD==null) && aux!=null){
            if(aux.getElem().equals(origen)){
                auxO=aux;
            }
            if(aux.getElem().equals(destino)){
                auxD=aux;
            }
            aux=aux.getSigVertice();
        }
        if(auxO!=null && auxD!=null){
            Lista visitados=new Lista();
            camino=caminoPesoMenor(auxO, destino, 0, pesoMax, visitados, camino);
        }
        return camino;
    }
}
