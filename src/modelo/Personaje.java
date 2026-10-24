package modelo;

public abstract class Personaje extends Entidad {
    protected int mana;
    protected int manaMax;
    protected int nivel;
    protected int experiencia;
    protected int oro;

    public Personaje(String nombre, int vida, int ataque, int defensa, int velocidad, int mana) {
        super(nombre, vida, ataque, defensa, velocidad);
        this.mana = mana;
        this.manaMax = mana;
        this.nivel = 1;
        this.experiencia = 0;
        this.oro = 0;
    }

    public void sumarExperiencia(int xpGanada) {
        this.experiencia += xpGanada;
        verificarSubidaNivel();
    }

    private void verificarSubidaNivel() {
        int xpNecesaria = this.nivel * 100; // Fórmula que definimos
        while (this.experiencia >= xpNecesaria) {
            subirDeNivel();
            this.experiencia -= xpNecesaria;
            xpNecesaria = this.nivel * 100; // Recalcular para el nuevo nivel
        }
    }

    private void subirDeNivel() {
        this.nivel++;
        this.vidaMax += 20;
        this.vida = this.vidaMax; // Cura al máximo
        this.manaMax += 10;
        this.mana = this.manaMax; // Maná al máximo
        this.ataque += 5;
        System.out.println("¡" + this.nombre + " ha subido al nivel " + this.nivel + "!");
    }
    
    // Acá irán los métodos de usarItem, defender, etc.
}