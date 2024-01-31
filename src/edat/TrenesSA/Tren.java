/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edat.TrenesSA;

/**
 *
 * @author Leo
 */
public class Tren {
    private int id;
    private String propulsion;
    private int cantVagonesPasaj;
    private int cantVagonesCarga;
    private String linea;

    public Tren(int id, String propulsion, int cantVagonesPasaj, int cantVagonesCarga, String linea) {
        this.id = id;
        this.propulsion = propulsion;
        this.cantVagonesPasaj = cantVagonesPasaj;
        this.cantVagonesCarga = cantVagonesCarga;
        this.linea = linea;
    }

    public int getId() {
        return id;
    }

    public String getPropulsion() {
        return propulsion;
    }

    public int getCantVagonesPasaj() {
        return cantVagonesPasaj;
    }

    public int getCantVagonesCarga() {
        return cantVagonesCarga;
    }

    public String getLinea() {
        return linea;
    }

    public void setPropulsion(String propulsion) {
        this.propulsion = propulsion;
    }

    public void setCantVagonesPasaj(int cantVagonesPasaj) {
        this.cantVagonesPasaj = cantVagonesPasaj;
    }

    public void setCantVagonesCarga(int cantVagonesCarga) {
        this.cantVagonesCarga = cantVagonesCarga;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }
    
    
}
