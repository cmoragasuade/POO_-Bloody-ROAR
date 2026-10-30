package dto;

import java.util.List;

// Datos de la partida fuera de combate: la party, el oro, el inventario y la sala
public class EstadoPartidaDTO {

    private final List<EntidadDTO> personajes;
    private final List<ItemDTO> inventario;
    private final int oro;
    private final int sala;
    private final int salaFinal;

    public EstadoPartidaDTO(
            List<EntidadDTO> personajes,
            List<ItemDTO> inventario,
            int oro,
            int sala,
            int salaFinal) {

        this.personajes = personajes;
        this.inventario = inventario;
        this.oro = oro;
        this.sala = sala;
        this.salaFinal = salaFinal;
    }

    public List<EntidadDTO> getPersonajes() {
        return personajes;
    }

    public List<ItemDTO> getInventario() {
        return inventario;
    }

    public int getOro() {
        return oro;
    }

    public int getSala() {
        return sala;
    }

    public int getSalaFinal() {
        return salaFinal;
    }
}
