package principal;

public class Digimon {
	String nombre;
	int nivel;
	int puntosAtaque;
	int salud;
	int DP1;
	int DP2;

	public Digimon(String nombre, int nivel) {
		this.nombre = nombre;
		this.nivel = nivel;
		this.puntosAtaque = nivel * 5;
		this.salud = nivel * 10;
		this.DP1 = 10;
		this.DP2 = 10;
	}

	public void ataque1() {
		System.out.println(this.nombre + " usó Ataque1!");
		System.out.println("Causa " + this.puntosAtaque + " de daño.");
		this.DP1--;
	}

	public void ataque2() {
		System.out.println(this.nombre + " usó Ataque2!");
		System.out.println("Causa " + (this.puntosAtaque * 2) + " de daño.");
		this.DP2 -= 2;
	}
}
