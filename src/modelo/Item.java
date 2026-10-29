package modelo;

public abstract class Item {
    protected String nombre;
    protected int precio;
    protected boolean consumible;

    public Item(String nombre, int precio, boolean consumible) {
        this.nombre = nombre;
        this.precio = precio;
        this.consumible = consumible;
    }

    public abstract void aplicar(Personaje personaje);

    public boolean esConsumible() {
        return consumible;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }
}
