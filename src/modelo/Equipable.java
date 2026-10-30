package modelo;

// Ítem que se equipa (no se gasta): da bonus de ataque y/o defensa
public abstract class Equipable extends Item {
    private static final long serialVersionUID = 1L;

    protected int bonusAtaque;
    protected int bonusDefensa;

    public Equipable(int idItem, String nombre, int precio, int bonusAtaque, int bonusDefensa) {
        super(idItem, nombre, precio, false);
        this.bonusAtaque = bonusAtaque;
        this.bonusDefensa = bonusDefensa;
    }

    // Aplicar un equipable = equiparlo (Personaje se encarga de sacar el arma anterior)
    @Override
    public void aplicar(Personaje personaje) {
        personaje.equiparArma(this);
    }

    @Override
    public String getDescripcion() {
        return "+" + bonusAtaque + " ataque, +" + bonusDefensa + " defensa";
    }

    public int getBonusAtaque() {
        return bonusAtaque;
    }

    public int getBonusDefensa() {
        return bonusDefensa;
    }
}
