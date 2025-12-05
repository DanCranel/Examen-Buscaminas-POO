
Buscaminas en consola en Java

Proyecto de Buscaminas en consola desarrollado en Java como examen práctico de POO

El juego funciona completamente por consola mediante la clase 'VisaConsola'.


## Objetivos del proyecto

- Aplicar el patron de arquitectura **MVC**
- Utilizar conceptos de **POO** como clases, objetos, encapsulamiento
- Manejar errores mediante **excepciones personalizadas** 
- Serializar objetos del juego (`Serializable`)
- Construir un juego funcional con logica completa de Buscaminas

## Estructura del proyecto
|
|-- Examen/Buscaminas/POO/
|
|-- modelo/
|
||--- Casilla.java
|
||--- Tablero.java
|
||--- Jugador.java
|
||--- Juego.java
|
|-- vista/
|
||--- VistaConsola.java
|
|-- controlador/
|
||--- ControladorJuego.java
|
|- excepciones/
|
||--- CasillaYaDescubiertaException.java
|
||--- CoordenadaFueraDeRangoException.java
|
|--persistencia/
|
||--- packagae-info.java

## Explicacion de las Clases Principales

- ## **Casilla.java**

Representa cada posicion individual del tablero.

**Atributos**

- 'tieneMina'
- 'descubierta'
- 'marcada'
- 'minasAlrededor'

Funciones importantes:

- 'incrementarMinasAlrededor()'
- Getters y setters
- Implementa 'Serializable'

## **Tablero.java**

Contiene la matriz de casillas y toda la logica interna del Buscaminas.

- Funciones clave:

- Generar minas aleatorias
- Calcular minas alrededor
- validar coordenadas
- Devolver cuantas casillas quedan sin descubrir

## **Jugador.java**

Guarda:
- Nombre
- Numero de movimientos realizados

Implementa 'Serializable'

- ## **Juego.java**

Es la "fachada del modelo", coordina Jugador + Tablero.

**Maneja:**
- Logica de victoria / derrota
- Procesamiento de movimientos
- Excepciones por coordenadas o casillas descubiertas
- Estado final del juego

- ## **VistaConsola.java**

Interfaz de usuario por consola. 

**Permite:** 
- Mostrar la portada
- Pedir nombre del jugador
- Mostrar menu principal
- Solicitar opcion al usuario

- ## **ControladorJuego.java**

Conecta la vista con el modelo/
Aqui se gestiona el flujo completo del juego.

- ## **Excepciones personalizadas**

- 'CasillaYaDescubiertaException'
- 'CoordenadaFueraDeRangoException'

Se utilizan para validar la logica del tablero y hacer el codigo mas limpio. 

- ## Como ejecutar el proyecto

1. Imorta el repositorio en **Eclipse** o compilalo desde terminal.
2. Asegurate de que la estructura de paquetes este correcta.
3. Ejecuta la clase:
4. Sigue las instrucciones en consola.

## Guardado y carga de partidas

El paquete 'persistencia/' esta creado para implementar:

- Guardado del objeto 'Juego' en un archivo '.dat'
- Carga de partidas usando 'ObjectInputStream' y 'ObjectOutputStream'

## Patron MVC aplicado
 
 Para organizar el proyecto se decidio dividirlo en tres partes para que el codigo sea mas facil de entender y mantener:
 
 -**Modelo:** Aqui estan las clases que representan todo lo que pasa dentro del juego (Casillas, Tablero, Jugador y Juego)
 Basicamente es donde vive la logica del buscaminas.
 
 -**Vista:** En este caso es la consola. Usamos la clase 'VistaConsola'para mostrar mensajes y recibir lo que el jugador escribe
 
 -**Controlador:** Esta parte conecta la vista con el modelo. En 'ControladorJuego' se decidio manejar el flujo princiapl del programa,
 llamando a los metodos del modelo segun lo que el usuario va escogiendo. 

 ## Autores del Proyecto
 
 - **Carlos Daniel Morales Trelles**
 - **Gabriel Alberto Serrano Zaldua**
 - **Oscar Sebastian Velez Alvarez**
 - **Nicolas Mateo Velez Alvarez**
 
 
 ## Mejoras futuras sugeridas
 
- Implementar guardado y carga mediante serialización  
- Añadir niveles de dificultad  
- Crear un tablero visual más amigable en consola  
- Llevar el juego a una interfaz gráfica (JavaFX o Swing)  
- Añadir estadísticas del jugador  
 
 ## Notas finales
 Este proyecto fue desarrollado como examen práctico de POO, implementando de manera completa el patrón MVC, manejo de excepciones, clases modelo y una interfaz en consola.