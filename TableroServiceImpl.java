package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import comun.Constants;
import dto.CasillaDTO;
import dto.PersonaDTO;
import dto.TableroDTO;
import utils.Utils;


public class TableroServiceImpl implements TableroService {
	
	CasillaService casillaImpl = new CasillaServiceImpl();
	
	public void crearTablero (){
		System.out.print("--------------------------\n");
		System.out.print("| ---------------------- | \n");
		System.out.print("| |       WUMPUS       | |\n");
		System.out.print("| ---------------------- |\n");
		System.out.print("--------------------------\n\n");
	
		
		TableroDTO tablero = obtenerConfiguracionTablero();
		
		String matriz[][] = crearEstructuraTablero(tablero);
		
		anyadirElementos(matriz,tablero);		
		
		pintarTablero(matriz,tablero);
		
		comienzaJuego(matriz,tablero);
	}
	
	/**
	 * Solicitar al usuario la configuracion del tablero.
	 * @return
	 */
	private TableroDTO obtenerConfiguracionTablero(){
		
		TableroDTO tablero = new TableroDTO();
	
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		//DIMENSION X
		System.out.print("Las dimensiones minimas del tablero para un juego entretenido deben se de 3x3, \nno necesariamente tiene que ser cuadrado\n");
		System.out.print("Ingresa dimensión X: ");
		String stringX = scanner.nextLine();
		while(!Utils.isNumeric(stringX) || Integer.parseInt(stringX)<3){
			System.out.print(Constants.ERROR_NUMERICO);
			System.out.print("Ingresa dimensión X: ");
			stringX = scanner.nextLine();
		}
		tablero.setNumCasillasX(Integer.parseInt(stringX));
		tablero.setDimensionX(Integer.parseInt(stringX)*2);
		
		//DIMENSION Y
		System.out.print("Ingresa dimensión Y: ");
		String stringY = scanner.nextLine();
		while(!Utils.isNumeric(stringY ) || Integer.parseInt(stringY)<3){
			System.out.print(Constants.ERROR_NUMERICO);
			System.out.print("Ingresa dimensión Y: ");
			stringY = scanner.nextLine();
		}
		tablero.setNumCasillasY(Integer.parseInt(stringY));
		tablero.setDimensionY(Integer.parseInt(stringY)*2);
		
		int maxPozos = tablero.getNumCasillasX()*tablero.getNumCasillasY()-7;
		//NUMERO DE POZOS
		System.out.print("El número de pozos máximos para que la partida sea entrenida debe ser menor o igual a: "+maxPozos);
		System.out.print("\nIngresa el número de pozos: ");
		String pozos= scanner.nextLine();
		while(!Utils.isNumeric(pozos ) || Integer.parseInt(pozos) > maxPozos || Integer.parseInt(pozos)<0){
			System.out.print(Constants.ERROR_NUMERICO);
			System.out.print("Ingresa el número de pozos: ");
			pozos = scanner.nextLine();
		}
		tablero.setNumPozos(Integer.parseInt(pozos));
		
		return tablero;
	}
	
	/**
	 * Crea la estructura del tablero.
	 * 
	 * @param tablero
	 * @return
	 */
	private String[][] crearEstructuraTablero(TableroDTO tablero){
		
		String matriz[][] = new String[tablero.getDimensionX() + 1][tablero.getDimensionY() +1];		

		for (int x=0; x < matriz.length; x++) {
		  for (int y=0; y < matriz[x].length; y++) {	
			if (x==0 ||x==matriz.length||y==0||y== matriz[x].length|| x%2==0 || y%2==0){
				matriz[x][y] = "*";
			}else{
				matriz[x][y] = " ";
			}
		   }
		}
		
		
		return matriz;
	}
	
	/**
	 * Se insertan en el tablero, la puerta, la persona, el wumpus ,los pozos y las percepciones
	 * 
	 * @param matriz
	 * @param tablero
	 */
	private void anyadirElementos(String[][] matriz, TableroDTO tablero) {
		
		List<CasillaDTO> listaCasillas = new ArrayList<CasillaDTO>(); 
		int X = tablero.getNumCasillasX();
		int Y = tablero.getNumCasillasY();
		//Creo la puerta
		listaCasillas.add(casillaImpl.crearCasilla(tablero,tablero.getDimensionX(),1,"_"));
		//Pongo a la persona en la puerta
		listaCasillas.add(casillaImpl.crearCasilla(tablero,tablero.getDimensionX()-1,1,Constants.PERSONA));
		//Crear el wumpus
		int poswumpusx= (int) (Math.random() * X + 1);
		int poswumpusy= (int) (Math.random() * Y + 1);
		//comprobar que no caiga donde esta el cazador
		while(!" ".equals(casillaImpl.obtenerValorCasilla(listaCasillas,(poswumpusx*2-1),(poswumpusy*2-1)))){
		 	poswumpusx = (int) (Math.random() * X + 1);
			poswumpusy= (int) (Math.random() * Y + 1);
		}
	  	listaCasillas.add(casillaImpl.crearCasilla(tablero,(poswumpusx*2-1),(poswumpusy*2-1),Constants.WUMPUS));
		
		//Crear el tesoro
		int postesorox= (int) (Math.random() * X + 1);
		int postesoroy= (int) (Math.random() * Y + 1);
		//comprobar que no caiga donde esta el cazador y el wumpu
		while(!" ".equals(casillaImpl.obtenerValorCasilla(listaCasillas,(postesorox*2-1),(postesoroy*2-1)))){
		 	postesorox= (int) (Math.random() * X + 1);
			postesoroy= (int) (Math.random() * Y + 1);
		}
		listaCasillas.add(casillaImpl.crearCasilla(tablero,(postesorox*2-1),(postesoroy*2-1),Constants.TESORO));
		
	  	//Crear n pozos
	  	for(int i=0;i<tablero.getNumPozos();i++){
	  		//Crear los pozos
			int poshoyox= (int) (Math.random() * X + 1);
			int poshoyoy= (int) (Math.random() * Y + 1);
			//comprobar que no caiga donde esta el cazador y el wumpu
			while(!" ".equals(casillaImpl.obtenerValorCasilla(listaCasillas,(poshoyox*2-1),(poshoyoy*2-1)))){
				poshoyox= (int) (Math.random() * X + 1);
			 	poshoyoy= (int) (Math.random() * Y + 1);
			}
	  		listaCasillas.add(casillaImpl.crearCasilla(tablero,(poshoyox*2-1),(poshoyoy*2-1),Constants.POZO));
	  	}
	  	List<CasillaDTO> listaCasillasFinal = casillaImpl.asignarPercepciones(listaCasillas, tablero);
	  	tablero.setListaCasillas(listaCasillasFinal);
	}
	
	/**
	 * Mostrar el tablero por la consola.
	 * 
	 * @param matriz
	 * @param tablero
	 */
	private void pintarTablero(String[][] matriz,TableroDTO tablero) {

		System.out.println("\n");
		for (int x=0; x < matriz.length; x++) {
		  for (int y=0; y < matriz[x].length; y++) {
			  //Buscamos el valor de la casilla vacia y la puerta de salida
			  if(" ".equals(matriz[x][y]) || (tablero.getDimensionX()==x && y==1)){
				  System.out.print(casillaImpl.obtenerValorCasilla(tablero.getListaCasillas(),x,y));
			  }else{
				  System.out.print (matriz[x][y]);
			  }
		    if (y!=matriz[x].length-1){ 
		    	System.out.print(" ");
		     	
		    }else{
		    	System.out.print("\n");
		    }
		  }
		}
		
		System.out.println("\n");
	           

	    }
	
	/**
	 * Muestra la opciones del juego por consola
	 */
	private void getOpcionesJuego(){
		System.out.print("\nInstrucciones que realiza nuestro cazador:\n");
		System.out.print("- Pulsa 'X' para avanazar\n");
		System.out.print("- Pulsa 'W' para mirar hacia arriba\n");
		System.out.print("- Pulsa 'A' para mirar hacia la izquierda\n");
		System.out.print("- Pulsa 'D' para mirar hacia la derecha\n");
		System.out.print("- Pulsa 'S' para mirar hacia abajo\n");
		System.out.print("- Pulsa 'F' para lanzar flechas \n");
		System.out.print("- Pulsa 'I' para mirar las opciones \n\n");
	}
	
	/**
	 * Comienza el juego.
	 * 
	 * @param matriz
	 * @param tablero
	 */
	private void comienzaJuego(String[][] matriz, TableroDTO tablero){
		System.out.print("************************\n");
		System.out.print("*  Comienza el juego   *\n");
		System.out.print("************************\n\n");
		
		getOpcionesJuego();
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		PersonaDTO persona = new PersonaDTO();
		persona.setOrientacion(Constants.ORIENTACION_NORTE);
		persona.setPosX(tablero.getDimensionX()-1);
		persona.setPosY(1);
		persona.setTieneTesoro(false);
		persona.setEstaVivo(true);
		persona.setPuedoSalir(false);
		
		System.out.print("\nIngresa el número de fechas: ");
		String numFlechas = scanner.nextLine();
		while(!Utils.isNumeric(numFlechas) || Integer.parseInt(numFlechas)<0){
			System.out.print(Constants.ERROR_NUMERICO);
			System.out.print("\nIngresa el número de fechas: ");
			numFlechas = scanner.nextLine();
		}
		persona.setNumFechas(Integer.parseInt(numFlechas));
		
	  	System.out.print(infoCasilla(casillaImpl.obtenerCasilla(tablero.getListaCasillas(),persona.getPosX(),persona.getPosY())));
		System.out.print("Usted está orientado hacia el Norte.\n");
		
		while(persona.isEstaVivo() && !persona.isPuedoSalir()){ 
			System.out.print("Escoja una acción: ");
			
			String action = scanner.nextLine();
			switch (action.toUpperCase()) {
	         case "F":
	        	 if(persona.getNumFechas()>0){
					persona.setNumFechas(persona.getNumFechas()-1);
					String orientacion = persona.getOrientacion();
					boolean eliminado = false;
		        	switch (orientacion){
		        	 case Constants.ORIENTACION_NORTE:
		        		 eliminado = comprobarEliminarWumpus(tablero,persona,-2,0);
		        		 break;
		        	 case Constants.ORIENTACION_SUR:
		        		 eliminado = comprobarEliminarWumpus(tablero,persona,+2,0);
		        		 break;
		        	 case Constants.ORIENTACION_ESTE:
		        		 eliminado = comprobarEliminarWumpus(tablero,persona,0,+2);
		        		 break;
		        	 case Constants.ORIENTACION_OESTE:
		        		 eliminado = comprobarEliminarWumpus(tablero,persona,0,-2);
		        		 break;
		        	}
		        	if(eliminado){
		        		pintarTablero(matriz,tablero);
		        	}
					
				 }
				 System.out.print("Te quedan "+persona.getNumFechas()+" fechas.\n");
	             break;
	         case "W":
	        	 persona.setOrientacion(Constants.ORIENTACION_NORTE);
	        	 System.out.print("Usted está orientado hacia el Norte.\n");
	             break;
	         case "A":
	        	 persona.setOrientacion(Constants.ORIENTACION_OESTE);
	        	 System.out.print("Usted está orientado hacia el Oeste.\n");
	             break;
	         case "D":
	        	 persona.setOrientacion(Constants.ORIENTACION_ESTE);
	        	 System.out.print("Usted está orientado hacia el Este.\n");
	             break;
	         case "S":
	        	 persona.setOrientacion(Constants.ORIENTACION_SUR);
	        	 System.out.print("Usted está orientado hacia el Sur.\n");
	             break;
	         case "I":
	        	 getOpcionesJuego();
	        	 break;
	         case "X":
	        	 String orientacion = persona.getOrientacion();
	        	 switch (orientacion){
		        	 case Constants.ORIENTACION_NORTE:
		        		 if(persona.getPosX() != 1){
		        			 evaluarMovimiento(tablero,persona,-2,0);
		        		 }
		        		 break;
		        	 case Constants.ORIENTACION_SUR:
		        		 if(persona.getPosX() != (tablero.getDimensionX()-1)){
		        			 evaluarMovimiento(tablero,persona,+2,0);
		        		   }else if((persona.getPosX() == (tablero.getDimensionX()-1) && persona.getPosY()==1)
		        				   && !persona.isTieneTesoro()){
		       				System.out.print("Tienes que volver a recoger el tesoro.\n");
		        		   }else if((persona.getPosX() == (tablero.getDimensionX()-1) && persona.getPosY()==1)
		        				   && persona.isTieneTesoro()){
		        			   persona.setPuedoSalir(true);
		        		   }
		        		 break;
		        	 case Constants.ORIENTACION_ESTE:
		        		 if(persona.getPosY() != (tablero.getDimensionY()-1)){
		        			 evaluarMovimiento(tablero,persona,0,+2);
		        		 }
		        		 break;
		        	 case Constants.ORIENTACION_OESTE:
		        		 if(persona.getPosY() != 1){
		        			 evaluarMovimiento(tablero,persona,0,-2);
		        		 }
		        		 break;
	        	 }
	        	 if(persona.isEstaVivo() && !persona.isPuedoSalir()){
	        		 pintarTablero(matriz,tablero);
	        	 }
	             break;
	         default:
	        	 System.out.print("La opción elegida no es correcta.\n");
	     }
			
		}
		if(persona.isEstaVivo() && persona.isPuedoSalir()){
			 System.out.print("WINNER.\n");
		}
		 System.out.print("\n¿Quieres volver a jugar?(S/N)\n");
		 String nextTime = scanner.nextLine();
		 while(!Constants.SI.equals(nextTime.toUpperCase()) && !Constants.NO.equals(nextTime.toUpperCase())){
			 System.out.print("\nIntroduce S o N.\n");
			 nextTime = scanner.nextLine();
		 }
		 if(Constants.SI.equals(nextTime.toUpperCase())){
			 crearTablero();
		 }else if(Constants.NO.equals(nextTime.toUpperCase())){
			 System.out.print("\nBYE BYE \n");
		 }
		

	}
	
	/**
	 * Comprobar que el wumpus ha sido eliminado.
	 * @param tablero
	 * @param persona
	 * @param incrementoX
	 * @param incrementoY
	 * @return
	 */
	private boolean comprobarEliminarWumpus(TableroDTO tablero, PersonaDTO persona, int incrementoX, int incrementoY){
		boolean eliminado = false;
		CasillaDTO cas = casillaImpl.obtenerCasilla(tablero.getListaCasillas(),(persona.getPosX()+incrementoX),(persona.getPosY()+incrementoY));
		if(cas!=null){
			if(Constants.WUMPUS.equals(cas.getValue())){
				System.out.print("Has eliminado al WUMPUS.\n");
				quitarHedor(tablero.getListaCasillas(), cas);
				cas.setValue(actualizarValorCasilla(cas));
				cas.setOriginalValue(actualizarValorCasilla(cas));
				eliminado = true;
			}
		}
		CasillaDTO casActual = casillaImpl.obtenerCasilla(tablero.getListaCasillas(),persona.getPosX(),persona.getPosY());
		casActual.setValue(Constants.PERSONA);
		return eliminado;
	}
	
	/**
	 * Cuando se efectua un movimiento o cambio de casilla se evaluan los movimientos.
	 * @param tablero
	 * @param persona
	 * @param incrementoX
	 * @param incrementoY
	 */
	private void evaluarMovimiento(TableroDTO tablero, PersonaDTO persona, int incrementoX, int incrementoY){

		CasillaDTO cas = casillaImpl.obtenerCasilla(tablero.getListaCasillas(),(persona.getPosX()+incrementoX),(persona.getPosY()+incrementoY));
		if(cas!=null){
			if(Constants.WUMPUS.equals(cas.getValue())||Constants.POZO.equals(cas.getValue())){
				persona.setEstaVivo(false);
				System.out.print("GAME OVER.\n");
			}else if(Constants.TESORO.equals(cas.getValue())){
				persona.setTieneTesoro(true);
				cas.setValue(actualizarValorCasilla(cas));
				cas.setOriginalValue(actualizarValorCasilla(cas));
				System.out.print("ENHORABUENA, has conseguido el tesoro, busca la salida.\n");
				quitarBrillo(tablero.getListaCasillas(),cas);
			}else if("_".equals(cas.getValue()) && persona.isTieneTesoro()){
				persona.setPuedoSalir(true);
			}
			cas.setValue(Constants.PERSONA);
			System.out.print(infoCasilla(cas));
		}else{
			CasillaDTO newCasilla = casillaImpl.crearCasilla(tablero,(persona.getPosX()+incrementoX),(persona.getPosY()+incrementoY),Constants.PERSONA);
			List<CasillaDTO> listaCas = tablero.getListaCasillas();
			listaCas.add(newCasilla);
			tablero.setListaCasillas(listaCas);
		}
		
		//Actualizar tablero
		CasillaDTO casActual = casillaImpl.obtenerCasilla(tablero.getListaCasillas(),persona.getPosX(),persona.getPosY());
		if(Constants.PERSONA.equals(casActual.getOriginalValue())){
			casActual.setValue(actualizarValorCasilla(casActual));
		}else if(Constants.TESORO.equals(casActual.getOriginalValue())){
				casActual.setValue(actualizarValorCasilla(casActual));
		}else{
			casActual.setValue(casActual.getOriginalValue());
			
		}
		persona.setPosX(persona.getPosX()+incrementoX);
		persona.setPosY(persona.getPosY()+incrementoY);
		
		
	}
	
	/**
	 * Muestra por consola las precepciones de la casilla si las tuviese
	 * @param casilla
	 * @return
	 */
	private String infoCasilla(CasillaDTO casilla){
		String result = "Info: \n";
		int count = 0;
		if(casilla.isHedor()){
			count++;
			result = result+"- Se huele el hedor del wumpus\n";
		}
		if(casilla.isBrisa()){
			count++;
			result = result+"- Se percibe la brisa del pozo\n";
		}
		if(casilla.isBrillo()){
			count++;
			result = result+"- Se percibe el brillo del tesoro\n";
		}
		if(count == 0){
			return "";
		}
		return result;
		
	}
	
	/**
	 * Cuando salimos de la casilla, o borramos algun elemento actualizamos el valor de la casilla.
	 * 
	 * @param casilla
	 * @return
	 */
	private String actualizarValorCasilla (CasillaDTO casilla){
		String result = " ";
		if(casilla.isHedor()){
			result ="Ç";
    	}else if(casilla.isBrisa()){
    		result ="~";
    	}else if(casilla.isBrillo()){
    		result ="&";
    	}
		return result;
	}
	
	/**
	 * Cuando la persona recoge el tesoro se elimina del tablero la percepcion de brillo.
	 * @param listaCasillas
	 * @param casilla
	 */
	private void quitarBrillo(List<CasillaDTO> listaCasillas,CasillaDTO casilla){
		//izquierda
        if(!casilla.isParedIzq()){
        	CasillaDTO casillaIzq = casillaImpl.obtenerCasilla(listaCasillas, (casilla.getPosX()-2),casilla.getPosY());
        	casillaIzq.setBrillo(false);
        	if(!Constants.WUMPUS.equals(casillaIzq.getValue())&&!Constants.POZO.equals(casillaIzq.getValue())){
        		casillaIzq.setValue(actualizarValorCasilla(casillaIzq));
        		casillaIzq.setOriginalValue(actualizarValorCasilla(casillaIzq));
        	}
        }
        //Arriba
        if(!casilla.isParedArr()){
        	CasillaDTO casillaArr = casillaImpl.obtenerCasilla(listaCasillas, casilla.getPosX(),(casilla.getPosY()-2));
        	casillaArr.setBrillo(false);
        	if(!Constants.WUMPUS.equals(casillaArr.getValue())&&!Constants.POZO.equals(casillaArr.getValue())){
        		casillaArr.setValue(actualizarValorCasilla(casillaArr));
        		casillaArr.setOriginalValue(actualizarValorCasilla(casillaArr));
        	}
        }
        //Derecha
        if(!casilla.isParedDer()){
        	CasillaDTO casillaDer = casillaImpl.obtenerCasilla(listaCasillas,(casilla.getPosX()+2),casilla.getPosY());
        	casillaDer.setBrillo(false);
        	if(!Constants.WUMPUS.equals(casillaDer.getValue())&&!Constants.POZO.equals(casillaDer.getValue())){
        		casillaDer.setValue(actualizarValorCasilla(casillaDer));
        		casillaDer.setOriginalValue(actualizarValorCasilla(casillaDer));
        	}
        }
        //Abajo
        if(!casilla.isParedAba()){
        	CasillaDTO casillaAba = casillaImpl.obtenerCasilla(listaCasillas,casilla.getPosX(), (casilla.getPosY()+2));
        	casillaAba.setBrillo(false);
        	if(!Constants.WUMPUS.equals(casillaAba.getValue())&&!Constants.POZO.equals(casillaAba.getValue())){
        		casillaAba.setValue(actualizarValorCasilla(casillaAba));
        		casillaAba.setOriginalValue(actualizarValorCasilla(casillaAba));
        	}
        }
	}
	
	/**
	 * Cuando se eliminar el wumpus se elimina el hedor del tablero.
	 * @param listaCasillas
	 * @param casilla
	 */
	private void quitarHedor(List<CasillaDTO> listaCasillas,CasillaDTO casilla){
		//izquierda
		if(!casilla.isParedIzq()){
			CasillaDTO casillaIzq = casillaImpl.obtenerCasilla(listaCasillas, (casilla.getPosX()-2),casilla.getPosY());
			casillaIzq.setHedor(false);
			if(!Constants.TESORO.equals(casillaIzq.getValue())&&!Constants.POZO.equals(casillaIzq.getValue())){
				casillaIzq.setValue(actualizarValorCasilla(casillaIzq));
				casillaIzq.setOriginalValue(actualizarValorCasilla(casillaIzq));
			}
		}
		//Arriba
		if(!casilla.isParedArr()){
			CasillaDTO casillaArr = casillaImpl.obtenerCasilla(listaCasillas, casilla.getPosX(),(casilla.getPosY()-2));
			casillaArr.setHedor(false);
			if(!Constants.TESORO.equals(casillaArr.getValue())&&!Constants.POZO.equals(casillaArr.getValue())){
				casillaArr.setValue(actualizarValorCasilla(casillaArr));
				casillaArr.setOriginalValue(actualizarValorCasilla(casillaArr));
			}
		}
		//Derecha
		if(!casilla.isParedDer()){
			CasillaDTO casillaDer = casillaImpl.obtenerCasilla(listaCasillas,(casilla.getPosX()+2),casilla.getPosY());
			casillaDer.setHedor(false);
			if(!Constants.TESORO.equals(casillaDer.getValue())&&!Constants.POZO.equals(casillaDer.getValue())){
				casillaDer.setValue(actualizarValorCasilla(casillaDer));
				casillaDer.setOriginalValue(actualizarValorCasilla(casillaDer));
			}
		}
		//Abajo
		if(!casilla.isParedAba()){
			CasillaDTO casillaAba = casillaImpl.obtenerCasilla(listaCasillas,casilla.getPosX(), (casilla.getPosY()+2));
			casillaAba.setHedor(false);
			if(!Constants.TESORO.equals(casillaAba.getValue())&&!Constants.POZO.equals(casillaAba.getValue())){
				casillaAba.setValue(actualizarValorCasilla(casillaAba));
				casillaAba.setOriginalValue(actualizarValorCasilla(casillaAba));
			}
		}
	}

}
