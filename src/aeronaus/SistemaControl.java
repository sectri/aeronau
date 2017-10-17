package aeronaus;

import java.util.Scanner;

class SistemaControl {

	Scanner e = new Scanner(System.in);
	Aeronau[] aeronaus = new Aeronau[5];
	
	public static void main(String[] args) {
		SistemaControl SC = new SistemaControl();
		SC.SControl();
	}
		
	void SControl() {
		
		char menuNum;
		
		do {
			System.out.println("1- Donar d'alta avi� al sistema");
			System.out.println("2- Gestiona un dels avions");
			System.out.println("3- Manteniment de l'espai aeri");
			System.out.println("4- Informaci� de l'espai aeri");
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
	
	void infoAeri() {
		
	}

	void mantenimentAeri() {
		
	}

	int seleccionarNau() {
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
	
	int posicioLliure() {
		int posicioLliure = 5;
		
		for(int c = 0; c < 5; c++) {
			if(aeronaus[c] == null) {
				posicioLliure = c;
				break;
			}
		}
		return posicioLliure;
	}
	
	void crearAeronau(int posicioLliure) {
		boolean ok = false;
		String matricula;
		int X = 0; 
		int Y = 0;
		
		System.out.println("Introdueix model: ");
		String model = e.next();
		
		System.out.println("Introdueix fabricant: ");
		String fabricant = e.next();
		
		System.out.println("Introdueix capacitat: ");
		int capacitat = e.nextInt();
		
		switch (posicioLliure) {
		case 0:
			X = 10;
			Y = 100;
			break;
		case 1:
			X = 10;
			Y = 200;
			break;
		case 2:
			X = 10;
			Y = 300;
			break;
		case 3:
			X = 10;
			Y = 400;
			break;
		case 4:
			X = 10;
			Y = 500;
			break;
		}
		
		do {
			System.out.println("Introdueix matricula: ");
			matricula = e.next();
			ok = comprovarOk(posicioLliure, matricula);
		}
		while(!ok);
		
		aeronaus[posicioLliure] = new Aeronau(model, fabricant, capacitat, X, Y, matricula);
	}

	void gestionarNau(int aeronau) {
		aeronaus[aeronau].setAparcat(false);
		char accio;
		do {
			System.out.println("Q - Pujar / Baixar tren aterratge");
			System.out.println("W - Accelerar / Frenar");
			System.out.println("E - Encendre / Apagar motor");
			System.out.println("R - Posicionar X, Y");
			System.out.println("A - Agafar / Baixar al�ada");
			System.out.println("S - Aparcar");
			System.out.println("D - Establir rumb");
			System.out.println("F - Finalitzar operativa");
			
			accio = e.next().charAt(0);
			switch(accio) {
			case 'q':
			case 'Q':
				setTrenAterratge(aeronau);
				break;
			case 'w':
			case 'W':
				setVelocitat(aeronau);
				break;
			case 'e':
			case 'E':
				motorOnOff(aeronau);
				break;
			case 'r':
			case 'R':
				setXY(aeronau);
				break;
			case 'a':
			case 'A':
				setAlcada(aeronau);
				break;
			case 's':
			case 'S':
				setAparcat(aeronau);
				if(aeronaus[aeronau].getAparcat()) {
					accio = 'p';
				}
				break;
			case 'd':
			case 'D':
				setRumb(aeronau);
				break;
			}
		}
		while(accio != 'f' && accio != 'F');
	}

	void motorOnOff(int aeronau) {
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
				System.out.println("Baixa l'al�ada a 0");
			}
			if(!aeronaus[aeronau].getTrenAterratgeDesplegat()) {
				System.out.println("Desplega el tren d'aterratge");
			}
		}
	}

	void setVelocitat(int aeronau) {
		if(aeronaus[aeronau].getMotorEnces()) {
			System.out.println("Introdueix velocitat: ");
			int velocitat = e.nextInt();
			
			if(velocitat >= 300) {
				if(!aeronaus[aeronau].getTrenAterratgeDesplegat()) {
					aeronaus[aeronau].setVelocitat(velocitat);
					System.out.println("Velocitat actualitzada");
				}
				else {
					System.out.println("Primer plega el tren d'aterratge");
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
					System.out.println("Primer desplega el tren d'aterratge");
				}
			}
		}
		else {
			System.out.println("El motor esta apagat");
		}
	}
	
	void setAlcada(int aeronau) {
		if(aeronaus[aeronau].getMotorEnces()) {
			System.out.println("Introdueix al�ada: ");
			int alcada = e.nextInt();
			
			if(aeronaus[aeronau].getVelocitat() >= 180) {
				if(alcada >= 500) {
					if(aeronaus[aeronau].getTrenAterratgeDesplegat() == false) {
						aeronaus[aeronau].setAlcada(alcada);
						System.out.println("Al�ada actualitzada");
					}
					else {
						System.out.println("Primer plega el tren d'aterratge");
					}
				}
				else if(alcada < 500) {
					if(alcada < 100) {
						if(aeronaus[aeronau].getTrenAterratgeDesplegat() == true) {
							aeronaus[aeronau].setAlcada(alcada);
							System.out.println("Al�ada actualitzada");
						}
						else {
							System.out.println("Primer desplega el tren d'aterratge");
						}
					}
					else {
						aeronaus[aeronau].setAlcada(alcada);
						System.out.println("Al�ada actualitzada");
					}
				}
			}
			else {
				System.out.println("Augmenta velocitat minim a 180 per augmentar l'al�ada");
			}
		}
		else {
			System.out.println("El motor esta apagat");
		}
	}
	
	void setTrenAterratge(int aeronau) {
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
	
	void setTrenAccio(int aeronau, boolean desplegat) {
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
				System.out.println("Baixa l'altura a menys de 500 per desplegar el tren d'aterratge");
			}
			if(aeronaus[aeronau].getVelocitat() >= 300) {
				System.out.println("Baixa la velocitat a menys de 300 per desplegar el tren d'aterratge");
			}
		}
	}
	
	void setAparcat(int aeronau) {
		if(!aeronaus[aeronau].getMotorEnces() && aeronaus[aeronau].getVelocitat() == 0 && aeronaus[aeronau].getAlcada() == 0) {
			aeronaus[aeronau].setAparcat(true);
			System.out.println("Aeronau aparcada");
		}
		else {
			if(aeronaus[aeronau].getVelocitat() > 0){
				System.out.println("Baixar velocitat a 0");
			}
			if(aeronaus[aeronau].getAlcada() > 0) {
				System.out.println("Baixat al�ada a 0");
			}
			if(aeronaus[aeronau].getMotorEnces()) {
				System.out.println("Apaga el motor");
			}
		}
	}
	
	void setRumb(int aeronau) {
		System.out.println("Selecciona rumb: ");
		int rumb = e.nextInt();
		if(rumb >= 0 && rumb <= 360) {
			aeronaus[aeronau].setRumb(rumb);
		}
		else {
			System.out.println("Rumb incorrecte");
		}
	}
	
	void setXY(int aeronau) {
		if(aeronaus[aeronau].getMotorEnces()) {
			if(aeronaus[aeronau].getAlcada() > 0) {
				System.out.println("Selecciona X: ");
				int X = e.nextInt();
				System.out.println("Selecciona Y: ");
				int Y = e.nextInt();
				boolean error = false;
				for(int c = 0; c < aeronaus.length; c++) {
					if(aeronaus[aeronau] != null) {
						if((aeronaus[aeronau].getX() - X) <= 1 && (aeronaus[aeronau].getY() - Y) <= 1
								&& (aeronaus[aeronau].getX() - X) >= -1 && (aeronaus[aeronau].getY() - Y) >= -1) {
							System.out.println("Les naus no es poden creuar");
							error = true;
							break;
						}
					}
				}
				if(error == false) {
					aeronaus[aeronau].setXY(X, Y);
					System.out.println("X i Y actualitzats");
				}
			}
			else {
				System.out.println("Primer enlaira");
			}
		}
		else {
			System.out.println("El motor esta apagat");
		}
	}

	boolean comprovarOk(int posicioLliure, String matricula) {
		boolean ok = true;
		for(int c = 0; c < 5; c++) {
			if(c == posicioLliure) {
				continue;
			}
			if(aeronaus[c] != null) {
				if(aeronaus[c].getMatricula().equals(matricula)) {
					ok = false;
					System.out.println("Hi  ha una aeronau amb el mateix nom");
					break;
				}
			}
		}
		return ok;
	}
} 