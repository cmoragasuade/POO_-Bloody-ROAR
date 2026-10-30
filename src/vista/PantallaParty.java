package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import controlador.GameManager;
import controlador.PartidaException;
import dto.EntidadDTO;
import dto.EstadoPartidaDTO;
import dto.ItemDTO;

// Estado de la party entre batallas. Desde acá se va a pelear, a la tienda o se guarda.
public class PantallaParty extends JFrame {
    private static final long serialVersionUID = 1L;

    private final GameManager gameManager;

    private JTextArea txtAreaEstadoParty;
    private JButton btnBatalla;
    private JButton btnTienda;
    private JButton btnGuardar;
    private JButton btnVolver;

    public PantallaParty(GameManager gameManager, EstadoPartidaDTO estado) {
        this.gameManager = gameManager;

        setTitle("Bloody Roar - Estado de la Party");
        setSize(700, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        inicializarComponentes(estado);
        inicializarEventos();
    }

    private void inicializarComponentes(EstadoPartidaDTO estado) {
        JLabel lblTitulo = new JLabel("ESTADO DE LA PARTY  -  Sala " + estado.getSala() + " de " + estado.getSalaFinal()
                + "  -  Oro: " + estado.getOro(), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));
        add(lblTitulo, BorderLayout.NORTH);

        txtAreaEstadoParty = new JTextArea();
        txtAreaEstadoParty.setEditable(false);
        txtAreaEstadoParty.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtAreaEstadoParty.setText(armarTexto(estado));
        txtAreaEstadoParty.setCaretPosition(0);
        add(new JScrollPane(txtAreaEstadoParty), BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 12));
        btnBatalla = new JButton("Ir a la batalla (Sala " + estado.getSala() + ")");
        btnTienda = new JButton("Tienda");
        btnGuardar = new JButton("Guardar partida");
        btnVolver = new JButton("Menú principal");
        panelInferior.add(btnBatalla);
        panelInferior.add(btnTienda);
        panelInferior.add(btnGuardar);
        panelInferior.add(btnVolver);
        add(panelInferior, BorderLayout.SOUTH);
    }

    // Arma el texto con los datos reales que vienen en el DTO
    private String armarTexto(EstadoPartidaDTO estado) {
        String texto = "--------------------------------------------------\n";
        int numero = 1;
        for (EntidadDTO p : estado.getPersonajes()) {
            texto += " " + numero + ". " + p.getNombre().toUpperCase() + " (" + p.getTipo() + ")\n";
            texto += "    Nivel: " + p.getNivel() + "\n";
            texto += "    HP: " + p.getVidaActual() + "/" + p.getVidaMaxima()
                    + " | MP: " + p.getManaActual() + "/" + p.getManaMaximo()
                    + " | ATK: " + p.getAtaque() + " | DEF: " + p.getDefensa() + "\n";
            texto += "    Habilidad: " + p.getHabilidad() + "\n";
            if (!p.getEstado().isEmpty()) {
                texto += "    " + p.getEstado() + "\n";
            }
            texto += "--------------------------------------------------\n";
            numero++;
        }
        texto += "\n INVENTARIO COMPARTIDO:\n";
        if (estado.getInventario().isEmpty()) {
            texto += "  (vacío)\n";
        }
        for (ItemDTO item : estado.getInventario()) {
            texto += "  • " + item.getNombre() + ": " + item.getDescripcion() + "\n";
        }
        return texto;
    }

    private void inicializarEventos() {
        btnBatalla.addActionListener(e -> gameManager.iniciarBatalla());
        btnTienda.addActionListener(e -> gameManager.abrirTienda());
        btnGuardar.addActionListener(e -> guardar());
        btnVolver.addActionListener(e -> gameManager.mostrarMenuPrincipal());
    }

    private void guardar() {
        try {
            gameManager.guardarPartida();
            JOptionPane.showMessageDialog(this, "Partida guardada.", "Guardar", JOptionPane.INFORMATION_MESSAGE);
        } catch (PartidaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Guardar", JOptionPane.ERROR_MESSAGE);
        }
    }
}
