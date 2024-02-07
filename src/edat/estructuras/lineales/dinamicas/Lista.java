/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edat.estructuras.lineales.dinamicas;

/**
 *
 * @author Leo
 */
public class Lista {
    //Atributos

    private Nodo cabecera;
    private int longitud;

    public Lista() {
        cabecera = null;
        longitud = 0;
    }

    public boolean insertar(Object elem, int posicion) {
        boolean exito = false;
        
        if (posicion >= 1 && posicion <= longitud + 1) {
            Nodo nuevoN = new Nodo(elem, null);
            if (posicion == 1) {
                nuevoN.setEnlace(cabecera);
                cabecera = nuevoN;
            }else{
                Nodo aux=cabecera; //El nodo auxiliar apunta a la cabera, de donde empezara a recorrer
                /*Con el for hacemos que aux se mueva hasta el nodo anterior a la
                posicion donde debemos agregar el nuevo nodo*/
                for(int j=1; j<posicion-1; j++){
                    aux=aux.getEnlace();
                }
                nuevoN.setEnlace(aux.getEnlace()); //Enlazamos el nuevo Nodo por delante
                aux.setEnlace(nuevoN); //Enlazamos el nuevo Nodo por detras
            }
            exito=true;
            longitud++;
        }
        return exito;
    }
    
    public boolean eliminar(int posicion){
         boolean exito = false;
        
        if (posicion >= 1 && posicion <= longitud) {
            if(posicion==1){ //Caso especial posicion 1
                cabecera=cabecera.getEnlace();
            }else{
                Nodo aux=cabecera;
                for(int j=1; j<posicion-1; j++){ //El bucle se detiene en el nodo pos-1
                    aux=aux.getEnlace(); //Avanzamos al sig nodo
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
            longitud=longitud-1;
            exito=true;
        }
        return exito;
    }

    public boolean esVacia() {
        return cabecera == null;
    }
    
    public String toString(){
        String cadena;
        if(!esVacia()){
            Nodo aux;
            aux=cabecera;
            cadena="[";
            while(aux!=null){
                cadena=cadena + aux.getElem() + " ";
                aux=aux.getEnlace();
            }
            cadena=cadena+"]";
        }
        else{
            cadena="La lista esta vacia";
        }
        return cadena;
    }
    
     public Object recuperar(int pos){
        Object obj;
        if(pos>0 & pos<=longitud){
            int ite;
            Nodo aux;
            aux=cabecera;
            for(ite=1; ite<pos; ite++){
                aux=aux.getEnlace();
            }
            obj=aux.getElem();
        }
        else{
            obj=null;
        }
        return obj;
    }
     
     public int localizar(Object elemento){
        int pos=-1;
        int ite=1;
        Nodo aux;
        aux=cabecera;
        while(ite<=longitud && (!aux.getElem().equals(elemento))){
            aux=aux.getEnlace();
            ite++;
        }
        if(ite<=longitud && aux.getElem().equals(elemento)){
            pos=ite;
        }
        return pos;
    }
     
     public int longitud(){
        return longitud;
    }
     
    public void vaciar(){
        cabecera=null;
        longitud=0;
    }
    
    public Lista clone() {
        Lista clonada = new Lista();
        if (!esVacia()) {
            Nodo aux, auxClon;
            /*A la cabecera de lista a clonar le asigno el elemento de la cabecera 
            de la lista original y null como enlace*/
            clonada.cabecera = new Nodo(cabecera.getElem(), null);
            aux = cabecera; //Aux apunta a la cabecera, se movera sobre la lista original
            auxClon = clonada.cabecera; //auxClon apunta a la cabecera de la clonada, se movera sobre la misma lista 
            aux = aux.getEnlace();//aux avanza una pos sobre la lista
            while (aux != null) {
                //Al enlace del nodo sobre el que estamos le asignamos el elemento correspendiente y su enlace null
                auxClon.setEnlace(new Nodo(aux.getElem(), null));
                aux = aux.getEnlace();
                auxClon = auxClon.getEnlace();
            }
            clonada.longitud = longitud;
        }
        return clonada;
    }
    
    
}
