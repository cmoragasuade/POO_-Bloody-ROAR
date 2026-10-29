package modelo;

public abstract class Enemigo extends Entidad {
    protected int experienciaOtorgada;
    protected int oroOtorgado;

    public Enemigo(String nombre, int vidaMaxima, int ataque, int defensa, int velocidad, int experienciaOtorgada, int oroOtorgado) {
        super(nombre, vidaMaxima, ataque, defensa, velocidad);
        this.experienciaOtorgada = experienciaOtorgada;
        this.oroOtorgado = oroOtorgado;
    }

    public int getExperienciaOtorgada() {
        return experienciaOtorgada;
    }

    public int getOroOtorgado() {
        return oroOtorgado;
    }
}
