package test;

import java.util.List;

import modelo.Arquero;
import modelo.Batalla;
import modelo.Curador;
import modelo.Enemigo;
import modelo.EnemigoFactory;
import modelo.Guerrero;
import modelo.ItemFactory;
import modelo.Mago;
import modelo.Party;
import modelo.Personaje;

// Prueba por consola, sin pantallas: juega las 3 salas solo con el modelo.
// El "jugador" ataca siempre al primer enemigo y usa la habilidad cuando puede.
public class MainPruebas {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA DEL MODELO: 3 SALAS SEGUIDAS ===");

        Party party = new Party("Grupo 6");
        party.agregarPersonaje(new Guerrero("Yugo", 120, 40, 22, 15, 12, 20));
        party.agregarPersonaje(new Mago("Bakuryu", 90, 70, 18, 10, 16, 25));
        party.agregarPersonaje(new Arquero("Long", 100, 50, 24, 11, 18, 22));
        party.agregarPersonaje(new Curador("Alice", 110, 80, 16, 12, 15, 18));
        party.agregarItem(new ItemFactory().crearItem(ItemFactory.POCION_VIDA));

        EnemigoFactory fabrica = new EnemigoFactory();

        for (int sala = 1; sala <= EnemigoFactory.SALA_FINAL; sala++) {
            List<Enemigo> enemigos = fabrica.crearEnemigosParaSala(sala);
            Batalla batalla = new Batalla(party, enemigos);
            batalla.iniciarBatalla();

            int acciones = 0;
            while (!batalla.termino() && acciones < 500) {
                Personaje actual = (Personaje) batalla.obtenerSiguienteTurno();
                if (actual.getManaActual() >= actual.getCostoHabilidad() && acciones % 2 == 0) {
                    batalla.usarHabilidad(0);
                } else {
                    batalla.atacar(0);
                }
                acciones++;
            }

            System.out.println("\n--- Sala " + sala + " (rondas: " + batalla.getRonda() + ") ---");
            System.out.print(batalla.getRegistroReciente());
            System.out.println("Ganó la party: " + batalla.verificarGanador());
            for (String linea : batalla.getResumenRecompensas()) {
                System.out.println("  " + linea);
            }
            if (!batalla.verificarGanador()) {
                System.out.println("GAME OVER en la sala " + sala);
                break;
            }
        }

        System.out.println("\nEstado final de la party:");
        for (Personaje p : party.getPersonajes()) {
            System.out.println("- " + p.getNombre() + " | Nivel " + p.getNivel() + " | HP: " + p.getVidaActual() + "/" + p.getVidaMaxima()
                    + " | MP: " + p.getManaActual() + "/" + p.getManaMaxima());
        }
        System.out.println("Oro de la party: " + party.getOro());
    }
}
