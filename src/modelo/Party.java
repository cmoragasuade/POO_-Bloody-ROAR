package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// El equipo del jugador: de 2 a 4 personajes, con inventario y oro compartidos.
public class Party implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int TAMANIO_MIN = 2;
    public static final int TAMANIO_MAX = 4;

    private String nombre;
    private List<Personaje> personajes;
    private int oro;
    private List<Item> inventario;

    public Party(String nombre) {
        this.nombre = nombre;
        this.personajes = new ArrayList<>();
        this.oro = 0;
        this.inventario = new ArrayList<>();
    }

    public boolean agregarPersonaje(Personaje personaje) {
        if (this.personajes.size() >= TAMANIO_MAX) {
            return false;
        }
        this.personajes.add(personaje);
        return true;
    }

    public void eliminarPersonaje(Personaje personaje) {
        this.personajes.remove(personaje);
    }

    public int cantidadPersonajes() {
        return this.personajes.size();
    }

    public boolean estaLista() {
        return this.personajes.size() >= TAMANIO_MIN && this.personajes.size() <= TAMANIO_MAX;
    }

    // La party pierde cuando no queda nadie vivo
    public boolean estaDerrotada() {
        for (Personaje personaje : personajes) {
            if (personaje.estaVivo()) {
                return false;
            }
        }
        return true;
    }

    public List<Personaje> getPersonajesVivos() {
        List<Personaje> vivos = new ArrayList<>();
        for (Personaje personaje : personajes) {
            if (personaje.estaVivo()) {
                vivos.add(personaje);
            }
        }
        return vivos;
    }

    public void agregarItem(Item item) {
        this.inventario.add(item);
    }

    // Usa un ítem del inventario sobre un personaje. Si es consumible, se gasta.
    public boolean usarItem(Item item, Personaje personaje) {
        if (!this.inventario.contains(item)) {
            return false;
        }
        item.aplicar(personaje);
        if (item.esConsumible()) {
            this.inventario.remove(item);
        }
        return true;
    }

    public void sumarOro(int cantidad) {
        this.oro += cantidad;
    }

    public boolean restarOro(int cantidad) {
        if (this.oro < cantidad) {
            return false;
        }
        this.oro -= cantidad;
        return true;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public List<Item> getInventario() {
        return inventario;
    }

    public int getOro() {
        return oro;
    }
}
