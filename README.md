# Agenda Telefónica MVC

Aplicación de escritorio desarrollada en Java que permite gestionar una agenda telefónica mediante una interfaz gráfica, aplicando el patrón arquitectónico MVC (Modelo-Vista-Controlador).

## 1. Descripción del proyecto

El proyecto consiste en desarrollar un sistema de gestión de contactos que permita registrar, consultar, modificar y eliminar información de una agenda telefónica.

La aplicación utiliza Java Swing para la interfaz gráfica y Gradle como herramienta de automatización y gestión del proyecto.

La información se administra en memoria durante la ejecución de la aplicación, sin conexión a bases de datos.

## 2. Tecnologías utilizadas

- **Lenguaje:** Java 21.
- **Interfaz gráfica:** Java Swing.
- **Gestor de construcción:** Gradle.
- **DSL de Gradle:** Groovy.
- **Arquitectura:** MVC (Modelo-Vista-Controlador).
- **IDE:** IntelliJ IDEA.

## 3. Arquitectura del proyecto

El proyecto implementa el patrón MVC, separando las responsabilidades en tres capas:

### Modelo (modelo)

Contiene las entidades y la lógica de negocio.

- `Contacto.java`: representa un contacto mediante nombre, apellido y teléfono. Implementa validaciones y criterios de igualdad.
- `Agenda.java`: administra los contactos y proporciona las operaciones de gestión de la agenda.

### Vista (vista)

Contiene los componentes gráficos desarrollados con Java Swing.

- `VentanaPrincipal.java`: ventana principal de la aplicación.

### Controlador (controlador)

Se encarga de gestionar los eventos de la interfaz gráfica y coordinar las operaciones con el modelo.

- `AgendaControlador.java`: conecta las acciones del usuario con la lógica de negocio.

### Punto de entrada

- `Main.java`: inicializa el modelo, la vista y el controlador.

## 4. Funcionalidades

La aplicación contempla las siguientes operaciones:

- Agregar contactos.
- Comprobar si un contacto ya existe.
- Listar contactos ordenados alfabéticamente.
- Buscar contactos por nombre y apellido.
- Eliminar contactos.
- Modificar números telefónicos.
- Consultar si la agenda está llena.
- Consultar los espacios disponibles.

## 5. Reglas de negocio y validaciones

- Los nombres y apellidos son obligatorios.
- No se permiten contactos duplicados.
- La comparación de contactos ignora mayúsculas y minúsculas.
- El teléfono debe cumplir un formato válido mediante expresiones regulares (Regex).
- La agenda tiene una capacidad predeterminada de 10 contactos.
- La capacidad máxima puede configurarse al inicializar la agenda.

## 6. Requisitos para ejecutar el proyecto

- Java Development Kit (JDK) 21.
- IntelliJ IDEA o un entorno compatible con proyectos Gradle.
- Gradle Wrapper incluido en el repositorio.

## 7. Instalación y ejecución

Clonar el repositorio:

```bash
git clone https://github.com/KristianKnt/hackaton-2-agenda-telefonica.git
```

Ingresar al directorio:

```bash
cd hackaton-2-agenda-telefonica
```

Ejecutar en Windows:

```bash
gradlew.bat run
```

Ejecutar en Linux o macOS:

```bash
./gradlew run
```

*Nota: el comando de ejecución requiere que el proyecto tenga configurado el plugin `application` de Gradle. Se confirmará cuando revisemos el archivo `build.gradle`.*

## 8. Estructura del proyecto

```text
src/
└── main/
    └── java/
        └── com/hackathon/agenda/
            ├── Main.java
            ├── modelo/
            │   ├── Contacto.java
            │   └── Agenda.java
            ├── vista/
            │   └── VentanaPrincipal.java
            └── controlador/
                └── AgendaControlador.java
```

## 9. Autores

- Cristian Camilo Rojas Capera
- Evelyn Pascual
- Laura Torres Contreras
- Nayant Gonzalez
- Nicole Betancourt Alvarez



---

**Proyecto académico - Hackathon 2 Generation**

Agenda Telefónica MVC.
