package test;

import java.util.ArrayList;
import java.util.List;

import modelo.Arquero;
import modelo.Batalla;
import modelo.Curador;
import modelo.EnemigoTerrestre;
import modelo.Guerrero;
import modelo.Mago;
import modelo.Party;
import modelo.Personaje;

public class MainPruebas {
    public static void main(String[] args) {
        System.out.println("=== PRUEBAS DE INTERACCION ENTRE CLASES ===");

        Party party = new Party();
        party.agregarPersonaje(new Guerrero("Yugo", 120, 40, 22, 15, 12, 20));
        party.agregarPersonaje(new Mago("Bakuryu", 90, 70, 18, 10, 16, 25));
        party.agregarPersonaje(new Arquero("Long", 100, 50, 24, 11, 18, 22));
        party.agregarPersonaje(new Curador("Alice", 110, 80, 16, 12, 15, 18));

        List<modelo.Enemigo> enemigos = new ArrayList<>();
        enemigos.add(new EnemigoTerrestre("Lobo Salvaje", 80, 14, 8, 10, 50, 10));
        enemigos.add(new EnemigoTerrestre("Basilisco", 90, 16, 9, 11, 60, 15));

        Batalla batalla = new Batalla(party, enemigos);

        System.out.println("\nTurnos ordenados por velocidad:");
        for (var e : batalla.getColaTurnos()) {
            System.out.println("- " + e.getNombre() + " | velocidad: " + e.getVelocidad());
        }

        System.out.println("\nSimulacion de acciones:");
        Personaje atacante = party.getPersonajes().get(0);
        modelo.Enemigo objetivo = enemigos.get(0);

        atacante.atacar(objetivo);
        System.out.println(atacante.getNombre() + " ataca a " + objetivo.getNombre() + ".");
        System.out.println("HP enemigo: " + objetivo.getVidaActual() + "/" + objetivo.getVidaMaxima());

        party.getPersonajes().get(1).usarHabilidad(objetivo);
        System.out.println(party.getPersonajes().get(1).getNombre() + " usa habilidad sobre " + objetivo.getNombre() + ".");
        System.out.println("HP enemigo: " + objetivo.getVidaActual() + "/" + objetivo.getVidaMaxima());

        party.getPersonajes().get(3).defender();
        System.out.println(party.getPersonajes().get(3).getNombre() + " se prepara a defender.");

        System.out.println("\nEstado final de la party:");
        for (Personaje p : party.getPersonajes()) {
            System.out.println("- " + p.getNombre() + " | HP: " + p.getVidaActual() + "/" + p.getVidaMaxima()
                    + " | MP: " + p.getManaActual() + "/" + p.getManaMaxima());
        }

        System.out.println("\nEstado final de enemigos:");
        for (var enemigo : enemigos) {
            System.out.println("- " + enemigo.getNombre() + " | HP: " + enemigo.getVidaActual() + "/" + enemigo.getVidaMaxima());
        }
    }
}
