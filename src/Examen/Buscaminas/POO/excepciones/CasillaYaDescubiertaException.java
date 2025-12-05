package Examen.Buscaminas.POO.excepciones;


//Excepción personalizada

//Se lanza cuando el usuario intenta descubrir una casilla que ya estaba descubierta.

public class CasillaYaDescubiertaException extends Exception {

    private static final long serialVersionUID = 1L;

    public CasillaYaDescubiertaException(String mensaje) {
        super(mensaje);
    }
}
