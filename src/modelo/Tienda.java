package modelo;

import java.util.ArrayList;
import java.util.List;

// Vende ítems a la party a cambio de oro. Cada compra crea un ítem nuevo con la fábrica.
public class Tienda {
    private List<Item> itemsDisponibles;
    private ItemFactory fabrica;

    public Tienda() {
        this.fabrica = new ItemFactory();
        this.itemsDisponibles = new ArrayList<>();
        for (int idItem : ItemFactory.TODOS) {
            this.itemsDisponibles.add(fabrica.crearItem(idItem));
        }
    }

    // Devuelve true si la compra se hizo (alcanzaba el oro)
    public boolean venderItem(Party comprador, Item item) {
        if (!comprador.restarOro(item.getPrecio())) {
            return false;
        }
        comprador.agregarItem(fabrica.crearItem(item.getIdItem()));
        return true;
    }

    public List<Item> getItemsDisponibles() {
        return itemsDisponibles;
    }
}
