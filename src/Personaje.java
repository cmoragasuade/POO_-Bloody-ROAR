/* public abstract class Personaje {
    protected String nombre;
    protected int vida_actual;
    protected int vida_max;
    protected int mana;
    protected int nivel;
    protected int ataque;
    protected int defensa;
    protected int velocAtaque;
    protected int experiencia;
    protected int energiaBestia;
    protected boolean estaTransformado;

    public Personaje(String nombre, int vida_max, int mana, int ataque, int defensa, int velocAtaque, int energiaBestia) {
        this.nombre = nombre;
        this.vida_max = vida_max;
        this.vida_actual = vida_max;
        this.mana = mana;
        this.nivel = 1;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocAtaque = velocAtaque;
        this.experiencia = 0;
        this.energiaBestia = energiaBestia;
        this.estaTransformado = false;
    }

    public abstract void atacar();

    public abstract void defender();

    public void recibirDanio(int cantidad) {
        int danioEfectivo = Math.max(1, cantidad - this.defensa);
        this.vida_actual = Math.max(0, this.vida_actual - danioEfectivo);
    }

    public void subirNivel() {
        this.nivel++;
        this.vida_max += 20;
        this.vida_actual = this.vida_max;
        this.mana += 10;
        this.ataque += 5;
        this.defensa += 3;
        this.velocAtaque += 2;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida_actual() {
        return vida_actual;
    }

    public void setVida_actual(int vida_actual) {
        this.vida_actual = Math.max(0, Math.min(this.vida_max, vida_actual));
    }

    public int getVida_max() {
        return vida_max;
    }

    public void setVida_max(int vida_max) {
        this.vida_max = Math.max(1, vida_max);
        this.vida_actual = Math.min(this.vida_actual, this.vida_max);
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getVelocAtaque() {
        return velocAtaque;
    }

    public void setVelocAtaque(int velocAtaque) {
        this.velocAtaque = velocAtaque;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public int getEnergiaBestia() {
        return energiaBestia;
    }

    public void setEnergiaBestia(int energiaBestia) {
        this.energiaBestia = energiaBestia;
    }

    public boolean isEstaTransformado() {
        return estaTransformado;
    }

    public void setEstaTransformado(boolean estaTransformado) {
        this.estaTransformado = estaTransformado;
    }
}
*/
// Clase Personaje