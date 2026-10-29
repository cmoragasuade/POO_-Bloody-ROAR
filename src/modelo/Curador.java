package modelo;

public class Curador extends Personaje {
    private int potenciaCurativa;
    private double resistenciaEfectos;
    private int bonificadorAgilidad;

    public Curador(String nombre, int vidaMaxima, int manaMaxima, int ataque, int defensa, int velocidad, int energiaBestia) {
        super(nombre, vidaMaxima, manaMaxima, ataque, defensa, velocidad, energiaBestia);
        this.potenciaCurativa = 30;
        this.resistenciaEfectos = 0.20;
        this.bonificadorAgilidad = 5;
    }

    @Override
    public void usarHabilidad(Entidad objetivo) {
        if (this.manaActual >= 25) {
            this.manaActual -= 25;
            this.curar(this.potenciaCurativa);
        }
    }

    @Override
    public void atacar(Entidad objetivo) {
        objetivo.recibirDanio(this.ataque);
    }
}
