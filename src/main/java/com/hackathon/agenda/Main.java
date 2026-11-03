package com.hackathon.agenda;

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

      // Mostrar la ventana
      vista.setVisible(true);

    });
  }
}