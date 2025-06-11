/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject3.controller;

/**
 *
 * @author JL
 */

import com.mycompany.mavenproject3.model.ArbolBinario;

// Controlador del árbol binario: se comunica con el modelo (ArbolBinario)
public class ArbolController<T extends Comparable<T>> {

    // Instancia del árbol binario
    public ArbolBinario<T> arbol = new ArbolBinario<>();

    // Inserta un dato en el árbol
    public void insertar(T dato) {
        arbol.insertar(dato);
    }

    // Devuelve el recorrido InOrden como texto
    public String inOrden() {
        return arbol.inOrden().toString();
    }

    // Devuelve el recorrido PreOrden como texto
    public String preOrden() {
        return arbol.preOrden().toString();
    }

    // Devuelve el recorrido PostOrden como texto
    public String postOrden() {
        return arbol.postOrden().toString();
    }

    // Devuelve la altura del árbol
    public int altura() {
        return arbol.altura();
    }

    // Devuelve el nivel donde se encuentra un dato específico
    public int nivel(T dato) {
        return arbol.nivel(dato);
    }

    // Devuelve el grado del árbol
    public int grado() {
        return arbol.grado();
    }

    // Devuelve el recorrido en amplitud como texto
    public String amplitud() {
        return arbol.busquedaAmplitud().toString();
    }
    
    // Elimina un nodo
    public void eliminar(T dato) {
        arbol.eliminar(dato);
    }

    // Verifica si el árbol contiene un dato
    public boolean contiene(T dato) {
        return arbol.contiene(dato);
    }

}
