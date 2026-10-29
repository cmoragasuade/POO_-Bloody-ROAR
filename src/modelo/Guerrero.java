package modelo;

public class Guerrero extends Personaje {
    private int escudoAbsorcion;
    private double probabilidadBloqueo;
    private int furiaLobo;

    public Guerrero(String nombre, int vidaMaxima, int manaMaxima, int ataque, int defensa, int velocidad, int energiaBestia) {
        super(nombre, vidaMaxima, manaMaxima, ataque, defensa, velocidad, energiaBestia);
        this.escudoAbsorcion = 10;
        this.probabilidadBloqueo = 0.20;
        this.furiaLobo = 0;
    }

    @Override
    public void usarHabilidad(Entidad objetivo) {
        if (this.manaActual >= 15) {
            this.manaActual -= 15;
            int bonificacion = (int) Math.round(this.ataque * 0.5);
            this.furiaLobo = bonificacion;
            objetivo.recibirDanio(bonificacion);
        }
    }

    @Override
    public void atacar(Entidad objetivo) {
        objetivo.recibirDanio(this.ataque);
    }
}
