package Examen.Buscaminas.POO.persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Examen.Buscaminas.POO.modelo.Juego;

// Maneja la persistencia de la partida usando archivos binarios.
 // Se usa serialización de objetos (ObjectOutputStream / ObjectInputStream).

public class GestorArchivos {

    // Guarda un objeto Juego en un archivo binario.
	
    public void guardarPartida(Juego juego, String rutaArchivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(juego);
        }
    }

    // Carga un objeto Juego desde un archivo binario.
    
    public Juego cargarPartida(String rutaArchivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            return (Juego) ois.readObject();
        }
    }
}
