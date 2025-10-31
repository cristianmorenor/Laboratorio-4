
package domain;

public class ClashRoyaleException extends Exception {
    private static final long serialVersionUID = 1L;

    public static final String ELIXIR_UNKNOWN = "ELIXIR_UNKNOWN";
    public static final String ELIXIR_ERROR = "ELIXIR_ERROR";
    public static final String RESISTANCE_UNKNOWN = "RESISTANCE_UNKNOWN";
    public static final String RESISTANCE_ERROR = "RESISTANCE_ERROR";
    public static final String IMPOSSIBLE = "IMPOSSIBLE";

    public ClashRoyaleException(String message) {
        super(message);
    }

    public ClashRoyaleException(String message, Throwable cause) {
        super(message, cause);
    }
}
