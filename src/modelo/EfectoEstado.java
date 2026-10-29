package modelo;

public class EfectoEstado {
    private String nombre;
    private int duracion;
    private int valor;
    private boolean activo;

    public EfectoEstado(String nombre, int duracion, int valor) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.valor = valor;
        this.activo = true;
    }

    public void disminuirDuracion() {
        if (this.activo) {
            this.duracion = Math.max(0, this.duracion - 1);
            if (this.duracion == 0) {
                this.activo = false;
            }
        }
    }

    public boolean estaExpirado() {
        return !this.activo || this.duracion <= 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getValor() {
        return valor;
    }

    public boolean isActivo() {
        return activo;
    }
}
