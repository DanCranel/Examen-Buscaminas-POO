package Examen.Buscaminas.POO.modelo;

import java.io.Serializable;

/**
 * Clase Jugador
 * Representa al jugador: ejemplo de clase simple en POO.
 */
public class Jugador implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private int movimientos;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.movimientos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getMovimientos() {
        return movimientos;
    }

    // Cada vez que el jugador realiza una jugada, se incrementa este contador.
     
    public void incrementarMovimientos() {
        movimientos++;
    }
}
