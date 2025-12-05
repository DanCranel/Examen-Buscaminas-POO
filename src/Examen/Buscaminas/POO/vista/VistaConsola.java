package Examen.Buscaminas.POO.vista;

import java.util.Scanner;

import Examen.Buscaminas.POO.modelo.Casilla;
import Examen.Buscaminas.POO.modelo.Juego;
import Examen.Buscaminas.POO.modelo.Tablero;


 // Clase VistaConsola
 // Implementa la "Vista" del patrón MVC utilizando la consola.

public class VistaConsola {

    private Scanner scanner;

    public VistaConsola() {
        scanner = new Scanner(System.in);
    }

    public void mostrarPortada() {
        System.out.println("============================================");
        System.out.println("      BIENVENIDO AL BUSCAMINAS EN CONSOLA   ");
        System.out.println("     Proyecto POO - MVC - Excepciones       ");
        System.out.println("============================================\n");
    }

    public String pedirNombreJugador() {
        System.out.print("Ingrese el nombre del jugador: ");
        return scanner.nextLine();
    }

    public int mostrarMenuPrincipal() {
        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1. Nuevo juego");
        System.out.println("2. Cargar partida");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
        String opcion = scanner.nextLine();
        try {
            return Integer.parseInt(opcion);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Muestra el tablero en consola con letras (filas) y números (columnas).

    public void mostrarTablero(Juego juego) {
        Tablero tablero = juego.getTablero();
        Casilla[][] casillas = tablero.getCasillas();

        int filas = tablero.getFilas();
        int columnas = tablero.getColumnas();

        System.out.println("\n===== TABLERO =====");
        // Encabezado columnas
        System.out.print("   ");
        for (int c = 1; c <= columnas; c++) {
            if (c < 10) {
                System.out.print(" " + c + " ");
            } else {
                System.out.print(c + " ");
            }
        }
        System.out.println();

        for (int f = 0; f < filas; f++) {
            // Letra de fila
            char letraFila = (char) ('A' + f);
            System.out.print(" " + letraFila + " ");

            for (int c = 0; c < columnas; c++) {
                Casilla casilla = casillas[f][c];
                char simbolo;

                if (casilla.isDescubierta()) {
                    if (casilla.tieneMina()) {
                        simbolo = '*'; // mina
                    } else if (casilla.getMinasAlrededor() > 0) {
                        simbolo = Character.forDigit(casilla.getMinasAlrededor(), 10);
                    } else {
                        simbolo = ' '; // casilla vacía
                    }
                } else {
                    if (casilla.isMarcada()) {
                        simbolo = 'F'; // bandera
                    } else {
                        simbolo = '#'; // casilla oculta
                    }
                }

                System.out.print(" " + simbolo + " ");
            }
            System.out.println();
        }
    }

    // Lee la jugada del usuario en el formato "A5 D".
     
    public String leerJugada() {
        System.out.println("\nIngrese jugada en el formato: A5 D (D=Descubrir, M=Marcar)");
        System.out.println("O escriba 'G' para guardar partida, 'S' para salir.");
        System.out.print("Jugada: ");
        return scanner.nextLine();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarResultado(Juego juego) {
        System.out.println("\n===== FIN DE LA PARTIDA =====");
        if (juego.isVictoria()) {
            System.out.println("¡Felicidades " + juego.getJugador().getNombre() + "! Has ganado.");
        } else {
            System.out.println("Lo sentimos, " + juego.getJugador().getNombre() + ". Has perdido.");
        }
        System.out.println("Movimientos realizados: " + juego.getJugador().getMovimientos());
    }

    public String pedirRutaArchivoGuardar() {
        System.out.print("Ingrese el nombre del archivo para guardar (ej: partida.dat): ");
        return scanner.nextLine();
    }

    public String pedirRutaArchivoCargar() {
        System.out.print("Ingrese el nombre del archivo a cargar (ej: partida.dat): ");
        return scanner.nextLine();
    }
}
