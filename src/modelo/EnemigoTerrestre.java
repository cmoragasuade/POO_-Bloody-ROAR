package modelo;

// Enemigo estándar, sin ventajas especiales (README). Usa el ataque básico de Enemigo.
public class EnemigoTerrestre extends Enemigo {
    private static final long serialVersionUID = 1L;

    public EnemigoTerrestre(String nombre, int vidaMaxima, int ataque, int defensa, int velocidad, int experienciaOtorgada, int oroOtorgado) {
        super(nombre, vidaMaxima, ataque, defensa, velocidad, experienciaOtorgada, oroOtorgado);
    }

    @Override
    public String getTipo() {
        return "Terrestre";
    }
}
