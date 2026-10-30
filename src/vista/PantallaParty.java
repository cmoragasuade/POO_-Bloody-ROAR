package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import controlador.GameManager;
import controlador.PartidaException;
import dto.EntidadDTO;
import dto.EstadoPartidaDTO;
import dto.ItemDTO;
import vista.PanelFondo;
import java.awt.Graphics;
// Estado de la party entre batallas. Desde acá se va a pelear, a la tienda o se guarda.
public class PantallaParty extends JFrame {
    private static final long serialVersionUID = 1L;

    // Cambiá este nombre por el de tu imagen real dentro de src/resources
    private static final String IMAGEN_FONDO = "/resources/party.jpg";

    private static final Color COLOR_BOTON = new Color(35, 35, 60);
    private static final Color COLOR_BORDE = new Color(230, 170, 40);

    private final GameManager gameManager;

    private JTextArea txtAreaEstadoParty;
    private JButton btnBatalla;
    private JButton btnTienda;
    private JButton btnGuardar;
    private JButton btnVolver;

    public PantallaParty(GameManager gameManager, EstadoPartidaDTO estado) {
        this.gameManager = gameManager;

        setTitle("Bloody Roar - Estado de la Party");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(new PanelFondo(IMAGEN_FONDO, 170));
        setLayout(new BorderLayout());

        inicializarComponentes(estado);
        inicializarEventos();
    }

    private void inicializarComponentes(EstadoPartidaDTO estado) {
        JLabel lblTitulo = new JLabel("ESTADO DE LA PARTY  -  Sala " + estado.getSala() + " de " + estado.getSalaFinal()
                + "  -  Oro: " + estado.getOro(), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));
        add(lblTitulo, BorderLayout.NORTH);

        txtAreaEstadoParty = new JTextArea() {
            private static final long serialVersionUID = 1L;

            // Capa oscura semitransparente detrás del texto para que se lea sobre la imagen
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(0, 0, 0, 170));
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        txtAreaEstadoParty.setEditable(false);
        txtAreaEstadoParty.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtAreaEstadoParty.setForeground(Color.WHITE);
        txtAreaEstadoParty.setOpaque(false);
        txtAreaEstadoParty.setText(armarTexto(estado));
        txtAreaEstadoParty.setCaretPosition(0);

        JScrollPane scroll = new JScrollPane(txtAreaEstadoParty);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 15, 0, 15),
                BorderFactory.createLineBorder(COLOR_BORDE, 2)));
        add(scroll, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 12));
        panelInferior.setOpaque(false);
        btnBatalla = new JButton("Ir a la batalla (Sala " + estado.getSala() + ")");
        btnTienda = new JButton("Tienda");
        btnGuardar = new JButton("Guardar partida");
        btnVolver = new JButton("Menú principal");
        estilizarBoton(btnBatalla);
        estilizarBoton(btnTienda);
        estilizarBoton(btnGuardar);
        estilizarBoton(btnVolver);
        panelInferior.add(btnBatalla);
        panelInferior.add(btnTienda);
        panelInferior.add(btnGuardar);
        panelInferior.add(btnVolver);
        add(panelInferior, BorderLayout.SOUTH);
    }

    // Estilo oscuro con borde dorado, el mismo que usa el menú principal
    private void estilizarBoton(JButton boton) {
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setForeground(Color.WHITE);
        boton.setBackground(COLOR_BOTON);
        boton.setOpaque(true);
        boton.setContentAreaFilled(true);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDE, 2),
                BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    // Arma el texto con los datos reales que vienen en el DTO
    private String armarTexto(EstadoPartidaDTO estado) {
        String texto = "--------------------------------------------------\n";
        int numero = 1;
        for (EntidadDTO p : estado.getPersonajes()) {
            texto += " " + numero + ". " + p.getNombre().toUpperCase() + " (" + p.getTipo() + ")\n";
            texto += "    Nivel: " + p.getNivel() + "\n";
            texto += "    HP: " + p.getVidaActual() + "/" + p.getVidaMaxima()
                    + " | MP: " + p.getManaActual() + "/" + p.getManaMaximo()
                    + " | ATK: " + p.getAtaque() + " | DEF: " + p.getDefensa() + "\n";
            texto += "    Habilidad: " + p.getHabilidad() + "\n";
            if (!p.getEstado().isEmpty()) {
                texto += "    " + p.getEstado() + "\n";
            }
            texto += "--------------------------------------------------\n";
            numero++;
        }
        texto += "\n INVENTARIO COMPARTIDO:\n";
        if (estado.getInventario().isEmpty()) {
            texto += "  (vacío)\n";
        }
        for (ItemDTO item : estado.getInventario()) {
            texto += "  • " + item.getNombre() + ": " + item.getDescripcion() + "\n";
        }
        return texto;
    }

    private void inicializarEventos() {
        btnBatalla.addActionListener(e -> gameManager.iniciarBatalla());
        btnTienda.addActionListener(e -> gameManager.abrirTienda());
        btnGuardar.addActionListener(e -> guardar());
        btnVolver.addActionListener(e -> gameManager.mostrarMenuPrincipal());
    }
    
    private void guardar() {
        try {
            gameManager.guardarPartida();
            JOptionPane.showMessageDialog(this, "Partida guardada.", "Guardar", JOptionPane.INFORMATION_MESSAGE);
        } catch (PartidaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Guardar", JOptionPane.ERROR_MESSAGE);
        }
    }

 }