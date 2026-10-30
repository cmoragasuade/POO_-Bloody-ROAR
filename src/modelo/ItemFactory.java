package modelo;

// Fábrica de ítems (patrón Factory, como en EjemploSingletonFactory del profe):
// en un solo lugar se decide cómo se crea cada ítem del juego.
public class ItemFactory {
    public static final int POCION_VIDA = 1;
    public static final int POCION_MANA = 2;
    public static final int ELIXIR = 3;
    public static final int GARRA_DE_LOBO = 4;
    public static final int ARCO_DEL_TIGRE = 5;

    public static final int[] TODOS = { POCION_VIDA, POCION_MANA, ELIXIR, GARRA_DE_LOBO, ARCO_DEL_TIGRE };

    public Item crearItem(int idItem) {
        if (idItem == POCION_VIDA) {
            return new Consumible(POCION_VIDA, "Poción de Vida", 20, 40, 0);
        } else if (idItem == POCION_MANA) {
            return new Consumible(POCION_MANA, "Poción de Maná", 15, 0, 30);
        } else if (idItem == ELIXIR) {
            return new Consumible(ELIXIR, "Elixir", 45, 60, 40);
        } else if (idItem == GARRA_DE_LOBO) {
            return new Arma(GARRA_DE_LOBO, "Garra de Lobo", 60, 6, 0);
        } else if (idItem == ARCO_DEL_TIGRE) {
            return new Arma(ARCO_DEL_TIGRE, "Arco del Tigre", 70, 8, 0);
        }
        return null;
    }
}
