package Pantallas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaBatalla extends JFrame {
	private static final long serialVersionUID = 1L;
    private JLabel lblEstadoParty;
    private JLabel lblEstadoEnemigo;
    private JTextArea txtAreaLogBatalla;
    private JButton btnAtacar;
    private JButton btnDefender;
    private JButton btnHabilidad;
    private JButton btnTransformar;
    private JButton btnItem;

    public PantallaBatalla() {
        //Configuración básica de la ventana de batalla
        setTitle("Bloody Roar - Combate por Turnos");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel Superior: Información de los contendientes
        JPanel panelInfo = new JPanel(new GridLayout(1, 2, 10, 10));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblEstadoParty = new JLabel("Party: Yugo, Bakuryu, Long, Alice (HP/MP OK)");
        lblEstadoEnemigo = new JLabel("Enemigo Actual: Terrestre / Volador / Boss");
        
        panelInfo.add(lblEstadoParty);
        panelInfo.add(lblEstadoEnemigo);
        add(panelInfo, BorderLayout.NORTH);

        // Panel Central: Log de la batalla (consola visual del combate)
        txtAreaLogBatalla = new JTextArea();
        txtAreaLogBatalla.setEditable(false);
        txtAreaLogBatalla.setText("¡Comienza el combate!\nEs el turno de la Party.\n");
        
        JScrollPane scrollPane = new JScrollPane(txtAreaLogBatalla);
        add(scrollPane, BorderLayout.CENTER);

        // Panel Inferior: Botones de acciones del turno
        JPanel panelAcciones = new JPanel();
        panelAcciones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnAtacar = new JButton("Atacar");
        btnDefender = new JButton("Defender");
        btnHabilidad = new JButton("Habilidad");
        btnTransformar = new JButton("Transformar (Bestia)");
        btnItem = new JButton("Usar Ítem");

        panelAcciones.add(btnAtacar);
        panelAcciones.add(btnDefender);
        panelAcciones.add(btnHabilidad);
        panelAcciones.add(btnTransformar);
        panelAcciones.add(btnItem);

        add(panelAcciones, BorderLayout.SOUTH);

        // Programación de eventos (Listeners) delegando la acción al modelo / controlador
        btnAtacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí la vista captura el evento y delega la orden al controlador o modelo (Batalla)
                txtAreaLogBatalla.append("-> La Party realiza un ataque básico.\n");
            }
        });

        btnDefender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtAreaLogBatalla.append("-> Personaje adopta postura defensiva (daño reducido a la mitad).\n");
            }
        });

        btnHabilidad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtAreaLogBatalla.append("-> Se ejecuta la técnica o habilidad especial consumiendo Maná.\n");
            }
        });

        btnTransformar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtAreaLogBatalla.append("-> ¡Transformación en Bestia activada! Estadísticas potenciadas.\n");
            }
        });

        btnItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtAreaLogBatalla.append("-> Se accede al inventario compartido para consumir un ítem.\n");
            }
        });
    }

    // Método principal para pruebas locales independientes
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PantallaBatalla().setVisible(true);
            }
        });
    }
}