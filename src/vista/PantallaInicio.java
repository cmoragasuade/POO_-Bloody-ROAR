package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controlador.GameManager;
import controlador.PartidaException;
import util.CargadorImagenes;

// Menú principal. La pantalla solo muestra botones y le avisa al GameManager qué se tocó.
public class PantallaInicio extends JFrame {
    private static final long serialVersionUID = 1L;

    private final GameManager gameManager;

    private JButton btnIniciar;
    private JButton btnCargar;
    private JButton btnTienda;
    private JButton btnSalir;

    public PantallaInicio(GameManager gameManager) {
        this.gameManager = gameManager;

        setTitle("Bloody Roar - Menú Principal");
        setSize(600, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        inicializarComponentes();
        inicializarEventos();
    }

    private void inicializarComponentes() {
        // Imagen del título (si no está, se muestra el texto)
        JLabel lblTitulo = new JLabel("BLOODY ROAR - RPG", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        ImageIcon imagen = CargadorImagenes.cargar("bloody roar title.jpeg");
        if (imagen != null) {
            Image escalada = imagen.getImage().getScaledInstance(420, 220, Image.SCALE_SMOOTH);
            lblTitulo.setIcon(new ImageIcon(escalada));
            lblTitulo.setText("");
        }
        add(lblTitulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(4, 1, 12, 12));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 150, 30, 150));

        btnIniciar = new JButton("Iniciar Nueva Partida");
        btnCargar = new JButton("Cargar Partida");
        btnTienda = new JButton("Abrir Tienda");
        btnSalir = new JButton("Salir");

        panelBotones.add(btnIniciar);
        panelBotones.add(btnCargar);
        panelBotones.add(btnTienda);
        panelBotones.add(btnSalir);

        add(panelBotones, BorderLayout.CENTER);
    }

    private void inicializarEventos() {
        btnIniciar.addActionListener(e -> gameManager.nuevaPartida());
        btnCargar.addActionListener(e -> cargarPartida());
        btnTienda.addActionListener(e -> abrirTienda());
        btnSalir.addActionListener(e -> dispose());
    }

    private void cargarPartida() {
        try {
            gameManager.cargarPartida();
            gameManager.mostrarEstadoParty();
        } catch (PartidaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Cargar partida", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void abrirTienda() {
        if (gameManager.hayPartida()) {
            gameManager.abrirTienda();
        } else {
            JOptionPane.showMessageDialog(this, "Primero iniciá o cargá una partida.", "Tienda", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
