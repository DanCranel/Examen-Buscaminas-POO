package Examen.Buscaminas.POO.controlador;

import java.io.IOException;

import Examen.Buscaminas.POO.excepciones.CasillaYaDescubiertaException;
import Examen.Buscaminas.POO.excepciones.CoordenadaFueraDeRangoException;
import Examen.Buscaminas.POO.excepciones.EntradaInvalidaException;
import Examen.Buscaminas.POO.modelo.Accion;
import Examen.Buscaminas.POO.modelo.Juego;
import Examen.Buscaminas.POO.persistencia.GestorArchivos;
import Examen.Buscaminas.POO.vista.VistaConsola;

/**
 * Clase ControladorJuego
 * Implementa el "Controlador" del patrón MVC:
 * - Interpreta las entradas del usuario (desde la Vista).
 * - Llama a los métodos del Modelo (Juego).
 * - Gestiona las excepciones y decide qué mostrar en la Vista.
 */
public class ControladorJuego {

    private VistaConsola vista;
    private Juego juego;
    private GestorArchivos gestorArchivos;

    public ControladorJuego(VistaConsola vista) {
        this.vista = vista;
        this.gestorArchivos = new GestorArchivos();
    }

    // Método principal que muestra el menú y controla el flujo general del programa.
  
    public void iniciar() {
        vista.mostrarPortada();
        String nombre = vista.pedirNombreJugador();

        boolean salir = false;

        while (!salir) {
            int opcion = vista.mostrarMenuPrincipal();
            switch (opcion) {
                case 1:
                    juego = new Juego(nombre);
                    jugar();
                    break;
                case 2:
                    cargarPartida();
                    if (juego != null) {
                        jugar();
                    }
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    vista.mostrarMensaje("Opción inválida. Intente de nuevo.");
            }
        }

        vista.mostrarMensaje("Gracias por jugar. ¡Hasta pronto!");
    }

 
 // Bucle principal de juego: mientras no termine, lee jugadas y las procesa.
 
    private void jugar() {
        while (!juego.isTerminado()) {
            vista.mostrarTablero(juego);
            String entrada = vista.leerJugada();

            try {
                if (entrada.equalsIgnoreCase("G")) {
                    guardarPartida();
                    continue;
                } else if (entrada.equalsIgnoreCase("S")) {
                    juego.setTerminado(true);
                    juego.setVictoria(false);
                    break;
                }

                procesarEntrada(entrada);

            } catch (EntradaInvalidaException e) {
                vista.mostrarMensaje("Entrada inválida: " + e.getMessage());
            } catch (CasillaYaDescubiertaException e) {
                vista.mostrarMensaje("Advertencia: " + e.getMessage());
            } catch (CoordenadaFueraDeRangoException e) {
                vista.mostrarMensaje("Error: " + e.getMessage());
            } catch (Exception e) {
                vista.mostrarMensaje("Se produjo un error inesperado: " + e.getMessage());
            }
        }

        if (juego.isTerminado()) {
            vista.mostrarTablero(juego);
            vista.mostrarResultado(juego);
        }
    }


     // Interpreta la jugada escrita por el usuario.
     // Ejemplo: "A5 D" → fila=0, columna=4, acción=DESCUBRIR
     
    private void procesarEntrada(String entrada) throws EntradaInvalidaException,
            CasillaYaDescubiertaException, CoordenadaFueraDeRangoException {

        if (entrada == null || entrada.trim().isEmpty()) {
            throw new EntradaInvalidaException("No se ingresó ninguna jugada.");
        }

        String[] partes = entrada.trim().toUpperCase().split("\\s+");
        if (partes.length != 2) {
            throw new EntradaInvalidaException("Formato incorrecto. Use algo como: A5 D");
        }

        String coordStr = partes[0];
        String accionStr = partes[1];

        if (coordStr.length() < 2) {
            throw new EntradaInvalidaException("Coordenada muy corta.");
        }

        char filaChar = coordStr.charAt(0);
        if (filaChar < 'A' || filaChar > 'Z') {
            throw new EntradaInvalidaException("La fila debe ser una letra (A-J).");
        }
        int fila = filaChar - 'A';

        String colStr = coordStr.substring(1);
        int columna;
        try {
            columna = Integer.parseInt(colStr) - 1; // usuario ve 1-10, internamente 0-9
        } catch (NumberFormatException e) {
            throw new EntradaInvalidaException("La columna debe ser un número (1-10).");
        }

        Accion accion;
        if (accionStr.equals("D")) {
            accion = Accion.DESCUBRIR;
        } else if (accionStr.equals("M")) {
            accion = Accion.MARCAR;
        } else {
            throw new EntradaInvalidaException("Acción desconocida. Use D (descubrir) o M (marcar).");
        }

        // Se delega la lógica de negocio al modelo (Juego)
        juego.procesarMovimiento(fila, columna, accion);
    }

    // Llama al GestorArchivos para guardar la partida actual.

    private void guardarPartida() {
        String ruta = vista.pedirRutaArchivoGuardar();
        try {
            gestorArchivos.guardarPartida(juego, ruta);
            vista.mostrarMensaje("Partida guardada correctamente en: " + ruta);
        } catch (IOException e) {
            vista.mostrarMensaje("No se pudo guardar la partida: " + e.getMessage());
        }
    }

    // Llama al GestorArchivos para cargar una partida desde archivo.

    private void cargarPartida() {
        String ruta = vista.pedirRutaArchivoCargar();
        try {
            this.juego = gestorArchivos.cargarPartida(ruta);
            vista.mostrarMensaje("Partida cargada correctamente desde: " + ruta);
        } catch (IOException e) {
            vista.mostrarMensaje("No se pudo leer el archivo: " + e.getMessage());
            this.juego = null;
        } catch (ClassNotFoundException e) {
            vista.mostrarMensaje("Error de formato del archivo de partida: " + e.getMessage());
            this.juego = null;
        }
    }
}
