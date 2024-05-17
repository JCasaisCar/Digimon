package principal;

import java.util.Scanner;
import java.util.Random;

public class BatallaDigital {
    Domador domador;
    Digimon enemigo;

    public BatallaDigital(Domador domador) {
        this.domador = domador;
        this.enemigo = generarDigimonAleatorio();
    }

    public void iniciarBatalla() {
        System.out.println("¡Comienza la batalla!");

        boolean equipoDerrotado = false; // Variable para verificar si todos los Digimon del domador han sido derrotados

        while (!equipoDerrotado) {
            if (domador.equipo[0] != null) { // Verificar si el primer Digimon del equipo del domador es nulo
                System.out.println("Turno del domador");
                elige();
                pelea();

                if (enemigo.salud <= 0) {
                    System.out.println(enemigo.nombre + " ha sido derrotado.");
                    break;
                }
            } else {
                System.out.println("Todos los Digimon del domador han sido derrotados. ¡Has perdido!");
                equipoDerrotado = true; // Marcar que el equipo ha sido derrotado
                break; // Salir del bucle
            }

            System.out.println("Turno del enemigo");
            enemigoAtaqueAleatorio();
        }

        // Verificar si el enemigo puede ser capturado
        if (!equipoDerrotado && enemigo.salud <= 20) {
            boolean capturado = domador.captura(enemigo);
            if (capturado) {
                System.out.println(enemigo.nombre + " se ha unido a tu equipo.");
            } else {
                System.out.println("No se pudo capturar a " + enemigo.nombre);
            }
        }
    }






    public void elige() {
        System.out.println("Selecciona un Digimon para pelear:");
        for (int i = 0; i < domador.equipo.length; i++) {
            if (domador.equipo[i] != null) {
                System.out.println((i + 1) + ". " + domador.equipo[i].nombre);
            }
        }

        Scanner scanner = new Scanner(System.in);
        int seleccion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        if (seleccion >= 1 && seleccion <= 3 && domador.equipo[seleccion - 1] != null) {
            System.out.println("Selecciona el ataque:");
            System.out.println("1. Ataque1");
            System.out.println("2. Ataque2");

            int ataqueSeleccionado = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            if (ataqueSeleccionado == 1) {
                domador.equipo[seleccion - 1].ataque1();
            } else if (ataqueSeleccionado == 2) {
                domador.equipo[seleccion - 1].ataque2();
            } else {
                System.out.println("Selección de ataque inválida.");
                elige(); // Volver a pedir la selección
            }
        } else {
            System.out.println("Selección de Digimon inválida. Selecciona nuevamente.");
            elige(); // Volver a pedir la selección
        }
    }

    public void pelea() {
        int danio = enemigo.puntosAtaque;
        if (enemigo.DP1 >= 1) {
            danio = enemigo.puntosAtaque * 2;
            enemigo.DP1--;
        }

        domador.equipo[0].salud -= danio;
        System.out.println("El Digimon enemigo te hizo " + danio + " de daño.");

        // Verificar si el Digimon del domador ha sido derrotado
        if (domador.equipo[0].salud <= 0) {
            System.out.println(domador.equipo[0].nombre + " ha sido derrotado y eliminado del equipo.");
            eliminarDigimonDerrotado();
        }
    }

    private void eliminarDigimonDerrotado() {
        // Mover los Digimon restantes hacia adelante para llenar el espacio vacío
        for (int i = 0; i < domador.equipo.length - 1; i++) {
            domador.equipo[i] = domador.equipo[i + 1];
        }
        // Establecer el último Digimon como nulo para eliminarlo del equipo
        domador.equipo[domador.equipo.length - 1] = null;
    }

    private Digimon generarDigimonAleatorio() {
        String[] nombres = {"Agumon", "Gabumon", "Patamon"};
        Random random = new Random();
        String nombreAleatorio = nombres[random.nextInt(nombres.length)];
        int nivelAleatorio = random.nextInt(5) + 1;

        return new Digimon(nombreAleatorio, nivelAleatorio);
    }

    private void enemigoAtaqueAleatorio() {
        if (domador.equipo[0] != null) { // Verificar si el equipo del domador no está vacío
            int ataqueSeleccionado = new Random().nextInt(2) + 1; // 1 o 2

            if (ataqueSeleccionado == 1) {
                System.out.println("El enemigo usó Ataque1!");
                System.out.println("Causa " + enemigo.puntosAtaque + " de daño.");
                domador.equipo[0].salud -= enemigo.puntosAtaque;
            } else {
                System.out.println("El enemigo usó Ataque2!");
                int danio = enemigo.puntosAtaque * 2;
                System.out.println("Causa " + danio + " de daño.");
                domador.equipo[0].salud -= danio;
                enemigo.DP1 -= 2;
            }
        } else {
            System.out.println("Todos los Digimon del domador han sido derrotados. ¡Has perdido!");
            return; // Salir del método si el equipo del domador está vacío
        }
    }
}
