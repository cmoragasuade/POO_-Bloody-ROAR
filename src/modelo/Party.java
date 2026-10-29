package modelo;

import java.util.ArrayList;
import java.util.List;

public class Party {
    private List<Personaje> personajes;
    private int oro;
    private List<Item> inventario;

    public Party() {
        this.personajes = new ArrayList<>();
        this.oro = 0;
        this.inventario = new ArrayList<>();
    }

    public void agregarPersonaje(Personaje personaje) {
        if (this.personajes.size() < 4) {
            this.personajes.add(personaje);
        }
    }

    public void removerPersonaje(Personaje personaje) {
        this.personajes.remove(personaje);
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public boolean estaLista() {
        return this.personajes.size() >= 2 && this.personajes.size() <= 4;
    }

    public void agregarItem(Item item) {
        this.inventario.add(item);
    }

    public void usarItem(Item item, Personaje personaje) {
        if (this.inventario.contains(item)) {
            item.aplicar(personaje);
            this.inventario.remove(item);
        }
    }

    public List<Item> getInventario() {
        return inventario;
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }
}
