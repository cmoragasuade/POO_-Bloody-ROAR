package modelo;

import java.io.Serializable;

// Clase base de Personaje y Enemigo: lo que comparten todos los que pelean.
// Es Serializable para poder guardar la partida en un archivo (Clase 13).
public abstract class Entidad implements Serializable {
    private static final long serialVersionUID = 1L;

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

    // true si la maneja el jugador (Personaje), false si la maneja la computadora (Enemigo)
    public abstract boolean esDelJugador();

    // Texto para mostrar el tipo en pantalla (ej. "Guerrero", "Volador")
    public abstract String getTipo();

    // Fórmula del README: Daño = Ataque - Defensa. Si da menos de 1, el daño es 1.
    public void recibirDanio(int cantidad) {
        int danoEfectivo = Math.max(1, cantidad - getDefensaEfectiva());
        this.vidaActual = Math.max(0, this.vidaActual - danoEfectivo);
    }

    // La defensa que se usa al recibir un golpe. Personaje la cambia cuando defiende.
    protected int getDefensaEfectiva() {
        return this.defensa;
    }

    public boolean estaVivo() {
        return this.vidaActual > 0;
    }

    public void curar(int cantidad) {
        if (estaVivo()) {
            this.vidaActual = Math.min(this.vidaMaxima, this.vidaActual + cantidad);
        }
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
}
