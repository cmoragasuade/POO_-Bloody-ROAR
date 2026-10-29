package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class GameManager {
    private static final String ARCHIVO_PARTIDA = "savegame.txt";

    public void guardarPartida(Party party, int salaActual) {
        try (FileWriter writer = new FileWriter(new File(ARCHIVO_PARTIDA))) {
            writer.write("sala=" + salaActual + System.lineSeparator());
            writer.write("oro=" + party.getOro() + System.lineSeparator());
            for (Personaje personaje : party.getPersonajes()) {
                writer.write("personaje=" + personaje.getNombre() + ","
                        + personaje.getNivel() + ","
                        + personaje.getExperiencia() + ","
                        + personaje.getVidaActual() + ","
                        + personaje.getManaActual() + ","
                        + personaje.getOro() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo guardar la partida", e);
        }
    }

    public Party cargarPartida() {
        File archivo = new File(ARCHIVO_PARTIDA);
        if (!archivo.exists()) {
            return new Party();
        }

        try {
            List<String> lineas = Files.readAllLines(archivo.toPath());
            Party party = new Party();
            for (String linea : lineas) {
                if (linea.startsWith("personaje=")) {
                    String[] datos = linea.replace("personaje=", "").split(",");
                    if (datos.length >= 6) {
                        Guerrero p = new Guerrero(datos[0], 100, 30, 10, 5, 10, 10);
                        p.setOro(Integer.parseInt(datos[5]));
                        party.agregarPersonaje(p);
                    }
                }
            }
            return party;
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar la partida", e);
        }
    }
}
