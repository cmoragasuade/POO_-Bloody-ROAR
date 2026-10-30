package modelo;

public class Arma extends Equipable {
    private static final long serialVersionUID = 1L;

    public Arma(int idItem, String nombre, int precio, int bonusAtaque, int bonusDefensa) {
        super(idItem, nombre, precio, bonusAtaque, bonusDefensa);
    }
}
