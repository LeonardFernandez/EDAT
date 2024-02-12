/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edat.TrenesSA;

import edat.estructuras.lineales.dinamicas.Lista;
import java.util.Scanner;

/**
 *
 * @author Leo
 */
public class Main {

    private static final TrenesSA sistema = new TrenesSA();
    private static Scanner scanner = new Scanner(System.in);
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        //Menu
        int opcion;
        do {
            System.out.println(">>> MENU DE OPCIONES <<<");
            System.out.println("Seleccione una opcion: ");
            System.out.println("1. Realizar Carga Inicial");
            System.out.println("2. ABM de trenes");
            System.out.println("3. ABM de estaciones");
            System.out.println("4. ABM de lineas");
            System.out.println("5. ABM de la red de rieles");
            System.out.println("6. Consulta sobre trenes");
            System.out.println("7. Consultas sobre estaciones");
            System.out.println("8. Consultas sobre viajes");
            System.out.println("9. Mostrar sistema");
            System.out.println("10. Salir");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Carga inicial
                    sistema.cargaInicial();
                    break;
                case 2:
                    //ABM de trenes (Altas, Bajas, Modificaciones)
                    ABMTrenes();
                    break;
                case 3:
                    //ABM de estaciones (Altas, Bajas, Modificaciones)
                    ABMEstaciones();
                    break;
                case 4:
                    //ABM de lineas (Altas, Bajas, Modificaciones)
                    ABMLineas();
                    break;
                case 5:
                    //ABM de la red de rieles (Altas, Bajas, Modificaciones)
                    ABMRieles();
                    break;
//                case 6:
//                    //Consultas sobre trenes
//                    consultaTrenes();
//                    break;
//                case 7:
//                    //Consultas sobre estaciones
//                    consultaEstaciones();
//                    break;
//                case 8:
//                    //Consultas sobre viajes
//                    consultasViajes();
//                    break;
                case 9:
                    // Mostrar sistema
                    System.out.println(sistema.mostrarSistema());
                    break;
//                case 10:
//                    System.out.println("Saliendo del programa. ¡Hasta luego!");
//                    registrarLog("ESTADO FINAL");
//                    registrarLog("___________________________________________");
//                    registrarLog(estaciones.toString());
//                    registrarLog(lineas.toString());
//                    registrarLog(rieles.toString());
//                    registrarLog(trenes.toString());
//
//                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 10);
        scanner.close();
    }

    private static void ABMTrenes() {
        int opcion;
        boolean salir = false;
        do {
            System.out.println("MENU DE TRENES");
            System.out.println("Seleccione una opcion");
            System.out.println("1. Alta de Tren");
            System.out.println("2. Baja de tren");
            System.out.println("3. Modificar Tren");
            System.out.println("4. Volver Menu Principal");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    agregarTren();
                    break;
                case 2:
                    eliminarTren();
                    break;
                case 3:
                    modificacionesTren();
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida intente de nuevo");
                    break;
            }
        } while (!salir);
    }

    private static void agregarTren() {
        System.out.println("Ingrese el id(numerico) del tren");
        int id = scanner.nextInt();
        String propulsion = "";
        propulsion=selectPropulsion();
        System.out.println("Ingrese la cantidad de vagones de pasajeros(numerico)");
        int vagPasaj = scanner.nextInt();
        System.out.println("Ingrese la cantidad de vagones de carga(numerico)");
        int vagCarga = scanner.nextInt();
        //Seleccion linea
        String linea = selectLinea();
        if (sistema.agregarTren(id, propulsion, vagPasaj, vagCarga, linea)) {
            System.out.println("Tren agregado con exito");
        } else {
            System.out.println("Error al agregar el tren, verifique los datos");
        }
    }

    private static void eliminarTren() {
        int idEliminacion;
        System.out.println("Ingrese el id del tren a eliminar");
        idEliminacion = scanner.nextInt();
        if (sistema.eliminarTren(idEliminacion)) {
            System.out.println("Tren " + idEliminacion + " eliminado con exito");
        } else {
            System.out.println(RED + "ERROR al eliminar, compruebe el id ingresado" + RESET);
        }
    }

    private static void modificacionesTren() {
        int opcion, idMod;
        boolean exito=false;
        boolean salir = false;
        System.out.println("Ingrese id del tren a modificar");
        idMod=scanner.nextInt();
        do {            
            System.out.println("Seleccione la modificacion que desea realizar");
            System.out.println("1. Mod. propulsion");
            System.out.println("2. Mod. Cantidad Vagones de pasajeros");
            System.out.println("3. Mod. Cantidad Vagones de carga");
            System.out.println("4. Mod. Linea del tren");
            System.out.println("5. Volver menu Trenes");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    String propulsion=selectPropulsion();
                    exito=sistema.modificarPropulsionTren(idMod, propulsion);
                    break;
                case 2:
                    System.out.println("Ingrese la cantidad de vagones de pasajeros(numerico)");
                    int vagPasaj = scanner.nextInt();
                    exito=sistema.modificarVagPasajerosTren(idMod, vagPasaj);
                    break;
                case 3:
                    System.out.println("Ingrese la cantidad de vagones de carga(numerico)");
                    int vagCarga = scanner.nextInt();
                    exito=sistema.modificarVagCargaTren(idMod, vagCarga);
                case 4:
                    String linea=selectLinea();
                    exito=sistema.modificarLineaTren(idMod, linea);
            }
            if(exito){
                System.out.println("Modificacion exitosa");
            }else{
                System.out.println(RED+"ERROR no fue posible la modificacion, verique datos ingresados"+RESET);
            }
        } while (opcion != 5);
    }

    private static String selectPropulsion() {
        String propulsion="";
        boolean seleccionado;
        do {
            System.out.println("Seleccione propulsion \n 1-electricidad \n 2-diesel \n 3-fuel oil \n 4-gasolina \n 5-híbrido");
            int tipoPropulsion = scanner.nextInt();
            switch (tipoPropulsion) {
                case 1:
                    propulsion = "electricidad";
                    seleccionado = false;
                    break;
                case 2:
                    propulsion = "diesel";
                    seleccionado = false;
                    break;
                case 3:
                    propulsion = "fuel oil";
                    seleccionado = false;
                    break;
                case 4:
                    propulsion = "gasolina";
                    seleccionado = false;
                    break;
                case 5:
                    propulsion = "hibrido";
                    seleccionado = false;
                    break;
                default:
                    seleccionado = true;
                    System.out.println("Opcion no valida. Intente de nuevo");
                    break;
            }
        } while (seleccionado);
        return propulsion;
    }
    
    private static String selectLinea(){
        //Seleccion linea
        String linea="";
        Lista lineas = sistema.obtenerListaLineas();
        System.out.println(lineas.toString());
        System.out.println("Seleccione la linea del tren (numerico num->linea)");
        int seleccionLinea = scanner.nextInt();
        do {
            if ((seleccionLinea >= 1 && seleccionLinea <= lineas.longitud())) {
                linea = (String) lineas.recuperar(seleccionLinea);
            } else {
                System.out.println("Linea no valida intente de nuevo");
            }
        } while (!(seleccionLinea >= 1 && seleccionLinea <= lineas.longitud()));
        return linea;
    }
    
    private static void ABMEstaciones(){
        int opcion;
        boolean salir = false;
        do {
            System.out.println("MENU DE ESTACIONES");
            System.out.println("Seleccione una opcion");
            System.out.println("1. Alta de Estacion");
            System.out.println("2. Baja de Estacion");
            System.out.println("3. Modificar Estacion");
            System.out.println("4. Volver Menu Principal");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    agregarEstacion();
                    break;
                case 2:
                    eliminarEstacion();
                    break;
                case 3:
                    modificacionesEstaciones();
                    break;
                case 4:
                    salir=true;
                    break;
                default:
                    System.out.println("Opcion no valida intente de nuevo");
                    break;
            }
        }while(!salir);
    }
    
    private static void agregarEstacion(){
        System.out.println("Ingrese nombre de la estacion");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la calle de la estacion");
        String calle=scanner.nextLine();
        System.out.println("Ingrese altura/numero de la calle (numerico)");
        int numCalle=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese la ciudad de la estacion");
        String ciudad=scanner.nextLine();
        System.out.println("Ingrese el codigo postal (numerico)");
        int cp=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese el numero de vias de la estacion");
        int numVias = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese el numero de plataformas");
        int numPlataf=scanner.nextInt();
        scanner.nextLine();
        if(sistema.agregarEstacion(nombre, calle, numCalle, ciudad, cp, numVias, numPlataf)){
            System.out.println("Estacion agregada de forma exitosa");
        }else{
            System.out.println(RED+"ERROR compruebe los datos ingresados"+RESET);
        }
    }
    
    private static void eliminarEstacion(){
        System.out.println("Ingrese el nombre de la estacion a eliminar");
        String nombre=scanner.nextLine();
        if(sistema.eliminarEstacion(nombre)){
            System.out.println("Estacion eliminada con exito");
        }else{
            System.out.println(RED+"ERROR al eliminar estacion verifique el nombre ingresado"+RESET);
        }
    }
    
    private static void modificacionesEstaciones(){
        int opcion;
        String nombreEst;
        boolean exito=false;
        boolean salir = false;
        System.out.println("Ingrese nombre de la estacion a modificar");
        nombreEst=scanner.nextLine();
        do {            
            System.out.println("Seleccione la modificacion que desea realizar");
            System.out.println("1. Mod. calle");
            System.out.println("2. Mod. num calle");
            System.out.println("3. Mod. ciudad");
            System.out.println("4. Mod. codigo postal");
            System.out.println("5. Mod. cantidad de vias");
            System.out.println("6. Mod. cantidad de plataformas");
            System.out.println("7. Volver menu Estaciones");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la calle");
                    String calle = scanner.nextLine();
                    exito=sistema.modificarCalleEstacion(nombreEst, calle);
                    break;
                case 2:
                    System.out.println("Ingrese el numero de la calle(numerico)");
                    int num = scanner.nextInt();
                    scanner.nextLine();
                    exito=sistema.modificarNumCalleEstacion(nombreEst, num);
                    break;
                case 3:
                    System.out.println("Ingrese la ciudad de la estacion");
                    String ciudad=scanner.nextLine();
                    exito=sistema.modificarCiudadEstacion(nombreEst, ciudad);
                    break;
                case 4:
                    System.out.println("Ingrese el codigo postal (numerico)");
                    int cp=scanner.nextInt();
                    scanner.nextLine();
                    exito=sistema.modificarCPEstacion(nombreEst, cp);
                    break;
                case 5:
                    System.out.println("Ingrese el numero de vias de la estacion");
                    int numVias = scanner.nextInt();
                    scanner.nextLine();
                    exito=sistema.modificarCantViasEstacion(nombreEst, numVias);
                    break;
                case 6:
                    System.out.println("Ingrese el numero de plataformas");
                    int numPlataf=scanner.nextInt();
                    scanner.nextLine();
                    exito=sistema.modificarCantPlataformasEstacion(nombreEst, numPlataf);
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opcion no valida intente de nuevo");
                    break;
            }
            if(exito){
                System.out.println("Modificacion exitosa");
            }else{
                System.out.println(RED+"ERROR no fue posible la modificacion, verique datos ingresados"+RESET);
            }
        } while (opcion != 7);
    }    
    
    private static void ABMLineas(){
        int opcion;
        boolean salir = false;
        do {
            System.out.println("MENU DE LINEAS");
            System.out.println("Seleccione una opcion");
            System.out.println("1. Alta de linea");
            System.out.println("2. Baja de linea");
            System.out.println("3. Agregar estacion a una linea");
            System.out.println("4. Eliminar estacion de una linea");
            System.out.println("5. Volver Menu Principal");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    altaLinea();
                    break;
                case 2:
                    bajaLinea();
                    break;
                case 3:
                    agregarEstacionLinea();
                    break;
                case 4:
                    eliminarEstacionLinea();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida intente de nuevo");
                    break;
            }
        } while (!salir);
    }
    
    private static void altaLinea(){
        System.out.println("Ingrese nombre de la nueva linea");
        String nombre=scanner.nextLine();
        if(sistema.agregarLinea(nombre)){
            System.out.println("La linea "+nombre+" se agrego de forma exitosa");
        }else{
            System.out.println(RED+"ERROR la linea ya pertenece al sistema"+RESET);
        }
    }
    
    private static void bajaLinea(){
        System.out.println("Ingrese el nombre de la linea a eliminar");
        String nombre=scanner.nextLine();
        if(sistema.eliminarLinea(nombre)){
            System.out.println("La linea "+nombre+" ha sido eliminada con exito");
        }else{
            System.out.println(RED+"ERROR al eliminar la linea, verifique el nombre ingresado"+RESET);
        }
    }
    
    private static void agregarEstacionLinea(){
        System.out.println("Ingrese el nombre de la linea");
        String nombreLinea=scanner.nextLine();
        System.out.println("Ingrese el nombre de la estacion (debe encontrarse cargada previamente) a agregar en la linea "+nombreLinea);
        String nombreEstacion=scanner.nextLine();
        if(sistema.agregarEstacionALinea(nombreLinea, nombreEstacion)){
            System.out.println("La estacion "+nombreEstacion+ " ha sido agregada con exito a la linea "+nombreLinea);
        }else{
            System.out.println(RED+"ERROR compruebe los datos ingresados"+RESET);
        }
    }
    
    private static void eliminarEstacionLinea(){
        System.out.println("Ingrese el nombre de la linea");
        String nombreLinea=scanner.nextLine();
        System.out.println("Ingrese el nombre de la estacion a eliminar de la linea "+nombreLinea);
        String nombreEstacion=scanner.nextLine();
        if(sistema.eliminarEstacionDeLinea(nombreLinea, nombreEstacion)){
            System.out.println("La estacion "+nombreEstacion+ " ha sido eliminada con exito de la linea "+nombreLinea);
        }else{
            System.out.println(RED+"ERROR compruebe los datos ingresados"+RESET);
        }
    }
    
    private static void ABMRieles(){
        int opcion;
        boolean salir = false;
        do {
            System.out.println("MENU DE RIELES");
            System.out.println("Seleccione una opcion");
            System.out.println("1. Agregar conexion");
            System.out.println("2. Eliminar conexion");
            System.out.println("3. Volver Menu Principal");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    agregarConexion();
                    break;
                case 2:
                    eliminarConexion();
                    break;
                case 3:
                    salir=true;
                    break;
                default:
                    System.out.println("Opcion no valida intente de nuevo");
                    break;
            }
        }while(!salir);
    }
    
    private static void agregarConexion(){
        System.out.println("Ingrese la estacion de origen");
        String origen=scanner.nextLine();
        System.out.println("Ingrese estacion destino");
        String destino=scanner.nextLine();
        System.out.println("Ingrese la distancia entre las estaciones en km (numerico)");
        int distancia=scanner.nextInt();
        if(sistema.insertarConexion(origen, destino, distancia)){
            System.out.println("La conexion entre "+origen+" y "+destino+ " de "+distancia+"km ha sido agregada de forma exitosa");  
        }else{
            System.out.println(RED+"ERROR compruebe las estaciones ingresadas"+RESET);
        }
    }
    
    private static void eliminarConexion(){
        System.out.println("Ingrese la estacion de origen de la conexion");
        String origen=scanner.nextLine();
        System.out.println("Ingrese estacion destino de la conexion");
        String destino=scanner.nextLine();
        if(sistema.eliminarConexion(origen, destino)){
            System.out.println("La conexion entre "+origen+" y "+destino+ " fue eliminada con exito");
        }else{
            System.out.println(RED+"ERROR compruebe las estaciones ingresadas"+RESET);
        }
    }
}
