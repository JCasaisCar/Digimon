package principal;

public class Domador {
    String nombre;
    Digimon[] equipo = new Digimon[3];
    int cantidadDigimon = 0;

    public Domador(String nombre) {
        this.nombre = nombre;
        agregarDigimonAleatorio();
    }

    public void resetearEquipo() {
        for (int i = 0; i < equipo.length; i++) {
            equipo[i] = null;
        }
        agregarDigimonAleatorio();
    }
    
    private void agregarDigimonAleatorio() {
        String[] nombres = {"Agumon", "Gabumon", "Patamon"};
        int indiceAleatorio = (int) (Math.random() * nombres.length);
        equipo[0] = new Digimon(nombres[indiceAleatorio], (int) (Math.random() * 5) + 1);
        cantidadDigimon = 1;
    }

    public boolean captura(Digimon digimon) {
        // Verificar si el Digimon ya está en el equipo
        for (Digimon d : equipo) {
            if (d != null && d.nombre.equals(digimon.nombre)) {
                System.out.println("Ya tienes a " + digimon.nombre + " en tu equipo.");
                return false;
            }
        }

        if (digimon.salud <= 20) {
            if (cantidadDigimon < 3) {
                equipo[cantidadDigimon++] = digimon;
                System.out.println(digimon.nombre + " se ha unido a tu equipo.");
                return true;
            } else {
                System.out.println("Tu equipo está lleno. No puedes capturar más Digimon.");
            }
        } else {
            System.out.println("No se puede capturar a " + digimon.nombre + ". Su salud es demasiado alta.");
        }
        return false;
    }
}
