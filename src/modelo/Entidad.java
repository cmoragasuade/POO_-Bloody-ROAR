package modelo;

public abstract class Entidad {
    protected String nombre;
    protected int vidaMaxima;
    protected int vidaActual;
    protected int ataque;
    protected int defensa;
    protected int velocidad;

    public Entidad(String nombre, int vidaMaxima, int ataque, int defensa, int velocidad) {
        this.nombre = nombre;
        this.vidaMaxima = vidaMaxima;
        this.vidaActual = vidaMaxima;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocidad = velocidad;
    }

    public abstract void atacar(Entidad objetivo);

    public void recibirDanio(int cantidad) {
        int danoEfectivo = Math.max(1, cantidad - this.defensa);
        this.vidaActual = Math.max(0, this.vidaActual - danoEfectivo);
    }

    public boolean estaVivo() {
        return this.vidaActual > 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public void setVidaActual(int vidaActual) {
        this.vidaActual = Math.max(0, Math.min(this.vidaMaxima, vidaActual));
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public void curar(int cantidad) {
        this.vidaActual = Math.min(this.vidaMaxima, this.vidaActual + cantidad);
    }
}
