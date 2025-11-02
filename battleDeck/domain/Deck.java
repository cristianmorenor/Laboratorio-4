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

    public int estimateElixir(String unknown, String error) throws ClashRoyaleException {
        return 0;
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

}
