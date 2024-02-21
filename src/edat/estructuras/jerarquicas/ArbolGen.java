/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edat.estructuras.jerarquicas;

import edat.estructuras.lineales.dinamicas.Cola;
import edat.estructuras.lineales.dinamicas.Lista;

/**
 *
 * @author Leo
 */
public class ArbolGen {

    //Atributos
    NodoGen raiz;

    public ArbolGen() {
        raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre) {
        boolean exito = false;
        if (esVacio()) {
            raiz = new NodoGen(elemNuevo, null, null);
            exito = true;
        } else {
            NodoGen padre = obtenerNodo(raiz, elemPadre);
            if (padre != null) {
                //Al nodo nuevo le enlazo como HD el HEI de nodo padre
                NodoGen nuevo = new NodoGen(elemNuevo, null, padre.getHijoIzquierdo());
                //Luego como HEI del padre le asigno el nodo nuevo
                padre.setHijoIzquierdo(nuevo);
                exito = true;
            }
        }
        return exito;
    }

    private NodoGen obtenerNodo(NodoGen nodo, Object elemBuscado) {
        NodoGen resultado = null;
        if (nodo != null) {
            if (nodo.getElem().equals(elemBuscado)) {
                resultado = nodo;
            } else {
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null && resultado == null) {
                    resultado = obtenerNodo(hijo, elemBuscado);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return resultado;
    }

    public boolean pertenece(Object elemBuscado) {
        boolean pertenece = false;
        if (!esVacio()) {
            pertenece = perteneceR(raiz, elemBuscado);
        }
        return pertenece;
    }

    private boolean perteneceR(NodoGen nodo, Object elemBuscado) {
        boolean resultado = false;
        if (nodo != null) {
            if (nodo.getElem().equals(elemBuscado)) {
                resultado = true;
            } else {
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null && !resultado) {
                    resultado = perteneceR(hijo, elemBuscado);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return resultado;
    }

    public Object padre(Object elemBuscado) {
        Object padre = null;
        if (!esVacio()) {
            if (!raiz.getElem().equals(elemBuscado)) {
                padre = padreR(raiz, elemBuscado);
            }
        }
        return padre;
    }

    private Object padreR(NodoGen nodo, Object elemBuscado) {
        boolean encontrado = false;
        Object resultado = null;
        if (nodo != null) {
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null && !encontrado) {
                encontrado = hijo.getElem().equals(elemBuscado);
                if (encontrado) {
                    resultado = nodo.getElem();
                }
                hijo = hijo.getHermanoDerecho();
            }
            hijo = nodo.getHijoIzquierdo();
            while (!encontrado && hijo != null) {
                resultado = padreR(hijo, elemBuscado);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return resultado;
    }

    public Lista ancestros(Object obj) {
        Lista lista = new Lista();
        if (!this.esVacio()) {
            if (!raiz.getElem().equals(obj)) {
                ancestroAux(obj, raiz, lista);
                lista.eliminar(1);
            }
        }
        return lista;
    }

    private void ancestroAux(Object obj, NodoGen nodo, Lista lista) {
        boolean extHijoIzq;
        extHijoIzq = nodo.getHijoIzquierdo() != null;
        if (extHijoIzq) {
            ancestroAux(obj, nodo.getHijoIzquierdo(), lista);
            if (lista.esVacia()) {
                NodoGen temp = nodo.getHijoIzquierdo().getHermanoDerecho(); //Selecciona el hermano derecho del nodo izquierdo
                while (temp != null) {
                    ancestroAux(obj, temp, lista);
                    if (lista.esVacia()) {
                        temp = temp.getHermanoDerecho();
                    } else {
                        temp = null;
                    }
                }
            }
        }
        if (!lista.esVacia() || nodo.getElem().equals(obj)) {
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
        }
    }

    public int altura() {
        int altura = -1;
        if (raiz != null) {
            altura = alturaR(raiz);
        }
        return altura;
    }

    private int alturaR(NodoGen nodo) {
        int altH, alturaMax, altura;
        alturaMax = -1;
        altura = 0;
        if (nodo.getHijoIzquierdo() != null) {
            NodoGen aux = nodo.getHijoIzquierdo();
            while (aux != null) {
                altH = alturaR(aux) + 1;
                if (alturaMax < altH) {
                    alturaMax = altH;
                }
                aux = aux.getHermanoDerecho();
            }
        }
        if (alturaMax != -1) {
            altura = alturaMax;
        }
        return altura;
    }

    public int nivel(Object elemBuscado) {
        int nivel = -1;
        if (!esVacio()) {
            nivel = nivelR(elemBuscado, raiz);
        }
        return nivel;
    }

    private int nivelR(Object elemBuscado, NodoGen nodo) {
        //Seteamos nivel en -1 para el caso donde no se encuentre
        int nivel = -1;
        if (nodo != null) {
            //Si el elemento buscado esta en el nodo, entonces nivel es 0
            if (nodo.getElem().equals(elemBuscado)) {
                nivel = 0;
            } else {
                if (nodo.getHijoIzquierdo() != null) {
                    NodoGen aux = nodo.getHijoIzquierdo();
//                    nivel = nivelR(elemBuscado, aux);
                    while (aux != null && nivel == -1) {
                        nivel = nivelR(elemBuscado, aux);
                        aux = aux.getHermanoDerecho();
                    }
                    //En caso de que nivel haya sido modificado en cada vuelta recursiva ira sumando
                    if (nivel != -1) {
                        nivel = nivel + 1;
                    }
                }
            }
        }
        return nivel;
    }

    public boolean esVacio() {
        return raiz == null;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public Lista listarPreorden() {
        Lista lista = new Lista();
        listarPreordenR(raiz, lista);
        return lista;
    }

    private void listarPreordenR(NodoGen nodo, Lista lis) {
        if (nodo != null) {
            lis.insertar(nodo.getElem(), lis.longitud() + 1);
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                listarPreordenR(hijo, lis);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    public Lista listarInorden() {
        Lista lista = new Lista();
        if (!this.esVacio()) {
            inordenAux(lista, this.raiz);
        }
        return lista;

    }

    private void inordenAux(Lista lista, NodoGen nodo) {
        if (nodo != null) {
            inordenAux(lista, nodo.getHijoIzquierdo());
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            if (nodo.getHijoIzquierdo() != null) {
                NodoGen temp = nodo.getHijoIzquierdo().getHermanoDerecho(); //Selecciona el hermano derecho del nodo izquierdo
                while (temp != null) {
                    inordenAux(lista, temp);
                    temp = temp.getHermanoDerecho();
                }
            }
        }
    }

    public Lista listarPosorden() {
        Lista lista = new Lista();
        if (!this.esVacio()) {
            posordenAux(lista, this.raiz);
        }
        return lista;
    }

    private void posordenAux(Lista lista, NodoGen nodo) {
        if (nodo != null) {
            posordenAux(lista, nodo.getHijoIzquierdo());
            if (nodo.getHijoIzquierdo() != null) {
                NodoGen temp = nodo.getHijoIzquierdo().getHermanoDerecho(); //Selecciona el hermano derecho del nodo izquierdo
                while (temp != null) {
                    posordenAux(lista, temp);
                    temp = temp.getHermanoDerecho();
                }
            }
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
        }
    }

    //Listar por niveles
    public Lista listarPorNiveles() {
        Lista retorno = new Lista();
        Cola q = new Cola();
        q.poner(this.raiz);

        while (!q.esVacia()) {
            NodoGen aux = (NodoGen) q.obtenerFrente();
            q.sacar();
            retorno.insertar(aux.getElem(), retorno.longitud() + 1);
            aux = aux.getHijoIzquierdo();
            while (aux != null) {
                q.poner(aux);
                aux = aux.getHermanoDerecho();
            }
        }
        return retorno;
    }

    public String toString() {
        String cadena = "";
        if (esVacio()) {
            cadena = "El arbol esta vacio";
        } else {
            cadena = toStringR(raiz);
        }
        return cadena;
    }

    public ArbolGen clone() {
        ArbolGen clon = new ArbolGen();
        //verificamos si existe la raiz del arbol
        if (raiz != null) {
            //clonenamos la raiz del alrbol
            clon.raiz = new NodoGen(this.raiz.getElem(), null, null);
            //relizamos el llamado recursivo
            cloneR(this.raiz, clon.raiz);
        }
        return clon;
    }

    private void cloneR(NodoGen raiz, NodoGen raizClone) {
        if (raiz != null) {
            //Aux nos ayudara a recorrer el arbol a clonar
            NodoGen aux = raiz.getHijoIzquierdo();

            if (aux != null) {
                /*Creamos el nodo hijo izquierdo con sus enlaces en null para
                posteriormente ir enganchando a los siguientes nodos*/
                raizClone.setHijoIzquierdo(new NodoGen(aux.getElem(), null, null));
                //Nos pocicionamos sobre HEI del nodoClon
                NodoGen auxClone = raizClone.getHijoIzquierdo();
                while (aux.getHermanoDerecho() != null) {
                    //Clonamos los nodos hijos
                    //nos movemos al siguiente hijo
                    aux = aux.getHermanoDerecho();
                    auxClone.setHermanoDerecho(new NodoGen(aux.getElem(), null, null));
                    auxClone = auxClone.getHermanoDerecho();
                }

                //Paso recursivo
                aux = raiz.getHijoIzquierdo();
                auxClone = raizClone.getHijoIzquierdo();
                while (aux != null) {
                    cloneR(aux, auxClone);
                    aux = aux.getHermanoDerecho();
                    auxClone = auxClone.getHermanoDerecho();
                }
            }
        }
    }

    private String toStringR(NodoGen nodo) {
        String cadena = "";
        if (nodo != null) {
            cadena += nodo.getElem().toString() + " -> ";
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                //Avanzamos sobre los hijos y los vamos añadiendo a la cadena
                cadena += hijo.getElem().toString() + " ";
                hijo = hijo.getHermanoDerecho();
            }
            hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                cadena += "\n" + toStringR(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return cadena;
    }

    //Metodos adicionales
//    public boolean sonFrontera(Lista lista){
//        int[] cantHojasAG=new int[1] ;
//        int elemFrontera = lista.longitud();
//        boolean sonFrontera;
//        sonFrontera=fronteraR(raiz, lista, cantHojasAG);
//        int cantHojas=cantHojasAG[0];
//        if(cantHojas>=elemFrontera && sonFrontera){
//            sonFrontera=true;
//        }else{
//            sonFrontera=false;
//        }
//        return sonFrontera;
//    }
//    private boolean fronteraR(NodoGen nodo, Lista lista, int[] cantHojas){
//        boolean esFrontera=true;
//        if(nodo!=null){
//            NodoGen aux=nodo.getHijoIzquierdo();
//            if(aux==null && esFrontera){
//                esFrontera=perteneceLista(nodo.getElem(), lista);
//                if(esFrontera){
//                    cantHojas[0]=cantHojas[0]+1;
//                }
//            }
//            while(aux!=null && esFrontera){
//                esFrontera=fronteraR(aux, lista, cantHojas);
//                aux=aux.getHermanoDerecho();
//            }
//        }
//        return esFrontera;
//    }
    private boolean fronteraR(NodoGen nodo, Lista lista) {
        boolean esFrontera = true;
        if (nodo != null) {
            NodoGen aux = nodo.getHijoIzquierdo();
            if (aux == null) {
                esFrontera = perteneceLista(nodo.getElem(), lista);
            }
            while (aux != null && esFrontera) {
                esFrontera = fronteraR(aux, lista);
                aux = aux.getHermanoDerecho();
            }
        }
        return esFrontera;
    }

    private boolean perteneceLista(Object elemBuscado, Lista lista) {
        boolean encontrado = false;
        int longLista = lista.longitud();
        int i = 1;
        while (!encontrado && i <= longLista) {
            encontrado = elemBuscado.equals(lista.recuperar(i));
            i++;
        }
        return encontrado;
    }

    public boolean sonFrontera(Lista lista) {
        boolean esFrontera = false;
        if (this.esVacio() && lista.esVacia()) {
            esFrontera = true;
        } else {
            Lista clon = lista.clone();
            //Podriamos no mandar cuando la lista es vacia ya que siempre hay alguna hoja
            sonFronteraAux(raiz, clon);
            esFrontera = clon.esVacia();
        }
        return esFrontera;
    }

    private void sonFronteraAux(NodoGen nodo, Lista lista) {
        if (nodo != null) {
            if (null == nodo.getHijoIzquierdo()) {
                //Solo entra si es hoja
                int i = 0;
                boolean encontrado = false;
                Lista clon = lista.clone();
                i = posicionEnLista(clon, nodo.getElem());
                if (i != -1) {
                    lista.eliminar(i);
                }
            }
            if (!lista.esVacia()) {
                sonFronteraAux(nodo.getHijoIzquierdo(), lista);
                if (!lista.esVacia()) {
                    sonFronteraAux(nodo.getHermanoDerecho(), lista);
                }
            }
        }
    }

    private int posicionEnLista(Lista lista, Object elem) {
        boolean encontrado = false;
        int posicion = -1;
        int i = 0;
        while (!encontrado && !lista.esVacia()) {
            encontrado = elem.equals(lista.recuperar(1));
            lista.eliminar(1);
            i++;
        }
        if (encontrado) {
            posicion = i;
        }
        return posicion;
    }

    //Metodos adicionales parcial
    public boolean verificarCamino(Lista camino) {
        boolean esCamino = false;
        if (!esVacio()) {
            esCamino = verificarCaminoR(raiz, camino, 1);
        }
        return esCamino;
    }

    private boolean verificarCaminoR(NodoGen nodo, Lista lis, int pos) {
        boolean respetaCamino = false;
        Object elem = lis.recuperar(pos);
        int longitud = lis.longitud();
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                respetaCamino = true;
            }
            if (respetaCamino && pos < longitud) {
                NodoGen hijo = nodo.getHijoIzquierdo();
                respetaCamino = verificarCaminoR(hijo, lis, pos + 1);
                while (hijo != null && !respetaCamino) {
                    hijo = hijo.getHermanoDerecho();
                    respetaCamino = verificarCaminoR(hijo, lis, pos + 1);
                }
            }
        }
        return respetaCamino;
    }

    public Lista listarEntreNiveles(int nivMin, int nivMax) {
        Lista lista = new Lista();
        if (!this.esVacio()) {
            listarEntreNivelesR(lista, this.raiz, nivMin, nivMax, 0);
        }
        return lista;
    }

    private void listarEntreNivelesR(Lista lista, NodoGen nodo, int nivMin, int nivMax, int nivActual) {
        if (nodo != null) {
            if (nivActual <= nivMax) {
                listarEntreNivelesR(lista, nodo.getHijoIzquierdo(), nivMin, nivMax, nivActual + 1);
                if (nivActual >= nivMin) {
                    lista.insertar(nodo.getElem(), lista.longitud() + 1);
                }
                if (nodo.getHijoIzquierdo() != null) {
                    NodoGen temp = nodo.getHijoIzquierdo().getHermanoDerecho(); //Selecciona el hermano derecho del nodo izquierdo
                    nivActual++;
                    while (temp != null) {
                        listarEntreNivelesR(lista, temp, nivMin, nivMax, nivActual);
                        temp = temp.getHermanoDerecho();
                    }
                }

            }
        }
    }

    //***METODOS ADICIONALES PRACTICA FINAL***
    private Lista caminoAHojaMasCercana(NodoGen nodo, Lista caminoActual, Lista caminoMasCorto) {
        if (nodo != null) {
            if (nodo.getHijoIzquierdo() == null) { //Si es hoja
                //Si el camino actual es menor que el mas corto
                if (caminoMasCorto.esVacia() || caminoActual.longitud() < caminoMasCorto.longitud()) {
                    caminoMasCorto = caminoActual.clone();
                }
            }else{//Si no es hoja seguimos buscando
                caminoActual.insertar(nodo.getElem(), caminoActual.longitud()+1);
            }
        }
        return caminoMasCorto;
    }

}
