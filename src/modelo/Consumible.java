package modelo;

public class Consumible extends Item {
    private int vidaRecuperada;
    private int manaRecuperada;

    public Consumible(String nombre, int precio, int vidaRecuperada, int manaRecuperada) {
        super(nombre, precio, true);
        this.vidaRecuperada = vidaRecuperada;
        this.manaRecuperada = manaRecuperada;
    }

    @Override
    public void aplicar(Personaje personaje) {
        personaje.curar(vidaRecuperada);
        personaje.recuperarMana(manaRecuperada);
    }
}
