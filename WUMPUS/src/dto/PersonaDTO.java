package dto;

public class PersonaDTO {
	
	public PersonaDTO() {
		super();
	}

	/** Valor de la casilla*/
	private String orientacion; 
	
	/** numero de flechas*/
	private int numFechas;
	
	/** posición X de la persona*/
	private int posX;
	
	/**  posición Y de la persona*/
	private int posY;
	
	/**  Si la persona tiene el tesoro*/
	private boolean tieneTesoro;
	
	/**  Si la persona esta viva*/
	private boolean estaVivo;
	
	/**  Si la persona tiene el tesoro y puede salir para terminar el juego*/
	private boolean puedoSalir;

	public String getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(String orientacion) {
		this.orientacion = orientacion;
	}

	public int getNumFechas() {
		return numFechas;
	}

	public void setNumFechas(int numFechas) {
		this.numFechas = numFechas;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public boolean isTieneTesoro() {
		return tieneTesoro;
	}

	public void setTieneTesoro(boolean tieneTesoro) {
		this.tieneTesoro = tieneTesoro;
	}

	public boolean isEstaVivo() {
		return estaVivo;
	}

	public void setEstaVivo(boolean estaVivo) {
		this.estaVivo = estaVivo;
	}

	public boolean isPuedoSalir() {
		return puedoSalir;
	}

	public void setPuedoSalir(boolean puedoSalir) {
		this.puedoSalir = puedoSalir;
	}
	
}
