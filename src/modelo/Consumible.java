package modelo;

// Ítem que se gasta al usarlo: recupera vida y/o maná
public class Consumible extends Item {
    private static final long serialVersionUID = 1L;

    private int vidaRecuperada;
    private int manaRecuperada;

    public Consumible(int idItem, String nombre, int precio, int vidaRecuperada, int manaRecuperada) {
        super(idItem, nombre, precio, true);
        this.vidaRecuperada = vidaRecuperada;
        this.manaRecuperada = manaRecuperada;
    }

    @Override
    public void aplicar(Personaje personaje) {
        personaje.curar(vidaRecuperada);
        personaje.recuperarMana(manaRecuperada);
    }

    @Override
    public String getDescripcion() {
        return "Recupera " + vidaRecuperada + " de vida y " + manaRecuperada + " de maná";
    }
}
