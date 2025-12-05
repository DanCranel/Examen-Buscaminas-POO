package Examen.Buscaminas.POO.modelo;

import java.io.Serializable;
import java.util.Random;

import Examen.Buscaminas.POO.excepciones.CasillaYaDescubiertaException;

public class Tablero implements Serializable {

    private static final long serialVersionUID = 1L;

    private int filas;
    private int columnas;
    private int totalMinas;
    private Casilla[][] casillas;
    private int casillasSinMinaPorDescubrir;

    public Tablero(int filas, int columnas, int totalMinas) {
        this.filas = filas;
        this.columnas = columnas;
        this.totalMinas = totalMinas;
        inicializar();
    }

    private void inicializar() {
        casillas = new Casilla[filas][columnas];
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                casillas[f][c] = new Casilla();
            }
        }
        colocarMinasAleatorias();
        calcularMinasAlrededor();
        casillasSinMinaPorDescubrir = filas * columnas - totalMinas;
    }

    private void colocarMinasAleatorias() {
        Random random = new Random();
        int minasColocadas = 0;
        while (minasColocadas < totalMinas) {
            int f = random.nextInt(filas);
            int c = random.nextInt(columnas);
            if (!casillas[f][c].tieneMina()) {
                casillas[f][c].setTieneMina(true);
                minasColocadas++;
            }
        }
    }

    private void calcularMinasAlrededor() {
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                if (casillas[f][c].tieneMina()) {
                    // sumamos a sus vecinos
                    for (int df = -1; df <= 1; df++) {
                        for (int dc = -1; dc <= 1; dc++) {
                            int nf = f + df;
                            int nc = c + dc;
                            if (coordenadaValida(nf, nc) && !(df == 0 && dc == 0)) {
                                casillas[nf][nc].incrementarMinasAlrededor();
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean coordenadaValida(int fila, int columna) {
        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
    }

   
     // Descubre una casilla. Devuelve true si pisó una mina.
    
    public boolean descubrir(int fila, int columna) throws CasillaYaDescubiertaException {
        if (!coordenadaValida(fila, columna)) {
            return false;
        }

        Casilla casilla = casillas[fila][columna];

        if (casilla.isDescubierta()) {
            throw new CasillaYaDescubiertaException("La casilla ya fue descubierta.");
        }

        casilla.setDescubierta(true);

        if (casilla.tieneMina()) {
            // pisó una mina
            return true;
        }

        casillasSinMinaPorDescubrir--;

        // Si no hay minas alrededor, descubrimos en cascada
        if (casilla.getMinasAlrededor() == 0) {
            descubrirCascada(fila, columna);
        }

        return false;
    }

    private void descubrirCascada(int fila, int columna) {
        for (int df = -1; df <= 1; df++) {
            for (int dc = -1; dc <= 1; dc++) {
                int nf = fila + df;
                int nc = columna + dc;
                if (coordenadaValida(nf, nc) && !(df == 0 && dc == 0)) {
                    Casilla vecina = casillas[nf][nc];
                    if (!vecina.isDescubierta() && !vecina.tieneMina()) {
                        vecina.setDescubierta(true);
                        casillasSinMinaPorDescubrir--;
                        if (vecina.getMinasAlrededor() == 0) {
                            descubrirCascada(nf, nc);
                        }
                    }
                }
            }
        }
    }

    public void marcar(int fila, int columna) {
        if (!coordenadaValida(fila, columna)) {
            return;
        }
        Casilla casilla = casillas[fila][columna];
        if (!casilla.isDescubierta()) {
            casilla.setMarcada(!casilla.isMarcada());
        }
    }

    public boolean haGanado() {
        return casillasSinMinaPorDescubrir == 0;
    }

    public Casilla[][] getCasillas() {
        return casillas;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
}
