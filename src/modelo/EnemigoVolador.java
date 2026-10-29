package modelo;

public class EnemigoVolador extends Enemigo {
    private double probabilidadEsquive;

    public EnemigoVolador(String nombre, int vidaMaxima, int ataque, int defensa, int velocidad, int experienciaOtorgada, int oroOtorgado) {
        super(nombre, vidaMaxima, ataque, defensa, velocidad, experienciaOtorgada, oroOtorgado);
        this.probabilidadEsquive = 0.25;
    }

    @Override
    public void atacar(Entidad objetivo) {
        objetivo.recibirDanio(this.ataque);
    }

    public boolean esquivar() {
        return Math.random() < this.probabilidadEsquive;
    }
}
