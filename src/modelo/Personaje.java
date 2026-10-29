package modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Personaje extends Entidad {
    protected int nivel;
    protected int experiencia;
    protected int manaMaxima;
    protected int manaActual;
    protected int energiaBestia;
    protected boolean transformado;
    protected int oro;
    protected List<Item> inventario;

    public Personaje(String nombre, int vidaMaxima, int manaMaxima, int ataque, int defensa, int velocidad, int energiaBestia) {
        super(nombre, vidaMaxima, ataque, defensa, velocidad);
        this.nivel = 1;
        this.experiencia = 0;
        this.manaMaxima = manaMaxima;
        this.manaActual = manaMaxima;
        this.energiaBestia = energiaBestia;
        this.transformado = false;
        this.oro = 0;
        this.inventario = new ArrayList<>();
    }

    public abstract void usarHabilidad(Entidad objetivo);

    public void transformar() {
        if (this.manaActual >= 10) {
            this.manaActual -= 10;
            this.transformado = true;
            this.ataque += 5;
            this.defensa += 3;
        }
    }

    public void destransformar() {
        this.transformado = false;
        this.ataque = Math.max(1, this.ataque - 5);
        this.defensa = Math.max(1, this.defensa - 3);
    }

    public void recibirDanio(int cantidad) {
        int danoEfectivo = Math.max(1, cantidad - this.defensa);
        this.vidaActual = Math.max(0, this.vidaActual - danoEfectivo);
    }

    public void curar(int cantidad) {
        this.vidaActual = Math.min(this.vidaMaxima, this.vidaActual + cantidad);
    }

    public void recuperarMana(int cantidad) {
        this.manaActual = Math.min(this.manaMaxima, this.manaActual + cantidad);
    }

    public void sumarExperiencia(int cantidad) {
        this.experiencia += cantidad;
        while (this.experiencia >= this.getExperienciaNecesariaParaSiguienteNivel()) {
            this.experiencia -= this.getExperienciaNecesariaParaSiguienteNivel();
            subirNivel();
        }
    }

    public void subirNivel() {
        this.nivel++;
        this.vidaMaxima += 20;
        this.vidaActual = this.vidaMaxima;
        this.manaMaxima += 10;
        this.manaActual = this.manaMaxima;
        this.ataque += 5;
        this.defensa += 3;
    }

    public int getExperienciaNecesariaParaSiguienteNivel() {
        return this.nivel * 100;
    }

    public void agregarItem(Item item) {
        this.inventario.add(item);
    }

    public void usarItem(Item item) {
        if (this.inventario.contains(item)) {
            item.aplicar(this);
            this.inventario.remove(item);
        }
    }

    public void defender() {
        this.defensa += (int) Math.round(this.defensa * 0.5);
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

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    public List<Item> getInventario() {
        return inventario;
    }
}
