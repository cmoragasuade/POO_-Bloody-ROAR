package vista.paneles;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// Muestra lo que fue pasando en la batalla (igual que el PanelRegistro del profe)
public class PanelRegistro extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JTextArea txtRegistro;

    public PanelRegistro() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Registro del combate"));

        txtRegistro = new JTextArea(8, 50);
        txtRegistro.setEditable(false);
        txtRegistro.setLineWrap(true);
        txtRegistro.setWrapStyleWord(true);

        add(new JScrollPane(txtRegistro), BorderLayout.CENTER);
    }

    public void actualizar(String mensaje) {
        txtRegistro.setText(mensaje);
        txtRegistro.setCaretPosition(txtRegistro.getDocument().getLength());
    }
}
