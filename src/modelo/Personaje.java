package modelo;

import java.util.ArrayList;
import java.util.List;

// Personaje que maneja el jugador. El inventario y el oro NO están acá:
// son de toda la Party (corrección del profe).
public abstract class Personaje extends Entidad {
    private static final long serialVersionUID = 1L;

    private static final int COSTO_TRANSFORMACION = 10;
    private static final int DURACION_TRANSFORMACION = 3;

    protected int nivel;
    protected int experiencia;
    protected int manaMaxima;
    protected int manaActual;
    protected int energiaBestia;
    protected boolean transformado;
    protected int turnosTransformado;
    protected boolean defendiendo;
    protected Equipable armaEquipada;
    protected List<EfectoEstado> estadosActivos;

    public Personaje(String nombre, int vidaMaxima, int manaMaxima, int ataque, int defensa, int velocidad, int energiaBestia) {
        super(nombre, vidaMaxima, ataque, defensa, velocidad);
        this.nivel = 1;
        this.experiencia = 0;
        this.manaMaxima = manaMaxima;
        this.manaActual = manaMaxima;
        this.energiaBestia = energiaBestia;
        this.transformado = false;
        this.turnosTransformado = 0;
        this.defendiendo = false;
        this.armaEquipada = null;
        this.estadosActivos = new ArrayList<>();
    }

    // Cada clase tiene su habilidad. Devuelve un texto con lo que pasó, para el registro.
    // Recibe a los aliados porque algunas habilidades (la de Alice) afectan a toda la party.
    public abstract String usarHabilidad(Entidad objetivo, List<Personaje> aliados);

    public abstract String getNombreHabilidad();

    public abstract int getCostoHabilidad();

    @Override
    public boolean esDelJugador() {
        return true;
    }

    // Ataque básico: le pega al objetivo con su ataque
    @Override
    public void atacar(Entidad objetivo) {
        objetivo.recibirDanio(this.ataque);
    }

    // Defender: +50% de defensa hasta su próximo turno (README)
    public void defender() {
        this.defendiendo = true;
    }

    @Override
    protected int getDefensaEfectiva() {
        if (defendiendo) {
            return this.defensa + (int) Math.round(this.defensa * 0.5);
        }
        return this.defensa;
    }

    // Transformación en Bestia: cuesta maná, sube ataque y defensa por 3 turnos
    public boolean transformar() {
        if (this.transformado || this.manaActual < COSTO_TRANSFORMACION) {
            return false;
        }
        this.manaActual -= COSTO_TRANSFORMACION;
        this.transformado = true;
        this.turnosTransformado = DURACION_TRANSFORMACION;
        this.ataque += 5;
        this.defensa += 3;
        return true;
    }

    public void destransformar() {
        if (this.transformado) {
            this.transformado = false;
            this.turnosTransformado = 0;
            this.ataque = Math.max(1, this.ataque - 5);
            this.defensa = Math.max(1, this.defensa - 3);
        }
    }

    // Se llama al empezar el turno de este personaje: termina la defensa,
    // descuenta la duración de la transformación y de los efectos.
    // Devuelve un texto si algo terminó (o "" si no pasó nada).
    public String inicioDeTurno() {
        String mensaje = "";
        this.defendiendo = false;

        if (this.transformado) {
            this.turnosTransformado--;
            if (this.turnosTransformado <= 0) {
                destransformar();
                mensaje += this.nombre + " vuelve a su forma normal.\n";
            }
        }

        List<EfectoEstado> vencidos = new ArrayList<>();
        for (EfectoEstado efecto : estadosActivos) {
            efecto.disminuirDuracion();
            if (efecto.estaExpirado()) {
                vencidos.add(efecto);
            }
        }
        for (EfectoEstado efecto : vencidos) {
            this.ataque -= efecto.getValor();
            estadosActivos.remove(efecto);
            mensaje += "Terminó el efecto " + efecto.getNombre() + " de " + this.nombre + ".\n";
        }
        return mensaje;
    }

    public void agregarEfecto(EfectoEstado efecto) {
        this.estadosActivos.add(efecto);
        this.ataque += efecto.getValor();
    }

    public void limpiarEfectos() {
        for (EfectoEstado efecto : estadosActivos) {
            this.ataque -= efecto.getValor();
        }
        estadosActivos.clear();
    }

    // Gasta maná si alcanza. Lo usan las habilidades.
    protected boolean gastarMana(int cantidad) {
        if (this.manaActual < cantidad) {
            return false;
        }
        this.manaActual -= cantidad;
        return true;
    }

    public void recuperarMana(int cantidad) {
        if (estaVivo()) {
            this.manaActual = Math.min(this.manaMaxima, this.manaActual + cantidad);
        }
    }

    // Equipar un arma: si ya tenía una, primero se le saca el bonus de la anterior
    public void equiparArma(Equipable arma) {
        if (this.armaEquipada != null) {
            this.ataque -= this.armaEquipada.getBonusAtaque();
            this.defensa -= this.armaEquipada.getBonusDefensa();
        }
        this.armaEquipada = arma;
        this.ataque += arma.getBonusAtaque();
        this.defensa += arma.getBonusDefensa();
    }

    // Progresión (README): para pasar de nivel N a N+1 hacen falta N * 100 de XP
    public void sumarExperiencia(int cantidad) {
        this.experiencia += cantidad;
        verificarSubidaNivel();
    }

    public void verificarSubidaNivel() {
        while (this.experiencia >= getExperienciaNecesariaParaSiguienteNivel()) {
            this.experiencia -= getExperienciaNecesariaParaSiguienteNivel();
            subirNivel();
        }
    }

    // Al subir: +20 vida máx, +10 maná, +5 ataque, y se recupera todo (README)
    public void subirNivel() {
        this.nivel++;
        this.vidaMaxima += 20;
        this.vidaActual = this.vidaMaxima;
        this.manaMaxima += 10;
        this.manaActual = this.manaMaxima;
        this.ataque += 5;
        this.defensa += 3;
    }

    // Después de una batalla se limpian los estados temporales
    public void terminarBatalla() {
        this.defendiendo = false;
        destransformar();
        limpiarEfectos();
    }

    public int getExperienciaNecesariaParaSiguienteNivel() {
        return this.nivel * 100;
    }

    public int getNivel() {
        return nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public int getManaMaxima() {
        return manaMaxima;
    }

    public int getManaActual() {
        return manaActual;
    }

    public void setManaActual(int manaActual) {
        this.manaActual = Math.max(0, Math.min(this.manaMaxima, manaActual));
    }

    public int getEnergiaBestia() {
        return energiaBestia;
    }

    public boolean isTransformado() {
        return transformado;
    }

    public boolean isDefendiendo() {
        return defendiendo;
    }

    public Equipable getArmaEquipada() {
        return armaEquipada;
    }

    public List<EfectoEstado> getEstadosActivos() {
        return estadosActivos;
    }
}
