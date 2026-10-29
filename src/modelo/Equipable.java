package modelo;

public abstract class Equipable extends Item {
    protected int bonusAtaque;
    protected int bonusDefensa;

    public Equipable(String nombre, int precio, int bonusAtaque, int bonusDefensa) {
        super(nombre, precio, false);
        this.bonusAtaque = bonusAtaque;
        this.bonusDefensa = bonusDefensa;
    }

    @Override
    public void aplicar(Personaje personaje) {
        personaje.setAtaque(personaje.getAtaque() + this.bonusAtaque);
        personaje.setDefensa(personaje.getDefensa() + this.bonusDefensa);
    }
}
