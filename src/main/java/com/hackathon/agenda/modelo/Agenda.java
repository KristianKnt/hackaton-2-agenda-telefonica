package com.hackathon.agenda.modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Agenda {

    private List<Contacto> contactos;
    private int capacidadMaxima;

    public Agenda() {
        this(10);
    }

    public Agenda(int capacidadMaxima) {
        if (capacidadMaxima <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0.");
        }
        this.capacidadMaxima = capacidadMaxima;
        this.contactos = new ArrayList<>();
    }

    public boolean agendaLlena() {
        return contactos.size() >= capacidadMaxima;
    }

    public int espaciosLibres() {
        return capacidadMaxima - contactos.size();
    }

    // Verifica que el contacto se encuentre registrado por nombre y apellido
    public boolean existeContacto(Contacto c) {
        return contactos.contains(c);
    }

    // Formato colombiano: 3XXXXXXXXX, con +57 opcional
    private static final String REGEX_TELEFONO = "^(?:\\+57\\s?)?3[0-9]{9}$";

    private boolean telefonoValido(String telefono) {
        return telefono != null && telefono.matches(REGEX_TELEFONO);
    }

    public void añadirContacto(Contacto c) {
        if (c == null) {
            throw new IllegalArgumentException("El contacto no puede ser nulo.");
        }
        if (c.getNombre() == null || c.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (c.getApellido() == null || c.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        }
        if (!telefonoValido(c.getTelefono())) {
            throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
        }
        if (agendaLlena()) {
            throw new IllegalArgumentException("La agenda está llena.");
        }
        if (existeContacto(c)) {
            throw new IllegalArgumentException("Ese contacto ya existe.");
        }
        contactos.add(c);
    }

    // Retorna la lista de contactos ordenada alfabéticamente
    public List<Contacto> listarContactos() {
        List<Contacto> copia = new ArrayList<>(contactos);
        copia.sort(Comparator
                .comparing(Contacto::getNombre, String.CASE_INSENSITIVE_ORDER)
                .thenComparing(Contacto::getApellido, String.CASE_INSENSITIVE_ORDER));
        return copia;
    }

    // Ayudante: busca un contacto por nombre y apellido (sin importar mayúsculas)
    private Contacto encontrar(String nombre, String apellido) {
        if (nombre == null || apellido == null) {
            return null;
        }
        for (Contacto c : contactos) {
            if (c.getNombre().trim().equalsIgnoreCase(nombre.trim())
                    && c.getApellido().trim().equalsIgnoreCase(apellido.trim())) {
                return c;
            }
        }
        return null;
    }

    // Devuelve el teléfono del contacto localizado
    public String buscarContacto(String nombre, String apellido) {
        Contacto c = encontrar(nombre, apellido);
        if (c == null) {
            return null;
        }
        return c.getTelefono();
    }

    // Remueve el contacto de la agenda si existe
    public boolean eliminarContacto(Contacto c) {
        return contactos.remove(c);
    }

    // Actualiza el teléfono de un contacto existente (validando con Regex)
    public void modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
        Contacto c = encontrar(nombre, apellido);
        if (c == null) {
            throw new IllegalArgumentException("El contacto no existe.");
        }
        if (!telefonoValido(nuevoTelefono)) {
            throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
        }
        c.setTelefono(nuevoTelefono);
    }

}