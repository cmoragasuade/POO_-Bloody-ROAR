package modelo;

import java.util.ArrayList;
import java.util.List;

public class Party {
    private String nombre;
    private int tamanioMax;
    private List<Personaje> personajes;

    public Party(String nombre, int tamanioMax) {
        this.nombre = nombre;
        this.tamanioMax = tamanioMax;
        this.personajes = new ArrayList<>();
    }

    public void agregarPersonaje(Personaje p) {
        if (personajes.size() < tamanioMax) {
            personajes.add(p);
            System.out.println(p.getNombre() + " se unió a la party.");
        } else {
            System.out.println("La party está llena (máximo " + tamanioMax + " personajes).");
        }
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }
    
    public String getNombre() { return nombre; }
}