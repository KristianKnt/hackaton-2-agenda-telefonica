package com.hackathon.agenda.controlador;

import com.hackathon.agenda.modelo.Agenda;
import com.hackathon.agenda.modelo.Contacto;
import com.hackathon.agenda.vista.VentanaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AgendaControlador implements ActionListener {

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

        actualizarVentana();
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

        try {
            Contacto nuevoContacto = new Contacto(nombre, apellido, telefono);
            modelo.añadirContacto(nuevoContacto);
            actualizarVentana();
            vista.limpiarCampos();
            vista.mostrarExito("Contacto añadido correctamente.");

        } catch (IllegalArgumentException ex) {
            vista.mostrarError(ex.getMessage());
        }
    }

    private void modificarTelefono() {
        String nombre = vista.getNombre();
        String apellido = vista.getApellido();
        String nuevoTelefono = vista.getTelefono();

        try {
            modelo.modificarTelefono(nombre, apellido, nuevoTelefono);
            actualizarVentana();
            vista.limpiarCampos();
            vista.mostrarExito("Teléfono modificado correctamente.");
        } catch (IllegalArgumentException ex) {
            vista.mostrarError(ex.getMessage());
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

    private void eliminarContacto() {
        String nombre = vista.getNombre();
        String apellido = vista.getApellido();
        String telefono = vista.getTelefono();

        if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty()) {
            vista.mostrarError("Seleccione un contacto para eliminarlo.");
            return;
        }

        try {
            Contacto contacto = new Contacto(nombre, apellido, telefono);
            boolean eliminado = modelo.eliminarContacto(contacto);

            if (eliminado) {
                actualizarVentana();
                vista.limpiarCampos();
                vista.mostrarExito("Contacto eliminado con éxito.");
            } else {
                vista.mostrarError("El contacto no existe en la agenda.");
            }
        } catch (IllegalArgumentException ex) {
            vista.mostrarError("Error al eliminar: " + ex.getMessage());
        }
    }

    private void limpiarCampos() {
        vista.limpiarCampos();
        vista.mostrarExito("Campos del formulario limpios.");
    }

    private void actualizarVentana() {
        List<Contacto> lista = modelo.listarContactos();

        int capacidadTotal = lista.size() + modelo.espaciosLibres();
        vista.actualizarEspacios(modelo.espaciosLibres(), capacidadTotal);

        List<String[]> datosTabla = new ArrayList<>();
        for (Contacto c : lista) {
            datosTabla.add(new String[]{c.getNombre(), c.getApellido(), c.getTelefono()});
        }

        vista.actualizarTabla(datosTabla);
    }

}
