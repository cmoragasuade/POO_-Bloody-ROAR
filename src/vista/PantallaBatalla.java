package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import controlador.ControladorBatalla;
import dto.EntidadDTO;
import dto.EstadoBatallaDTO;
import dto.ItemDTO;
import vista.paneles.PanelEstadoEntidad;
import vista.paneles.PanelFondo;
import vista.paneles.PanelRegistro;

// Pantalla de combate por turnos. Igual que en EjemploBatallaMVC:
// los botones le avisan al controlador y la pantalla se redibuja con el DTO que recibe.
public class PantallaBatalla extends JFrame {
    private static final long serialVersionUID = 1L;

    private static final String IMAGEN_FONDO = "/resources/bloody roar fight.jpeg";

    private ControladorBatalla controlador;

    private JLabel lblTurno;
    private JPanel panelParty;
    private JPanel panelEnemigos;
    private PanelRegistro panelRegistro;

    private JComboBox<String> cmbObjetivo;
    private JComboBox<String> cmbItem;

    private JButton btnAtacar;
    private JButton btnDefender;
    private JButton btnHabilidad;
    private JButton btnTransformar;
    private JButton btnItem;
    private JButton btnContinuar;

    public PantallaBatalla(int sala) {
        setTitle("Bloody Roar - Combate por Turnos - Sala " + sala);
        setSize(1200, 760);
        setMinimumSize(new Dimension(1150, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(new PanelFondo(IMAGEN_FONDO));
        setLayout(new BorderLayout(10, 10));

        inicializarComponentes(sala);
        inicializarEventos();
    }

    private void inicializarComponentes(int sala) {
        // Arriba: título y de quién es el turno
        JPanel panelSuperior = new JPanel(new GridLayout(2, 1));
        panelSuperior.setOpaque(false);
        JLabel lblTitulo = new JLabel("SALA " + sala, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(Color.WHITE);
        lblTurno = new JLabel("-", SwingConstants.CENTER);
        lblTurno.setFont(new Font("Arial", Font.BOLD, 16));
        lblTurno.setForeground(Color.WHITE);
        panelSuperior.add(lblTitulo);
        panelSuperior.add(lblTurno);
        add(panelSuperior, BorderLayout.NORTH);

        // Centro: party a la izquierda, enemigos a la derecha
        JPanel panelCentro = new JPanel(new GridLayout(1, 2, 20, 0));
        panelCentro.setOpaque(false);
        panelCentro.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        panelParty = new JPanel(new GridLayout(0, 2, 5, 5));
        panelParty.setOpaque(false);
        panelEnemigos = new JPanel(new GridLayout(0, 2, 5, 5));
        panelEnemigos.setOpaque(false);
        JPanel columnaParty = new JPanel(new BorderLayout());
        columnaParty.setOpaque(false);
        columnaParty.setBorder(bordeTitulo("TU PARTY"));
        columnaParty.add(panelParty, BorderLayout.NORTH);
        JPanel columnaEnemigos = new JPanel(new BorderLayout());
        columnaEnemigos.setOpaque(false);
        columnaEnemigos.setBorder(bordeTitulo("ENEMIGOS"));
        columnaEnemigos.add(panelEnemigos, BorderLayout.NORTH);
        panelCentro.add(columnaParty);
        panelCentro.add(columnaEnemigos);
        add(panelCentro, BorderLayout.CENTER);

        // Abajo: registro y acciones
        JPanel panelInferior = new JPanel(new BorderLayout(5, 5));
        panelInferior.setOpaque(true);
        panelInferior.setBackground(new Color(238, 238, 238));
        panelRegistro = new PanelRegistro();
        panelInferior.add(panelRegistro, BorderLayout.CENTER);

        JPanel panelAcciones = new JPanel(new GridLayout(2, 1, 5, 5));
        panelAcciones.setBorder(BorderFactory.createTitledBorder("Acciones"));

        JPanel filaAtaque = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 4));
        cmbObjetivo = new JComboBox<>();
        btnAtacar = new JButton("Atacar");
        btnHabilidad = new JButton("Habilidad");
        btnDefender = new JButton("Defender");
        btnTransformar = new JButton("Transformar (Bestia)");
        filaAtaque.add(new JLabel("Objetivo:"));
        filaAtaque.add(cmbObjetivo);
        filaAtaque.add(btnAtacar);
        filaAtaque.add(btnHabilidad);
        filaAtaque.add(btnDefender);
        filaAtaque.add(btnTransformar);

        JPanel filaItem = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 4));
        cmbItem = new JComboBox<>();
        btnItem = new JButton("Usar Ítem");
        btnContinuar = new JButton("Continuar >>");
        btnContinuar.setEnabled(false);
        filaItem.add(new JLabel("Ítem:"));
        filaItem.add(cmbItem);
        filaItem.add(btnItem);
        filaItem.add(btnContinuar);

        panelAcciones.add(filaAtaque);
        panelAcciones.add(filaItem);
        panelInferior.add(panelAcciones, BorderLayout.SOUTH);

        add(panelInferior, BorderLayout.SOUTH);
    }

    // Los botones solo le avisan al controlador; la vista no resuelve reglas de negocio.
    // Se registran una sola vez, y el campo controlador se lee recién al hacer clic.
    private void inicializarEventos() {
        btnAtacar.addActionListener(e -> controlador.atacar(cmbObjetivo.getSelectedIndex()));
        btnHabilidad.addActionListener(e -> controlador.usarHabilidad(cmbObjetivo.getSelectedIndex()));
        btnDefender.addActionListener(e -> controlador.defender());
        btnTransformar.addActionListener(e -> controlador.transformar());
        btnItem.addActionListener(e -> controlador.usarItem(cmbItem.getSelectedIndex()));
        btnContinuar.addActionListener(e -> controlador.continuar());
    }

    // Borde con título en blanco para que se lea sobre el fondo oscuro
    private TitledBorder bordeTitulo(String titulo) {
        TitledBorder borde = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.WHITE), titulo);
        borde.setTitleColor(Color.WHITE);
        return borde;
    }

    // Se conecta el controlador después de crear la pantalla (como en el ejemplo del profe)
    public void conectarControlador(ControladorBatalla controlador) {
        this.controlador = controlador;
    }

    // Redibuja todo con los datos nuevos que manda el controlador
    public void actualizar(EstadoBatallaDTO estado) {
        if (estado.isBatallaTerminada()) {
            lblTurno.setText("La batalla terminó. Tocá \"Continuar\" para ver los resultados.");
        } else {
            lblTurno.setText("Ronda " + estado.getRonda() + "  -  Turno de: " + estado.getTurnoDe());
        }

        panelParty.removeAll();
        for (EntidadDTO personaje : estado.getParty()) {
            boolean tieneElTurno = personaje.getNombre().equals(estado.getTurnoDe());
            panelParty.add(new PanelEstadoEntidad(personaje, tieneElTurno));
        }

        panelEnemigos.removeAll();
        int objetivoElegido = cmbObjetivo.getSelectedIndex();
        cmbObjetivo.removeAllItems();
        for (EntidadDTO enemigo : estado.getEnemigos()) {
            panelEnemigos.add(new PanelEstadoEntidad(enemigo, false));
            if (enemigo.estaVivo()) {
                cmbObjetivo.addItem(enemigo.getNombre());
            } else {
                cmbObjetivo.addItem(enemigo.getNombre() + " (caído)");
            }
        }
        // Se mantiene el objetivo elegido; si ese enemigo cayó, se pasa al primero vivo
        if (objetivoElegido < 0 || objetivoElegido >= estado.getEnemigos().size()
                || !estado.getEnemigos().get(objetivoElegido).estaVivo()) {
            objetivoElegido = 0;
            for (int i = 0; i < estado.getEnemigos().size(); i++) {
                if (estado.getEnemigos().get(i).estaVivo()) {
                    objetivoElegido = i;
                    break;
                }
            }
        }
        cmbObjetivo.setSelectedIndex(objetivoElegido);

        cmbItem.removeAllItems();
        for (ItemDTO item : estado.getInventario()) {
            cmbItem.addItem(item.toString());
        }
        boolean hayItems = !estado.getInventario().isEmpty();
        if (!hayItems) {
            cmbItem.addItem("(sin ítems)");
        }

        panelRegistro.actualizar(estado.getRegistro());

        boolean puedeActuar = estado.isTurnoDelJugador() && !estado.isBatallaTerminada();
        btnAtacar.setEnabled(puedeActuar);
        btnHabilidad.setEnabled(puedeActuar);
        btnDefender.setEnabled(puedeActuar);
        btnTransformar.setEnabled(puedeActuar);
        btnItem.setEnabled(puedeActuar && hayItems);
        btnContinuar.setEnabled(estado.isBatallaTerminada());

        panelParty.revalidate();
        panelParty.repaint();
        panelEnemigos.revalidate();
        panelEnemigos.repaint();
    }
}