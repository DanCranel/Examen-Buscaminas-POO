package Examen.Buscaminas.POO;

import Examen.Buscaminas.POO.controlador.ControladorJuego;
import Examen.Buscaminas.POO.vista.VistaConsola;


public class Main {
	
	public static void main(String[] args) {
		VistaConsola vista = new VistaConsola();
		ControladorJuego controlador = new ControladorJuego(vista);
		controlador.iniciar();
	}

}
