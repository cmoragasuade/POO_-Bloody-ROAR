package dto;

// Datos de un ítem para mostrar en la tienda o en el inventario
public class ItemDTO {

    private final String nombre;
    private final String descripcion;
    private final int precio;

    public ItemDTO(String nombre, String descripcion, int precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    // Así se ve el ítem en una lista desplegable
    @Override
    public String toString() {
        return nombre + " - " + descripcion;
    }
}
