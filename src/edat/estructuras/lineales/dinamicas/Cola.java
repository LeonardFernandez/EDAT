/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edat.estructuras.lineales.dinamicas;

/**
 *
 * @author Leo
 */
public class Cola {
     //Atributos
    private Nodo frente;
    private Nodo fin;
    
    public Cola(){
        frente=null;
        fin=null;
    }
    
    public boolean poner(Object nuevo){
        Nodo nuevoN=new Nodo(nuevo,null);
        if(esVacia()){
            frente=nuevoN;
            fin=nuevoN;
        }
        else{
            //Al ultimo nodo lo hacemos apuntar al nuevo nodo que estamos poniendo
            fin.setEnlace(nuevoN);
            //Ahora fin apunta al nuevo nodo
            fin=nuevoN;
        }
        return true;
    }
    
    public boolean sacar(){
        boolean exito;
        exito=false;
        //Si al menos hay un elemento
        if(frente!=null){
            /*Al nodo frente le asignamos su enlace(siguiente nodo), luego el nodo 
            que estaba previamente en el frente no es apuntado y se lo lleva el recolector*/
            frente=frente.getEnlace();
            //En caso del que el frente quede en null, a fin le asignamos null tambien
            if(frente==null){
                fin=null;
            }
            exito=true;
        }
        return exito;
    }
    
    public boolean esVacia(){
        return frente==null;
    }
    
    public Object obtenerFrente(){
        Object obj;
        if(!esVacia()){
            obj=frente.getElem();
        }
        else{
            obj=null;
        }
        return obj;
    }
    
    public void vaciar(){
        fin=null;
        frente=null;
    }
    
    public Cola clone(){
        Cola clonada= new Cola();
        if(!esVacia()){
            Nodo aux1, aux2;
            //aux1 apunta al frente de la cola a clonar
            aux1=frente;
            //añadimos el nodo con el respectivo elemento a la cola clonada
            clonada.frente= new Nodo(aux1.getElem(), null);
            aux1=aux1.getEnlace();
            aux2=clonada.frente;
            while(aux1!=null){
                //añadimos el nodo que sigue a la cola clonada
                aux2.setEnlace( new Nodo(aux1.getElem(), null ) );
                aux1=aux1.getEnlace();//Avanzamos al siguiento nodo de la cola original
                aux2=aux2.getEnlace();//Avanzamos al siguiento nodo de la cola clonada
            }
        }
        return clonada;
    }
    
    public String toString(){
        String cadena;
        Nodo aux;
        cadena="";
        if(!esVacia()){
            aux=frente;
            cadena="[";
            while(aux!=null){
                cadena=cadena + aux.getElem() + " ";
                aux=aux.getEnlace();
            }
            cadena=cadena+"]";
        }else{
            cadena="La cola esta vacia";
        }
        return cadena;
    }
}
