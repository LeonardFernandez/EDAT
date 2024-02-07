/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edat.TrenesSA;

import edat.estructuras.conjuntistas.Diccionario;
import edat.estructuras.grafos.Grafo;
import edat.estructuras.lineales.dinamicas.Lista;
import java.util.HashMap;

/**
 *
 * @author Leo
 */
public class TrenesSA {
    private Diccionario estaciones;
    private Grafo mapaEstaciones;
    private Diccionario trenes;
    private HashMap<String, Lista> lineas;
    
    public TrenesSA(){
        estaciones=new Diccionario();
        mapaEstaciones=new Grafo();
        trenes= new Diccionario();
        lineas=new HashMap<>();
    }
    //***ABM TRENES***
    public boolean agregarTren(int codigo, String propulsion, int cantVagPasaj, int cantVagCarga, String linea){
        boolean exito;
        Tren tren= new Tren(codigo, propulsion, cantVagPasaj, cantVagCarga, linea);
        exito = trenes.insertar(codigo, tren);
        return exito;
    }
    
    public boolean eliminarTren(int codigoID){
        boolean exito;
        exito=trenes.eliminar(codigoID);
        return exito;
    }
    
    public boolean modificarPropulsionTren(int id, String propulsion){
        Tren trenMod = null;
        boolean exito=false;
        trenMod = (Tren) trenes.obtenerDato(id);
        if(trenMod!=null){
            trenMod.setPropulsion(propulsion);
            exito=true;
        }
        return exito;
    }
    
    public boolean modificarVagPasajerosTren(int id, int vagPasajeros){
        Tren trenMod = null;
        boolean exito=false;
        trenMod = (Tren) trenes.obtenerDato(id);
        if(trenMod!=null){
            trenMod.setCantVagonesPasaj(vagPasajeros);
            exito=true;
        }
        return exito;
    }
    
    public boolean modificarVagCargaTren(int id, int vagCarga){
        Tren trenMod = null;
        boolean exito=false;
        trenMod = (Tren) trenes.obtenerDato(id);
        if(trenMod!=null){
            trenMod.setCantVagonesCarga(vagCarga);
            exito=true;
        }
        return exito;
    }
    
    public boolean modificarLineaTren(int id, String nLinea){
        Tren trenMod = null;
        boolean exito=false;
        trenMod = (Tren) trenes.obtenerDato(id);
        if(trenMod!=null){
            trenMod.setLinea(nLinea);
            exito=true;
        }
        return exito;
    }
    
    //***ABM ESTACIONES***
    public boolean agregarEstacion(String nombre, String calle, int numCalle, String ciudad, int cp, int numVias, int numPlataformas){
        boolean exito=false;
        Estacion estacion = new Estacion(nombre, calle, numCalle, ciudad, cp, numVias, numPlataformas);
        if(estaciones.insertar(nombre, estacion)){
            mapaEstaciones.insertarVertice(nombre);
            exito=true;
        }
        return exito;
    }
    
    public boolean eliminarEstacion(String nombre){
        boolean exito=false;
        if(estaciones.eliminar(nombre)){
            mapaEstaciones.eliminarVertice(nombre);
            exito=true;
        }
        return exito;
    }
    
    
    
    public boolean modificarCalleEstacion(String nombre, String calle){
        boolean exito=false;
        Estacion estacionMod = null;
        estacionMod=(Estacion) estaciones.obtenerDato(nombre);
        if(estacionMod!=null){
            estacionMod.setCalle(calle);
            exito=true;
        }
        return exito;
    }
    
    public boolean modificarNumCalleEstacion(String nombre, int num){
        boolean exito=false;
        Estacion estacionMod = null;
        estacionMod=(Estacion) estaciones.obtenerDato(nombre);
        if(estacionMod!=null){
            estacionMod.setNumeroCalle(num);
            exito=true;
        }
        return exito;
    }
    
    public boolean modificarCiudadEstacion(String nombre, String ciudad){
        boolean exito=false;
        Estacion estacionMod = null;
        estacionMod=(Estacion) estaciones.obtenerDato(nombre);
        if(estacionMod!=null){
            estacionMod.setCiudad(ciudad);
            exito=true;
        }
        return exito;
    }
    
    public boolean modificarCPEstacion(String nombre, int cp){
        boolean exito=false;
        Estacion estacionMod = null;
        estacionMod=(Estacion) estaciones.obtenerDato(nombre);
        if(estacionMod!=null){
            estacionMod.setCodigoPostal(cp);
            exito=true;
        }
        return exito;
    }
    
    public boolean modificarCantViasEstacion(String nombre, int cantVias){
        boolean exito=false;
        Estacion estacionMod = null;
        estacionMod=(Estacion) estaciones.obtenerDato(nombre);
        if(estacionMod!=null){
            estacionMod.setCantVias(cantVias);
            exito=true;
        }
        return exito;
    }
    
    public boolean modificarCantPlataformasEstacion(String nombre, int cantPlataformas){
        boolean exito=false;
        Estacion estacionMod = null;
        estacionMod=(Estacion) estaciones.obtenerDato(nombre);
        if(estacionMod!=null){
            estacionMod.setCantPlataformas(cantPlataformas);
            exito=true;
        }
        return exito;
    }
    
    //***ABM LINEAS***
    public boolean agregarLinea(String nombre){
        boolean exito=false;
        if(!lineas.containsKey(nombre)){
            Lista listaEstaciones=new Lista();
            lineas.put(nombre, listaEstaciones);
            exito=true;
        }
        return exito;
    }
    
    public boolean eliminarLinea(String nombre){
        boolean exito=false;
        if(lineas.containsKey(nombre)){
            lineas.remove(nombre);
            exito=true;
        }
        return exito;
    }
    
    public boolean agregarEstacionALinea(String nombreLinea, String nombreEstacion){
        boolean exito=false;
        Estacion estacion = null;
        estacion=(Estacion) estaciones.obtenerDato(nombreEstacion);
        if(lineas.containsKey(nombreLinea) && estacion!=null){
            Lista listaEstaciones=lineas.get(nombreLinea);
            listaEstaciones.insertar(estacion, listaEstaciones.longitud()+1);
            exito=true;
        }
        return exito;
    }
    
    public boolean eliminarEstacionDeLinea(String nombreLinea, String nombreEstacion){
        boolean exito=false;
        if(lineas.containsKey(nombreLinea)){
            Lista listaEstaciones=lineas.get(nombreLinea);
            int i=1;
            boolean encontrado=false;
            while(i<=listaEstaciones.longitud() && !encontrado) {
                Estacion est = (Estacion) listaEstaciones.recuperar(i);
                encontrado=est.getNombre().equals(nombreEstacion);
                i++;
            }
            if(encontrado){
                listaEstaciones.eliminar(i-1);
                exito=true;
            }
        }
        return exito;
    }
    
    //***ABM MAPA ESTACIONES/RED DE RIELES***
    
    public boolean insertarConexion(Object origen, Object destino, int distancia){
        boolean exito=false;
        exito=mapaEstaciones.insertarArco(origen, destino, distancia);
        return exito;
    }
    
    public boolean eliminarConexion(Object origen, Object destino){
        boolean exito=false;
        exito=mapaEstaciones.eliminarArco(origen, destino);
        return exito;
    }
    
    //***CONSULTAS TRENES***
    
    public String infoTren(int id){
        //Dado un código de tren muestra toda la información del mismo
        String info="";
        Tren tren=(Tren) trenes.obtenerDato(id);
        info="ID: "+ tren.getId() + "\n";
        info="PROPULSION: "+ tren.getPropulsion()+ "\n";
        info="VAGONES DE PASAJEROS: "+ tren.getCantVagonesPasaj()+ "\n";
        info="VAGONES DE CARGA: "+ tren.getCantVagonesCarga()+ "\n";
        info="LINEA: "+ tren.getLinea()+ "\n";
        return info;
    }
    
    
}
