package dto;

// "Paquete de datos" de un personaje o enemigo para mostrar en pantalla.
// No tiene reglas de negocio (Clase 8 y 10). Mismo estilo que el ejemplo del profe.
public class EntidadDTO {

    private final String nombre;
    private final String tipo;
    private final int nivel;

    private final int vidaActual;
    private final int vidaMaxima;

    private final int manaActual;
    private final int manaMaximo;

    private final int ataque;
    private final int defensa;
    private final int velocidad;

    private final String estado;
    private final String habilidad;

    public EntidadDTO(
            String nombre,
            String tipo,
            int nivel,
            int vidaActual,
            int vidaMaxima,
            int manaActual,
            int manaMaximo,
            int ataque,
            int defensa,
            int velocidad,
            String estado,
            String habilidad) {

        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.vidaActual = vidaActual;
        this.vidaMaxima = vidaMaxima;
        this.manaActual = manaActual;
        this.manaMaximo = manaMaximo;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.estado = estado;
        this.habilidad = habilidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getNivel() {
        return nivel;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getManaActual() {
        return manaActual;
    }

    public int getManaMaximo() {
        return manaMaximo;
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

    public String getEstado() {
        return estado;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public boolean estaVivo() {
        return vidaActual > 0;
    }
}
