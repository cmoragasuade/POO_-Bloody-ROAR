package vista.paneles;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import dto.EntidadDTO;

// Muestra nombre, barras de vida/maná y estadísticas de un personaje o enemigo.
// Basado en el PanelEstadoEntidad del ejemplo del profe.
public class PanelEstadoEntidad extends JPanel {

    private static final long serialVersionUID = 1L;

    public PanelEstadoEntidad(EntidadDTO entidad, boolean tieneElTurno) {
        setLayout(new GridLayout(0, 1, 2, 2));

        String titulo = entidad.getTipo();
        if (entidad.getNivel() > 0) {
            titulo += " - Nivel " + entidad.getNivel();
        }
        if (tieneElTurno) {
            titulo = ">> SU TURNO << " + titulo;
            setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.ORANGE, 3), titulo));
        } else {
            setBorder(BorderFactory.createTitledBorder(titulo));
        }

        JLabel lblNombre = new JLabel(entidad.getNombre());
        lblNombre.setFont(new Font("SansSerif", Font.BOLD, 15));
        add(lblNombre);

        JProgressBar barraVida = new JProgressBar(0, entidad.getVidaMaxima());
        barraVida.setValue(entidad.getVidaActual());
        barraVida.setStringPainted(true);
        barraVida.setString("HP " + entidad.getVidaActual() + " / " + entidad.getVidaMaxima());
        barraVida.setForeground(new Color(190, 40, 40));
        add(barraVida);

        // Los enemigos no tienen maná: no se muestra la barra
        if (entidad.getManaMaximo() > 0) {
            JProgressBar barraMana = new JProgressBar(0, entidad.getManaMaximo());
            barraMana.setValue(entidad.getManaActual());
            barraMana.setStringPainted(true);
            barraMana.setString("MP " + entidad.getManaActual() + " / " + entidad.getManaMaximo());
            barraMana.setForeground(new Color(40, 90, 190));
            add(barraMana);
        }

        add(new JLabel("ATQ " + entidad.getAtaque() + " | DEF " + entidad.getDefensa() + " | VEL " + entidad.getVelocidad()));

        if (!entidad.getEstado().isEmpty()) {
            JLabel lblEstado = new JLabel(entidad.getEstado());
            lblEstado.setForeground(new Color(150, 80, 0));
            add(lblEstado);
        }
    }
}
