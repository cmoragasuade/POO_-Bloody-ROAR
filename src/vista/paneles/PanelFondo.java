package vista.paneles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

// Panel que dibuja una imagen de fondo ajustada al tamaño del panel.
// Es parte de la vista: solo muestra, no contiene lógica de negocio.
public class PanelFondo extends JPanel {
    private static final long serialVersionUID = 1L;

    private Image imagen;
    private int oscuridad; // 0 = sin capa oscura, 255 = negro total

    public PanelFondo(String ruta) {
        this(ruta, 140);
    }

    public PanelFondo(String ruta, int oscuridad) {
        this.oscuridad = oscuridad;
        URL url = getClass().getResource(ruta);
        if (url == null) {
            System.err.println("No se encontró la imagen: " + ruta);
        } else {
            imagen = new ImageIcon(url).getImage();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            g.setColor(new Color(0, 0, 0, oscuridad));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}