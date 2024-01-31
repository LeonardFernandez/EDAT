/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edat.estructuras.lineales.dinamicas;

/**
 *
 * @author Leo
 */
public class Pila {
   //Atributos
    private Nodo tope;
    
    //Constructor
    public Pila(){
        tope=null;
    }
    
    public boolean apilar(Object nuevoElem){
        //Crea un nuevo nodo pasandole el elemento a apilar mas el tope, que es el enlace a otro nodo
        Nodo nuevoN=new Nodo(nuevoElem, tope);
        //Actualizamos el tope con el nuevo nodo agregado
        tope=nuevoN;
        //Retornamos true, la pila nunca se llena
        return true;
    }
    
    public boolean desapilar(){
        /**/
        boolean exito;
        exito=false;
        if(tope!=null){
            tope=tope.getEnlace();
            exito=true;
        }
        return exito;
    }
    
    public Object obtenerTope(){
        Object obj;
        if(esVacia()){
            obj=null;
        }
        else{
            obj=tope.getElem();
        }
        return obj;
    }
    
    public boolean esVacia(){
        return tope==null;
    }
    
    public void vaciar(){
        /*Es suficiente poner null al tope ya que los nodos no estaran apuntados 
        por ningun puntero y se los lleva el recolector*/
       tope=null;
    }
    
    public Pila clone(){
        //Este metodo devuelve una pila clonada
        Pila clonada= new Pila(); //Creamos la pila a retornar
        Nodo auxThis; //auxThis se movera sobre la pila original
        Nodo auxClon; //auxClon se movera sobre la pila clonada
        Nodo nClonada; //nClonada es el nodo que iremos agregando a la pila a clonar
        if(!esVacia()){
            auxThis=this.tope;
            //le pasamos el elem del tope y le asignamos null a su enlace para posteriormente enlazarlo
            nClonada= new Nodo(auxThis.getElem(), null);
            clonada.tope=nClonada;
            auxClon=clonada.tope;
            auxThis=auxThis.getEnlace();
            while(auxThis!=null){
                //asignamos el elemento de la pila a clonar, con su enlace nulo para enlazarlo luego
                nClonada=new Nodo(auxThis.getElem(),null);
                auxClon.setEnlace(nClonada); //Agregamos en nuevo nodo
                auxClon=auxClon.getEnlace(); //avanzamos
                auxThis=auxThis.getEnlace(); //avanzamos en la pila
            }
        }
        return clonada;
    }
    
    public String toString(){
        String cadena="";
        Nodo aux;
        
        if(esVacia()){
            cadena="La pila esta vacia";
        }
        else{
            aux=tope;
//            cadena="[";
            while(aux!=null){
                cadena= aux.getElem()+ cadena ;
                //A aux le asignamos el siguiente elemento
                aux=aux.getEnlace();
                if(aux!=null){
                    cadena= ", " + cadena;
                }
            }
            cadena=cadena + "]";
            cadena="[" + cadena;
        }
        return cadena;
    }
    
}
