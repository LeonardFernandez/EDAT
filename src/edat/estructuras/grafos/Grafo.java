/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edat.estructuras.grafos;

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
            exito=true;
        }
        return exito;
    }

    public String toString() {
        String cadena = "";
        NodoVert vertice;
        NodoAdy adyacente;
        vertice = inicio;
        while (vertice != null) {
            cadena = cadena + "#" +vertice.getElem();
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

}
