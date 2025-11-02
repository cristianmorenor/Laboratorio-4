package domain;

/**
 * Exception class for Clash Royale application errors
 * 
 * @author MorenoRubiano
 *
 */
public class ClashRoyaleException extends Exception {

    // Mensajes de error como constantes públicas
    public static final String ELIXIR_UNKNOWN = "Elixir desconocido";
    public static final String ELIXIR_ERROR = "Error en valor de elixir";
    public static final String RESISTANCE_UNKNOWN = "Resistencia desconocida";
    public static final String RESISTANCE_ERROR = "Error en valor de resistencia";
    public static final String IMPOSSIBLE = "Operación imposible";

    /**
     * Constructor que recibe el mensaje de error
     * 
     * @param message El mensaje descriptivo del error
     */
    public ClashRoyaleException(String message) {
        super(message);
    }
}