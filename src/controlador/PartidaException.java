package controlador;

// Excepción propia para problemas al guardar o cargar la partida (Clase 13)
public class PartidaException extends Exception {
    private static final long serialVersionUID = 1L;

    public PartidaException(String mensaje) {
        super(mensaje);
    }

    public PartidaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
