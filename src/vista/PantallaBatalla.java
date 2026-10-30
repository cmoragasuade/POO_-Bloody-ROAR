package vista;

import java.awt.BorderLayout;
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

import controlador.ControladorBatalla;
import dto.EntidadDTO;
import dto.EstadoBatallaDTO;
import dto.ItemDTO;
import vista.paneles.PanelEstadoEntidad;
import vista.paneles.PanelRegistro;

// Pantalla de combate por turnos. Igual que en EjemploBatallaMVC:
// los botones le avisan al controlador y la pantalla se redibuja con el DTO que recibe.
public class PantallaBatalla extends JFrame {
    private static final long serialVersionUID = 1L;

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
        setSize(1100, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        inicializarComponentes(sala);
    }

    private void inicializarComponentes(int sala) {
        // Arriba: título y de quién es el turno
        JPanel panelSuperior = new JPanel(new GridLayout(2, 1));
        JLabel lblTitulo = new JLabel("SALA " + sala, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTurno = new JLabel("-", SwingConstants.CENTER);
        lblTurno.setFont(new Font("Arial", Font.BOLD, 16));
        panelSuperior.add(lblTitulo);
        panelSuperior.add(lblTurno);
        add(panelSuperior, BorderLayout.NORTH);

        // Centro: party a la izquierda, enemigos a la derecha
        JPanel panelCentro = new JPanel(new GridLayout(1, 2, 20, 0));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        // Cada lista va arriba de su columna para que los paneles no se estiren
        panelParty = new JPanel(new GridLayout(0, 2, 5, 5));
        panelEnemigos = new JPanel(new GridLayout(0, 2, 5, 5));
        JPanel columnaParty = new JPanel(new BorderLayout());
        columnaParty.setBorder(BorderFactory.createTitledBorder("TU PARTY"));
        columnaParty.add(panelParty, BorderLayout.NORTH);
        JPanel columnaEnemigos = new JPanel(new BorderLayout());
        columnaEnemigos.setBorder(BorderFactory.createTitledBorder("ENEMIGOS"));
        columnaEnemigos.add(panelEnemigos, BorderLayout.NORTH);
        panelCentro.add(columnaParty);
        panelCentro.add(columnaEnemigos);
        add(panelCentro, BorderLayout.CENTER);

        // Abajo: registro y acciones
        JPanel panelInferior = new JPanel(new BorderLayout(5, 5));
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

    // Se conecta el controlador después de crear la pantalla (como en el ejemplo del profe)
    public void conectarControlador(ControladorBatalla controlador) {
        this.controlador = controlador;

        btnAtacar.addActionListener(e -> controlador.atacar(cmbObjetivo.getSelectedIndex()));
        btnHabilidad.addActionListener(e -> controlador.usarHabilidad(cmbObjetivo.getSelectedIndex()));
        btnDefender.addActionListener(e -> controlador.defender());
        btnTransformar.addActionListener(e -> controlador.transformar());
        btnItem.addActionListener(e -> controlador.usarItem(cmbItem.getSelectedIndex()));
        btnContinuar.addActionListener(e -> controlador.continuar());
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
