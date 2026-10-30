package util;

import java.io.File;

import javax.swing.ImageIcon;

// Igual que en EjemploBatallaMVC del profe: busca la imagen en la carpeta de recursos.
// Si no la encuentra devuelve null y la pantalla muestra texto en su lugar.
public final class CargadorImagenes {

    private CargadorImagenes() {
    }

    public static ImageIcon cargar(String nombreArchivo) {
        String[] rutas = {
            "src/resources/" + nombreArchivo,
            "resources/" + nombreArchivo
        };

        for (String ruta : rutas) {
            File archivo = new File(ruta);
            if (archivo.exists()) {
                return new ImageIcon(ruta);
            }
        }
        return null;
    }
}
