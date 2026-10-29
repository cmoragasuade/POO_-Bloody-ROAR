package Pantallas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaInicio extends JFrame {
	private static final long serialVersionUID = 1L;



    
    private JButton btnIniciar;
    private JButton btnCargar;
    private JButton btnTienda;

    public PantallaInicio() {
        // Configuración básica de la ventana principal
        setTitle("Bloody Roar - Menú Principal");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel Superior: Título del juego
        JLabel lblTitulo = new JLabel("BLOODY ROAR - RPG", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel Central: Botones de opciones (Usando GridLayout para ordenar)
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1, 15, 15));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        btnIniciar = new JButton("Iniciar Nueva Partida");
        btnCargar = new JButton("Cargar Partida");
        btnTienda = new JButton("Abrir Tienda");

        panelBotones.add(btnIniciar);
        panelBotones.add(btnCargar);
        panelBotones.add(btnTienda);

        add(panelBotones, BorderLayout.CENTER);

        // Programación dirigida por eventos (Listeners para cada botón)
        
        // 1. Iniciar nueva partida
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí la vista delega la orden al GameManager o controlador del modelo
                JOptionPane.showMessageDialog(null, "Iniciando nueva partida...");
                // Ejemplo: abrirPantallaBatalla();
            }
        });

        // 2. Cargar partida guardada
        btnCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Delega al gestor de persistencia / GameManager
                JOptionPane.showMessageDialog(null, "Cargando partida guardada...");
            }
        });

        // 3. Abrir tienda
        btnTienda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Delega a la clase Tienda
                JOptionPane.showMessageDialog(null, "Abriendo catálogo de la tienda...");
            }
        });
    }

    // Método principal para probar la ejecución de la pantalla
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PantallaInicio().setVisible(true);
            }
        });
    }
}