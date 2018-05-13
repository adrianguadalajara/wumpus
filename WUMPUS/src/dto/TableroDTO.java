package dto;

import java.util.List;

public class TableroDTO {
	
	public TableroDTO() {
		super();
	}

	/**Casillas X del tablero*/
	private int numCasillasX;
	
	/**Casillas Y del tablero*/
	private int numCasillasY;
	
	/**Dimension X del tablero*/
	private int dimensionX;
	
	/**Dimension Y del tablero*/
	private int dimensionY;
	
	/**Numero de pozos del tablero*/
	private int numPozos;
	
	/**Lista de casillas del tablero*/
	private List<CasillaDTO> listaCasillas;

	public int getNumCasillasX() {
		return numCasillasX;
	}

	public void setNumCasillasX(int numCasillasX) {
		this.numCasillasX = numCasillasX;
	}

	public int getNumCasillasY() {
		return numCasillasY;
	}

	public void setNumCasillasY(int numCasillasY) {
		this.numCasillasY = numCasillasY;
	}

	public int getDimensionX() {
		return dimensionX;
	}

	public void setDimensionX(int dimensionX) {
		this.dimensionX = dimensionX;
	}

	public int getDimensionY() {
		return dimensionY;
	}

	public void setDimensionY(int dimensionY) {
		this.dimensionY = dimensionY;
	}

	public int getNumPozos() {
		return numPozos;
	}

	public void setNumPozos(int numPozos) {
		this.numPozos = numPozos;
	}

	public List<CasillaDTO> getListaCasillas() {
		return listaCasillas;
	}

	public void setListaCasillas(List<CasillaDTO> listaCasillas) {
		this.listaCasillas = listaCasillas;
	}
	
	
	
	
	
}
