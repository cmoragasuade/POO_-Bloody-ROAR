package modelo;

public class EnemigoTerrestre extends Enemigo {
    public EnemigoTerrestre(String nombre, int vidaMaxima, int ataque, int defensa, int velocidad, int experienciaOtorgada, int oroOtorgado) {
        super(nombre, vidaMaxima, ataque, defensa, velocidad, experienciaOtorgada, oroOtorgado);
    }

    @Override
    public void atacar(Entidad objetivo) {
        objetivo.recibirDanio(this.ataque);
    }
}
