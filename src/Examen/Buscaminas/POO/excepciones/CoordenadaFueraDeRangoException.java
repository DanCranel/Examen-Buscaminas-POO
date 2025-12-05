package Examen.Buscaminas.POO.excepciones;


 //  Excepción personalizada
 // Se lanza cuando el usuario ingresa una coordenada fuera del tablero.

public class CoordenadaFueraDeRangoException extends Exception {

    private static final long serialVersionUID = 1L;

    public CoordenadaFueraDeRangoException(String mensaje) {
        super(mensaje);
    }
}
