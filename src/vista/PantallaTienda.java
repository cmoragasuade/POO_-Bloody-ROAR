package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

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
import dto.ItemDTO;

// Tienda: se compran ítems con el oro de la party. Lo que se compra va al inventario compartido.
public class PantallaTienda extends JFrame {
    private static final long serialVersionUID = 1L;

    private final GameManager gameManager;

    private JLabel lblOro;
    private JTextArea txtInventario;
    private JButton btnVolver;

    public PantallaTienda(GameManager gameManager) {
        this.gameManager = gameManager;

        setTitle("Bloody Roar - Tienda");
        setSize(720, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        inicializarComponentes();
        actualizarDatos();
    }

    private void inicializarComponentes() {
        lblOro = new JLabel("", SwingConstants.CENTER);
        lblOro.setFont(new Font("Arial", Font.BOLD, 18));
        lblOro.setBorder(BorderFactory.createEmptyBorder(12, 0, 5, 0));
        add(lblOro, BorderLayout.NORTH);

        // Un renglón por ítem: nombre, descripción, precio y botón Comprar
        List<ItemDTO> catalogo = gameManager.getCatalogoTienda();
        JPanel panelCatalogo = new JPanel(new GridLayout(catalogo.size(), 1, 5, 5));
        panelCatalogo.setBorder(BorderFactory.createTitledBorder("Catálogo"));
        for (int i = 0; i < catalogo.size(); i++) {
            ItemDTO item = catalogo.get(i);
            JPanel fila = new JPanel(new BorderLayout(10, 0));
            fila.add(new JLabel("  " + item.getNombre() + "  -  " + item.getDescripcion()), BorderLayout.CENTER);
            JButton btnComprar = new JButton("Comprar (" + item.getPrecio() + " oro)");
            int indice = i;
            btnComprar.addActionListener(e -> comprar(indice));
            fila.add(btnComprar, BorderLayout.EAST);
            panelCatalogo.add(fila);
        }
        add(panelCatalogo, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new BorderLayout(5, 5));
        txtInventario = new JTextArea(6, 40);
        txtInventario.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtInventario);
        scroll.setBorder(BorderFactory.createTitledBorder("Inventario de la party"));
        panelInferior.add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> gameManager.mostrarEstadoParty());
        panelBotones.add(btnVolver);
        panelInferior.add(panelBotones, BorderLayout.SOUTH);

        add(panelInferior, BorderLayout.SOUTH);
    }

    private void comprar(int indice) {
        if (gameManager.comprarItem(indice)) {
            actualizarDatos();
        } else {
            JOptionPane.showMessageDialog(this, "No te alcanza el oro.", "Tienda", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void actualizarDatos() {
        lblOro.setText("TIENDA  -  Oro disponible: " + gameManager.getOro());
        String texto = "";
        List<ItemDTO> inventario = gameManager.getInventario();
        if (inventario.isEmpty()) {
            texto = "(vacío)";
        }
        for (ItemDTO item : inventario) {
            texto += "• " + item.getNombre() + "\n";
        }
        txtInventario.setText(texto);
    }
}
