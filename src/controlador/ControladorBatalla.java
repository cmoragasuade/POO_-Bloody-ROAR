package controlador;

import java.util.ArrayList;
import java.util.List;

import modelo.Batalla;
import modelo.Enemigo;
import modelo.EnemigoTerrestre;
import modelo.Party;
import modelo.Personaje;
import modelo.Guerrero;
import modelo.Mago;
import modelo.Arquero;
import modelo.Curador;

public class ControladorBatalla {
    private Batalla batalla;
    private Party party;
    private List<Enemigo> enemigos;

    public ControladorBatalla() {
        this.party = new Party();
        this.party.agregarPersonaje(new Guerrero("Yugo", 120, 40, 22, 15, 12, 20));
        this.party.agregarPersonaje(new Mago("Bakuryu", 90, 70, 18, 10, 16, 25));
        this.party.agregarPersonaje(new Arquero("Long", 100, 50, 24, 11, 18, 22));
        this.party.agregarPersonaje(new Curador("Alice", 110, 80, 16, 12, 15, 18));

        this.enemigos = new ArrayList<>();
        this.enemigos.add(new EnemigoTerrestre("Lobo Salvaje", 80, 14, 8, 10, 50, 10));
        this.enemigos.add(new EnemigoTerrestre("Basilisco", 90, 16, 9, 11, 60, 15));

        this.batalla = new Batalla(this.party, this.enemigos);
    }

    public void iniciarPrueba() {
        System.out.println("=== BATALLA DE PRUEBA ===");
        System.out.println("Party: " + this.party.getPersonajes().size() + " personajes");
        System.out.println("Enemigos: " + this.enemigos.size());
        System.out.println("Turnos iniciales:");
        for (var e : this.batalla.getColaTurnos()) {
            System.out.println("- " + e.getNombre() + " (velocidad: " + e.getVelocidad() + ")");
        }
    }

    public void simularTurno(int indicePersonaje, String accion) {
        List<Personaje> personajes = this.party.getPersonajes();
        if (indicePersonaje < 0 || indicePersonaje >= personajes.size()) {
            throw new IllegalArgumentException("Índice de personaje inválido");
        }

        Personaje atacante = personajes.get(indicePersonaje);
        Enemigo objetivo = this.enemigos.get(0);

        switch (accion.toUpperCase()) {
            case "ATACAR":
                atacante.atacar(objetivo);
                System.out.println(atacante.getNombre() + " ataca a " + objetivo.getNombre() + " por " + atacante.getAtaque());
                break;
            case "DEFENDER":
                atacante.defender();
                System.out.println(atacante.getNombre() + " se defiende.");
                break;
            case "HABILIDAD":
                atacante.usarHabilidad(objetivo);
                System.out.println(atacante.getNombre() + " usa habilidad sobre " + objetivo.getNombre());
                break;
            case "TRANSFORMAR":
                atacante.transformar();
                System.out.println(atacante.getNombre() + " se transforma en bestia.");
                break;
            default:
                throw new IllegalArgumentException("Acción no válida: " + accion);
        }

        for (Enemigo enemigo : this.enemigos) {
            if (enemigo.estaVivo()) {
                enemigo.atacar(atacante);
                System.out.println(enemigo.getNombre() + " ataca a " + atacante.getNombre() + " por " + enemigo.getAtaque());
                break;
            }
        }

        System.out.println("Estado party: ");
        for (Personaje p : this.party.getPersonajes()) {
            System.out.println(p.getNombre() + " - HP: " + p.getVidaActual() + "/" + p.getVidaMaxima() + " | MP: " + p.getManaActual() + "/" + p.getManaMaxima());
        }

        System.out.println("Estado enemigos: ");
        for (Enemigo e : this.enemigos) {
            System.out.println(e.getNombre() + " - HP: " + e.getVidaActual() + "/" + e.getVidaMaxima());
        }
    }
}
