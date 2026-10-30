package modelo;

// Enemigo que maneja la computadora. Al morir da experiencia y oro.
public abstract class Enemigo extends Entidad {
    private static final long serialVersionUID = 1L;

    protected int experienciaOtorgada;
    protected int oroOtorgado;

    public Enemigo(String nombre, int vidaMaxima, int ataque, int defensa, int velocidad, int experienciaOtorgada, int oroOtorgado) {
        super(nombre, vidaMaxima, ataque, defensa, velocidad);
        this.experienciaOtorgada = experienciaOtorgada;
        this.oroOtorgado = oroOtorgado;
    }

    @Override
    public boolean esDelJugador() {
        return false;
    }

    // Ataque básico de todos los enemigos (cada tipo lo puede cambiar)
    @Override
    public void atacar(Entidad objetivo) {
        objetivo.recibirDanio(this.ataque);
    }

    public int getExperienciaOtorgada() {
        return experienciaOtorgada;
    }

    public int getOroOtorgado() {
        return oroOtorgado;
    }
}
