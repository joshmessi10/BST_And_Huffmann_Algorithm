/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject3.view;

/**
 *
 * @author JL
 */
import com.mycompany.mavenproject3.controller.ArbolController;
import com.mycompany.mavenproject3.model.Nodo;

import javax.swing.*;
import java.awt.*;

public class VentanaArbol extends JFrame {
    private JTextField txtDato;
    private JTextArea txtResultado;
    private JComboBox<String> comboTipo;
    private JButton btnInsertar, btnInOrden, btnPreOrden, btnPostOrden, btnNivel, btnAmplitud, btnAltura, btnGrado, btnBuscar, btnEliminar;
    private PanelDibujoArbol<Integer> panelDibujoInt = new PanelDibujoArbol<>();
    private PanelDibujoArbol<String> panelDibujoStr = new PanelDibujoArbol<>();
    private boolean esNumero = true;

    private ArbolController<Integer> controllerInt = new ArbolController<>();
    private ArbolController<String> controllerStr = new ArbolController<>();

    private static final Color BUTTON_COLOR = new Color(70, 130, 180); // azul
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR2 = Color.BLACK;
    private static final Font ARIAL_BLACK = new Font("Arial Black", Font.PLAIN, 12);

    public VentanaArbol() {
        setTitle("Árbol Binario - Aplicación Java Swing");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.DARK_GRAY);

        JLabel lblDato = createStyledLabel("Dato:");
        lblDato.setBounds(20, 20, 100, 25);
        panel.add(lblDato);

        txtDato = createStyledTextField();
        txtDato.setBounds(120, 20, 100, 25);
        panel.add(txtDato);

        comboTipo = new JComboBox<>(new String[]{"Números", "Letras"});
        comboTipo.setBounds(230, 20, 120, 25);
        comboTipo.setFont(ARIAL_BLACK);
        comboTipo.setForeground(TEXT_COLOR2);
        panel.add(comboTipo);

        btnInsertar = createStyledButton("Insertar");
        btnInsertar.setBounds(360, 20, 120, 25);
        panel.add(btnInsertar);
        
        btnBuscar = createStyledButton("Buscar");
        btnBuscar.setBounds(500, 20, 120, 25);
        panel.add(btnBuscar);

        btnEliminar = createStyledButton("Eliminar");
        btnEliminar.setBounds(630, 20, 120, 25);
        panel.add(btnEliminar);

        btnInOrden = createStyledButton("In-Orden");
        btnInOrden.setBounds(20, 60, 120, 25);
        panel.add(btnInOrden);

        btnPreOrden = createStyledButton("Pre-Orden");
        btnPreOrden.setBounds(160, 60, 120, 25);
        panel.add(btnPreOrden);

        btnPostOrden = createStyledButton("Post-Orden");
        btnPostOrden.setBounds(300, 60, 120, 25);
        panel.add(btnPostOrden);

        

        btnAltura = createStyledButton("Altura");
        btnAltura.setBounds(20, 100, 100, 25);
        panel.add(btnAltura);

        btnGrado = createStyledButton("Grado");
        btnGrado.setBounds(130, 100, 100, 25);
        panel.add(btnGrado);
        
        btnNivel = createStyledButton("Nivel Nodo");
        btnNivel.setBounds(240, 100, 130, 25);
        panel.add(btnNivel);

        btnAmplitud = createStyledButton("Amplitud");
        btnAmplitud.setBounds(380, 100, 130, 25);
        panel.add(btnAmplitud);

        txtResultado = new JTextArea();
        txtResultado.setFont(ARIAL_BLACK);
        txtResultado.setEditable(false);
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        txtResultado.setForeground(TEXT_COLOR2);
        JScrollPane scroll = new JScrollPane(txtResultado);
        scroll.setBounds(20, 140, 740, 100);
        panel.add(scroll);

        panelDibujoInt.setBounds(20, 250, 740, 400);
        panelDibujoStr.setBounds(20, 250, 740, 400);
        panel.add(panelDibujoInt);
        panel.add(panelDibujoStr);
        panelDibujoStr.setVisible(false);

        add(panel);

        comboTipo.addActionListener(e -> {
            esNumero = comboTipo.getSelectedIndex() == 0;
            txtResultado.setText("Modo: " + (esNumero ? "Números" : "Letras"));
            panelDibujoInt.setVisible(esNumero);
            panelDibujoStr.setVisible(!esNumero);
            repaint();
        });

        btnInsertar.addActionListener(e -> insertarDato());
        btnInOrden.addActionListener(e -> mostrarResultado("inorden"));
        btnPreOrden.addActionListener(e -> mostrarResultado("preorden"));
        btnPostOrden.addActionListener(e -> mostrarResultado("postorden"));
        btnAltura.addActionListener(e -> mostrarResultado("altura"));
        btnGrado.addActionListener(e -> mostrarResultado("grado"));
        btnAmplitud.addActionListener(e -> mostrarResultado("amplitud"));
        btnNivel.addActionListener(e -> calcularNivel());
        btnBuscar.addActionListener(e -> buscarNodo());
        btnEliminar.addActionListener(e -> eliminarNodo());
    }

    private void insertarDato() {
        String valor = txtDato.getText().trim();
        if (valor.isEmpty()) {
            txtResultado.setText("Por favor ingresa un dato.");
            return;
        }
        try {
            if (esNumero) {
                Integer numero = Integer.parseInt(valor);
                controllerInt.insertar(numero);
                panelDibujoInt.setRaiz(controllerInt.arbol.raiz);
            } else {
                if (valor.length() != 1) throw new Exception("Solo se permite una letra.");
                controllerStr.insertar(valor.toUpperCase());
                panelDibujoStr.setRaiz(controllerStr.arbol.raiz);
            }
            txtResultado.setText("Dato insertado: " + valor);
            txtDato.setText("");
        } catch (Exception ex) {
            txtResultado.setText("Error: " + ex.getMessage());
        }
    }
    
    private void buscarNodo() {
        String valor = JOptionPane.showInputDialog(this, "Ingrese el valor a buscar:");
        if (valor == null || valor.trim().isEmpty()) {
            txtResultado.setText("Búsqueda cancelada.");
            return;
        }

        try {
            boolean encontrado = esNumero
                ? controllerInt.contiene(Integer.parseInt(valor.trim()))
                : controllerStr.contiene(valor.trim().toUpperCase());

            txtResultado.setText(encontrado ? "Nodo encontrado por Búsqueda Binaria: " + valor : "Nodo no encontrado.");
        } catch (Exception ex) {
            txtResultado.setText("Error en búsqueda: " + ex.getMessage());
        }
    }

    private void eliminarNodo() {
        String valor = JOptionPane.showInputDialog(this, "Ingrese el valor a eliminar:");
        if (valor == null || valor.trim().isEmpty()) {
            txtResultado.setText("Eliminación cancelada.");
            return;
        }

        try {
            if (esNumero) {
                controllerInt.eliminar(Integer.parseInt(valor.trim()));
                panelDibujoInt.setRaiz(controllerInt.arbol.raiz);
            } else {
                controllerStr.eliminar(valor.trim().toUpperCase());
                panelDibujoStr.setRaiz(controllerStr.arbol.raiz);
            }

            txtResultado.setText("Nodo eliminado: " + valor);
            repaint();
        } catch (Exception ex) {
            txtResultado.setText("Error al eliminar: " + ex.getMessage());
        }
    }
    
    private void mostrarResultado(String tipo) {
        String resultado = switch (tipo) {
            case "inorden" -> "Recorrido In-Orden: " + (esNumero ? controllerInt.inOrden() : controllerStr.inOrden());
            case "preorden" -> "Recorrido Pre-Orden: " + (esNumero ? controllerInt.preOrden() : controllerStr.preOrden());
            case "postorden" -> "Recorrido Post-Orden: " + (esNumero ? controllerInt.postOrden() : controllerStr.postOrden());
            case "altura" -> "Altura del árbol: " + (esNumero ? controllerInt.altura() : controllerStr.altura());
            case "grado" -> "Grado del árbol: " + (esNumero ? controllerInt.grado() : controllerStr.grado());
            case "amplitud" -> "Recorrido por búsqueda por amplitud: " + (esNumero ? controllerInt.amplitud() : controllerStr.amplitud());
            default -> "";
        };
        txtResultado.setText(resultado);
    }

    private void calcularNivel() {
        String valor = JOptionPane.showInputDialog(this, "Ingrese el valor del nodo:");
        if (valor == null || valor.trim().isEmpty()) return;
        try {
            int nivel = esNumero ? controllerInt.nivel(Integer.parseInt(valor.trim())) : controllerStr.nivel(valor.toUpperCase());
            txtResultado.setText(nivel == 0 ? "Nodo no encontrado." : "Nivel del nodo '" + valor + "': " + nivel);
        } catch (Exception e) {
            txtResultado.setText("Error al calcular nivel: " + e.getMessage());
        }
    }

    private JTextField createStyledTextField() {
        JTextField txt = new JTextField();
        txt.setFont(ARIAL_BLACK);
        txt.setForeground(TEXT_COLOR2);
        txt.setBackground(Color.WHITE);
        txt.setCaretColor(TEXT_COLOR2);
        return txt;
    }

    private JButton createStyledButton(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(ARIAL_BLACK);
        btn.setForeground(TEXT_COLOR);
        btn.setBackground(BUTTON_COLOR);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        return btn;
    }

    private JLabel createStyledLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(ARIAL_BLACK);
        lbl.setForeground(TEXT_COLOR);
        return lbl;
    }

    private static class PanelDibujoArbol<T> extends JPanel {
        private Nodo<T> raiz;

        public void setRaiz(Nodo<T> raiz) {
            this.raiz = raiz;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (raiz != null) dibujarNodo(g, raiz, getWidth() / 2, 50, getWidth() / 4);
        }

        private void dibujarNodo(Graphics g, Nodo<T> nodo, int x, int y, int separacion) {
            int radio = 30;
            int childY = y + 80;

            g.setColor(Color.BLACK);
            if (nodo.izquierdo != null) {
                int childX = x - separacion;
                g.drawLine(x, y + radio, childX, childY - radio);
                dibujarNodo(g, nodo.izquierdo, childX, childY, separacion / 2);
            }
            if (nodo.derecho != null) {
                int childX = x + separacion;
                g.drawLine(x, y + radio, childX, childY - radio);
                dibujarNodo(g, nodo.derecho, childX, childY, separacion / 2);
            }

            g.setColor(new Color(70, 130, 180)); // azul
            g.fillOval(x - radio, y - radio, radio * 2, radio * 2);
            g.setColor(Color.BLACK);
            g.drawOval(x - radio, y - radio, radio * 2, radio * 2);
            g.setColor(Color.WHITE);
            String texto = nodo.dato.toString();
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(texto);
            int textHeight = fm.getAscent();
            g.drawString(texto, x - textWidth / 2, y + textHeight / 4);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaArbol().setVisible(true));
    }
}

