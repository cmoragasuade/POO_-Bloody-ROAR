package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

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

// Resultado de la batalla: victoria (con recompensas) o derrota (game over)
public class PantallaResultados extends JFrame {
    private static final long serialVersionUID = 1L;

    private final GameManager gameManager;

    private JButton btnContinuar;
    private JButton btnMenuPrincipal;

    public PantallaResultados(GameManager gameManager, ResultadoBatallaDTO resultado) {
        this.gameManager = gameManager;

        setTitle("Bloody Roar - Resultados del Combate");
        setSize(560, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        inicializarComponentes(resultado);
        inicializarEventos();
    }

    private void inicializarComponentes(ResultadoBatallaDTO resultado) {
        String titulo = "DERROTA";
        if (resultado.isJuegoGanado()) {
            titulo = "¡GANASTE EL JUEGO!";
        } else if (resultado.isVictoria()) {
            titulo = "¡VICTORIA!";
        }
        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(lblTitulo, BorderLayout.NORTH);

        JTextArea txtAreaResultados = new JTextArea();
        txtAreaResultados.setEditable(false);
        txtAreaResultados.setFont(new Font("Monospaced", Font.PLAIN, 14));
        String texto = "--------------------------------------------------\n";
        for (String linea : resultado.getDetalle()) {
            texto += " • " + linea + "\n";
        }
        texto += "--------------------------------------------------\n";
        txtAreaResultados.setText(texto);
        add(new JScrollPane(txtAreaResultados), BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        btnContinuar = new JButton("Continuar");
        btnMenuPrincipal = new JButton("Menú Principal");
        // Solo se puede seguir si se ganó y quedan salas
        if (resultado.isVictoria() && !resultado.isJuegoGanado()) {
            panelInferior.add(btnContinuar);
        }
        panelInferior.add(btnMenuPrincipal);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void inicializarEventos() {
        btnContinuar.addActionListener(e -> gameManager.mostrarEstadoParty());
        btnMenuPrincipal.addActionListener(e -> gameManager.verificarGameOver());
    }
}
