package Pantallas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaResultados extends JFrame {
	

    private static final long serialVersionUID = 1L;
    private JTextArea txtAreaResultados;
    private JButton btnContinuar;
    private JButton btnMenuPrincipal;

    public PantallaResultados() {
        // Configuración de la ventana de resultados
        setTitle("Bloody Roar - Resultados del Combate");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel Superior: Título
        JLabel lblTitulo = new JLabel("¡VICTORIA!", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel Central: Detalle de recompensas y experiencia
        txtAreaResultados = new JTextArea();
        txtAreaResultados.setEditable(false);
        txtAreaResultados.setFont(new Font("Monospaced", Font.PLAIN, 14));
        
        // Simulación de datos provenientes del modelo (Batalla / Progresión)
        cargarDatosResultadosSimulados();

        JScrollPane scrollPane = new JScrollPane(txtAreaResultados);
        add(scrollPane, BorderLayout.CENTER);

        // Panel Inferior: Botones de navegación
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));

        btnContinuar = new JButton("Siguiente Batalla");
        btnMenuPrincipal = new JButton("Menú Principal");

        panelInferior.add(btnContinuar);
        panelInferior.add(btnMenuPrincipal);
        add(panelInferior, BorderLayout.SOUTH);

        // Eventos de los botones (Programación dirigida por eventos)
        btnContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Cargando siguiente combate...");
                dispose();
            }
        });

        btnMenuPrincipal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Volviendo al menú principal...");
                dispose();
            }
        });
    }

    private void cargarDatosResultadosSimulados() {
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------------------------\n");
        sb.append(" RECOMPENSAS OBTENIDAS:\n");
        sb.append("--------------------------------------------------\n");
        sb.append(" • Experiencia ganada: +150 EXP (para cada miembro)\n");
        sb.append(" • Oro recolectado: +50 monedas\n");
        sb.append(" • Ítems encontrados: 1x Poción de Vida\n\n");
        sb.append(" ESTADO DE LA PARTY:\n");
        sb.append(" • Yugo: Nivel 1 -> Sube a Nivel 2! (¡Estadísticas mejoradas!)\n");
        sb.append(" • Bakuryu: Nivel 1 (EXP: 150/200)\n");
        sb.append("--------------------------------------------------\n");
        
        txtAreaResultados.setText(sb.toString());
    }

    // Main de prueba local
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PantallaResultados().setVisible(true);
            }
        });
    }
}