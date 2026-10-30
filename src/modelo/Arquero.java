package modelo;

import java.util.List;

// Long - Forma Tigre. Mucho ataque y velocidad.
public class Arquero extends Personaje {
    private static final long serialVersionUID = 1L;

    private double probabilidadCritico;
    private double multiplicadorCritico;
    private int cargasInstintoTigre;

    public Arquero(String nombre, int vidaMaxima, int manaMaxima, int ataque, int defensa, int velocidad, int energiaBestia) {
        super(nombre, vidaMaxima, manaMaxima, ataque, defensa, velocidad, energiaBestia);
        this.probabilidadCritico = 0.25;
        this.multiplicadorCritico = 1.5;
        this.cargasInstintoTigre = 0;
    }

    @Override
    public String getTipo() {
        return "Arquero (Tigre)";
    }

    @Override
    public String getNombreHabilidad() {
        return "Ráfaga de Tigre";
    }

    @Override
    public int getCostoHabilidad() {
        return 10;
    }

    // Ataque básico con chance de golpe crítico. Cada ataque suma una carga.
    @Override
    public void atacar(Entidad objetivo) {
        int danio = this.ataque;
        if (Math.random() < probabilidadCritico) {
            danio = (int) Math.round(this.ataque * multiplicadorCritico);
        }
        objetivo.recibirDanio(danio);
        this.cargasInstintoTigre++;
    }

    // Ráfaga de Tigre: 3 golpes seguidos; el tercero es crítico asegurado (README)
    @Override
    public String usarHabilidad(Entidad objetivo, List<Personaje> aliados) {
        if (!gastarMana(getCostoHabilidad())) {
            return nombre + " no tiene maná suficiente para " + getNombreHabilidad() + ".";
        }
        int vidaAntes = objetivo.getVidaActual();
        objetivo.recibirDanio((int) (this.ataque * 0.8));
        objetivo.recibirDanio((int) (this.ataque * 0.8));
        objetivo.recibirDanio((int) Math.round(this.ataque * multiplicadorCritico));
        this.cargasInstintoTigre = 0;
        int danio = vidaAntes - objetivo.getVidaActual();
        return nombre + " usa " + getNombreHabilidad() + ": 3 flechas a " + objetivo.getNombre()
                + " (la última crítica) por " + danio + " de daño en total.";
    }

    public int getCargasInstintoTigre() {
        return cargasInstintoTigre;
    }
}
