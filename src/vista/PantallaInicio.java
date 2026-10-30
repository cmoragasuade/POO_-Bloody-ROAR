package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controlador.GameManager;
import controlador.PartidaException;
import vista.paneles.PanelFondo;

// Menú principal. La pantalla solo muestra botones y le avisa al GameManager qué se tocó.
public class PantallaInicio extends JFrame {
    private static final long serialVersionUID = 1L;

    private static final Color COLOR_FONDO = new Color(20, 20, 35);
    private static final Color COLOR_BOTON = new Color(35, 35, 60);
    private static final Color COLOR_BORDE = new Color(230, 170, 40);

    private final GameManager gameManager;

    private JButton btnIniciar;
    private JButton btnCargar;
    private JButton btnTienda;
    private JButton btnSalir;

    public PantallaInicio(GameManager gameManager) {
        this.gameManager = gameManager;

        setTitle("Bloody Roar - Menú Principal");
        setSize(800, 740);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(COLOR_FONDO);

        inicializarComponentes();
        inicializarEventos();
    }

    private void inicializarComponentes() {
        // Arriba: la imagen del título como banner, sin capa oscura
        PanelFondo banner = new PanelFondo("/resources/inicio.jpg", 0);
        banner.setPreferredSize(new Dimension(800, 420));
        add(banner, BorderLayout.NORTH);

        // Abajo: panel oscuro con los botones
        JPanel panelMenu = new JPanel(new BorderLayout());
        panelMenu.setBackground(COLOR_FONDO);

        JPanel panelBotones = new JPanel(new GridLayout(4, 1, 0, 12));
        panelBotones.setOpaque(false);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(25, 230, 25, 230));

        btnIniciar = new JButton("Iniciar Nueva Partida");
        btnCargar = new JButton("Cargar Partida");
        btnTienda = new JButton("Abrir Tienda");
        btnSalir = new JButton("Salir");

        estilizarBoton(btnIniciar);
        estilizarBoton(btnCargar);
        estilizarBoton(btnTienda);
        estilizarBoton(btnSalir);

        panelBotones.add(btnIniciar);
        panelBotones.add(btnCargar);
        panelBotones.add(btnTienda);
        panelBotones.add(btnSalir);

        panelMenu.add(panelBotones, BorderLayout.CENTER);
        add(panelMenu, BorderLayout.CENTER);
    }

    // Estilo oscuro con borde dorado para que los botones combinen con el juego
    private void estilizarBoton(JButton boton) {
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setForeground(Color.WHITE);
        boton.setBackground(COLOR_BOTON);
        boton.setOpaque(true);
        boton.setContentAreaFilled(true);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(COLOR_BORDE, 2));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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