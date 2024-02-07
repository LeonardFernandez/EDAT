/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edat.estructuras.conjuntistas;

/**
 *
 * @author Leo
 */
public class Diccionario {

    //TDA Diccionario implementado con arbolAVL
    private NodoAVLDic raiz;
    
    public Diccionario() {
        raiz = null;
    }
    
    private NodoAVLDic rotarIzq(NodoAVLDic pivote) {
        NodoAVLDic h = pivote.getDerecho();
        NodoAVLDic temp = null;
        if (h.getIzquierdo() != null) {
            temp = h.getIzquierdo();
        }
        h.setIzquierdo(pivote); //h pasa a ser padre de pivote(su padre)
        pivote.setDerecho(temp); //temp pasa a ser HD de pivote
        //Recalculamos las alturas
        pivote.recalcularAltura();
        h.recalcularAltura();
        //Retornamos el nuevo padre(h) de este subarbol
        return h;
    }
    
    private NodoAVLDic rotarDer(NodoAVLDic pivote) {
        NodoAVLDic h = pivote.getIzquierdo();
        NodoAVLDic temp = null;
        if (h.getDerecho() != null) {
            temp = h.getDerecho();
        }
        h.setDerecho(pivote);
        pivote.setIzquierdo(temp);
        //Recalculamos las alturas
        pivote.recalcularAltura();
        h.recalcularAltura();
        //Retornamos el nuevo padre(h) de este subarbol
        return h;
    }
    
    private NodoAVLDic rotarIzDer(NodoAVLDic padre) {
        //Rotamos hacia la izq el hijoIzq
        NodoAVLDic hijoIzq = rotarIzq(padre.getIzquierdo());
        //Enganchamos el nuevo hijoIzq a su padre
        padre.setIzquierdo(hijoIzq);
        //Rotamos todo el arbol hacia la derecha y retornamos este nuevo padre
        NodoAVLDic nuevoPadre = rotarDer(padre);
        //Recalculamos alturas
        /*hijoIzq.recalcularAltura();
        padre.recalcularAltura();*/
        return nuevoPadre;
    }
    
    private NodoAVLDic rotarDerIz(NodoAVLDic padre) {
        //Rotamos hacia la der el hijo derecho
        NodoAVLDic hijoDer = rotarDer(padre.getDerecho());
        //Engachamos este nuevo hijo a derecho a su padre
        padre.setDerecho(hijoDer);
        //Rotamos todo el arbol hacia la izquierda y retornamos este nuevo padre
        NodoAVLDic nuevoPadre = rotarIzq(padre);
        return nuevoPadre;
    }
    
    private int balance(NodoAVLDic nodo) {
        //Devuelve el balance de un nodo
        int balance = -1;
        if (nodo != null) {
            //Si los hijos son nulos los valores seran -1
            int altIzq = -1;
            int altDer = -1;
            if (nodo.getIzquierdo() != null) {
                altIzq = nodo.getIzquierdo().getAltura();
            }
            if (nodo.getDerecho() != null) {
                altDer = nodo.getDerecho().getAltura();
            }
            balance = altIzq - altDer;
        }
        return balance;
    }
    
    private NodoAVLDic rotaciones(NodoAVLDic padre, int balancePadre) {
        int balanceDer = balance(padre.getDerecho());
        int balanceIzq = balance(padre.getIzquierdo());
        NodoAVLDic nuevoPadre;
        //Si el padre esta desbalanceado hacia la izquierda
        if (balancePadre >= 2) {
            if (balanceIzq <= -1) { //Si el hijoIzq esta desbalanceado hacia la derecha
                //Hacemos rotacion doble izquierda-derecha
                nuevoPadre = rotarIzDer(padre);
            } else {
                // Si no, rotacion simple a derecha
                nuevoPadre = rotarDer(padre);
            }
        } else { //El padre esta desbalanceado hacia la derecha
            if (balanceDer >= 1) { //Si el hijo derecho esta desbalanceado hacia la izquierda
                //Hacemos rotacion doble derecha-izquierda
                nuevoPadre = rotarDerIz(padre);
            } else { // Si no, hacemos rotacion simple
                nuevoPadre = rotarIzq(padre);
            }
        }
        //Debemos recalcular altura?
        //nuevoPadre.recalcularAltura();
        return nuevoPadre;
    }
    
    private void balancear(NodoAVLDic padre, NodoAVLDic hijo) {
        //Vamos a balancear el hijo
        int balance = balance(hijo);
        //Si el hijo esta desbalanceado
        if (balance < -1 || balance > 1) {
            //Aplicamos las rotaciones y devuelve la nueva raiz del subarbol
            NodoAVLDic hijoBalanceado = rotaciones(hijo, balance);
            /*Si el padre es nulo, significa que estamos balanceando la raiz 
            del arbol y por lo tanto no es necesario setearle el padre*/
            if (padre != null) {
                //Si padre es mayor que el hijo, la nueva raiz se engancha al enlace izquierdo del padre
                if (padre.getClave().compareTo(hijoBalanceado.getClave()) > 0) {
                    padre.setIzquierdo(hijoBalanceado);
                } else {
                    padre.setDerecho(hijoBalanceado);
                }
                padre.recalcularAltura();
            } else { //Si el padre es nulo se trata de la raiz
                raiz = hijoBalanceado;
                //raiz.recalcularAltura(); //NO NECESARIO
            }
        }
    }
    
    public boolean insertar(Comparable clave, Object dato) {
        boolean exito = true;
        if (raiz != null) {
            exito = insertarR(null, raiz, clave, dato);
        } else {
            raiz = new NodoAVLDic(clave, dato, null, null);
        }
        return exito;
    }
    
    private boolean insertarR(NodoAVLDic padre, NodoAVLDic nodo, Comparable clave, Object dato) {
        boolean exito = true;
        //NodoAVL nuevoHijo = new NodoAVL(elem, null, null);
        //Si existe dentro del arbol, no se inserta
        if (nodo.getClave().equals(clave)) {
            exito = false;
        } else { //Si es distinto vamos a revisar los hijos

            //Si la clave es menor 
            if ((nodo.getClave()).compareTo(clave) > 0) {
                if (nodo.getIzquierdo() != null) { //Si no es nulo vamos a seguir por el izq
                    exito = insertarR(nodo, nodo.getIzquierdo(), clave, dato);
                } else { //Si es nulo entonces lo insertamos en esta posicion
                    NodoAVLDic nuevoHijo = new NodoAVLDic(clave, dato, null, null);
                    nodo.setIzquierdo(nuevoHijo);
                    exito = true;
                }
            } else { //Si la clave es mayor 
                if (nodo.getDerecho() != null) { //Si no es nulo seguimos por la derecha
                    exito = insertarR(nodo, nodo.getDerecho(), clave, dato);
                } else { //Si es nulo entonces insertamos como HD del nodo
                    NodoAVLDic nuevoHijo = new NodoAVLDic(clave, dato, null, null);
                    nodo.setDerecho(nuevoHijo);
                    exito = true;
                }
            }
        }
        if (exito) {
            nodo.recalcularAltura();
            balancear(padre, nodo);
        }
        return exito;
    }
    
    public boolean eliminar(Comparable clave) {
        boolean exito = false;
        if (!esVacio()) {
            exito = eliminarR(null, raiz, clave);
        }
        return exito;
    }
    
    private boolean eliminarR(NodoAVLDic padre, NodoAVLDic nodo, Comparable clave) {
        boolean exito = false;
        if (nodo.getClave().equals(clave)) { //Encontramos el nodo
            NodoAVLDic reemplazo = null; //Caso 1(sin hijos) reemplazo es nulo
            //Caso 2 tiene un hijo
            if (nodo.getIzquierdo() != null && nodo.getDerecho() == null) {
                reemplazo = nodo.getIzquierdo();
            }
            if (nodo.getIzquierdo() == null && nodo.getDerecho() != null) {
                reemplazo = nodo.getDerecho();
            }
            //Caso 3 tiene dos hijos
            if (nodo.getIzquierdo() != null && nodo.getDerecho() != null) {
                reemplazo = predecesor(nodo, nodo.getIzquierdo());
                reemplazo.setIzquierdo(nodo.getIzquierdo());
                reemplazo.setDerecho(nodo.getDerecho());
            }
            if (reemplazo != null) {
                reemplazo.recalcularAltura();
                nodo = reemplazo;
            }
            //Pongo el reemplazo del nodo eliminado
            if (padre == null) { //Caso especial nodo es raiz
                raiz = reemplazo;
            } else {
                if (padre.getClave().compareTo(clave) > 0) {
                    padre.setIzquierdo(reemplazo);
                } else {
                    padre.setDerecho(reemplazo);
                }
            }
            exito = true;
        } else { //Si no es el nodo buscado, seguimos buscando en los hijos
            if (nodo.getClave().compareTo(clave) > 0 && nodo.getIzquierdo() != null) {
                exito = eliminarR(nodo, nodo.getIzquierdo(), clave);
            } else if (nodo.getClave().compareTo(clave) < 0 && nodo.getDerecho() != null) {
                exito = eliminarR(nodo, nodo.getDerecho(), clave);
            }
        }
        if (exito) {
            //Si eliminacion exitosa, entonces recalculamos altura y balanceamos(si es necesario)
            nodo.recalcularAltura();
            balancear(padre, nodo);
        }
        return exito;
    }
    
    private NodoAVLDic predecesorñ(NodoAVLDic padre, NodoAVLDic nodo) {
        //Este metodo devuelve el predecesor de nodo padre
        NodoAVLDic predecesor = null;
        if (nodo.getDerecho() == null) {
            predecesor = nodo;
            if (nodo.getIzquierdo() != null) {
                /*En caso de que el nodo predecesor tenga hijoIzq debe 
                engancharse al padre para no perderlo*/
                if (padre.getIzquierdo().getClave() != nodo.getClave()) {
                    padre.setDerecho(nodo.getIzquierdo());
                } else {
                    padre.setDerecho(nodo.getIzquierdo());
                }
            } else {
                if (padre.getIzquierdo().getClave() != nodo.getClave()) {
                    padre.setDerecho(null);
                }
            }
        } else {
            predecesor(nodo, nodo.getDerecho());
        }
        padre.recalcularAltura();
        return predecesor;
    }
    
    private NodoAVLDic predecesor(NodoAVLDic padre, NodoAVLDic nodo) {
        //Este metodo devuelve el predecesor de nodo padre
        NodoAVLDic predecesor = null;
        if(nodo.getDerecho()==null){
            predecesor=nodo;
            if(padre.getIzquierdo()!=null && padre.getIzquierdo().getClave()==nodo.getClave()){
                padre.setIzquierdo(nodo.getIzquierdo());
            }else{
                padre.setDerecho(nodo.getIzquierdo());
            }
            nodo.setIzquierdo(null); //Limpio el predecesor
        }else{
            predecesor=predecesor(nodo,nodo.getDerecho());
        }
        if(predecesor!=null){
            nodo.recalcularAltura();
            balancear(padre,nodo);
        }
        return predecesor;
    }
    
    public boolean esVacio() {
        return raiz == null;
    }
    
    public String toString() {
        String cadena = "";
        if (esVacio()) {
            cadena = "El arbol esta vacio";
        } else {
            cadena = toString(raiz);
        }
        return cadena;
    }
    
    private String toString(NodoAVLDic nodo) {
        String cadena = "";
        if (nodo != null) {
            cadena = nodo.getClave() + "  ";
            cadena = cadena + "HI:";
            if (nodo.getIzquierdo() != null) {
                cadena = cadena + nodo.getIzquierdo().getClave() + "  ";
            }
            cadena = cadena + "HD:";
            if (nodo.getDerecho() != null) {
                cadena = cadena + nodo.getDerecho().getClave();
            }
            cadena = cadena + "     Altura: " + nodo.getAltura();
            cadena = cadena + "\n";
            cadena = cadena + toString(nodo.getIzquierdo());
            cadena = cadena + toString(nodo.getDerecho());
        }
        return cadena;
    }
    
    public Object obtenerDato(Comparable clave) {
        Object retornar = null;
        if (!esVacio()) {
            NodoAVLDic aux = buscarNodo(raiz, clave);
            if (aux != null) {
                retornar = aux.getDato();
            }
        }
        return retornar;
    }
    
    private NodoAVLDic buscarNodo(NodoAVLDic nodo, Comparable clave) {
        NodoAVLDic nodoEncontrado = null;
        if (nodo != null) {
            if (nodo.getClave().equals(clave)) { //Caso base
                nodoEncontrado = nodo;
            } else {
                if (nodo.getClave().compareTo(clave) < 0) {
                    nodoEncontrado = buscarNodo(nodo.getDerecho(), clave);
                } else {
                    nodoEncontrado = buscarNodo(nodo.getIzquierdo(), clave);
                }
            }
        }
        return nodoEncontrado;
    }
}
