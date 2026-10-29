package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Batalla {
    private Party party;
    private List<Enemigo> enemigos;
    private List<Entidad> colaTurnos;

    public Batalla(Party party, List<Enemigo> enemigos) {
        this.party = party;
        this.enemigos = enemigos;
        this.colaTurnos = new ArrayList<>();
        inicializarTurnos();
    }

    private void inicializarTurnos() {
        this.colaTurnos.clear();
        for (Personaje personaje : this.party.getPersonajes()) {
            this.colaTurnos.add(personaje);
        }
        for (Enemigo enemigo : this.enemigos) {
            this.colaTurnos.add(enemigo);
        }
        Collections.sort(this.colaTurnos, Comparator.comparingInt(Entidad::getVelocidad).reversed());
    }

    public Entidad obtenerSiguienteTurno() {
        if (this.colaTurnos.isEmpty()) {
            return null;
        }
        Entidad entidad = this.colaTurnos.remove(0);
        return entidad;
    }

    public boolean verificarFinBatalla() {
        for (Enemigo enemigo : this.enemigos) {
            if (enemigo.estaVivo()) {
                return false;
            }
        }
        return true;
    }

    public void otorgarRecompensas() {
        for (Personaje personaje : this.party.getPersonajes()) {
            int experienciaTotal = 0;
            for (Enemigo enemigo : this.enemigos) {
                experienciaTotal += enemigo.getExperienciaOtorgada();
            }
            personaje.sumarExperiencia(experienciaTotal / this.party.getPersonajes().size());
        }
    }

    public List<Entidad> getColaTurnos() {
        return colaTurnos;
    }
}
