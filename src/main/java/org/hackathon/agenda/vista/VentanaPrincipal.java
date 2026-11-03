package org.hackathon.agenda.vista;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    private final JTextField txtNombre = ComponentesGraficos.crearCampo();
    private final JTextField txtApellido = ComponentesGraficos.crearCampo();
    private final JTextField txtTelefono = ComponentesGraficos.crearCampo();

    private final JButton btnAnadir = ComponentesGraficos.crearBoton("Añadir contacto", true);
    private final JButton btnModificar = ComponentesGraficos.crearBoton("Modificar teléfono", false);
    private final JButton btnEliminar = ComponentesGraficos.crearBoton("Eliminar", false);
    private final JButton btnBuscar = ComponentesGraficos.crearBoton("Buscar", false);
    private final JButton btnLimpiar = ComponentesGraficos.crearBoton("Limpiar", false);

    private final DefaultTableModel modeloTabla = new DefaultTableModel(
            new String[]{"Nombre", "Apellido", "Teléfono"}, 0) {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    private final JTable tabla = new JTable(modeloTabla);

    private final JLabel lblEspacios = new JLabel();
    private final JLabel lblEstado = new JLabel();

    public VentanaPrincipal() {
        super("Agenda telefónica");
        txtTelefono.setToolTipText("Ejemplos: 600000000 o +34 600 000 000");
        ComponentesGraficos.estilizarTabla(tabla);
        configurarSeleccionDeFila();

        JPanel contenido = new JPanel(new BorderLayout(0, 20));
        contenido.setBackground(ComponentesGraficos.FONDO);
        contenido.setBorder(BorderFactory.createEmptyBorder(28, 36, 28, 36));
        contenido.add(construirEncabezado(), BorderLayout.NORTH);
        contenido.add(construirZonaCentral(), BorderLayout.CENTER);
        contenido.add(construirPanelEstado(), BorderLayout.SOUTH);

        setContentPane(contenido);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(920, 700);
        setMinimumSize(new Dimension(820, 620));
        setLocationRelativeTo(null);

        mostrarMensajeInicial();
    }

    private JPanel construirEncabezado() {
        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));

        JLabel subtitulo = ComponentesGraficos.crearEtiqueta("Tu libreta de contactos");
        JLabel titulo = new JLabel("Agenda telefónica");
        titulo.setFont(ComponentesGraficos.FUENTE_TITULO);
        titulo.setForeground(ComponentesGraficos.VERDE_OSCURO);
        textos.add(subtitulo);
        textos.add(Box.createVerticalStrut(4));
        textos.add(titulo);

        lblEspacios.setFont(ComponentesGraficos.FUENTE_NEGRITA);
        lblEspacios.setForeground(ComponentesGraficos.DORADO);

        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setOpaque(false);
        encabezado.add(textos, BorderLayout.WEST);
        encabezado.add(lblEspacios, BorderLayout.EAST);
        return encabezado;
    }

    private JPanel construirZonaCentral() {
        JPanel zona = new JPanel(new BorderLayout(0, 20));
        zona.setOpaque(false);
        zona.add(construirFormulario(), BorderLayout.NORTH);
        zona.add(construirTabla(), BorderLayout.CENTER);
        return zona;
    }

    private JPanel construirFormulario() {
        JPanel campos = new JPanel(new GridLayout(1, 3, 16, 0));
        campos.setOpaque(false);
        campos.add(campoConEtiqueta("Nombre", txtNombre));
        campos.add(campoConEtiqueta("Apellido", txtApellido));
        campos.add(campoConEtiqueta("Teléfono", txtTelefono));

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        botones.setOpaque(false);
        botones.add(btnAnadir);
        botones.add(btnModificar);
        botones.add(btnEliminar);
        botones.add(btnBuscar);
        botones.add(btnLimpiar);

        JPanel formulario = new JPanel(new BorderLayout(0, 16));
        formulario.setOpaque(false);
        formulario.add(campos, BorderLayout.NORTH);
        formulario.add(botones, BorderLayout.CENTER);
        return formulario;
    }

    private JPanel campoConEtiqueta(String texto, JTextField campo) {
        JPanel panel = new JPanel(new BorderLayout(0, 6));
        panel.setOpaque(false);
        panel.add(ComponentesGraficos.crearEtiqueta(texto), BorderLayout.NORTH);
        panel.add(campo, BorderLayout.CENTER);
        return panel;
    }

    private JScrollPane construirTabla() {
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createLineBorder(ComponentesGraficos.BORDE));
        scroll.getViewport().setBackground(Color.WHITE);
        return scroll;
    }

    private JPanel construirPanelEstado() {
        lblEstado.setFont(ComponentesGraficos.FUENTE_NEGRITA);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(ComponentesGraficos.TARJETA);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ComponentesGraficos.BORDE),
                BorderFactory.createEmptyBorder(14, 18, 14, 18)));
        panel.add(lblEstado, BorderLayout.CENTER);
        return panel;
    }

    private void configurarSeleccionDeFila() {
        tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (!e.getValueIsAdjusting() && fila >= 0) {
                txtNombre.setText(String.valueOf(modeloTabla.getValueAt(fila, 0)));
                txtApellido.setText(String.valueOf(modeloTabla.getValueAt(fila, 1)));
                txtTelefono.setText(String.valueOf(modeloTabla.getValueAt(fila, 2)));
            }
        });
    }

    private void mostrarMensajeInicial() {
        lblEstado.setForeground(ComponentesGraficos.VERDE_OSCURO);
        lblEstado.setText("●  Listo. Escribe los datos de un contacto para empezar.");
    }

    public void mostrarError(String mensaje) {
        lblEstado.setForeground(ComponentesGraficos.ROJO);
        lblEstado.setText("●  " + mensaje);
    }

    public void mostrarExito(String mensaje) {
        lblEstado.setForeground(ComponentesGraficos.VERDE_EXITO);
        lblEstado.setText("●  " + mensaje);
    }

    public void actualizarEspacios(int libres, int maximo) {
        lblEspacios.setText("Espacios libres: " + libres + " de " + maximo);
    }

    public void actualizarTabla(List<String[]> contactos) {
        modeloTabla.setRowCount(0);
        for (String[] contacto : contactos) {
            modeloTabla.addRow(contacto);
        }
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        tabla.clearSelection();
        txtNombre.requestFocus();
    }

    public String getNombre() {
        return txtNombre.getText().trim();
    }

    public String getApellido() {
        return txtApellido.getText().trim();
    }

    public String getTelefono() {
        return txtTelefono.getText().trim();
    }

    public void setTelefono(String telefono) {
        txtTelefono.setText(telefono);
    }

    public JButton getBtnAnadir() {
        return btnAnadir;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JTable getTabla() {
        return tabla;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }
}
