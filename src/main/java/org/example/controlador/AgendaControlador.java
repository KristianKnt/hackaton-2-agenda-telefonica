package org.example.controlador;

import org.example.modelo.Agenda;
import org.example.modelo.Contacto;
import org.example.vista.VentanaPrincipal;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgendaControlador implements ActionListener, ListSelectionListener {

    private final Agenda modelo;
    private final VentanaPrincipal vista;

    public AgendaControlador(Agenda modelo, VentanaPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;

        this.vista.getBtnAnadir().addActionListener(this);
        this.vista.getBtnModificar().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);
        this.vista.getBtnBuscar().addActionListener(this);
        this.vista.getBtnLimpiar().addActionListener(this);

        this.vista.getTabla().getSelectionModel().addListSelectionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object btnOrigen = e.getSource();

        if (btnOrigen == vista.getBtnAnadir()) {
            anadirContacto();
        } else if (btnOrigen == vista.getBtnModificar()) {
            modificarTelefono();
        } else if (btnOrigen == vista.getBtnEliminar()) {
            eliminarContacto();
        } else if (btnOrigen == vista.getBtnBuscar()) {
            buscarContacto();
        } else if (btnOrigen == vista.getBtnLimpiar()) {
            limpiarCampos();
        }
    }

    private void anadirContacto() {

        String nombre = vista.getNombre();
        String apellido = vista.getApellido();
        String telefono = vista.getTelefono();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            vista.mostrarError("El nombre y apellido son obligatorios");
            return;
        }

        if (modelo.agendaLlena()) {
            vista.mostrarError("La agenda esta llena. Ya no puedes agregar más contactos");
            return;
        }

        try {
            Contacto nuevoContacto = new Contacto(nombre, apellido, telefono);
            if (modelo.existeContacto(nuevoContacto)) {
                vista.mostrarError("El contacto ya existe en la agenda.");
                return;
            }

            boolean agregado = modelo.añadirContacto(nuevoContacto);
            if (agregado) {
                vista.limpiarCampos();
                vista.mostrarExito("Contacto añadido correctamente.");
            } else {
                vista.mostrarError("No se pudo añadir el contacto.");
            }
        } catch (IllegalArgumentException ex) {
            vista.mostrarError("Error en formato de datos: " + ex.getMessage());
        }
    }

    private void modificarTelefono() {
        String nombre = vista.getNombre();
        String apellido = vista.getApellido();
        String nuevoTelefono = vista.getTelefono();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            vista.mostrarError("Debe indicar el nombre y apellido del contacto a modificar.");
            return;
        }

        try {
            boolean modificado = modelo.modificarTelefono(nombre, apellido, nuevoTelefono);
            if (modificado) {
                vista.limpiarCampos();
                vista.mostrarExito("Telefono modificado correctamente.");
            } else {
                vista.mostrarError("Contacto no encontrado para modificar.");
            }
        } catch (IllegalArgumentException ex) {
            vista.mostrarError("Error en formato de telefono: " + ex.getMessage());
        }
    }

    private void buscarContacto() {
        String nombre = vista.getNombre();
        String apellido = vista.getApellido();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            vista.mostrarError("Ingrese nombre y apellido para buscar.");
            return;
        }

        String telefonoEncontrado = modelo.buscarContacto(nombre, apellido);
        if (telefonoEncontrado != null && !telefonoEncontrado.isEmpty()) {
            vista.setTelefono(telefonoEncontrado);
            vista.mostrarExito("Contacto encontrado. Telefono: " + telefonoEncontrado);
        } else {
            vista.mostrarError("No se encontró ningún contacto con ese nombre y apellido.");
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }


}
