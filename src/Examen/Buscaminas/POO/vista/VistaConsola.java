package Examen.Buscaminas.POO.vista;

import java.util.Scanner;


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
}