package org.hackathon.agenda.modelo;

import java.util.Objects;
import java.util.Locale;

public class Contacto {

    // Atributos
    private String nombre;
    private String apellido;
    private String telefono;

    // Constructor
    public Contacto(String nombre, String apellido, String telefono) {
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
    }

    // Setters
    public void setNombre(String nombre) {
        validarNombre(nombre);
        this.nombre = nombre.trim();
    }

    public void setApellido(String apellido) {
        validarApellido(apellido);
        this.apellido = apellido.trim();
    }

    public void setTelefono(String telefono) {
        validarTelefono(telefono);
        this.telefono = telefono.trim();
    }

    // Métodos para validación
    private void validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
    }

    private void validarApellido(String apellido) {
        if (apellido == null || apellido.isBlank()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }
    }

    // Validación Regex
    private void validarTelefono(String telefono) {
        if (telefono == null || !telefono.matches("^(?:\\+57\\s?)?3[0-9]{9}$")) {
            throw new IllegalArgumentException(
                    "El teléfono debe ser un celular colombiano válido"
            );
        }
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    // Método equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Contacto contacto = (Contacto) obj;

        return nombre.equalsIgnoreCase(contacto.nombre)
                && apellido.equalsIgnoreCase(contacto.apellido);
    }

    // Método hashCode
    @Override
    public int hashCode() {
        return Objects.hash(
                nombre.toLowerCase(Locale.ROOT),
                apellido.toLowerCase(Locale.ROOT)
        );
    }
}