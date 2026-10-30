package modelo;

import java.util.List;

// Yugo - Forma Lobo. Mucha vida y defensa.
public class Guerrero extends Personaje {
    private static final long serialVersionUID = 1L;

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
    public String getTipo() {
        return "Guerrero (Lobo)";
    }

    @Override
    public String getNombreHabilidad() {
        return "Furia Lobo";
    }

    @Override
    public int getCostoHabilidad() {
        return 15;
    }

    // Probabilidad de bloquear: si bloquea, el golpe pierde el escudo de absorción
    @Override
    public void recibirDanio(int cantidad) {
        if (Math.random() < probabilidadBloqueo) {
            cantidad = Math.max(0, cantidad - escudoAbsorcion);
        }
        super.recibirDanio(cantidad);
    }

    // README: cuanto menos vida tiene, más ataque gana
    public int activarFuriaLobo() {
        double porcentajeVida = (double) this.vidaActual / this.vidaMaxima;
        if (porcentajeVida < 0.05) {
            return (int) Math.round(this.ataque * 0.5);
        } else if (porcentajeVida < 0.25) {
            return (int) Math.round(this.ataque * 0.3);
        } else if (porcentajeVida < 0.50) {
            return (int) Math.round(this.ataque * 0.2);
        }
        return (int) Math.round(this.ataque * 0.1);
    }

    // Furia Lobo: +ataque durante 3 turnos (como EfectoEstado) y golpea al objetivo
    @Override
    public String usarHabilidad(Entidad objetivo, List<Personaje> aliados) {
        if (!gastarMana(getCostoHabilidad())) {
            return nombre + " no tiene maná suficiente para " + getNombreHabilidad() + ".";
        }
        this.furiaLobo = activarFuriaLobo();
        agregarEfecto(new EfectoEstado("Furia Lobo", 3, this.furiaLobo));
        int vidaAntes = objetivo.getVidaActual();
        atacar(objetivo);
        int danio = vidaAntes - objetivo.getVidaActual();
        return nombre + " usa " + getNombreHabilidad() + " (+" + furiaLobo + " de ataque por 3 turnos) y le hace "
                + danio + " de daño a " + objetivo.getNombre() + ".";
    }
}
