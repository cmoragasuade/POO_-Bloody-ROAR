package Pantallas;




import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaParty extends JFrame {

	private static final long serialVersionUID = 1L;
	
    private JButton btnVolver;
    private JTextArea txtAreaEstadoParty;

    public PantallaParty() {
        // Configuración de la ventana
        setTitle("Bloody Roar - Estado de la Party");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel Superior: Título
        JLabel lblTitulo = new JLabel("ESTADO DE LA PARTY", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel Central: Área de texto donde se listarán los personajes (Aplicando polimorfismo indirecto)
        txtAreaEstadoParty = new JTextArea();
        txtAreaEstadoParty.setEditable(false);
        txtAreaEstadoParty.setFont(new Font("Monospaced", Font.PLAIN, 14));
        
        // Simulación de carga de datos provenientes del Modelo (Party / Personajes)
        cargarDatosPartySimulados();

        JScrollPane scrollPane = new JScrollPane(txtAreaEstadoParty);
        add(scrollPane, BorderLayout.CENTER);

        // Panel Inferior: Botón para volver
        JPanel panelInferior = new JPanel();
        btnVolver = new JButton("Volver al Menú");
        panelInferior.add(btnVolver);
        add(panelInferior, BorderLayout.SOUTH);

        // Acción del botón (Programación dirigida por eventos)
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cierra la pantalla de estado y vuelve al menú principal
                dispose();
            }
        });
    }

    // Método para poblar la vista con los datos del modelo
    private void cargarDatosPartySimulados() {
        // En tu código real, esto se obtiene llamando a los métodos del GameManager o Party:
        // Ej: party.getPersonajes()
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------------------------\n");
        sb.append(" 1. YUGO (Guerrero - Lobo)\n");
        sb.append("    Nivel: 1 | EXP: 0/100\n");
        sb.append("    HP: 100/100 | MP: 30/30 | ATK: 45 | DEF: 20\n");
        sb.append("--------------------------------------------------\n");
        sb.append(" 2. BAKURYU (Mago - Topo)\n");
        sb.append("    Nivel: 1 | EXP: 0/100\n");
        sb.append("    HP: 70/70   | MP: 60/60 | ATK: 30 | DEF: 10\n");
        sb.append("--------------------------------------------------\n");
        sb.append(" 3. LONG (Arquero - Tigre)\n");
        sb.append("    Nivel: 1 | EXP: 0/100\n");
        sb.append("    HP: 80/80   | MP: 40/40 | ATK: 50 | DEF: 12\n");
        sb.append("--------------------------------------------------\n");
        sb.append(" 4. ALICE (Curador - Conejo)\n");
        sb.append("    Nivel: 1 | EXP: 0/100\n");
        sb.append("    HP: 75/75   | MP: 70/70 | ATK: 25 | DEF: 15\n");
        sb.append("--------------------------------------------------\n");

        txtAreaEstadoParty.setText(sb.toString());
    }

    // Main de prueba local
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PantallaParty().setVisible(true);
            }
        });
    }
}