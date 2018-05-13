package dto;

public class CasillaDTO {
	
	public CasillaDTO() {
		super();
	}

	/** Valor de la casilla*/
	private String value; 
	
	/** Valor original de la casilla*/
	private String originalValue; 
	
	/** Posicion horizontal  de la casilla*/
	private int posX; 
	
	/** Posicion horizontal  de la casilla*/
	private int posY;  
	
	/** Si la casilla tiene pared por la izquierda*/
	private boolean paredIzq;  
	
	/** Si la casilla tiene pared por la derecha*/
	private boolean paredDer;  
	
	/** Si la casilla tiene pared por la arriba*/
	private boolean paredArr;  
	
	/** Si la casilla tiene pared por la abajo*/
	private boolean paredAba; 
	
	/** Si la casilla tiene hedor del wumpus nos indica si el wumpus esta cerca*/
	private boolean hedor; 
	
	/** Si la casilla tiene brisa nos indica si hay un pozo cerca*/
	private boolean brisa; 
	
	/** Si la casilla tiene brillo nos indica que el tesoro esta cerca*/
	private boolean brillo;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOriginalValue() {
		return originalValue;
	}

	public void setOriginalValue(String originalValue) {
		this.originalValue = originalValue;
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

	public boolean isParedIzq() {
		return paredIzq;
	}

	public void setParedIzq(boolean paredIzq) {
		this.paredIzq = paredIzq;
	}

	public boolean isParedDer() {
		return paredDer;
	}

	public void setParedDer(boolean paredDer) {
		this.paredDer = paredDer;
	}

	public boolean isParedArr() {
		return paredArr;
	}

	public void setParedArr(boolean paredArr) {
		this.paredArr = paredArr;
	}

	public boolean isParedAba() {
		return paredAba;
	}

	public void setParedAba(boolean paredAba) {
		this.paredAba = paredAba;
	}


	public boolean isHedor() {
		return hedor;
	}

	public void setHedor(boolean hedor) {
		this.hedor = hedor;
	}

	public boolean isBrisa() {
		return brisa;
	}

	public void setBrisa(boolean brisa) {
		this.brisa = brisa;
	}

	public boolean isBrillo() {
		return brillo;
	}

	public void setBrillo(boolean brillo) {
		this.brillo = brillo;
	}

	
	
}
