package modelo;

// Jefe: más fuerte y con 2 fases. Con la mitad de vida pasa a la fase 2 (README).
public class EnemigoBoss extends Enemigo {
    private static final long serialVersionUID = 1L;

    private int fase;

    public EnemigoBoss(String nombre, int vidaMaxima, int ataque, int defensa, int velocidad, int experienciaOtorgada, int oroOtorgado) {
        super(nombre, vidaMaxima, ataque, defensa, velocidad, experienciaOtorgada, oroOtorgado);
        this.fase = 1;
    }

    @Override
    public String getTipo() {
        return "Boss (fase " + fase + ")";
    }

    @Override
    public void atacar(Entidad objetivo) {
        objetivo.recibirDanio(this.ataque + (this.fase * 5));
    }

    // Recibe el golpe y revisa si tiene que cambiar de fase
    @Override
    public void recibirDanio(int cantidad) {
        super.recibirDanio(cantidad);
        cambiarFase();
    }

    public void cambiarFase() {
        if (this.fase == 1 && estaVivo() && this.vidaActual <= this.vidaMaxima * 0.5) {
            this.fase = 2;
            this.ataque += 10;
        }
    }

    public int getFase() {
        return fase;
    }
}
