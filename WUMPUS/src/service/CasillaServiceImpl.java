package service;

import java.util.ArrayList;
import java.util.List;

import comun.Constants;
import dto.CasillaDTO;
import dto.TableroDTO;



public class CasillaServiceImpl implements CasillaService {
	
	
	public CasillaDTO crearCasilla (TableroDTO tablero, int posX, int posY,String value){
		
		CasillaDTO casilla = new CasillaDTO();
		casilla.setPosX(posX);
		casilla.setPosY(posY);
		casilla.setValue(value);
		casilla.setOriginalValue(value);
		asignarParedes(tablero,casilla,posX,posY);
		
		return casilla;

	}
	
	/**
	 * Comprobamos si la casilla tiene alguna pared del tablero
	 * 
	 * @param tablero
	 * @param casilla
	 * @param posX
	 * @param posY
	 */
	private void asignarParedes (TableroDTO tablero,CasillaDTO casilla, int posX, int posY ){
		boolean paredArr = false;
		boolean paredIzq = false;
		boolean paredDer = false;
		boolean paredAba = false;
		if(posX == 1){
			paredIzq = true;
		}
		if(posY == 1){
			paredArr = true;
		}
		if(posX == tablero.getDimensionX()-1){
			paredDer = true;
		}
		if(posY == tablero.getDimensionY()-1){
			paredAba = true;
		}
		casilla.setParedArr(paredArr);
		casilla.setParedIzq(paredIzq);
		casilla.setParedDer(paredDer);
		casilla.setParedAba(paredAba);
		
	}
	public String obtenerValorCasilla(List<CasillaDTO> listaCasillas, int x, int y){
		String result = " ";
		if(listaCasillas!= null){
			for (CasillaDTO casilla : listaCasillas) {
				if(casilla.getPosX()==x && casilla.getPosY()==y){
					result = casilla.getValue();
				}
			}
		}
		return result;
	}	
	public CasillaDTO obtenerCasilla(List<CasillaDTO> listaCasillas, int x, int y){
		CasillaDTO result = null;
		if(listaCasillas!= null){
			for (CasillaDTO casilla : listaCasillas) {
				if(casilla.getPosX()==x && casilla.getPosY()==y){
					result = casilla;
				}
			}
		}
		return result;
	}	
	public List<CasillaDTO> asignarPercepciones(List<CasillaDTO> listaCasillas, TableroDTO tablero){
		List<CasillaDTO> result = new ArrayList<CasillaDTO>();
		for (CasillaDTO casilla : listaCasillas) {
			//izquierda
	        if(!casilla.isParedIzq()){
	        	actualizarPrecepciones(listaCasillas, result,(casilla.getPosX()-2),casilla.getPosY(), casilla.getValue());
	        }
	        //Arriba
	        if(!casilla.isParedArr()){
	        	actualizarPrecepciones(listaCasillas, result,casilla.getPosX(),(casilla.getPosY()-2), casilla.getValue());
	        }
	        //Derecha
	        if(!casilla.isParedDer()){
	        	actualizarPrecepciones(listaCasillas, result,(casilla.getPosX()+2),casilla.getPosY(), casilla.getValue());
	        }
	        //Abajo
	        if(!casilla.isParedAba()){
	        	actualizarPrecepciones(listaCasillas, result,casilla.getPosX(), (casilla.getPosY()+2), casilla.getValue());
	        }
		}
		for (CasillaDTO casillaDTO : result) {
			listaCasillas.add(casillaDTO);
		}
		return listaCasillas;
	}
	
	/**
	 * Actualiza la percepciones segun el tipo de casilla
	 * @param listaCasillas
	 * @param result
	 * @param x
	 * @param y
	 * @param tipo
	 */
	private void actualizarPrecepciones(List<CasillaDTO> listaCasillas,List<CasillaDTO> result, int x, int y, String tipo){

		if(Constants.WUMPUS.equals(tipo)){
			actualizarPrecepcionesWumpus(listaCasillas, result, x, y);
		}else if(Constants.POZO.equals(tipo)){
			actualizarPrecepcionesPozo(listaCasillas, result, x, y);
		} else if(Constants.TESORO.equals(tipo)){
			actualizarPrecepcionesTesoro(listaCasillas, result, x, y);
		}
		
	}
	/**
	 * Actualiza las percepciones(Hedor) del wumpus
	 * 
	 * @param listaCasillas
	 * @param result
	 * @param x
	 * @param y
	 */
	private void actualizarPrecepcionesWumpus(List<CasillaDTO> listaCasillas,List<CasillaDTO> result, int x, int y){
    	if(" ".equals(obtenerValorCasilla(listaCasillas,x,y))){
    		result.add(crearCasillaHedor(x,y));
    	}else{
    		actualizarCasillaConHedor(listaCasillas,x,y);
    	}
	}
	
	/**
	 * Actualiza las percepciones(Brisas) de los pozos
	 * @param listaCasillas
	 * @param result
	 * @param x
	 * @param y
	 */
	private void actualizarPrecepcionesPozo(List<CasillaDTO> listaCasillas,List<CasillaDTO> result, int x, int y){
		if(" ".equals(obtenerValorCasilla(listaCasillas,x,y))){
			result.add(crearCasillaBrisa(x,y));
		}else{
			actualizarCasillaConBrisa(listaCasillas,x,y);
		}
	}
	
	/**
	 * Actualiza las percepciones(Brillo) del tesoro
	 * @param listaCasillas
	 * @param result
	 * @param x
	 * @param y
	 */
	private void actualizarPrecepcionesTesoro(List<CasillaDTO> listaCasillas,List<CasillaDTO> result, int x, int y){
		if(" ".equals(obtenerValorCasilla(listaCasillas,x,y))){
			result.add(crearCasillaBrillo(x,y));
		}else{
			actualizarCasillaConBrillo(listaCasillas,x,y);
		}
	}
	
	/**
	 * Crear la casilla del hedor
	 * @param posX
	 * @param posY
	 * @return
	 */
	private CasillaDTO crearCasillaHedor (int posX, int posY){
		
		CasillaDTO casilla = new CasillaDTO();
		casilla.setPosX(posX);
		casilla.setPosY(posY);
		casilla.setValue("Ç");
		casilla.setOriginalValue("Ç");
		casilla.setHedor(true);
	
		return casilla;
	}
	
	/**
	 * Actualiza el parametro "Hedor" de la casilla
	 * @param listaCasillas
	 * @param x
	 * @param y
	 */
	private void actualizarCasillaConHedor(List<CasillaDTO> listaCasillas, int x, int y){
		if(listaCasillas!= null){
			for (CasillaDTO casilla : listaCasillas) {
				if(casilla.getPosX()==x && casilla.getPosY()==y){
					casilla.setHedor(true);
				}
			}
		}
	}
	
	/**
	 * Crea una casilla para la brisa
	 * 
	 * @param posX
	 * @param posY
	 * @return
	 */
	private CasillaDTO crearCasillaBrisa (int posX, int posY){
		
		CasillaDTO casilla = new CasillaDTO();
		casilla.setPosX(posX);
		casilla.setPosY(posY);
		casilla.setValue("~");
		casilla.setOriginalValue("~");
		casilla.setBrisa(true);
		
		return casilla;
	}
	
	/**
	 * Actualiza la propuedad "Brisa" de la casilla 
	 * @param listaCasillas
	 * @param x
	 * @param y
	 */
	private void actualizarCasillaConBrisa(List<CasillaDTO> listaCasillas, int x, int y){
		if(listaCasillas!= null){
			for (CasillaDTO casilla : listaCasillas) {
				if(casilla.getPosX()==x && casilla.getPosY()==y){
					casilla.setBrisa(true);
				}
			}
		}
	}
	
	/**
	 * Crea una casilla de brillo
	 * @param posX
	 * @param posY
	 * @return
	 */
	private CasillaDTO crearCasillaBrillo (int posX, int posY){
		
		CasillaDTO casilla = new CasillaDTO();
		casilla.setPosX(posX);
		casilla.setPosY(posY);
		casilla.setValue("$");
		casilla.setOriginalValue("$");
		casilla.setBrillo(true);
		
		return casilla;
	}
	
	/**
	 * Se actualiza la propiedad "Brillo" de la celda
	 * @param listaCasillas
	 * @param x
	 * @param y
	 */
	private void actualizarCasillaConBrillo(List<CasillaDTO> listaCasillas, int x, int y){
		if(listaCasillas!= null){
			for (CasillaDTO casilla : listaCasillas) {
				if(casilla.getPosX()==x && casilla.getPosY()==y){
					casilla.setBrillo(true);
				}
			}
		}
	}
	
	
	

}
