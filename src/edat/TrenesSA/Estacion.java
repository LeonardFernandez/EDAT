/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edat.TrenesSA;

/**
 *
 * @author Leo
 */
public class Estacion {
    private String nombre;
    private String calle;
    private int numeroCalle;
    private String ciudad;
    private int codigoPostal;
    private int cantVias;
    private int cantPlataformas;
    
    public Estacion(String unNombre, String unaCalle, int unNum, String unaCiudad, int cp, int numVias, int numPlat){
        nombre = unNombre;
        calle= unaCalle;
        numeroCalle = unNum;
        ciudad=unaCiudad;
        codigoPostal=cp;
        cantVias=numVias;
        cantPlataformas=numPlat;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCalle() {
        return calle;
    }

    public int getNumeroCalle() {
        return numeroCalle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public int getCantVias() {
        return cantVias;
    }

    public int getCantPlataformas() {
        return cantPlataformas;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumeroCalle(int numeroCalle) {
        this.numeroCalle = numeroCalle;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setCantVias(int cantVias) {
        this.cantVias = cantVias;
    }

    public void setCantPlataformas(int cantPlataformas) {
        this.cantPlataformas = cantPlataformas;
    }

    @Override
    public String toString() {
        return "Estacion{" + "nombre=" + nombre + ", calle=" + calle + ", numeroCalle=" + numeroCalle + ", ciudad=" + ciudad + ", codigoPostal=" + codigoPostal + ", cantVias=" + cantVias + ", cantPlataformas=" + cantPlataformas + '}';
    }
    
}
