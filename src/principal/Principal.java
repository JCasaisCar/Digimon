package principal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		boolean salir = true;
		while (salir) {
			try {
				Scanner leer = new Scanner(System.in);

				boolean salirmenu = true;
				int nMenu;

				System.out.println("Bienvenid@ a LA BATALLA DE LOS DIGIMON");
				while (salirmenu) {
					
					System.out.println("¿Que te apetece hacer?");
					System.out.println("1. Iniciar batalla");
					System.out.println("2. Salir");
					nMenu = (int) leer.nextFloat();

					switch (nMenu) {
					case 1:
						System.out.println("Has elegido la opción 1");

						

						break;
					case 2:
						System.out.println("Has elegido la opción 2");
						System.out.println("As salido del programa!!");

						salirmenu = false;

						break;
					default:
						System.out.println("La opción seleccionada no existe, escribela otra vez");
					}
				}

				salir = false;
				leer.close();

			} catch (InputMismatchException e) {
				System.out.println("Entrada no válida");
				System.out.println("Empezamos de nuevo!!");
			}
		}
	}

}
