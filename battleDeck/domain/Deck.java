package domain;

import java.util.ArrayList;

public class Deck extends Thing {

    private ArrayList<Card> cards;

    /**
     * Constructs a new deck
     * 
     * @param name
     * @param type
     */
    public Deck(String name, String type) {
        super(name, type);
        cards = new ArrayList<Card>();
    }

    /**
     * Add a new Card
     * 
     * @param c
     */
    public void addCard(Card c) {
        cards.add(c);
    }

    /**
     * Calculate the average elixir of all cards in the deck
     * 
     * @return The average elixir as an integer
     * @throws ClashRoyaleException IMPOSSIBLE if the deck has no cards
     * @throws ClashRoyaleException ELIXIR_UNKNOWN if any card has unknown elixir
     * @throws ClashRoyaleException ELIXIR_ERROR if any card has invalid elixir
     *                              value
     */
    @Override
    public int elixir() throws ClashRoyaleException {
        // Validamos que el mazo tenga cartas
        if (cards.isEmpty()) {
            throw new ClashRoyaleException(ClashRoyaleException.IMPOSSIBLE);
        }

        // Acumular el elixir de todas las cartas
        int totalElixir = 0;
        for (Card card : cards) {
            totalElixir += card.elixir(); // Puede lanzar ELIXIR_UNKNOWN o ELIXIR_ERROR
        }

        // Calcular y retornar el promedio como entero
        return totalElixir / cards.size();
    }

    /**
     * Calculate the average elixir of the cards in the deck
     * considering only the cards with valid values
     * 
     * @return Average elixir of valid cards
     * @throws ClashRoyaleException IMPOSSIBLE, if there are no valid values
     */
    public int knownElixir() throws ClashRoyaleException {
        int totalElixir = 0;
        int validCards = 0;

        // Iteramos sobre todas las cartas,como pide el diseño astah.
        for (Card card : cards) {
            try {
                // Intentar obtener el elixir de la carta
                int cardElixir = card.elixir();
                totalElixir += cardElixir;
                validCards++;
            } catch (ClashRoyaleException e) {
                // Ignorar cartas con ELIXIR_UNKNOWN o ELIXIR_ERROR
                // y continuar con la siguiente carta
            }
        }

        // Si no hay cartas válidas, lanzar excepción
        if (validCards == 0) {
            throw new ClashRoyaleException(ClashRoyaleException.IMPOSSIBLE);
        }

        // Calcular y retornar el promedio de las cartas válidas
        return totalElixir / validCards;
    }

    @Override
    public int resistance() throws ClashRoyaleException {
        return 0;
    }

    @Override
    public String data() throws ClashRoyaleException {
        StringBuffer answer = new StringBuffer();
        answer.append(name + ": " + type + ". Elixir:" + elixir() + "   Resistencia:" + resistance());
        for (Card c : cards) {
            answer.append("\n\t" + c.data());
        }
        return answer.toString();
    }

    /**
     * Calculate the average elixir of the deck, replacing unknown and error values
     * with estimated values based on valid cards
     * 
     * @param unknown Strategy for unknown values: "MIN", "MAX", or "AVG"
     * @param error   Strategy for error values: "MIN", "MAX", or "AVG"
     * @return Average elixir including replacements
     * @throws ClashRoyaleException IMPOSSIBLE if there are no valid values to base
     *                              estimates on
     * @throws ClashRoyaleException PARAMETER_ERROR if strategies are invalid
     */
    public int estimateElixir(String unknown, String error) throws ClashRoyaleException {
        // Validar parámetros
        if (!isValidStrategy(unknown) || !isValidStrategy(error)) {
            throw new ClashRoyaleException(ClashRoyaleException.PARAMETER_ERROR);
        }

        // FASE 1: Calcular estadísticas de valores válidos
        ArrayList<Integer> validElixirs = new ArrayList<>();

        for (Card card : cards) {
            try {
                int cardElixir = card.elixir();
                validElixirs.add(cardElixir);
            } catch (ClashRoyaleException e) {
                // Ignorar por ahora, solo recolectamos válidos
            }
        }

        // Si no hay valores válidos, es imposible estimar
        if (validElixirs.isEmpty()) {
            throw new ClashRoyaleException(ClashRoyaleException.IMPOSSIBLE);
        }

        // Calcular MIN, MAX, AVG de valores válidos
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        int sumValid = 0;

        for (int elixir : validElixirs) {
            if (elixir < minValue)
                minValue = elixir;
            if (elixir > maxValue)
                maxValue = elixir;
            sumValid += elixir;
        }

        int avgValue = sumValid / validElixirs.size();

        // FASE 2: Calcular promedio con reemplazos
        int totalElixir = 0;
        int cardCount = 0;

        for (Card card : cards) {
            try {
                // Intentar obtener valor válido
                int cardElixir = card.elixir();
                totalElixir += cardElixir;

            } catch (ClashRoyaleException e) {
                // Determinar qué reemplazo usar según el tipo de error
                int replacementValue;

                if (e.getMessage().equals(ClashRoyaleException.ELIXIR_UNKNOWN)) {
                    replacementValue = getReplacement(unknown, minValue, maxValue, avgValue);
                } else if (e.getMessage().equals(ClashRoyaleException.ELIXIR_ERROR)) {
                    replacementValue = getReplacement(error, minValue, maxValue, avgValue);
                } else {
                    // Otro tipo de excepción (no debería ocurrir)
                    throw e;
                }

                totalElixir += replacementValue;
            }

            cardCount++;
        }

        return totalElixir / cardCount;
    }

    /**
     * Get replacement value based on strategy
     * 
     * @param strategy "MIN", "MAX", or "AVG"
     * @param min      minimum valid value
     * @param max      maximum valid value
     * @param avg      average of valid values
     * @return replacement value
     */
    private int getReplacement(String strategy, int min, int max, int avg) {
        switch (strategy.toUpperCase()) {
            case "MIN":
                return min;
            case "MAX":
                return max;
            case "AVG":
                return avg;
            default:
                return avg;
        }
    }

    /**
     * Validate that strategy is valid
     * 
     * @param strategy String to validate
     * @return true if valid
     */
    private boolean isValidStrategy(String strategy) {
        return strategy != null &&
                (strategy.equalsIgnoreCase("MIN") ||
                        strategy.equalsIgnoreCase("MAX") ||
                        strategy.equalsIgnoreCase("AVG"));
    }

}
