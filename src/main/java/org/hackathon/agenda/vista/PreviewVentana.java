package org.hackathon.agenda.vista;

import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.util.List;

public class PreviewVentana {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();

            List<String[]> contactos = new ArrayList<>();
            contactos.add(new String[]{"Ana", "Pérez", "600000000"});
            contactos.add(new String[]{"Carlos", "Gómez", "+34 600 111 222"});
            contactos.add(new String[]{"Marta", "Ruiz", "611222333"});
            ventana.actualizarTabla(contactos);
            ventana.actualizarEspacios(7, 10);

            ventana.getBtnAnadir().addActionListener(e -> ventana.mostrarExito("¡Contacto añadido correctamente!"));
            ventana.getBtnEliminar().addActionListener(e -> ventana.mostrarError("El contacto no existe en la agenda."));
            ventana.getBtnLimpiar().addActionListener(e -> ventana.limpiarCampos());

            ventana.setVisible(true);
        });
    }
}
