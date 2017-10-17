package aeronaus;

public class Aeronau {
	private String model;
	private String fabricant;
	private int capacitat;
	private int x;
	private int y;
	private String matricula;
	private boolean motorEnces = false;
	private int velocitat = 0;
	private int alcada = 0;
	private int rumb;
	private boolean aparcat = true;
	private boolean trenAterratgeDesplegat = true;
	
	public Aeronau(String model, String fabricant, int capacitat, int X, int Y, String matricula) {
		this.model = model;
		this.fabricant = fabricant;
		this.capacitat = capacitat;
		this.x = X;
		this.y = Y;
		this.matricula = matricula;
	}
	
	
	//Setters
	
	public void setMotorEnces(boolean estat) {
	this.motorEnces = estat;
	}
	
	public void setVelocitat(int velocitat) {
		this.velocitat = velocitat;
	}
	
	public void setAlcada(int alcada) {
		this.alcada = alcada;
	}
	
	public void setAparcat(boolean aparcat) {
		this.aparcat = aparcat;		
	}
	
	public void setRumb(int rumb) {
		this.rumb = rumb;
	}
	
	public void setTrenAterratgeDesplegat(boolean trenAterratge) {
		this.trenAterratgeDesplegat = trenAterratge;
	}
	
	public void setXY(int X, int Y){
		this.x = X;
		this.y = Y;
	}

	//Getters
	
	public String getModel() {
		return this.model;
	}
	
	public String getFabricant() {
		return this.fabricant;
	}
	
	public int getCapacitat() {
		return this.capacitat;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public String getMatricula() {
		return this.matricula;
	}
	
	public boolean getMotorEnces() {
		return this.motorEnces;
	}
	public int getVelocitat() {
		return this.velocitat;
	}
	
	public int getAlcada() {
		return this.alcada;
	}

	public boolean getAparcat() {
		return this.aparcat;
	}
	
	public int getRumb() {
		return this.rumb;
	}
	
	public boolean getTrenAterratgeDesplegat() {
		return this.trenAterratgeDesplegat;
	}
}
