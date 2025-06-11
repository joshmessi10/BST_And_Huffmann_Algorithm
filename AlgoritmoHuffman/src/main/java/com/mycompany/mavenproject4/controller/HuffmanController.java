/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject4.controller;

import com.mycompany.mavenproject4.model.ArbolHuffman;
import com.mycompany.mavenproject4.model.NodoHuffman;
import java.util.Map;

/**
 *
 * @author JL
 */
public class HuffmanController {
    private ArbolHuffman arbol = new ArbolHuffman(); // Árbol de Huffman que gestiona los datos

    // Construye el árbol desde un texto dado
    public void construir(String texto) {
        arbol.construirDesdeTexto(texto);
    }

    // Retorna la raíz del árbol (para visualización o análisis)
    public NodoHuffman getRaiz() {
        return arbol.raiz;
    }

    // Retorna el mapa de códigos binarios por carácter
    public Map<Character, String> getCodigos() {
        return arbol.getCodigos();
    }
}