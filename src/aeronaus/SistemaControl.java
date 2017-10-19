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
		boolean nausDisponibles = false;
		
		do {
			nausDisponibles = nausDisponibles();
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
				if(nausDisponibles) {
					int aeronau = 5;
					aeronau = seleccionarNau();
					if(aeronaus[aeronau] == null) {
						System.out.println("No hi ha ninguna nau en aquest espai");
					}
					else {
						gestionarNau(aeronau);
						mantenimentAeri();
						infoAeri();
					}
				}
				else {
					System.out.println("No hi ha ninguna nau a pilotar");
				}
				break;
			case '3':
				if(nausDisponibles) {
					mantenimentAeri();
				}
				else {
					System.out.println("No hi han naus per manteniment");
				}
				break;
			case '4':
				if(nausDisponibles) {
					infoAeri();
				}
				else {
					System.out.println("No hi han naus a mirar");
				}
				break;
			}
		}
		while(menuNum!='5');
	}
	
	void infoAeri() {
		System.out.println("Sitació de les aeronaus:");
		System.out.println("		Aeronau 1	Aeronau 2	Aeronau 3	Aeronau 4	Aeronau 5");
		System.out.println("		-------------------------------------------------------------------------");
		System.out.println("Fabricant:	  " + infoMostrar("marca", 0) + "		 " + infoMostrar("marca", 1) + "		 " + infoMostrar("marca", 2) + "		 " + infoMostrar("marca", 3) + "		 " + infoMostrar("marca", 4));
		System.out.println("Model:		  " + infoMostrar("model", 0) + "		 " + infoMostrar("model", 1) + "		 " + infoMostrar("model", 2) + "		 " + infoMostrar("model", 3) + "		 " + infoMostrar("model", 4));
		System.out.println("Matrícula:	  " + infoMostrar("matricula", 0) + "		 " + infoMostrar("matricula", 1) + "		 " + infoMostrar("matricula", 2) + "		 " + infoMostrar("matricula", 3) + "		 " + infoMostrar("matricula", 4));
		System.out.println("X:		  " + infoMostrar("x", 0) + "		 " + infoMostrar("x", 1) + "		 " + infoMostrar("x", 2) + "		 " + infoMostrar("x", 3) + "		 " + infoMostrar("x", 4));
		System.out.println("Y:		  " + infoMostrar("y", 0) + "		 " + infoMostrar("y", 1) + "		 " + infoMostrar("y", 2) + "		 " + infoMostrar("y", 3) + "		 " + infoMostrar("y", 4));
		System.out.println("Alçada:		  " + infoMostrar("alcada", 0) + "		 " + infoMostrar("alcada", 1) + "		 " + infoMostrar("alcada", 2) + "		 " + infoMostrar("alcada", 3) + "		 " + infoMostrar("alcada", 4));
		System.out.println("Velocitat:	  " + infoMostrar("velocitat", 0) + "		 " + infoMostrar("velocitat", 1) + "		 " + infoMostrar("velocitat", 2) + "		 " + infoMostrar("velocitat", 3) + "		 " + infoMostrar("velocitat", 4));
		System.out.println("Tren aterratge:	  " + infoMostrar("tren", 0) + "	 " + infoMostrar("tren", 1) + "	 " + infoMostrar("tren", 2) + "	 " + infoMostrar("tren", 3) + "	 " + infoMostrar("tren", 4));
		System.out.println("Motor		  " + infoMostrar("motor", 0) + "		 " + infoMostrar("motor", 1) + "		 " + infoMostrar("motor", 2) + "		 " + infoMostrar("motor", 3) + "		 " + infoMostrar("motor", 4));
		System.out.println("Aparcat		  " + infoMostrar("aparcat", 0) + "	 " + infoMostrar("aparcat", 1) + "	 " + infoMostrar("aparcat", 2) + "	 " + infoMostrar("aparcat", 3) + "	 " + infoMostrar("aparcat", 4));
		System.out.println();
		System.out.println("Perills detectats:");
		System.out.println();
		
		perills();
	}
	
	void perills() {
		int c2;
		for(int c1 = 0; c1 < aeronaus.length; c1++) {
			if(aeronaus[c1] != null) {
				for(c2 = c1 + 1; c2 < aeronaus.length; c2++) {
					if(aeronaus[c2] != null && aeronaus[c2].getMatricula() != aeronaus[c1].getMatricula()) {
						int alcada = aeronaus[c1].getAlcada() - aeronaus[c2].getAlcada();
						int X = aeronaus[c1].getX() - aeronaus[c2].getX();
						int Y = aeronaus[c1].getY() - aeronaus[c2].getY();
						
						if(alcada < 500 && alcada > -500) {
							if((X < 50 && X > -50) && (Y < 50 && Y > -50)) {
								System.out.println("Perill de colisió: " + aeronaus[c1].getMatricula() + " i " + aeronaus[c2].getMatricula() + ": Mateixa X i Y. Alçada de "+ alcada + " metres entre elles");
								System.out.println();
							}
							else if(X < 50 && X > -50){
								System.out.println("Perill de colisió: " + aeronaus[c1].getMatricula() + " i " + aeronaus[c2].getMatricula() + ": Mateixa X. Alçada de "+ alcada + " metres entre elles");
								System.out.println();
							}
							else if(Y < 50 && Y > -50) {
								System.out.println("Perill de colisió: " + aeronaus[c1].getMatricula() + " i " + aeronaus[c2].getMatricula() + ": Mateixa Y. Alçada de "+ alcada + " metres entre elles");
								System.out.println();
							}
						}	
					}
				}
			}
		}
	}
	
	String infoMostrar(String cosa, int aeronau) {
		if(aeronaus[aeronau] != null) {
			if(cosa == "marca") {
					cosa = aeronaus[aeronau].getFabricant();
				}
			else if(cosa == "model") {
				cosa = aeronaus[aeronau].getModel();
			}
			else if(cosa == "matricula") {
				cosa = aeronaus[aeronau].getMatricula();
			}
			else if(cosa == "x") {
				cosa = String.valueOf(aeronaus[aeronau].getX());
			}
			else if(cosa == "y") {
				cosa = String.valueOf(aeronaus[aeronau].getY());
			}
			else if(cosa == "alcada") {
				cosa = String.valueOf(aeronaus[aeronau].getAlcada());
			}
			else if(cosa == "velocitat") {
				cosa = String.valueOf(aeronaus[aeronau].getVelocitat());
			}
			else if(cosa == "tren") {
				if(aeronaus[aeronau].getTrenAterratgeDesplegat()) {
					cosa = "Desplegat";
				}
				else {
					cosa = "Plegat";
				}
			}
			else if(cosa == "motor") {
				if(aeronaus[aeronau].getMotorEnces()) {
					cosa = "On";
				}
				else {
					cosa = "Off";
				}
			}
			else if(cosa == "aparcat") {
				if(aeronaus[aeronau].getAparcat()) {
					cosa = "Aparcat";
				}
				else {
					cosa = "No aparcat";
				}
			}
		}
		else {
			if(cosa == "tren" || cosa == "aparcat") {
				cosa = "	";
			}
			else {
				cosa = "";
			}
		}
		return cosa;
	}

	void mantenimentAeri() {
		for(int c = 0; c < aeronaus.length; c++) {
			if(aeronaus[c] != null) {
				if(aeronaus[c].getAparcat() || aeronaus[c].getX() < 0 || aeronaus[c].getY() < 0 || aeronaus[c].getX() > 1000 || aeronaus[c].getY() > 1000) {
					System.out.println("Aeronau amb la matricula " + aeronaus[c].getMatricula() + " eliminada");
					aeronaus[c] = null;
				}
			}
		}
	}

	boolean nausDisponibles() {
		
		for(int c = 0; c < aeronaus.length; c++) {
			if(aeronaus[c] != null) {
				return true;
			}
		}
		return false;
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
			X = 100;
			Y = 100;
			break;
		case 1:
			X = 200;
			Y = 200;
			break;
		case 2:
			X = 300;
			Y = 300;
			break;
		case 3:
			X = 400;
			Y = 400;
			break;
		case 4:
			X = 500;
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
			System.out.print("Q - Pujar / Baixar tren aterratge : ");
				if(aeronaus[aeronau].getTrenAterratgeDesplegat()) {
					System.out.println("Desplegat");
				}
				else {
					System.out.println("Plegat");
				}
			System.out.println("W - Accelerar / Frenar 		  : " + aeronaus[aeronau].getVelocitat());
			System.out.print("E - Encendre / Apagar motor	  : ");
				if(aeronaus[aeronau].getMotorEnces()) {
					System.out.println("Engegat");
				}
				else {
					System.out.println("Apagat");
				}
			System.out.println("R - Posicionar X, Y		  : " + aeronaus[aeronau].getX() + ", " + aeronaus[aeronau].getY());
			System.out.println("A - Agafar / Baixar alçada	  : " + aeronaus[aeronau].getAlcada());
			System.out.print("S - Aparcar			  : ");
				if(aeronaus[aeronau].getAparcat()) {
					System.out.println("Aparcat");
				}
				else {
					System.out.println("No aparcat");
				}
			System.out.println("D - Establir rumb	  	  : " + aeronaus[aeronau].getRumb());
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
				System.out.println("Baixa l'alçada a 0");
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
			System.out.println("Introdueix alçada: ");
			int alcada = e.nextInt();
			
			if(aeronaus[aeronau].getVelocitat() >= 180) {
				if(alcada >= 500) {
					if(aeronaus[aeronau].getTrenAterratgeDesplegat() == false) {
						aeronaus[aeronau].setAlcada(alcada);
						System.out.println("Alçada actualitzada");
					}
					else {
						System.out.println("Primer plega el tren d'aterratge");
					}
				}
				else if(alcada < 500) {
					if(alcada < 100) {
						if(aeronaus[aeronau].getTrenAterratgeDesplegat() == true) {
							aeronaus[aeronau].setAlcada(alcada);
							System.out.println("Alçada actualitzada");
						}
						else {
							System.out.println("Primer desplega el tren d'aterratge");
						}
					}
					else {
						aeronaus[aeronau].setAlcada(alcada);
						System.out.println("Alçada actualitzada");
					}
				}
			}
			else {
				System.out.println("Augmenta velocitat minim a 180 per augmentar l'alçada");
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
				System.out.println("Baixat alçada a 0");
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
					System.out.println("Hi ha una aeronau amb la meteixa matricula");
					break;
				}
			}
		}
		return ok;
	}
} 