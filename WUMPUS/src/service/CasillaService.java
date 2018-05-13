package service;

import java.util.List;

import dto.CasillaDTO;
import dto.TableroDTO;

public interface CasillaService {
	/**
	 * Crear la casilla del tablero en la posicion indicada y con su valor
	 * 
	 * @param tablero
	 * @param posX
	 * @param posY
	 * @param value
	 * @return
	 */
	public CasillaDTO crearCasilla (TableroDTO tablero, int posX, int posY,String value);

	/**
	 * Obtiene el valor que se muestra en la casilla
	 * 
	 * @param listaCasillas
	 * @param x
	 * @param y
	 * @return
	 */
	public String obtenerValorCasilla(List<CasillaDTO> listaCasillas, int x, int y);
	
	/**
	 * Obtiene todos los parámetros de la casilla casilla 
	 * 
	 * @param listaCasillas
	 * @param x
	 * @param y
	 * @return CasillaDTO
	 */
	public CasillaDTO obtenerCasilla(List<CasillaDTO> listaCasillas, int x, int y);
	
	/**
	 * Asignar las casillas de las percepciones
	 * 
	 * @param listaCasillas
	 * @param tablero
	 * @return
	 */
	public List<CasillaDTO> asignarPercepciones(List<CasillaDTO> listaCasillas, TableroDTO tablero);
	
}
