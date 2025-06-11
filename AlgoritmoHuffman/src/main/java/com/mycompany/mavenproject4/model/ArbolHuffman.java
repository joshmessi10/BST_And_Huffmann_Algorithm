/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject4.model;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author JL
 */
public class ArbolHuffman {
    public NodoHuffman raiz;                      // Nodo raíz del árbol de Huffman
    private Map<Character, String> codigos = new HashMap<>(); // Mapa que asocia cada carácter con su código binario

    // Construye el árbol de Huffman a partir de un texto
    public void construirDesdeTexto(String texto) {
        // Paso 1: calcular frecuencias de cada carácter
        Map<Character, Integer> frecuencias = new HashMap<>();
        for (char c : texto.toCharArray()) {
            frecuencias.put(c, frecuencias.getOrDefault(c, 0) + 1);
        }

        // Paso 2: crear cola de prioridad con nodos hoja
        PriorityQueue<NodoHuffman> cola = new PriorityQueue<>();
        for (var entry : frecuencias.entrySet()) {
            cola.add(new NodoHuffman(entry.getKey(), entry.getValue()));
        }

        // Paso 3: construir el árbol de Huffman combinando los nodos
        while (cola.size() > 1) {
            NodoHuffman n1 = cola.poll(); // nodo con menor frecuencia
            NodoHuffman n2 = cola.poll(); // siguiente menor

            // Crear nodo padre sin carácter ('\0'), con frecuencia combinada
            NodoHuffman padre = new NodoHuffman('\0', n1.frecuencia + n2.frecuencia);
            padre.izquierdo = n1;
            padre.derecho = n2;

            cola.add(padre); // volver a insertar el nodo combinado
        }

        raiz = cola.poll(); // único nodo restante es la raíz
        generarCodigos(raiz, ""); // paso final: asignar los códigos binarios
    }

    // Recorre el árbol y genera los códigos de cada carácter
    private void generarCodigos(NodoHuffman nodo, String codigo) {
        if (nodo == null) return;

        // Si es hoja, asignar el código acumulado
        if (nodo.esHoja()) codigos.put(nodo.caracter, codigo);

        generarCodigos(nodo.izquierdo, codigo + "0");
        generarCodigos(nodo.derecho, codigo + "1");
    }

    // Devuelve el mapa de códigos de Huffman
    public Map<Character, String> getCodigos() {
        return codigos;
    }
}