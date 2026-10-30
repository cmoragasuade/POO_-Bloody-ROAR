package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import controlador.GameManager;
import dto.ResultadoBatallaDTO;
import vista.paneles.PanelFondo;

// Resultado de la batalla: victoria (con recompensas) o derrota (game over)
public class PantallaResultados extends JFrame {
    private static final long serialVersionUID = 1L;

    private static final String IMAGEN_FONDO = "/resources/resultados.jpg";

    private static final Color COLOR_BOTON = new Color(35, 35, 60);
    private static final Color COLOR_BORDE = new Color(230, 170, 40);
    private static final Color COLOR_VICTORIA = new Color(255, 215, 80);
    private static final Color COLOR_DERROTA = new Color(230, 80, 80);

    private final GameManager gameManager;

    private JButton btnContinuar;
    private JButton btnMenuPrincipal;

    public PantallaResultados(GameManager gameManager, ResultadoBatallaDTO resultado) {
        this.gameManager = gameManager;

        setTitle("Bloody Roar - Resultados del Combate");
        setSize(700, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(new PanelFondo(IMAGEN_FONDO, 150));
        setLayout(new BorderLayout());

        inicializarComponentes(resultado);
        inicializarEventos();
    }

    private void inicializarComponentes(ResultadoBatallaDTO resultado) {
        String titulo = "DERROTA";
        Color colorTitulo = COLOR_DERROTA;
        if (resultado.isJuegoGanado()) {
            titulo = "¡GANASTE EL JUEGO!";
            colorTitulo = COLOR_VICTORIA;
        } else if (resultado.isVictoria()) {
            titulo = "¡VICTORIA!";
            colorTitulo = COLOR_VICTORIA;
        }
        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitulo.setForeground(colorTitulo);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // Área de texto con una capa oscura semitransparente para que se lea sobre la imagen
        JTextArea txtAreaResultados = new JTextArea() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(0, 0, 0, 170));
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        txtAreaResultados.setEditable(false);
        txtAreaResultados.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtAreaResultados.setForeground(Color.WHITE);
        txtAreaResultados.setOpaque(false);
        String texto = "--------------------------------------------------\n";
        for (String linea : resultado.getDetalle()) {
            texto += " • " + linea + "\n";
        }
        texto += "--------------------------------------------------\n";
        txtAreaResultados.setText(texto);
        txtAreaResultados.setCaretPosition(0);

        JScrollPane scroll = new JScrollPane(txtAreaResultados);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 20, 0, 20),
                BorderFactory.createLineBorder(COLOR_BORDE, 2)));
        add(scroll, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelInferior.setOpaque(false);
        btnContinuar = new JButton("Continuar");
        btnMenuPrincipal = new JButton("Menú Principal");
        estilizarBoton(btnContinuar);
        estilizarBoton(btnMenuPrincipal);
        // Solo se puede seguir si se ganó y quedan salas
        if (resultado.isVictoria() && !resultado.isJuegoGanado()) {
            panelInferior.add(btnContinuar);
        }
        panelInferior.add(btnMenuPrincipal);
        add(panelInferior, BorderLayout.SOUTH);
    }

    // Estilo oscuro con borde dorado, el mismo que usan el menú y la pantalla de party
    private void estilizarBoton(JButton boton) {
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setForeground(Color.WHITE);
        boton.setBackground(COLOR_BOTON);
        boton.setOpaque(true);
        boton.setContentAreaFilled(true);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDE, 2),
                BorderFactory.createEmptyBorder(6, 14, 6, 14)));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void inicializarEventos() {
        btnContinuar.addActionListener(e -> gameManager.mostrarEstadoParty());
        btnMenuPrincipal.addActionListener(e -> gameManager.verificarGameOver());
    }
}