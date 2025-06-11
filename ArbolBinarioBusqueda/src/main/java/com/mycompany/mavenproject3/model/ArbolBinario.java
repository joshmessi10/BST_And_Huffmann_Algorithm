/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject3.model;

/**
 *
 * @author JL
 */
import java.util.*;

// Clase genérica que representa un Árbol Binario de Búsqueda
public class ArbolBinario<T extends Comparable<T>> {
    public Nodo<T> raiz; // Nodo raíz del árbol

    // Método público para insertar un dato en el árbol
    public void insertar(T dato) {
        raiz = insertarRecursivo(raiz, dato); // Llama al método recursivo de inserción
    }

    // Inserta un nuevo nodo en el árbol de forma recursiva
    private Nodo<T> insertarRecursivo(Nodo<T> nodo, T dato) {
        if (nodo == null) {
            return new Nodo<>(dato); // Crea un nuevo nodo si se llegó a un espacio vacío
        }

        // Compara el dato con el nodo actual y decide si va a la izquierda o derecha
        if (dato.compareTo(nodo.dato) < 0) {
            nodo.izquierdo = insertarRecursivo(nodo.izquierdo, dato);
        } else {
            nodo.derecho = insertarRecursivo(nodo.derecho, dato);
        }

        return nodo;
    }

    // Retorna una lista con los elementos del árbol en recorrido InOrden
    public List<T> inOrden() {
        List<T> resultado = new ArrayList<>();
        inOrdenRecursivo(raiz, resultado);
        return resultado;
    }

    // Recorre el árbol en orden (izquierda - raíz - derecha)
    private void inOrdenRecursivo(Nodo<T> nodo, List<T> lista) {
        if (nodo != null) {
            inOrdenRecursivo(nodo.izquierdo, lista);
            lista.add(nodo.dato);
            inOrdenRecursivo(nodo.derecho, lista);
        }
    }

    // Retorna una lista con los elementos del árbol en recorrido PreOrden
    public List<T> preOrden() {
        List<T> resultado = new ArrayList<>();
        preOrdenRecursivo(raiz, resultado);
        return resultado;
    }

    // Recorre el árbol en preorden (raíz - izquierda - derecha)
    private void preOrdenRecursivo(Nodo<T> nodo, List<T> lista) {
        if (nodo != null) {
            lista.add(nodo.dato);
            preOrdenRecursivo(nodo.izquierdo, lista);
            preOrdenRecursivo(nodo.derecho, lista);
        }
    }

    // Retorna una lista con los elementos del árbol en recorrido PostOrden
    public List<T> postOrden() {
        List<T> resultado = new ArrayList<>();
        postOrdenRecursivo(raiz, resultado);
        return resultado;
    }

    // Recorre el árbol en postorden (izquierda - derecha - raíz)
    private void postOrdenRecursivo(Nodo<T> nodo, List<T> lista) {
        if (nodo != null) {
            postOrdenRecursivo(nodo.izquierdo, lista);
            postOrdenRecursivo(nodo.derecho, lista);
            lista.add(nodo.dato);
        }
    }

    // Calcula y retorna la altura del árbol (número de niveles)
    public int altura() {
        return alturaRecursivo(raiz);
    }

    // Calcula la altura de manera recursiva
    private int alturaRecursivo(Nodo<T> nodo) {
        if (nodo == null)
            return 0;

        int izquierda = alturaRecursivo(nodo.izquierdo);
        int derecha = alturaRecursivo(nodo.derecho);

        return 1 + Math.max(izquierda, derecha); // Altura = 1 + máximo de sus subárboles
    }

    // Devuelve el nivel (profundidad) de un nodo con cierto dato
    public int nivel(T dato) {
        return nivelRecursivo(raiz, dato, 1); // El nivel inicial es 1 (raíz)
    }

    // Busca el nivel de un nodo con un valor específico de forma recursiva
    private int nivelRecursivo(Nodo<T> nodo, T dato, int nivel) {
        if (nodo == null) return 0;
        if (nodo.dato.equals(dato)) return nivel;

        // Buscar en el subárbol izquierdo
        int abajo = nivelRecursivo(nodo.izquierdo, dato, nivel + 1);
        if (abajo != 0) return abajo;

        // Buscar en el subárbol derecho
        return nivelRecursivo(nodo.derecho, dato, nivel + 1);
    }

    // Calcula el grado del árbol (el mayor número de hijos entre todos los nodos)
    public int grado() {
        return gradoRecursivo(raiz);
    }

    // Determina el grado máximo de los nodos de manera recursiva
    private int gradoRecursivo(Nodo<T> nodo) {
        if (nodo == null) return 0;

        int hijos = 0;
        if (nodo.izquierdo != null) hijos++;
        if (nodo.derecho != null) hijos++;

        // Compara el número de hijos del nodo actual con el máximo de los subárboles
        return Math.max(hijos, Math.max(gradoRecursivo(nodo.izquierdo), gradoRecursivo(nodo.derecho)));
    }

    // Realiza una búsqueda por amplitud (también conocida como BFS)
    public List<T> busquedaAmplitud() {
        List<T> resultado = new ArrayList<>();
        if (raiz == null) return resultado;

        Queue<Nodo<T>> cola = new LinkedList<>();
        cola.add(raiz); // Comienza por la raíz

        while (!cola.isEmpty()) {
            Nodo<T> actual = cola.poll(); // Extrae el primer nodo de la cola
            resultado.add(actual.dato); // Agrega su valor al resultado

            // Agrega sus hijos a la cola
            if (actual.izquierdo != null) cola.add(actual.izquierdo);
            if (actual.derecho != null) cola.add(actual.derecho);
        }

        return resultado;
    }
    
    // Busca si un valor está en el árbol (búsqueda binaria)
    public boolean contiene(T dato) {
        return contieneRecursivo(raiz, dato);
    }

    private boolean contieneRecursivo(Nodo<T> nodo, T dato) {
        if (nodo == null) return false;
        if (dato.equals(nodo.dato)) return true;

        // Búsqueda binaria: decide si ir a izquierda o derecha
        return dato.compareTo(nodo.dato) < 0
                ? contieneRecursivo(nodo.izquierdo, dato)
                : contieneRecursivo(nodo.derecho, dato);
    }
    
    // Elimina un nodo del árbol (búsqueda binaria)
    public void eliminar(T dato) {
        raiz = eliminarRecursivo(raiz, dato);
    }

    private Nodo<T> eliminarRecursivo(Nodo<T> nodo, T dato) {
        if (nodo == null) return null;

        if (dato.compareTo(nodo.dato) < 0) {
            nodo.izquierdo = eliminarRecursivo(nodo.izquierdo, dato);
        } else if (dato.compareTo(nodo.dato) > 0) {
            nodo.derecho = eliminarRecursivo(nodo.derecho, dato);
        } else {
            // Nodo encontrado
            if (nodo.izquierdo == null) return nodo.derecho;
            if (nodo.derecho == null) return nodo.izquierdo;

            // Reemplazar por el menor del subárbol derecho
            nodo.dato = encontrarMinimo(nodo.derecho).dato;
            nodo.derecho = eliminarRecursivo(nodo.derecho, nodo.dato);
        }

        return nodo;
    }

    private Nodo<T> encontrarMinimo(Nodo<T> nodo) {
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }
        return nodo;
    }

    
    
}
