/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject4.view;

import com.mycompany.mavenproject4.controller.HuffmanController;
import com.mycompany.mavenproject4.model.NodoHuffman;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class VentanaHuffman extends JFrame {

    private JTextArea txtEntrada;
    private JTextArea txtCodigos;
    private JButton btnConstruir;
    private JScrollPane scrollDibujo;
    private PanelDibujoHuffman panelDibujo;

    private HuffmanController controller = new HuffmanController();

    private static final Color BUTTON_COLOR = new Color(70, 130, 180); // azul
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR2 = Color.BLACK;
    private static final Color NODE_COLOR = new Color(70, 130, 180); // azul nodos
    private static final Font ARIAL_BLACK = new Font("Arial Black", Font.PLAIN, 12);

    public VentanaHuffman() {
        setTitle("Árbol de Huffman - Aplicación Java Swing");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.DARK_GRAY);

        JLabel lblEntrada = createStyledLabel("Texto:");
        lblEntrada.setBounds(20, 20, 100, 25);
        panel.add(lblEntrada);

        txtEntrada = createStyledTextArea();
        JScrollPane scrollEntrada = new JScrollPane(txtEntrada);
        scrollEntrada.setBounds(20, 50, 350, 80);
        panel.add(scrollEntrada);

        btnConstruir = createStyledButton("Construir Árbol");
        btnConstruir.setBounds(400, 50, 150, 30);
        panel.add(btnConstruir);

        JLabel lblCodigos = createStyledLabel("Códigos generados:");
        lblCodigos.setBounds(20, 140, 200, 25);
        panel.add(lblCodigos);

        txtCodigos = createStyledTextArea();
        txtCodigos.setEditable(false);
        JScrollPane scrollCodigos = new JScrollPane(txtCodigos);
        scrollCodigos.setBounds(20, 170, 350, 100);
        panel.add(scrollCodigos);

        panelDibujo = new PanelDibujoHuffman();
        scrollDibujo = new JScrollPane(panelDibujo);
        scrollDibujo.setBounds(20, 280, 740, 300);
        panel.add(scrollDibujo);

        add(panel);

        btnConstruir.addActionListener(e -> construirArbol());
    }

    private void construirArbol() {
        String texto = txtEntrada.getText().trim();
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un texto para construir el árbol.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        controller.construir(texto);
        Map<Character, String> codigos = controller.getCodigos();

        StringBuilder resultado = new StringBuilder();
        for (Map.Entry<Character, String> entry : codigos.entrySet()) {
            resultado.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        txtCodigos.setText(resultado.toString());
        panelDibujo.setRaiz(controller.getRaiz());
        panelDibujo.adjustSize();
    }

    private JTextArea createStyledTextArea() {
        JTextArea txt = new JTextArea();
        txt.setFont(ARIAL_BLACK);
        txt.setForeground(TEXT_COLOR2);
        txt.setBackground(Color.WHITE);
        txt.setCaretColor(TEXT_COLOR2);
        txt.setLineWrap(true);
        txt.setWrapStyleWord(true);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaHuffman().setVisible(true));
    }

    private static class PanelDibujoHuffman extends JPanel {
        private NodoHuffman raiz;
        private int profundidad = 0;

        public void setRaiz(NodoHuffman raiz) {
            this.raiz = raiz;
            this.profundidad = calcularProfundidad(raiz);
            repaint();
        }

        public void adjustSize() {
            int altura = profundidad * 80 + 100;
            int anchura = calcularAnchura(raiz) * 60 + 100;
            setPreferredSize(new Dimension(Math.max(anchura, 800), altura));
            revalidate();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (raiz != null) dibujarNodo(g, raiz, getWidth() / 2, 30, getWidth() / 4);
        }

        private void dibujarNodo(Graphics g, NodoHuffman nodo, int x, int y, int separacion) {
            int radio = 30;
            int childY = y + 80;
            String texto = nodo.esHoja() ? String.valueOf(nodo.caracter) : String.valueOf(nodo.frecuencia);

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

            g.setColor(NODE_COLOR);
            g.fillOval(x - radio, y - radio, radio * 2, radio * 2);
            g.setColor(Color.BLACK);
            g.drawOval(x - radio, y - radio, radio * 2, radio * 2);
            g.setColor(Color.WHITE);
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(texto);
            int textHeight = fm.getAscent();
            g.drawString(texto, x - textWidth / 2, y + textHeight / 4);
        }

        private int calcularProfundidad(NodoHuffman nodo) {
            if (nodo == null) return 0;
            int izq = calcularProfundidad(nodo.izquierdo);
            int der = calcularProfundidad(nodo.derecho);
            return 1 + Math.max(izq, der);
        }

        private int calcularAnchura(NodoHuffman nodo) {
            if (nodo == null) return 0;
            if (nodo.esHoja()) return 1;
            return calcularAnchura(nodo.izquierdo) + calcularAnchura(nodo.derecho);
        }
    }
}