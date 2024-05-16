package principal;

import java.util.Random;

public class Domador {
    String nombre;
    Digimon[] equipo = new Digimon[3];
    int cantidadDigimon = 0;

    public Domador(String nombre) {
        this.nombre = nombre;
        //Para seleccionar aleatoriamente uno de los tres Digimon icónicos al registrar el Domador
        String[] nombres = {"Agumon", "Gabumon", "Patamon"};
        int indiceAleatorio = new Random().nextInt(nombres.length);
        equipo[0] = new Digimon(nombres[indiceAleatorio], new Random().nextInt(5) + 1);
        cantidadDigimon = cantidadDigimon + 1;
    }

    
}
