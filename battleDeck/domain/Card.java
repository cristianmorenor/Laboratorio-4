package domain;

public class Card extends Thing {

    private Integer elixir; // [1..10]]
    private Integer hitPoints; // [100..5000] + [0] No aplica

    public Card(String name, String type, Integer elixir, Integer hitPoints) {
        super(name, type);
        this.elixir = elixir;
        this.hitPoints = hitPoints;
    }

    @Override
    public int elixir() throws ClashRoyaleException {
        if (elixir == null)
            throw new ClashRoyaleException(ClashRoyaleException.ELIXIR_UNKNOWN);
        if ((elixir < 1) || (10 < elixir))
            throw new ClashRoyaleException(ClashRoyaleException.ELIXIR_ERROR);
        return elixir;
    }

    @Override
    public int resistance() throws ClashRoyaleException {
        if (hitPoints == null)
            throw new ClashRoyaleException(ClashRoyaleException.RESISTANCE_UNKNOWN);
        if ((hitPoints != 0) && (hitPoints < 100) || (5000 < hitPoints))
            throw new ClashRoyaleException(ClashRoyaleException.RESISTANCE_ERROR);
        return hitPoints;
    }

    @Override
    public String data() {
        String theData = name + ": " + type;
        try {
            theData = theData + ". Elixir:" + elixir() + "   Resistencia:" + (resistance() != 0 ? resistance() : "N/A");
        } catch (ClashRoyaleException e) {
            theData += ". *** Datos incompletos o erróneos.";
        }
        return theData;
    }
}
