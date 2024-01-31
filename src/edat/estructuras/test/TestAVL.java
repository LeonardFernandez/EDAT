/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edat.estructuras.test;

import edat.estructuras.conjuntistas.Diccionario;

/**
 *
 * @author Leo
 */
public class TestAVL {
    public static void main(String[] args) {
        Diccionario diccionario = new Diccionario();
        diccionario.insertar("75", null);
        diccionario.insertar("20", null);
        diccionario.insertar("80", null);
        diccionario.insertar("77", null);
        diccionario.insertar("93", null);
        diccionario.insertar("15", null);
        diccionario.insertar("18", null);
        diccionario.insertar("13",null);
        diccionario.insertar("78", null);
        diccionario.insertar("14", null);
        diccionario.insertar("25", null);
        diccionario.insertar("16", null);
        System.out.println(diccionario.toString());
        diccionario.eliminar("93");
        //diccionario.eliminar("93");
        System.out.println(diccionario.toString());
    }
}
