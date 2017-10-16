import java.util.Scanner;

public class SistemaControl {

	Scanner e = new Scanner(System.in);
	Aeronau[] aeronaus = new Aeronau[5];
	
	public static void main(String[] args) {
		SistemaControl SC = new SistemaControl();
		SC.SControl();
	}
		
	public void SControl() {
		
		char menuNum;
		
		do {
			System.out.println("1- Donar d'alta avió al sistema");
			System.out.println("2- Gestiona un dels avions");
			System.out.println("3- Manteniment de l'espai aeri");
			System.out.println("4- Informació de l'espai aeri");
			System.out.println("5- Sortir");
			
			menuNum = e.next().charAt(0);			
			switch(menuNum) {
			case '1':
				int posicioLliure = posicioLliure();
				if(posicioLliure != 5) {
					crearAeronau(posicioLliure);
				}
				else {
					System.out.println("No hi han espais lliures");
				}
				
				break;
			case '2':
				int aeronau;
					aeronau = seleccionarNau();
					if(aeronaus[aeronau] == null) {
						System.out.println("No hi ha ninguna nau aqui");
					}
					else {
						gestionarNau(aeronau);
						mantenimentAeri();
						infoAeri();
					}
				break;
			case '3': 
				mantenimentAeri();
				break;
			case '4':
				infoAeri();
				break;
			}
		}
		while(menuNum!='5');
	}
	
	private void infoAeri() {
		
	}

	private void mantenimentAeri() {
		
	}

	public int seleccionarNau() {
		int aeronau = 5;
		System.out.println("Selecciona quina aeronau vols utilitzar:");
		System.out.println();
		for(int c = 0; c < 5; c++) {
			if(aeronaus[c] != null) {
				System.out.println(c+1 + "- " + aeronaus[c].getMatricula());
			}
		}
		System.out.println();
		aeronau = e.nextInt();
		return aeronau-1;
	}
	
	public int posicioLliure() {
		int posicioLliure = 5;
		
		for(int c = 0; c < 5; c++) {
			if(aeronaus[c] == null) {
				posicioLliure = c;
				break;
			}
		}
		return posicioLliure;
	}
	
	public void crearAeronau(int posicioLliure) {
		System.out.println("Introdueix model: ");
		String model = e.next();
		System.out.println("Introdueix fabricant: ");
		String fabricant = e.next();
		System.out.println("Introdueix capacitat: ");
		int capacitat = e.nextInt();
		System.out.println("Introdueix X: ");
		int X = e.nextInt();
		System.out.println("Introdueix Y: ");
		int Y = e.nextInt();
		System.out.println("Introdueix matricula: ");
		String matricula = e.next();
		
		aeronaus[posicioLliure] = new Aeronau(model, fabricant, capacitat, X, Y, matricula);
	}

	public void gestionarNau(int aeronau) {
		aeronaus[aeronau].setAparcat(false);
		char accio;
		do {
			System.out.println("Q - Pujar / Baixar tren aterratge");
			System.out.println("W - Accelerar / Frenar");
			System.out.println("E - Encendre / Apagar motor");
			System.out.println("R - Posicionar X, Y");
			System.out.println("A - Agafar / Baixar alçada");
			System.out.println("S - Aparcar");
			System.out.println("D - Establir rumb");
			System.out.println("F - Finalitzar operativa");
			
			accio = e.next().charAt(0);
			switch(accio) {
			case 'e':
			case 'E':
				motorOnOff(aeronau);
				break;
			case 'w':
			case 'W':
				setVelocitat(aeronau);
				break;
			case 'a':
			case 'A':
				setAlcada(aeronau);
				break;
			case 'q':
			case 'Q':
				setTrenAterratge(aeronau);
				break;
			case 'd':
			case 'D':
				setRumb(aeronau);
				break;
			case 's':
			case 'S':
				setAparcat(aeronau);
				if(aeronaus[aeronau].getAparcat()) {
					accio = 'p';
				}
				break;
			}
		}
		while(accio != 'f' && accio != 'F');
	}

	public void motorOnOff(int aeronau) {
		if(!aeronaus[aeronau].getMotorEnces()) {
			aeronaus[aeronau].setMotorEnces(true);
			System.out.println("Motor ences");
		}
		else if(aeronaus[aeronau].getVelocitat() == 0 && aeronaus[aeronau].getAlcada() == 0 && aeronaus[aeronau].getTrenAterratgeDesplegat()) {
			aeronaus[aeronau].setMotorEnces(false);
			System.out.println("Motor apagat");
		}
		else {
			if(aeronaus[aeronau].getVelocitat() > 0) {
				System.out.println("Baixa la velocitat a 0");
			}
			if(aeronaus[aeronau].getAlcada() > 0) {
				System.out.println("Baixa l'alçada a 0");
			}
			if(!aeronaus[aeronau].getTrenAterratgeDesplegat()) {
				System.out.println("Treu el tren d'aterratge");
			}
		}
	}

	public void setVelocitat(int aeronau) {
		if(aeronaus[aeronau].getMotorEnces()) {
			System.out.println("Introdueix velocitat: ");
			int velocitat = e.nextInt();
			
			if(velocitat >= 300) {
				if(!aeronaus[aeronau].getTrenAterratgeDesplegat()) {
					aeronaus[aeronau].setVelocitat(velocitat);
					System.out.println("Velocitat actualitzada");
				}
				else {
					System.out.println("Plega el tren d'aterratge");
				}
			}
			else if(velocitat < 300 && velocitat >= 180) {
				aeronaus[aeronau].setVelocitat(velocitat);
				System.out.println("Velocitat actualitzada");
			}
			else if(aeronaus[aeronau].getAlcada() < 100) {
				if(aeronaus[aeronau].getTrenAterratgeDesplegat()) {
					aeronaus[aeronau].setVelocitat(velocitat);
					System.out.println("Velocitat actualitzada");
				}
				else {
					System.out.println("Desplega el tren d'aterratge");
				}
			}
		}
		else {
			System.out.println("El motor esta apagat");
		}
	}
	
	public void setAlcada(int aeronau) {
		if(aeronaus[aeronau].getMotorEnces()) {
			System.out.println("Introdueix alçada: ");
			int alcada = e.nextInt();
			
			if(aeronaus[aeronau].getVelocitat() >= 180) {
				if(alcada >= 500) {
					if(aeronaus[aeronau].getTrenAterratgeDesplegat() == false) {
						aeronaus[aeronau].setAlcada(alcada);
						System.out.println("Alçada actualitzada");
					}
					else {
						System.out.println("Plega el tren d'aterratge");
					}
				}
				else if(alcada < 500) {
					if(alcada < 100) {
						if(aeronaus[aeronau].getTrenAterratgeDesplegat() == true) {
							aeronaus[aeronau].setAlcada(alcada);
							System.out.println("Alçada actualitzada");
						}
						else {
							System.out.println("Desplega el tren d'aterratge");
						}
					}
					else {
						aeronaus[aeronau].setAlcada(alcada);
						System.out.println("Alçada actualitzada");
					}
				}
			}
			else {
				System.out.println("Vas massa lent per augmentar l'alçada");
			}
		}
		else {
			System.out.println("El motor esta apagat");
		}
	}
	
	public void setTrenAterratge(int aeronau) {
		if(aeronaus[aeronau].getMotorEnces()) {
			if(aeronaus[aeronau].getTrenAterratgeDesplegat() == false) {
				setTrenAccio(aeronau, false);
			}
			else if(aeronaus[aeronau].getTrenAterratgeDesplegat() == true) {
				if(aeronaus[aeronau].getAlcada() > 0) {
					setTrenAccio(aeronau, true);
				}
				else {
					System.out.println("Primer enlaira");
				}
			}
		}
		else {
			System.out.println("El motor esta apagat");
		}
	}
	
	public void setTrenAccio(int aeronau, boolean desplegat) {
		if(aeronaus[aeronau].getAlcada() < 500 && aeronaus[aeronau].getVelocitat() < 300) {
			if(!desplegat) {
				aeronaus[aeronau].setTrenAterratgeDesplegat(true);
				System.out.println("Tren d'aterratge desplegat");
			}
			else {
				aeronaus[aeronau].setTrenAterratgeDesplegat(false);
				System.out.println("Tren d'aterratge plegat");
			}
		}
		else {
			if(aeronaus[aeronau].getAlcada() >= 500) {
				System.out.println("Baixa l'altura per desplegar el tren d'aterratge");
			}
			if(aeronaus[aeronau].getVelocitat() >= 300) {
				System.out.println("Baixa la velocitat per desplegar el tren d'aterratge");
			}
		}
	}
	
	
	public void setRumb(int aeronau) {
		System.out.println("Selecciona rumb");
		int rumb = e.nextInt();
		if(rumb > 0 || rumb < 360) {
			aeronaus[aeronau].setRumb(rumb);
		}
		else {
			System.out.println("Rumb incorrecte");
		}
	}
	
	public void setAparcat(int aeronau) {
		if(!aeronaus[aeronau].getMotorEnces() && aeronaus[aeronau].getVelocitat() == 0 && aeronaus[aeronau].getAlcada() == 0) {
			aeronaus[aeronau].setAparcat(true);
			System.out.println("Aeronau aparcada");
		}
		else {
			if(aeronaus[aeronau].getVelocitat() > 0){
				System.out.println("Baixar velocitat a 0");
			}
			if(aeronaus[aeronau].getAlcada() > 0) {
				System.out.println("Baixat alçada a 0");
			}
			if(aeronaus[aeronau].getMotorEnces()) {
				System.out.println("Apaga el motor");
			}
		}
	}
	
	public void setXY(int aeronau) {
		
	}
	
	public void setRumb() {
		
	}
} 