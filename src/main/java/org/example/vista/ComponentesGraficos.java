package org.example.vista;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class ComponentesGraficos {

    public static final Color FONDO = new Color(0xF5F0E6);
    public static final Color TARJETA = new Color(0xFBF8F1);
    public static final Color VERDE_OSCURO = new Color(0x12302B);
    public static final Color VERDE_HOVER = new Color(0x1F4A42);
    public static final Color DORADO = new Color(0x8A6A3B);
    public static final Color BORDE = new Color(0xD9CDB4);
    public static final Color FILA_SELECCIONADA = new Color(0xEFE4D0);
    public static final Color ROJO = new Color(0xA63A32);
    public static final Color VERDE_EXITO = new Color(0x2E7D52);

    public static final Font FUENTE_TITULO = new Font("Georgia", Font.PLAIN, 34);
    public static final Font FUENTE_TEXTO = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FUENTE_NEGRITA = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FUENTE_ETIQUETA = new Font("Segoe UI", Font.BOLD, 11);

    public static JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto.toUpperCase());
        etiqueta.setFont(FUENTE_ETIQUETA);
        etiqueta.setForeground(DORADO);
        return etiqueta;
    }

    public static JTextField crearCampo() {
        JTextField campo = new JTextField();
        campo.setFont(FUENTE_TEXTO);
        campo.setForeground(VERDE_OSCURO);
        campo.setCaretColor(VERDE_OSCURO);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE),
                BorderFactory.createEmptyBorder(9, 12, 9, 12)));
        return campo;
    }

    public static JButton crearBoton(String texto, boolean principal) {
        return new BotonRedondeado(texto, principal);
    }

    public static void estilizarTabla(JTable tabla) {
        tabla.setFont(FUENTE_TEXTO);
        tabla.setForeground(VERDE_OSCURO);
        tabla.setRowHeight(34);
        tabla.setShowVerticalLines(false);
        tabla.setGridColor(BORDE);
        tabla.setSelectionBackground(FILA_SELECCIONADA);
        tabla.setSelectionForeground(VERDE_OSCURO);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tabla.setDefaultRenderer(Object.class, new RenderizadorConMargen());

        RenderizadorConMargen cabecera = new RenderizadorConMargen();
        cabecera.setBackground(VERDE_OSCURO);
        cabecera.setForeground(Color.WHITE);
        cabecera.setFont(FUENTE_NEGRITA);
        tabla.getTableHeader().setDefaultRenderer(cabecera);
        tabla.getTableHeader().setPreferredSize(new Dimension(0, 38));
        tabla.getTableHeader().setReorderingAllowed(false);
    }

    private static class RenderizadorConMargen extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable tabla, Object valor,
                boolean seleccionada, boolean conFoco, int fila, int columna) {
            super.getTableCellRendererComponent(tabla, valor, seleccionada, false, fila, columna);
            setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
            return this;
        }
    }

    private static class BotonRedondeado extends JButton {
        private final boolean principal;

        BotonRedondeado(String texto, boolean principal) {
            super(texto);
            this.principal = principal;
            setFont(FUENTE_NEGRITA);
            setForeground(principal ? Color.WHITE : VERDE_OSCURO);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setOpaque(false);
            setRolloverEnabled(true);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setBorder(BorderFactory.createEmptyBorder(11, 22, 11, 22));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Color relleno;
            if (principal) {
                relleno = getModel().isRollover() ? VERDE_HOVER : VERDE_OSCURO;
            } else {
                relleno = getModel().isRollover() ? FILA_SELECCIONADA : TARJETA;
            }
            if (getModel().isPressed()) {
                relleno = relleno.darker();
            }

            int ancho = getWidth() - 1;
            int alto = getHeight() - 1;
            g2.setColor(relleno);
            g2.fillRoundRect(0, 0, ancho, alto, alto, alto);
            if (!principal) {
                g2.setColor(VERDE_OSCURO);
                g2.drawRoundRect(0, 0, ancho, alto, alto, alto);
            }
            g2.dispose();

            super.paintComponent(g);
        }
    }
}
