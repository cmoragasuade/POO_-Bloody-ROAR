package modelo;

import java.util.ArrayList;
import java.util.List;

// Fábrica de enemigos (patrón Factory): arma el grupo de enemigos de cada sala.
// Sala 1 y 2: enemigos comunes. Sala 3: el Boss.
public class EnemigoFactory {
    public static final int SALA_FINAL = 3;

    public List<Enemigo> crearEnemigosParaSala(int sala) {
        List<Enemigo> enemigos = new ArrayList<>();
        if (sala == 1) {
            enemigos.add(new EnemigoTerrestre("Lobo Salvaje", 80, 18, 6, 10, 60, 15));
            enemigos.add(new EnemigoTerrestre("Basilisco", 90, 20, 7, 11, 70, 20));
        } else if (sala == 2) {
            enemigos.add(new EnemigoTerrestre("Jabalí Feroz", 110, 24, 9, 9, 90, 25));
            enemigos.add(new EnemigoVolador("Murciélago Gigante", 70, 22, 5, 17, 80, 25));
            enemigos.add(new EnemigoVolador("Halcón Sombrío", 75, 23, 6, 19, 85, 30));
        } else {
            enemigos.add(new EnemigoBoss("Busuzima", 320, 30, 12, 14, 300, 120));
        }
        return enemigos;
    }
}
