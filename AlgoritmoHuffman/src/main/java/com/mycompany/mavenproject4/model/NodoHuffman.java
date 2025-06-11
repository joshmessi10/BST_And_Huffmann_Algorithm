/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject4.model;

/**
 *
 * @author JL
 */
public class NodoHuffman implements Comparable<NodoHuffman> {
    public char caracter;                  // Carácter que representa (solo en nodos hoja)
    public int frecuencia;                 // Frecuencia del carácter o suma de frecuencias (en nodos internos)
    public NodoHuffman izquierdo, derecho; // Referencias a los hijos izquierdo y derecho

    // Constructor: inicializa un nodo con un carácter y su frecuencia
    public NodoHuffman(char caracter, int frecuencia) {
        this.caracter = caracter;
        this.frecuencia = frecuencia;
    }

    // Determina si es una hoja (no tiene hijos)
    public boolean esHoja() {
        return izquierdo == null && derecho == null;
    }

    // Implementa comparación por frecuencia (para la cola de prioridad)
    @Override
    public int compareTo(NodoHuffman o) {
        return Integer.compare(this.frecuencia, o.frecuencia);
    }
}