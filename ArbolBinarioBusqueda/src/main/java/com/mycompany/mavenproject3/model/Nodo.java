/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject3.model;

/**
 *
 * @author JL
 */
// Clase Nodo para un árbol binario
public class Nodo<T> {
    // Dato que guarda el nodo (puede ser número o letra)
    public T dato;
    // Referencia al hijo izquierdo
    public Nodo<T> izquierdo;
    // Referencia al hijo derecho
    public Nodo<T> derecho;
    // Constructor: crea un nodo con el dato dado y sin hijos
    public Nodo(T dato) {
        this.dato = dato;
        izquierdo = null;
        derecho = null;
    }
}
