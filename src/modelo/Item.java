package modelo;

import java.io.Serializable;

// Molde general de cualquier objeto (pociones, armas). Serializable para guardar la partida.
public abstract class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    protected int idItem;
    protected String nombre;
    protected int precio;
    protected boolean consumible;

    public Item(int idItem, String nombre, int precio, boolean consumible) {
        this.idItem = idItem;
        this.nombre = nombre;
        this.precio = precio;
        this.consumible = consumible;
    }

    // Qué le hace el ítem al personaje que lo usa: lo decide cada tipo de ítem
    public abstract void aplicar(Personaje personaje);

    // Texto corto para mostrar en la tienda y en el inventario
    public abstract String getDescripcion();

    public boolean esConsumible() {
        return consumible;
    }

    public int getIdItem() {
        return idItem;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }
}
