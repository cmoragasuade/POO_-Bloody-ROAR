package modelo;

import java.util.Comparator;

// Ordena de mayor a menor velocidad: el más rápido juega primero (README).
// Mismo estilo que los Comparator del ejemplo del profe.
public class ComparadorPorVelocidad implements Comparator<Entidad> {

    @Override
    public int compare(Entidad e1, Entidad e2) {
        return Integer.compare(e2.getVelocidad(), e1.getVelocidad());
    }
}
