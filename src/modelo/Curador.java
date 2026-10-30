package modelo;

import java.util.List;

// Alice - Forma Conejo. Soporte: cura a la party.
public class Curador extends Personaje {
    private static final long serialVersionUID = 1L;

    private int potenciaCurativa;
    private double resistenciaEfectos;
    private int bonificadorAgilidad;

    public Curador(String nombre, int vidaMaxima, int manaMaxima, int ataque, int defensa, int velocidad, int energiaBestia) {
        super(nombre, vidaMaxima, manaMaxima, ataque, defensa, velocidad + 5, energiaBestia);
        this.potenciaCurativa = 30;
        this.resistenciaEfectos = 0.20;
        this.bonificadorAgilidad = 5;
    }

    @Override
    public String getTipo() {
        return "Curador (Conejo)";
    }

    @Override
    public String getNombreHabilidad() {
        return "Curación Conejo";
    }

    @Override
    public int getCostoHabilidad() {
        return 25;
    }

    // Curación Conejo: cura 30 de vida a toda la party (README). No necesita objetivo enemigo.
    @Override
    public String usarHabilidad(Entidad objetivo, List<Personaje> aliados) {
        if (!gastarMana(getCostoHabilidad())) {
            return nombre + " no tiene maná suficiente para " + getNombreHabilidad() + ".";
        }
        for (Personaje aliado : aliados) {
            curarAliado(aliado);
        }
        return nombre + " usa " + getNombreHabilidad() + ": la party recupera " + potenciaCurativa + " de vida.";
    }

    public void curarAliado(Personaje aliado) {
        aliado.curar(potenciaCurativa);
    }

    public int getBonificadorAgilidad() {
        return bonificadorAgilidad;
    }

    public double getResistenciaEfectos() {
        return resistenciaEfectos;
    }
}
