package controlador;

import java.util.ArrayList;
import java.util.List;

import dto.EntidadDTO;
import dto.ItemDTO;
import modelo.Enemigo;
import modelo.Entidad;
import modelo.Item;
import modelo.Personaje;

// Pasa objetos del modelo a DTOs para la vista. Es una clase auxiliar
// (Pure Fabrication, Clase 5) que usan los dos controladores.
public class ConversorDTO {

    public static EntidadDTO convertir(Entidad entidad) {
        int nivel = 0;
        int manaActual = 0;
        int manaMaximo = 0;
        String estado = "";
        String habilidad = "";

        if (entidad.esDelJugador()) {
            Personaje personaje = (Personaje) entidad;
            nivel = personaje.getNivel();
            manaActual = personaje.getManaActual();
            manaMaximo = personaje.getManaMaxima();
            habilidad = personaje.getNombreHabilidad() + " (" + personaje.getCostoHabilidad() + " MP)";
            if (personaje.isTransformado()) {
                estado += "BESTIA ";
            }
            if (personaje.isDefendiendo()) {
                estado += "DEFENDIENDO ";
            }
            if (personaje.getArmaEquipada() != null) {
                estado += "Arma: " + personaje.getArmaEquipada().getNombre();
            }
        }
        if (!entidad.estaVivo()) {
            estado = "CAÍDO";
        }

        return new EntidadDTO(
            entidad.getNombre(),
            entidad.getTipo(),
            nivel,
            entidad.getVidaActual(),
            entidad.getVidaMaxima(),
            manaActual,
            manaMaximo,
            entidad.getAtaque(),
            entidad.getDefensa(),
            entidad.getVelocidad(),
            estado.trim(),
            habilidad
        );
    }

    public static List<EntidadDTO> convertirPersonajes(List<Personaje> personajes) {
        List<EntidadDTO> lista = new ArrayList<>();
        for (Personaje personaje : personajes) {
            lista.add(convertir(personaje));
        }
        return lista;
    }

    public static List<EntidadDTO> convertirEnemigos(List<Enemigo> enemigos) {
        List<EntidadDTO> lista = new ArrayList<>();
        for (Enemigo enemigo : enemigos) {
            lista.add(convertir(enemigo));
        }
        return lista;
    }

    public static ItemDTO convertir(Item item) {
        return new ItemDTO(item.getNombre(), item.getDescripcion(), item.getPrecio());
    }

    public static List<ItemDTO> convertirItems(List<Item> items) {
        List<ItemDTO> lista = new ArrayList<>();
        for (Item item : items) {
            lista.add(convertir(item));
        }
        return lista;
    }
}
