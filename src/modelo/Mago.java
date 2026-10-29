package modelo;

public class Mago extends Personaje {
    private int poderMagico;
    private double probabilidadEfectoEstado;
    private boolean estaBajoTierra;

    public Mago(String nombre, int vidaMaxima, int manaMaxima, int ataque, int defensa, int velocidad, int energiaBestia) {
        super(nombre, vidaMaxima, manaMaxima, ataque, defensa, velocidad, energiaBestia);
        this.poderMagico = 15;
        this.probabilidadEfectoEstado = 0.30;
        this.estaBajoTierra = false;
    }

    @Override
    public void usarHabilidad(Entidad objetivo) {
        if (this.manaActual >= 20) {
            this.manaActual -= 20;
            objetivo.recibirDanio(this.ataque + this.poderMagico);
        }
    }

    @Override
    public void atacar(Entidad objetivo) {
        objetivo.recibirDanio(this.ataque);
    }
}
