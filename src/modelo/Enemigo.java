package modelo;

public abstract class Enemigo extends Entidad {
    protected int xpOtorgada;
    protected int oroOtorgado;

    public Enemigo(String nombre, int vida, int ataque, int defensa, int velocidad, int xpOtorgada, int oroOtorgado) {
        super(nombre, vida, ataque, defensa, velocidad);
        this.xpOtorgada = xpOtorgada;
        this.oroOtorgado = oroOtorgado;
    }

    public int getXpOtorgada() { return xpOtorgada; }
    public int getOroOtorgado() { return oroOtorgado; }
}