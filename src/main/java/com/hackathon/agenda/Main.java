package com.hackathon.agenda;
// llamamos la importaciones de el MVC
import com.hackathon.agenda.controlador.AgendaControlador;
import com.hackathon.agenda.modelo.Agenda;
import com.hackathon.agenda.vista.VentanaPrincipal;

import javax.swing.SwingUtilities;

public class Main {

  public static void main(String[] args) {

    SwingUtilities.invokeLater(() -> {

      //  Creamos el modelo
      Agenda agenda = new Agenda();

      // Creamos la vista
      VentanaPrincipal vista = new VentanaPrincipal();

      // Aquí instanciaremos el controlador
      new AgendaControlador(agenda, vista);

      // Mostrar la ventana
      vista.setVisible(true);

    });
  }
}