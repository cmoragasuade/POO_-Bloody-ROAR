package modelo;

// Enemigo con probabilidad de esquivar los ataques (README)
public class EnemigoVolador extends Enemigo {
    private static final long serialVersionUID = 1L;

    private double probabilidadEsquive;

    public EnemigoVolador(String nombre, int vidaMaxima, int ataque, int defensa, int velocidad, int experienciaOtorgada, int oroOtorgado) {
        super(nombre, vidaMaxima, ataque, defensa, velocidad, experienciaOtorgada, oroOtorgado);
        this.probabilidadEsquive = 0.25;
    }

    @Override
    public String getTipo() {
        return "Volador";
    }

    public boolean esquivar() {
        return Math.random() < this.probabilidadEsquive;
    }

    // Antes de recibir el golpe intenta esquivarlo
    @Override
    public void recibirDanio(int cantidad) {
        if (esquivar()) {
            return;
        }
        super.recibirDanio(cantidad);
    }
}
