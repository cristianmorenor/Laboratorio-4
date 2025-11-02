package test;

import domain.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DeckTest {

    @Test
    public void shouldCalculateTheElixirOfADeck() {
        Deck c = new Deck("Beatdown", "AGRESSIVE");
        c.addCard(new Card("P.E.K.K.A", "TROOP", 7, 4982));
        c.addCard(new Card("Hog Rider", "TROOP", 4, 1696));
        c.addCard(new Card("Baby Dragon", "TROOP", 4, 1526));
        try {
            assertEquals(3, c.elixir());
        } catch (ClashRoyaleException e) {
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldThrowExceptionIfDeckHasNoCard() {
        Deck c = new Deck("Beatdown", "AGRESSIVE");
        try {
            int elixir = c.elixir();
            fail("Did not throw exception");
        } catch (ClashRoyaleException e) {
            assertEquals(ClashRoyaleException.IMPOSSIBLE, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionIfThereIsErrorInElixir() {
        Deck c = new Deck("Beatdown", "AGRESSIVE");
        c.addCard(new Card("P.E.K.K.A", "TROOP", 7, 4982));
        c.addCard(new Card("Hog Rider", "TROOP", -4, 1696));
        c.addCard(new Card("Baby Dragon", "TROOP", 4, 1526));
        try {
            int elixir = c.elixir();
            fail("Did not throw exception");
        } catch (ClashRoyaleException e) {
            assertEquals(ClashRoyaleException.ELIXIR_ERROR, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionIfElixirIsNotKnown() {
        Deck c = new Deck("Beatdown", "AGRESSIVE");
        c.addCard(new Card("P.E.K.K.A", "TROOP", 7, 4982));
        c.addCard(new Card("Hog Rider", "TROOP", 4, 1696));
        c.addCard(new Card("Baby Dragon", "TROOP", null, 1526));
        try {
            int elixir = c.elixir();
            fail("Did not throw exception");
        } catch (ClashRoyaleException e) {
            assertEquals(ClashRoyaleException.ELIXIR_UNKNOWN, e.getMessage());
        }
    }

    @Test // prueba para el nuevo método knownElixir
    // En el caso en que el Mazo tiene todas las cartas válidas
    public void shouldCalculateKnownElixirWithAllValidCards() {
        Deck deck = new Deck("Test", "AGRESSIVE");
        deck.addCard(new Card("Card1", "TROOP", 3, 1000));
        deck.addCard(new Card("Card2", "TROOP", 5, 1000));
        deck.addCard(new Card("Card3", "TROOP", 7, 1000));

        try {
            assertEquals(5, deck.knownElixir()); // (3+5+7)/3 = 5
        } catch (ClashRoyaleException e) {
            fail("Should not throw exception");
        }
    }

    @Test // prueba para el nuevo método knownElixir
          // En el caso en que el Mazo tine algunas cartas inválidas (ignorarlas)
    public void shouldCalculateKnownElixirIgnoringInvalidCards() {
        Deck deck = new Deck("Test", "AGRESSIVE");
        deck.addCard(new Card("Card1", "TROOP", 4, 1000)); // Válida
        deck.addCard(new Card("Card2", "TROOP", -2, 1000)); // Error (negativo)
        deck.addCard(new Card("Card3", "TROOP", null, 1000)); // Desconocido (null)
        deck.addCard(new Card("Card4", "TROOP", 6, 1000)); // Válida

        try {
            assertEquals(5, deck.knownElixir()); // (4+6)/2 = 5
        } catch (ClashRoyaleException e) {
            fail("Should not throw exception");
        }
    }

    @Test // prueba para el nuevo método knownElixir
    // Caso en el que el mazo esta sin cartas válidas → Excepción IMPOSSIBLE
    public void shouldThrowExceptionIfNoValidCards() {
        Deck deck = new Deck("Test", "AGRESSIVE");
        deck.addCard(new Card("Card1", "TROOP", -5, 1000)); // Error
        deck.addCard(new Card("Card2", "TROOP", null, 1000)); // Desconocido
        deck.addCard(new Card("Card3", "TROOP", 15, 1000)); // Error (>10)

        try {
            deck.knownElixir();
            fail("Should throw IMPOSSIBLE exception");
        } catch (ClashRoyaleException e) {
            assertEquals(ClashRoyaleException.IMPOSSIBLE, e.getMessage());
        }

    }

    @Test // prueba para el nuevo método knownElixir
    // En el caso en que el Mazo esta vacío → Excepción IMPOSSIBLE
    public void shouldThrowExceptionIfDeckIsEmpty() {
        Deck deck = new Deck("Test", "AGRESSIVE");

        try {
            deck.knownElixir();
            fail("Should throw IMPOSSIBLE exception");
        } catch (ClashRoyaleException e) {
            assertEquals(ClashRoyaleException.IMPOSSIBLE, e.getMessage());
        }
    }

}