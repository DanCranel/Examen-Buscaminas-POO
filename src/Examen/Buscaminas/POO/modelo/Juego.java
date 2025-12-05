package Examen.Buscaminas.POO.modelo;

import java.io.Serializable;

import Examen.Buscaminas.POO.exepciones.CasillaYaDescubiertaException;
import Examen.Buscaminas.POO.exepciones.CoordenadaFueraDeRangoException;

/**
 * Clase Juego
 * Es una "fachada" del modelo: coordina Tablero y Jugador.
 * El Controlador llama a este modelo para procesar la lógica del juego.
 */
public class Juego implements Serializable {

    private static final long serialVersionUID = 1L;

    private Tablero tablero;
    private Jugador jugador;
    private boolean terminado;
    private boolean victoria;

    public Juego(String nombreJugador) {
        this.jugador = new Jugador(nombreJugador);
        // Se crea un tablero de 10x10 con 10 minas
        this.tablero = new Tablero(10, 10, 10);
        this.terminado = false;
        this.victoria = false;
    }

     // Procesa el movimiento del jugador.
     // Aplica validaciones y delega en el Tablero la acción concreta.
     
     
    public void procesarMovimiento(int fila, int columna, Accion accion)
            throws CasillaYaDescubiertaException, CoordenadaFueraDeRangoException {

        if (!tablero.coordenadaValida(fila, columna)) {
            // Excepción personalizada para coordenadas fuera de rango
            throw new CoordenadaFueraDeRangoException("Coordenada fuera del tablero.");
        }

        if (terminado) {
            return;
        }

        jugador.incrementarMovimientos();

        switch (accion) {
            case MARCAR:
                tablero.marcar(fila, columna);
                break;
            case DESCUBRIR:
                boolean pisoMina = tablero.descubrir(fila, columna);
                if (pisoMina) {
                    terminado = true;
                    victoria = false;
                } else if (tablero.haGanado()) {
                    terminado = true;
                    victoria = true;
                }
                break;
        }
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public boolean isTerminado() {
        return terminado;
    }

    public boolean isVictoria() {
        return victoria;
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }

    public void setVictoria(boolean victoria) {
        this.victoria = victoria;
    }
}
