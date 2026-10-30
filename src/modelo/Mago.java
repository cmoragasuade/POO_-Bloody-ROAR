package modelo;

import java.util.List;

// Bakuryu - Forma Topo. Mucho maná y velocidad.
public class Mago extends Personaje {
    private static final long serialVersionUID = 1L;

    private int poderMagico;
    private double probabilidadEfectoEstado;
    private boolean estaBajoTierra;

    public Mago(String nombre, int vidaMaxima, int manaMaxima, int ataque, int defensa, int velocidad, int energiaBestia) {
        super(nombre, vidaMaxima, manaMaxima, ataque, defensa, velocidad, energiaBestia);
        this.poderMagico = 15;
        this.probabilidadEfectoEstado = 0.30;
        this.estaBajoTierra = false;
    }

    @Override
    public String getTipo() {
        return "Mago (Topo)";
    }

    @Override
    public String getNombreHabilidad() {
        return "Topo Ciego";
    }

    @Override
    public int getCostoHabilidad() {
        return 20;
    }

    // Topo Ciego: daño mágico (ataque + poder mágico). 30% de probabilidad
    // de meterse bajo tierra y esquivar el próximo golpe.
    @Override
    public String usarHabilidad(Entidad objetivo, List<Personaje> aliados) {
        if (!gastarMana(getCostoHabilidad())) {
            return nombre + " no tiene maná suficiente para " + getNombreHabilidad() + ".";
        }
        int vidaAntes = objetivo.getVidaActual();
        objetivo.recibirDanio(this.ataque + this.poderMagico);
        int danio = vidaAntes - objetivo.getVidaActual();
        String mensaje = nombre + " usa " + getNombreHabilidad() + " y le hace " + danio + " de daño a " + objetivo.getNombre() + ".";
        if (Math.random() < probabilidadEfectoEstado) {
            this.estaBajoTierra = true;
            mensaje += " ¡Se oculta bajo tierra y esquivará el próximo golpe!";
        }
        return mensaje;
    }

    // Si está bajo tierra, esquiva el golpe completo y sale
    @Override
    public void recibirDanio(int cantidad) {
        if (estaBajoTierra) {
            estaBajoTierra = false;
            return;
        }
        super.recibirDanio(cantidad);
    }

    public boolean isEstaBajoTierra() {
        return estaBajoTierra;
    }
}
