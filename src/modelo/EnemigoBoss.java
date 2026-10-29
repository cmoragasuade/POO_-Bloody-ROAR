package modelo;

public class EnemigoBoss extends Enemigo {
    private int fase;

    public EnemigoBoss(String nombre, int vidaMaxima, int ataque, int defensa, int velocidad, int experienciaOtorgada, int oroOtorgado) {
        super(nombre, vidaMaxima, ataque, defensa, velocidad, experienciaOtorgada, oroOtorgado);
        this.fase = 1;
    }

    @Override
    public void atacar(Entidad objetivo) {
        objetivo.recibirDanio(this.ataque + (this.fase * 5));
    }

    public void cambiarFase() {
        if (this.vidaActual <= this.vidaMaxima * 0.5) {
            this.fase = 2;
            this.ataque += 10;
        }
    }
}
