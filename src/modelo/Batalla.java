package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// El "árbitro" de la pelea: arma la cola de turnos por velocidad, ejecuta las acciones,
// hace jugar solos a los enemigos y decide quién gana. No sabe nada de Swing (Clase 8).
public class Batalla {
    private static final Random azar = new Random();
    private static final int MAX_LINEAS_REGISTRO = 12;

    private Party party;
    private List<Enemigo> enemigos;
    private List<Entidad> colaTurnos;
    private int indiceTurnoActual;
    private int ronda;
    private List<String> registro;
    private List<String> resumenRecompensas;
    private boolean finalizada;

    public Batalla(Party party, List<Enemigo> enemigos) {
        this.party = party;
        this.enemigos = enemigos;
        this.colaTurnos = new ArrayList<>();
        this.registro = new ArrayList<>();
        this.resumenRecompensas = new ArrayList<>();
        this.finalizada = false;
    }

    public void iniciarBatalla() {
        this.ronda = 1;
        agregarMensaje("¡Comienza el combate! --- Ronda 1 ---");
        calcularTurnos();
        this.indiceTurnoActual = 0;
        prepararTurnoActual();
    }

    // Cola de turnos: todos los vivos ordenados por velocidad.
    // Se mezcla antes de ordenar para que los empates queden al azar (README).
    public void calcularTurnos() {
        this.colaTurnos.clear();
        for (Personaje personaje : this.party.getPersonajes()) {
            if (personaje.estaVivo()) {
                this.colaTurnos.add(personaje);
            }
        }
        for (Enemigo enemigo : this.enemigos) {
            if (enemigo.estaVivo()) {
                this.colaTurnos.add(enemigo);
            }
        }
        Collections.shuffle(this.colaTurnos);
        Collections.sort(this.colaTurnos, new ComparadorPorVelocidad());
    }

    // De quién es el turno ahora (null si la batalla terminó)
    public Entidad obtenerSiguienteTurno() {
        if (termino() || colaTurnos.isEmpty()) {
            return null;
        }
        return colaTurnos.get(indiceTurnoActual);
    }

    public boolean esTurnoDelJugador() {
        Entidad actual = obtenerSiguienteTurno();
        return actual != null && actual.esDelJugador();
    }

    // ===================== Acciones del jugador =====================


    public void atacar(int indiceEnemigo) {
        if (!esTurnoDelJugador()) {
            return;
        }
        Personaje atacante = personajeActual();
        Enemigo objetivo = elegirEnemigo(indiceEnemigo);
        registrarGolpe(atacante, objetivo, "ataca a");
        terminarTurnoJugador();
    }

    public void defender() {
        if (!esTurnoDelJugador()) {
            return;
        }
        Personaje personaje = personajeActual();
        personaje.defender();
        agregarMensaje(personaje.getNombre() + " se defiende (+50% de defensa hasta su próximo turno).");
        terminarTurnoJugador();
    }

    public void usarHabilidad(int indiceEnemigo) {
        if (!esTurnoDelJugador()) {
            return;
        }
        Personaje personaje = personajeActual();
        Enemigo objetivo = elegirEnemigo(indiceEnemigo);
        int manaAntes = personaje.getManaActual();
        String tipoAntes = objetivo.getTipo();
        agregarMensaje(personaje.usarHabilidad(objetivo, party.getPersonajes()));
        avisarCambioDeFase(objetivo, tipoAntes);
        // Si no le alcanzó el maná, no pierde el turno
        if (personaje.getManaActual() < manaAntes) {
            terminarTurnoJugador();
        }
    }

    public void transformar() {
        if (!esTurnoDelJugador()) {
            return;
        }
        Personaje personaje = personajeActual();
        if (personaje.transformar()) {
            agregarMensaje("¡" + personaje.getNombre() + " se transforma en Bestia! (+5 ataque, +3 defensa por 3 turnos)");
            terminarTurnoJugador();
        } else {
            agregarMensaje(personaje.getNombre() + " no puede transformarse (ya está transformado o le falta maná).");
        }
    }

    // Usa un ítem del inventario de la party sobre el personaje que tiene el turno
    public void usarItem(int indiceItem) {
        if (!esTurnoDelJugador()) {
            return;
        }
        List<Item> inventario = party.getInventario();
        if (indiceItem < 0 || indiceItem >= inventario.size()) {
            agregarMensaje("No hay ningún ítem para usar.");
            return;
        }
        Personaje personaje = personajeActual();
        Item item = inventario.get(indiceItem);
        party.usarItem(item, personaje);
        agregarMensaje(personaje.getNombre() + " usa " + item.getNombre() + ".");
        terminarTurnoJugador();
    }

    // ===================== Manejo de turnos =====================

    private void terminarTurnoJugador() {
        avanzarIndice();
        prepararTurnoActual();
    }

    // Pasa al siguiente de la cola. Si se terminó la ronda, arma una nueva.
    private void avanzarIndice() {
        this.indiceTurnoActual++;
        if (this.indiceTurnoActual >= this.colaTurnos.size()) {
            this.ronda++;
            calcularTurnos();
            this.indiceTurnoActual = 0;
            if (!termino()) {
                agregarMensaje("--- Ronda " + ronda + " ---");
            }
        }
    }

    // Avanza hasta que le toque a un personaje del jugador:
    // los muertos se saltean y los enemigos juegan solos.
    private void prepararTurnoActual() {
        while (!termino()) {
            Entidad actual = colaTurnos.get(indiceTurnoActual);
            if (!actual.estaVivo()) {
                avanzarIndice();
            } else if (actual.esDelJugador()) {
                String aviso = ((Personaje) actual).inicioDeTurno();
                if (!aviso.isEmpty()) {
                    agregarMensaje(aviso.trim());
                }
                return;
            } else {
                ejecutarTurnoEnemigo((Enemigo) actual);
                avanzarIndice();
            }
        }
        finalizarBatalla();
    }

    // El enemigo ataca a un personaje vivo elegido al azar
    private void ejecutarTurnoEnemigo(Enemigo enemigo) {
        List<Personaje> vivos = party.getPersonajesVivos();
        if (vivos.isEmpty()) {
            return;
        }
        Personaje objetivo = vivos.get(azar.nextInt(vivos.size()));
        registrarGolpe(enemigo, objetivo, "ataca a");
    }

    // ===================== Fin de la batalla =====================

    public boolean verificarFinBatalla() {
        for (Enemigo enemigo : this.enemigos) {
            if (enemigo.estaVivo()) {
                return false;
            }
        }
        return true;
    }

    public boolean termino() {
        return verificarFinBatalla() || party.estaDerrotada();
    }

    // true si ganó la party
    public boolean verificarGanador() {
        return verificarFinBatalla() && !party.estaDerrotada();
    }

    private void finalizarBatalla() {
        if (finalizada) {
            return;
        }
        finalizada = true;
        if (verificarGanador()) {
            agregarMensaje("¡VICTORIA! Todos los enemigos fueron derrotados.");
            otorgarRecompensas();
        } else {
            agregarMensaje("DERROTA... La party fue vencida.");
        }
        for (Personaje personaje : party.getPersonajes()) {
            personaje.terminarBatalla();
        }
    }

    // La XP total se reparte entre los personajes vivos; el oro va a la party.
    // Los caídos se levantan con un cuarto de su vida para la próxima pelea.
    public void otorgarRecompensas() {
        int experienciaTotal = 0;
        int oroTotal = 0;
        for (Enemigo enemigo : this.enemigos) {
            experienciaTotal += enemigo.getExperienciaOtorgada();
            oroTotal += enemigo.getOroOtorgado();
        }
        party.sumarOro(oroTotal);
        resumenRecompensas.add("Oro obtenido: +" + oroTotal + " (total: " + party.getOro() + ")");

        List<Personaje> vivos = party.getPersonajesVivos();
        int experienciaPorPersonaje = experienciaTotal / vivos.size();
        resumenRecompensas.add("Experiencia: +" + experienciaPorPersonaje + " para cada personaje en pie");

        for (Personaje personaje : party.getPersonajes()) {
            if (personaje.estaVivo()) {
                int nivelAntes = personaje.getNivel();
                personaje.sumarExperiencia(experienciaPorPersonaje);
                if (personaje.getNivel() > nivelAntes) {
                    resumenRecompensas.add(personaje.getNombre() + " ¡sube a nivel " + personaje.getNivel() + "!");
                } else {
                    resumenRecompensas.add(personaje.getNombre() + ": nivel " + personaje.getNivel() + " (XP "
                            + personaje.getExperiencia() + "/" + personaje.getExperienciaNecesariaParaSiguienteNivel() + ")");
                }
            } else {
                personaje.setVidaActual(personaje.getVidaMaxima() / 4);
                resumenRecompensas.add(personaje.getNombre() + " había caído y se recupera con " + personaje.getVidaActual() + " de vida.");
            }
        }
    }

    // ===================== Ayudas =====================

    private Personaje personajeActual() {
        return (Personaje) colaTurnos.get(indiceTurnoActual);
    }

    // El enemigo elegido; si no es válido o ya murió, el primero vivo
    private Enemigo elegirEnemigo(int indice) {
        if (indice >= 0 && indice < enemigos.size() && enemigos.get(indice).estaVivo()) {
            return enemigos.get(indice);
        }
        for (Enemigo enemigo : enemigos) {
            if (enemigo.estaVivo()) {
                return enemigo;
            }
        }
        return enemigos.get(0);
    }

    // Ejecuta un ataque básico y deja el mensaje con el daño real que hizo
    private void registrarGolpe(Entidad atacante, Entidad objetivo, String verbo) {
        int vidaAntes = objetivo.getVidaActual();
        String tipoAntes = objetivo.getTipo();
        atacante.atacar(objetivo);
        int danio = vidaAntes - objetivo.getVidaActual();
        if (danio == 0) {
            agregarMensaje(atacante.getNombre() + " " + verbo + " " + objetivo.getNombre() + ", ¡pero lo esquiva!");
        } else {
            agregarMensaje(atacante.getNombre() + " " + verbo + " " + objetivo.getNombre() + " y le hace " + danio + " de daño.");
        }
        if (!objetivo.estaVivo()) {
            agregarMensaje(objetivo.getNombre() + " fue derrotado.");
        }
        avisarCambioDeFase(objetivo, tipoAntes);
    }

    private void avisarCambioDeFase(Entidad objetivo, String tipoAntes) {
        if (objetivo.estaVivo() && !objetivo.getTipo().equals(tipoAntes)) {
            agregarMensaje("¡" + objetivo.getNombre() + " cambia: ahora es " + objetivo.getTipo() + "!");
        }
    }

    private void agregarMensaje(String mensaje) {
        registro.add(mensaje);
    }

    // Las últimas líneas del registro, para mostrar en pantalla
    public String getRegistroReciente() {
        String texto = "";
        int desde = Math.max(0, registro.size() - MAX_LINEAS_REGISTRO);
        for (int i = desde; i < registro.size(); i++) {
            texto += registro.get(i) + "\n";
        }
        return texto;
    }

    public Party getParty() {
        return party;
    }

    public List<Enemigo> getEnemigos() {
        return enemigos;
    }

    public List<Entidad> getColaTurnos() {
        return colaTurnos;
    }

    public int getRonda() {
        return ronda;
    }

    public List<String> getResumenRecompensas() {
        return resumenRecompensas;
    }
}
