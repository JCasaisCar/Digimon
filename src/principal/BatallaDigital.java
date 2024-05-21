package principal;

import java.util.Random;
import java.util.Scanner;

/**
 * Clase que gestiona la batalla digital entre el domador y un Digimon enemigo.
 * 
 * @author JCasaisCar
 * @version 1.0
 */
public class BatallaDigital {
    Domador domador;
    Digimon enemigo;
    private Scanner scanner;

    /**
     * Constructor de la clase BatallaDigital.
     *
     * @param domador El domador que participa en la batalla.
     * @param scanner El objeto Scanner para la entrada de datos.
     */
    public BatallaDigital(Domador domador, Scanner scanner) {
        this.domador = domador;
        this.scanner = scanner;
    }

    /**
     * Inicia la batalla digital.
     */
    public void iniciarBatalla() {
        domador.resetearEquipo();
        this.enemigo = generarDigimonAleatorio();

        System.out.println("¡Comienza la batalla!");

        boolean equipoDerrotado = false;

        while (!equipoDerrotado) {
            // Turno del domador
            if (domador.equipo[0] != null) {
                System.out.println("Turno del domador");
                elige();

                System.out.println("¿Quieres capturar al enemigo?");
                System.out.println("Escribe un 1 si quieres o un 0 si no quieres:");
                String numero = scanner.nextLine();
                while (!numero.equals("0") && !numero.equals("1")) {
                    System.out.println("Tienes que escribir un número comprendido entre 0 y 1");
                    System.out.println("Escríbelo otra vez:");
                    numero = scanner.nextLine();
                }

                if (numero.equals("1")) {
                    domador.captura(enemigo);
                }

                pelea();

                // Verificamos si el equipo del domador ha sido derrotado después de la pelea
                if (domador.equipo[0] == null) {
                    equipoDerrotado = true;
                }
            } else {
                equipoDerrotado = true;
                break;
            }

            // Turno del enemigo
            if (domador.equipo[0] != null) {
                System.out.println("Turno del enemigo");
                enemigoAtaqueAleatorio();

                // Verificamos si el equipo del domador ha sido derrotado después del turno del enemigo
                if (domador.equipo[0] == null) {
                    equipoDerrotado = true;
                }
            }
        }

        if (equipoDerrotado) {
            System.out.println("Todos los Digimon del domador han sido derrotados. ¡Has perdido!");
        }
    }

    /**
     * Permite al domador elegir un Digimon y un ataque.
     */
    public void elige() {
        System.out.println("Selecciona un Digimon para pelear:");
        for (int i = 0; i < domador.equipo.length; i++) {
            if (domador.equipo[i] != null) {
                System.out.println((i + 1) + ". " + domador.equipo[i].nombre);
            }
        }

        int seleccion = scanner.nextInt();
        scanner.nextLine(); // Limpiamos el buffer

        if (seleccion >= 1 && seleccion <= 3 && domador.equipo[seleccion - 1] != null) {
            System.out.println("Selecciona el ataque:");
            System.out.println("1. Ataque1");
            System.out.println("2. Ataque2");

            int ataqueSeleccionado = scanner.nextInt();
            scanner.nextLine(); // Limpiamos el buffer

            if (ataqueSeleccionado == 1) {
                domador.equipo[seleccion - 1].ataque1();
            } else if (ataqueSeleccionado == 2) {
                domador.equipo[seleccion - 1].ataque2();
            } else {
                System.out.println("Selección de ataque inválida");
                elige();
            }
        } else {
            System.out.println("Selección de Digimon inválida. Selecciona nuevamente");
            elige();
        }
    }

    /**
     * Gestiona la pelea entre el domador y el Digimon enemigo.
     */
    public void pelea() {
        // Turno del domador
        if (domador.equipo[0] != null) {
            // Calculamos el daño causado por el domador
            int danioDomador = enemigo.puntosAtaque;
            if (enemigo.DP1 >= 1) {
                danioDomador = enemigo.puntosAtaque * 2;
                enemigo.DP1--;
            }

            // Reducimos la salud del primer Digimon del domador debido al ataque del enemigo
            domador.equipo[0].salud -= danioDomador;
            System.out.println("Tu Digimon causó " + danioDomador + " de daño al enemigo");

            // Verificamos si el enemigo ha sido derrotado
            if (enemigo.salud <= 0) {
                System.out.println(enemigo.nombre + " ha sido derrotado");
                System.out.println("Ha ganado el domador");
                return;
            }
        } else {
            System.out.println("Todos los Digimon del domador han sido derrotados. ¡Has perdido!");
            return;
        }

        // Turno del enemigo
        if (domador.equipo[0] != null) {
            // Calculamos el daño causado por el enemigo
            int danioEnemigo = enemigo.puntosAtaque;
            if (enemigo.DP1 >= 1) {
                danioEnemigo = enemigo.puntosAtaque * 2;
                enemigo.DP1--;
            }

            // Reducimos la salud del Digimon del domador debido al ataque del enemigo
            domador.equipo[0].salud -= danioEnemigo;
            System.out.println("El enemigo usó Ataque2 con " + enemigo.nombre + "!");
            System.out.println("El enemigo causó " + danioEnemigo + " de daño al domador");

            // Verificamos si el Digimon del domador ha sido derrotado debido al ataque del enemigo
            if (domador.equipo[0].salud <= 0) {
                System.out.println(domador.equipo[0].nombre + " ha sido derrotado y eliminado del equipo");
                eliminarDigimonDerrotado();
            }
        }
    }

    /**
     * Elimina un Digimon derrotado del equipo del domador.
     */
    private void eliminarDigimonDerrotado() {
        for (int i = 0; i < domador.equipo.length - 1; i++) {
            domador.equipo[i] = domador.equipo[i + 1];
        }
        domador.equipo[domador.equipo.length - 1] = null;
    }

    /**
     * Genera un Digimon enemigo aleatorio.
     *
     * @return Un nuevo Digimon aleatorio.
     */
    private Digimon generarDigimonAleatorio() {
        String[] nombres = {"Agumon", "Gabumon", "Patamon"};
        Random random = new Random();
        String nombreAleatorio = nombres[random.nextInt(nombres.length)];
        int nivelAleatorio = random.nextInt(5) + 1;

        return new Digimon(nombreAleatorio, nivelAleatorio);
    }

    /**
     * Realiza un ataque aleatorio del enemigo al equipo del domador.
     */
    private void enemigoAtaqueAleatorio() {
        if (domador.equipo[0] != null) {
            int ataqueSeleccionado = new Random().nextInt(2) + 1;

            if (ataqueSeleccionado == 1) {
                System.out.println("El enemigo usó Ataque1 con " + enemigo.nombre + "!");
                System.out.println("Causa " + enemigo.puntosAtaque + " de daño");
                domador.equipo[0].salud -= enemigo.puntosAtaque;
            } else {
                System.out.println("El enemigo usó Ataque2 con " + enemigo.nombre + "!");
                int danio = enemigo.puntosAtaque * 2;
                System.out.println("Causa " + danio + " de daño");
                domador.equipo[0].salud -= danio;
                enemigo.DP1 -= 2;
            }

            if (domador.equipo[0].salud <= 0) {
                System.out.println(domador.equipo[0].nombre + " ha sido derrotado y eliminado del equipo");
                eliminarDigimonDerrotado();
            }
        }
    }
}
