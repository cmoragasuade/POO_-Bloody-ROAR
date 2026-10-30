package controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import dto.EstadoPartidaDTO;
import dto.ItemDTO;
import dto.ResultadoBatallaDTO;
import modelo.Arquero;
import modelo.Batalla;
import modelo.Curador;
import modelo.EnemigoFactory;
import modelo.Guerrero;
import modelo.Item;
import modelo.ItemFactory;
import modelo.Mago;
import modelo.Party;
import modelo.Personaje;
import modelo.Tienda;
import vista.PantallaBatalla;
import vista.PantallaInicio;
import vista.PantallaParty;
import vista.PantallaResultados;
import vista.PantallaSeleccion;
import vista.PantallaTienda;

// Controlador principal (patrón Controller de GRASP, Clase 5).
// Es Singleton (Clase 10): hay una sola partida en curso, así que tiene sentido una sola instancia.
// Recibe los pedidos de las pantallas, coordina el modelo y decide qué pantalla mostrar.
public class GameManager {
    private static final String ARCHIVO_PARTIDA = "partida.dat";
    private static final int ORO_INICIAL = 50;

    private static GameManager instancia;

    private Party party;
    private Tienda tienda;
    private Batalla batallaActual;
    private int nivelProgreso;
    private boolean juegoTerminado;
    private List<Personaje> personajesDisponibles;
    private EnemigoFactory fabricaEnemigos;
    private JFrame pantallaActual;

    private GameManager() {
        this.tienda = new Tienda();
        this.fabricaEnemigos = new EnemigoFactory();
        this.nivelProgreso = 1;
    }

    public static GameManager getInstance() {
        if (instancia == null) {
            instancia = new GameManager();
        }
        return instancia;
    }

    // ===================== Navegación entre pantallas =====================

    public void iniciarJuego() {
        mostrar(new PantallaInicio(this));
    }

    public void mostrarMenuPrincipal() {
        mostrar(new PantallaInicio(this));
    }

    public void mostrarEstadoParty() {
        mostrar(new PantallaParty(this, getEstadoPartida()));
    }

    // Cierra la pantalla anterior y abre la nueva
    private void mostrar(JFrame nueva) {
        if (pantallaActual != null) {
            pantallaActual.dispose();
        }
        pantallaActual = nueva;
        pantallaActual.setVisible(true);
    }

    // ===================== Nueva partida =====================

    public void nuevaPartida() {
        this.personajesDisponibles = crearPersonajesDisponibles();
        mostrar(new PantallaSeleccion(this, ConversorDTO.convertirPersonajes(personajesDisponibles)));
    }

    // Los 4 combatientes del README, con las estadísticas que había puesto Adri
    private List<Personaje> crearPersonajesDisponibles() {
        List<Personaje> lista = new ArrayList<>();
        lista.add(new Guerrero("Yugo", 120, 40, 22, 15, 12, 20));
        lista.add(new Mago("Bakuryu", 90, 70, 18, 10, 16, 25));
        lista.add(new Arquero("Long", 100, 50, 24, 11, 18, 22));
        lista.add(new Curador("Alice", 110, 80, 16, 12, 15, 18));
        return lista;
    }

    // Crea la party con los personajes elegidos (posiciones en la lista).
    // Devuelve false si no se eligieron entre 2 y 4.
    public boolean crearParty(List<Integer> elegidos) {
        if (elegidos.size() < Party.TAMANIO_MIN || elegidos.size() > Party.TAMANIO_MAX) {
            return false;
        }
        Party nueva = new Party("Grupo 6");
        for (int indice : elegidos) {
            nueva.agregarPersonaje(personajesDisponibles.get(indice));
        }
        nueva.sumarOro(ORO_INICIAL);
        ItemFactory fabricaItems = new ItemFactory();
        nueva.agregarItem(fabricaItems.crearItem(ItemFactory.POCION_VIDA));
        nueva.agregarItem(fabricaItems.crearItem(ItemFactory.POCION_VIDA));

        this.party = nueva;
        this.nivelProgreso = 1;
        this.batallaActual = null;
        mostrarEstadoParty();
        return true;
    }

    public boolean hayPartida() {
        return party != null;
    }

    public EstadoPartidaDTO getEstadoPartida() {
        return new EstadoPartidaDTO(
            ConversorDTO.convertirPersonajes(party.getPersonajes()),
            ConversorDTO.convertirItems(party.getInventario()),
            party.getOro(),
            nivelProgreso,
            EnemigoFactory.SALA_FINAL
        );
    }

    // ===================== Tienda =====================

    public void abrirTienda() {
        mostrar(new PantallaTienda(this));
    }

    public List<ItemDTO> getCatalogoTienda() {
        return ConversorDTO.convertirItems(tienda.getItemsDisponibles());
    }

    public List<ItemDTO> getInventario() {
        return ConversorDTO.convertirItems(party.getInventario());
    }

    public int getOro() {
        return party.getOro();
    }

    // true si se pudo comprar (alcanzaba el oro)
    public boolean comprarItem(int indice) {
        Item item = tienda.getItemsDisponibles().get(indice);
        return tienda.venderItem(party, item);
    }

    // ===================== Batalla =====================

    public void iniciarBatalla() {
        batallaActual = new Batalla(party, fabricaEnemigos.crearEnemigosParaSala(nivelProgreso));

        PantallaBatalla pantalla = new PantallaBatalla(nivelProgreso);
        ControladorBatalla controlador = new ControladorBatalla(batallaActual, pantalla, this);
        pantalla.conectarControlador(controlador);

        batallaActual.iniciarBatalla();
        controlador.refrescarVista();
        mostrar(pantalla);
    }

    // Lo llama ControladorBatalla cuando el jugador toca "Continuar" al terminar la pelea
    public void terminarBatalla() {
        boolean victoria = batallaActual.verificarGanador();
        boolean juegoGanado = victoria && nivelProgreso >= EnemigoFactory.SALA_FINAL;
        List<String> detalle = new ArrayList<>();

        if (victoria) {
            detalle.add("Sala " + nivelProgreso + " superada.");
            detalle.addAll(batallaActual.getResumenRecompensas());
            if (!juegoGanado) {
                cargarSiguienteNivel();
            }
        } else {
            detalle.add("Toda la party fue derrotada en la sala " + nivelProgreso + ".");
            detalle.add("GAME OVER");
        }
        juegoTerminado = !victoria || juegoGanado;
        batallaActual = null;
        mostrar(new PantallaResultados(this, new ResultadoBatallaDTO(victoria, juegoGanado, detalle)));
    }

    public void cargarSiguienteNivel() {
        nivelProgreso++;
    }

    // Al volver al menú: si el juego terminó (ganando o perdiendo) se borra la partida en curso
    public void verificarGameOver() {
        if (juegoTerminado) {
            party = null;
            juegoTerminado = false;
        }
        mostrarMenuPrincipal();
    }

    // ===================== Guardado y carga (Clase 13) =====================

    // Solo se puede guardar fuera de combate (README)
    public void guardarPartida() throws PartidaException {
        if (party == null) {
            throw new PartidaException("No hay ninguna partida para guardar.");
        }
        if (batallaActual != null) {
            throw new PartidaException("No se puede guardar en medio de una batalla.");
        }
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ARCHIVO_PARTIDA))) {
            salida.writeObject(party);
            salida.writeInt(nivelProgreso);
        } catch (IOException e) {
            throw new PartidaException("No se pudo guardar la partida.", e);
        }
    }

    public void cargarPartida() throws PartidaException {
        File archivo = new File(ARCHIVO_PARTIDA);
        if (!archivo.exists()) {
            throw new PartidaException("No hay ninguna partida guardada.");
        }
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) {
            this.party = (Party) entrada.readObject();
            this.nivelProgreso = entrada.readInt();
            this.batallaActual = null;
        } catch (IOException e) {
            throw new PartidaException("No se pudo leer la partida guardada.", e);
        } catch (ClassNotFoundException e) {
            throw new PartidaException("El archivo de partida no es válido.", e);
        }
    }

}
