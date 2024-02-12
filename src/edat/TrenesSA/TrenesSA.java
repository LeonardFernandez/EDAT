/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edat.TrenesSA;

import edat.estructuras.conjuntistas.Diccionario;
import edat.estructuras.grafos.Grafo;
import edat.estructuras.lineales.dinamicas.Lista;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leo
 */
public class TrenesSA {

    private Diccionario estaciones;
    private Grafo mapaEstaciones;
    private Diccionario trenes;
    private HashMap<String, Lista> lineas;
    private final String carga="src\\cargaInicial\\cargaTotal.txt";

    public TrenesSA() {
        estaciones = new Diccionario();
        mapaEstaciones = new Grafo();
        trenes = new Diccionario();
        lineas = new HashMap<>();
    }

    //***ABM TRENES***
    public boolean agregarTren(int codigo, String propulsion, int cantVagPasaj, int cantVagCarga, String linea) {
        boolean exito;
        Tren tren = new Tren(codigo, propulsion, cantVagPasaj, cantVagCarga, linea);
        exito = trenes.insertar(codigo, tren);
        return exito;
    }

    public boolean eliminarTren(int codigoID) {
        boolean exito;
        exito = trenes.eliminar(codigoID);
        return exito;
    }

    public boolean modificarPropulsionTren(int id, String propulsion) {
        Tren trenMod = null;
        boolean exito = false;
        trenMod = (Tren) trenes.obtenerDato(id);
        if (trenMod != null) {
            trenMod.setPropulsion(propulsion);
            exito = true;
        }
        return exito;
    }

    public boolean modificarVagPasajerosTren(int id, int vagPasajeros) {
        Tren trenMod = null;
        boolean exito = false;
        trenMod = (Tren) trenes.obtenerDato(id);
        if (trenMod != null) {
            trenMod.setCantVagonesPasaj(vagPasajeros);
            exito = true;
        }
        return exito;
    }

    public boolean modificarVagCargaTren(int id, int vagCarga) {
        Tren trenMod = null;
        boolean exito = false;
        trenMod = (Tren) trenes.obtenerDato(id);
        if (trenMod != null) {
            trenMod.setCantVagonesCarga(vagCarga);
            exito = true;
        }
        return exito;
    }

    public boolean modificarLineaTren(int id, String nLinea) {
        Tren trenMod = null;
        boolean exito = false;
        trenMod = (Tren) trenes.obtenerDato(id);
        if (trenMod != null) {
            trenMod.setLinea(nLinea);
            exito = true;
        }
        return exito;
    }

    //***ABM ESTACIONES***
    public boolean agregarEstacion(String nombre, String calle, int numCalle, String ciudad, int cp, int numVias, int numPlataformas) {
        boolean exito = false;
        Estacion estacion = new Estacion(nombre, calle, numCalle, ciudad, cp, numVias, numPlataformas);
        if (estaciones.insertar(nombre, estacion)) {
            mapaEstaciones.insertarVertice(nombre);
            exito = true;
        }
        return exito;
    }

    public boolean eliminarEstacion(String nombre) {
        boolean exito = false;
        if (estaciones.eliminar(nombre)) {
            mapaEstaciones.eliminarVertice(nombre);
            exito = true;
        }
        return exito;
    }

    public boolean modificarCalleEstacion(String nombre, String calle) {
        boolean exito = false;
        Estacion estacionMod = null;
        estacionMod = (Estacion) estaciones.obtenerDato(nombre);
        if (estacionMod != null) {
            estacionMod.setCalle(calle);
            exito = true;
        }
        return exito;
    }

    public boolean modificarNumCalleEstacion(String nombre, int num) {
        boolean exito = false;
        Estacion estacionMod = null;
        estacionMod = (Estacion) estaciones.obtenerDato(nombre);
        if (estacionMod != null) {
            estacionMod.setNumeroCalle(num);
            exito = true;
        }
        return exito;
    }

    public boolean modificarCiudadEstacion(String nombre, String ciudad) {
        boolean exito = false;
        Estacion estacionMod = null;
        estacionMod = (Estacion) estaciones.obtenerDato(nombre);
        if (estacionMod != null) {
            estacionMod.setCiudad(ciudad);
            exito = true;
        }
        return exito;
    }

    public boolean modificarCPEstacion(String nombre, int cp) {
        boolean exito = false;
        Estacion estacionMod = null;
        estacionMod = (Estacion) estaciones.obtenerDato(nombre);
        if (estacionMod != null) {
            estacionMod.setCodigoPostal(cp);
            exito = true;
        }
        return exito;
    }

    public boolean modificarCantViasEstacion(String nombre, int cantVias) {
        boolean exito = false;
        Estacion estacionMod = null;
        estacionMod = (Estacion) estaciones.obtenerDato(nombre);
        if (estacionMod != null) {
            estacionMod.setCantVias(cantVias);
            exito = true;
        }
        return exito;
    }

    public boolean modificarCantPlataformasEstacion(String nombre, int cantPlataformas) {
        boolean exito = false;
        Estacion estacionMod = null;
        estacionMod = (Estacion) estaciones.obtenerDato(nombre);
        if (estacionMod != null) {
            estacionMod.setCantPlataformas(cantPlataformas);
            exito = true;
        }
        return exito;
    }

    //***ABM LINEAS***
    public boolean agregarLinea(String nombre) {
        boolean exito = false;
        if (!lineas.containsKey(nombre)) {
            Lista listaEstaciones = new Lista();
            lineas.put(nombre, listaEstaciones);
            exito = true;
        }
        return exito;
    }

    public boolean eliminarLinea(String nombre) {
        boolean exito = false;
        if (lineas.containsKey(nombre)) {
            lineas.remove(nombre);
            exito = true;
        }
        return exito;
    }

    public boolean agregarEstacionALinea(String nombreLinea, String nombreEstacion) {
        boolean exito = false;
        Estacion estacion = null;
        estacion = (Estacion) estaciones.obtenerDato(nombreEstacion);
        if (lineas.containsKey(nombreLinea) && estacion != null) {
            Lista listaEstaciones = lineas.get(nombreLinea);
            listaEstaciones.insertar(estacion, listaEstaciones.longitud() + 1);
            exito = true;
        }
        return exito;
    }

    public boolean eliminarEstacionDeLinea(String nombreLinea, String nombreEstacion) {
        boolean exito = false;
        if (lineas.containsKey(nombreLinea)) {
            Lista listaEstaciones = lineas.get(nombreLinea);
            int i = 1;
            int longitud = listaEstaciones.longitud();
            boolean encontrado = false;
            while (i <= longitud && !encontrado) {
                Estacion est = (Estacion) listaEstaciones.recuperar(i);
                encontrado = est.getNombre().equals(nombreEstacion);
                i++;
            }
            if (encontrado) {
                listaEstaciones.eliminar(i - 1);
                exito = true;
            }
        }
        return exito;
    }

    //***ABM MAPA ESTACIONES/RED DE RIELES***
    public boolean insertarConexion(Object origen, Object destino, int distancia) {
        boolean exito = false;
        exito = mapaEstaciones.insertarArco(origen, destino, distancia);
        return exito;
    }

    public boolean eliminarConexion(Object origen, Object destino) {
        boolean exito = false;
        exito = mapaEstaciones.eliminarArco(origen, destino);
        return exito;
    }

    //***CONSULTAS TRENES***
    public String infoTren(int id) {
        //Dado un código de tren muestra toda la información del mismo
        String info = null;
        Tren tren = null;
        tren = (Tren) trenes.obtenerDato(id);
        if (tren != null) {
            info = "";
            info = info + "ID: " + tren.getId() + "\n";
            info = info + "PROPULSION: " + tren.getPropulsion() + "\n";
            info = info + "VAGONES DE PASAJEROS: " + tren.getCantVagonesPasaj() + "\n";
            info = info + "VAGONES DE CARGA: " + tren.getCantVagonesCarga() + "\n";
            info = info + "LINEA: " + tren.getLinea() + "\n";
        }
        return info;
    }

    public String ciudadesRecorridoTren(int id) {
        /*Dado un código de tren, verificar si está destinado a alguna línea
        y mostrar las ciudades que visitaría(Devuelve null si no se encuentra el tren)*/
        String cadena = null;
        Tren tren = null;
        tren = (Tren) trenes.obtenerDato(id);
        if (tren != null) {
            cadena="";
            String lineaTren = tren.getLinea();
            if (lineas.containsKey(lineaTren)) {
                Lista estacionesTren = lineas.get(lineaTren);
                int longitud = estacionesTren.longitud();
                cadena = "CIUDADES QUE VISITA EL TREN " + id + " : ";
                for (int j = 1; j <= longitud; j++) {
                    Estacion estacion = (Estacion) estacionesTren.recuperar(j);
                    cadena = cadena + estacion.getCiudad() + " - ";
                }
            } else {
                cadena = "El tren no tiene ciudades asignadas aun";
            }
        }
        return cadena;
    }

    //***CONSULTAS ESTACIONES***
    public String infoEstacion(String nombre) {
        //Dado un nombre de estación, mostrar toda su información
        String cadena = null;
        Estacion estacion=null;
        estacion=(Estacion) estaciones.obtenerDato(nombre);
        if (estacion!=null) {
            cadena="";
            cadena=cadena+"NOMBRE: "+estacion.getNombre()+"\n";
            cadena=cadena+"CALLE: "+estacion.getCalle()+"\n";
            cadena=cadena+"NUMERO CALLE: "+estacion.getNumeroCalle()+"\n";
            cadena=cadena+"CIUDAD: "+estacion.getCiudad()+"\n";
            cadena=cadena+"CODIGO POSTAL: "+estacion.getCodigoPostal()+"\n";
            cadena=cadena+"CANTIDAD DE VIAS: "+estacion.getCantVias()+"\n";
            cadena=cadena+"CANTIDAD PLATAFORMAS: "+estacion.getCantPlataformas()+"\n";
        }
        return cadena;
    }
    
    public String busquedaEstacionesPrefijo(String subcadena){
        //Dada una cadena, devolver todas las estaciones cuyo nombre comienza con dicha subcadena
        String cadena="";
        subcadena=subcadena.trim();
        Lista lista = estaciones.listarRango(subcadena, subcadena+"ZZZZZZZZZZZ");
        if(!lista.esVacia()){
            cadena="Las estaciones con prefijo "+subcadena+" son: ";
            int longitud = lista.longitud();
            for (int i = 1; i <= longitud; i++) {
                cadena=cadena+"\n";
                cadena=cadena+lista.recuperar(i);
            }
        }else{
            cadena="No existen estaciones con tal prefijo";
        }
        return cadena;
    }
    
    //***CONSULTAS VIAJES/MAPA GRAFOS***
    public String caminoMenosEstaciones(String origen, String destino){
        String cadena="";
        Lista camino=mapaEstaciones.caminoMenosVertices(origen, destino);
        if(!camino.esVacia()){
            int longitud = camino.longitud();
            cadena="El camino desde "+origen+" a "+destino+" que pasa por menos estaciones es: ";
            for (int i = 1; i <= longitud; i++) {
                cadena=cadena+camino.recuperar(i)+" - ";
            }
        }else{
            cadena="Error no existe ningun camino. Verifique datos ingresados";
        }
        return cadena;
    }
    
    
    
    public void cargaInicial(){
        try {
            FileReader fileReader = new FileReader(carga);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                carga(linea);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TrenesSA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TrenesSA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void carga(String linea){
        StringTokenizer parametro = new StringTokenizer(linea, ";");
        switch(parametro.nextToken()){
            case"E":
                String nombre=parametro.nextToken();
                String calle=parametro.nextToken();
                int numeroCalle=Integer.parseInt(parametro.nextToken());
                String ciudad=parametro.nextToken();
                int cp=Integer.parseInt(parametro.nextToken());
                int cantVias=Integer.parseInt(parametro.nextToken());
                int cantPlataformas=Integer.parseInt(parametro.nextToken());
                Estacion e =new Estacion(nombre, calle, numeroCalle, ciudad, cp, cantVias, cantPlataformas);
                if(estaciones.insertar(nombre, e) && mapaEstaciones.insertarVertice(nombre)){
                    System.out.println("Estacion " +nombre+" cargada con exito");
                }else{
                    System.out.println("ERROR AL CARGAR ESTACION "+nombre);
                }
                break;
            case"T":
                int id=Integer.parseInt(parametro.nextToken());
                String propulsion=parametro.nextToken();
                int vagPasaj=Integer.parseInt(parametro.nextToken());
                int vagCarga=Integer.parseInt(parametro.nextToken());
                String lineaT=parametro.nextToken();
                Tren t=new Tren(id, propulsion, vagPasaj, vagCarga, lineaT);
                if(trenes.insertar(id, t)){
                    System.out.println("Tren "+id+" cargado con exito");
                }else{
                    System.out.println("ERROR AL CARGAR EL TREN "+id);
                }
                break;
            case"R":
                String e1=parametro.nextToken();
                String e2=parametro.nextToken();
                int km = Integer.parseInt(parametro.nextToken());
                if(mapaEstaciones.insertarArco(e1, e2, km)){
                    System.out.println("Conexion entre "+e1+" "+e2+ " - "+km+"km");
                }else{
                    System.out.println("ERROR AL INSERTAR CONEXION "+e1+" "+e2+ " - "+km+"km");
                }
                break;
            case"L":
                String nombreLinea=parametro.nextToken();
                if(!lineas.containsKey(nombreLinea)){ //Si la linea no se encuentra aun cargada
                    Lista listaEstaciones=new Lista();
                    while(parametro.hasMoreTokens()){
                        String nombreEstacion=parametro.nextToken();
                        Object aux=estaciones.obtenerDato(nombreEstacion);
                        if((aux)!=null){ //Si existe la estacion
                            listaEstaciones.insertar(aux, listaEstaciones.longitud()+1);
                        }else{
                            System.out.println("ERROR ESTACION "+nombreEstacion+" NO EXISTE");
                        }
                    }
                    lineas.put(nombreLinea, listaEstaciones); //Insercion exitosa
                    System.out.println("Linea "+nombreLinea+" agregada con exito");
                }else{
                    System.out.println("ERROR LA LINEA "+nombreLinea+" YA SE ENCUENTRA CARGADA");
                }
                break;
        }
    }
    
    public String mostrarSistema(){
        String cadena="";
        cadena=">>> DETALLE SISTEMA CARGADO <<<\n";
        cadena=cadena+"----TRENES----\n"+trenes.toString()+"\n";
        cadena=cadena+"----ESTACIONES----\n"+estaciones.toString()+"\n";
        cadena=cadena+"----LINEAS----\n"+lineas.keySet()+"\n";
        cadena=cadena+"----MAPA DE TRENES----\n"+mapaEstaciones.toString()+"\n";
        return cadena;
    }

    
    public Lista obtenerListaLineas(){
        Lista lista=new Lista();
        int i=1;
        for (String linea : lineas.keySet()) {
            lista.insertar("("+i+"->"+linea+")", lista.longitud()+1);
            i++;
        }
        lista.insertar("("+i+"->"+"no-asignado"+")", lista.longitud()+1);
        return lista;
    }
}
