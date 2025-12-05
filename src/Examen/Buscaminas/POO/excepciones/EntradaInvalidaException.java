package Examen.Buscaminas.POO.excepciones;

// Excepción personalizada
// Se utiliza para indicar errores de formato en la entrada del usuario.
 
public class EntradaInvalidaException extends Exception {

    private static final long serialVersionUID = 1L;

    public EntradaInvalidaException(String mensaje) {
        super(mensaje);
    }
}
