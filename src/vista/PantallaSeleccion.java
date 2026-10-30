package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controlador.GameManager;
import dto.EntidadDTO;

// Al iniciar partida el jugador elige de 2 a 4 combatientes (corrección del profe y README)
public class PantallaSeleccion extends JFrame {
    private static final long serialVersionUID = 1L;

    private final GameManager gameManager;
    private final List<JCheckBox> casillas;

    private JButton btnConfirmar;
    private JButton btnVolver;

    public PantallaSeleccion(GameManager gameManager, List<EntidadDTO> disponibles) {
        this.gameManager = gameManager;
        this.casillas = new ArrayList<>();

        setTitle("Bloody Roar - Elegir Personajes");
        setSize(640, 460);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        inicializarComponentes(disponibles);
        inicializarEventos();
    }

    private void inicializarComponentes(List<EntidadDTO> disponibles) {
        JLabel lblTitulo = new JLabel("Elegí entre 2 y 4 personajes para tu party", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelPersonajes = new JPanel(new GridLayout(disponibles.size(), 1, 8, 8));
        panelPersonajes.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        for (EntidadDTO personaje : disponibles) {
            JCheckBox casilla = new JCheckBox(
                personaje.getNombre() + " - " + personaje.getTipo()
                + "   |   HP " + personaje.getVidaMaxima()
                + "  MP " + personaje.getManaMaximo()
                + "  ATQ " + personaje.getAtaque()
                + "  DEF " + personaje.getDefensa()
                + "  VEL " + personaje.getVelocidad()
                + "   |   " + personaje.getHabilidad()
            );
            casilla.setSelected(true);
            casillas.add(casilla);
            panelPersonajes.add(casilla);
        }
        add(panelPersonajes, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        btnConfirmar = new JButton("Confirmar party");
        btnVolver = new JButton("Volver al menú");
        panelBotones.add(btnConfirmar);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void inicializarEventos() {
        btnConfirmar.addActionListener(e -> confirmar());
        btnVolver.addActionListener(e -> gameManager.mostrarMenuPrincipal());
    }

    private void confirmar() {
        List<Integer> elegidos = new ArrayList<>();
        for (int i = 0; i < casillas.size(); i++) {
            if (casillas.get(i).isSelected()) {
                elegidos.add(i);
            }
        }
        if (!gameManager.crearParty(elegidos)) {
            JOptionPane.showMessageDialog(this, "La party tiene que tener entre 2 y 4 personajes.", "Elegir personajes", JOptionPane.WARNING_MESSAGE);
        }
    }
}
