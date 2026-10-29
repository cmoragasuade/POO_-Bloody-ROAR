package modelo;

public class Arquero extends Personaje {
    private double probabilidadCritico;
    private double multiplicadorCritico;
    private int cargasInstintoTigre;

    public Arquero(String nombre, int vidaMaxima, int manaMaxima, int ataque, int defensa, int velocidad, int energiaBestia) {
        super(nombre, vidaMaxima, manaMaxima, ataque, defensa, velocidad, energiaBestia);
        this.probabilidadCritico = 0.25;
        this.multiplicadorCritico = 1.5;
        this.cargasInstintoTigre = 0;
    }

    @Override
    public void usarHabilidad(Entidad objetivo) {
        if (this.manaActual >= 10) {
            this.manaActual -= 10;
            for (int i = 0; i < 3; i++) {
                objetivo.recibirDanio((int) (this.ataque * 0.8));
            }
            this.cargasInstintoTigre = 0;
        }
    }

    @Override
    public void atacar(Entidad objetivo) {
        objetivo.recibirDanio(this.ataque);
    }
}
