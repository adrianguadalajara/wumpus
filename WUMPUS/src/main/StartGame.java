package main;

import service.TableroService;
import service.TableroServiceImpl;

public class StartGame {
	
	private StartGame() {
		
	}
	
	public static void main(String[] args) {
		
		TableroService tablero = new TableroServiceImpl();
		//Crear tablero y empezar a jugar
		tablero.crearTablero();
		
	}
}





    

