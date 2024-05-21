package principal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

	public static boolean validarNombre(String nombre) {
		//Expresión regular para comprobar si el nombre es correcto
		String expr = "[a-zA-ZáéíóúüÁÉÍÓÚÜñÑ\\s]+";
		return nombre != null && nombre.matches(expr);
	}

	public static void main(String[] args) {
		//Creamos un solo Scanner para toda la aplicación para que no nos de errores al cerralo
		Scanner leer = new Scanner(System.in);

		boolean salir = true;
		while (salir) {
			try {
				boolean salirmenu = true;
				int nMenu;
				String nombreDomador;

				System.out.println("Bienvenid@ a LA BATALLA DE LOS DIGIMON");

				System.out.println("Dime tu nombre de domador:");
				nombreDomador = leer.nextLine();
				while (!validarNombre(nombreDomador)) {
					System.out.println("Nombre Incorrecto");
					System.out.println("Dime otra vez tu nombre de domador:");
					nombreDomador = leer.nextLine();
				}

				Domador domador = new Domador(nombreDomador);

				while (salirmenu) {

					System.out.println("¿Que te apetece hacer?");
					System.out.println("1. Iniciar batalla");
					System.out.println("2. Salir");
					nMenu = leer.nextInt();

					switch (nMenu) {
					case 1:
						System.out.println("Has elegido la opción 1");
						BatallaDigital batalla = new BatallaDigital(domador, leer);
						batalla.iniciarBatalla();
						break;
					case 2:
						System.out.println("Has elegido la opción 2");
						System.out.println("Has salido del programa!!");
						salirmenu = false;
						break;
					default:
						System.out.println("La opción seleccionada no existe, escribela otra vez");
					}
				}

				salir = false;

			} catch (InputMismatchException e) {
				System.out.println("Entrada no válida");
				System.out.println("Empezamos de nuevo!!");
				leer.nextLine(); //Limpiamos el buffer
			}
		}
		leer.close(); //Cerramos el Scanner al final del programa para evitar errores
	}

}
