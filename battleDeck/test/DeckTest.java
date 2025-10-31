package test;
import domain.*;


import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class DeckTest{
   
 
    @Test
    public void shouldCalculateTheElixirOfADeck(){                              
        Deck c = new Deck("Beatdown", "AGRESSIVE");
        c.addCard(new Card("P.E.K.K.A", "TROOP",7,4982));
        c.addCard(new Card("Hog Rider", "TROOP",4,1696));
        c.addCard(new Card("Baby Dragon", "TROOP",4,1526));
        try {
           assertEquals(3,c.elixir());
        } catch (ClashRoyaleException e){
            fail("Threw a exception");
        }    
    }    

    
    @Test
    public void shouldThrowExceptionIfDeckHasNoCard(){
        Deck c = new Deck("Beatdown", "AGRESSIVE");
        try { 
           int elixir=c.elixir();
           fail("Did not throw exception");
        } catch (ClashRoyaleException e) {
            assertEquals(ClashRoyaleException.IMPOSSIBLE,e.getMessage());
        }    
    }    
    
    
   @Test
    public void shouldThrowExceptionIfThereIsErrorInElixir(){
        Deck c = new Deck("Beatdown", "AGRESSIVE");
        c.addCard(new Card("P.E.K.K.A", "TROOP",7,4982));
        c.addCard(new Card("Hog Rider", "TROOP",-4,1696));
        c.addCard(new Card("Baby Dragon", "TROOP",4,1526));
        try { 
           int elixir=c.elixir();
           fail("Did not throw exception");
        } catch (ClashRoyaleException e) {
            assertEquals(ClashRoyaleException.ELIXIR_ERROR,e.getMessage());
        }    
    }     
    
   @Test
    public void shouldThrowExceptionIfElixirIsNotKnown(){
        Deck c = new Deck("Beatdown", "AGRESSIVE");
        c.addCard(new Card("P.E.K.K.A", "TROOP",7,4982));
        c.addCard(new Card("Hog Rider", "TROOP",4,1696));
        c.addCard(new Card("Baby Dragon", "TROOP", null,1526));
        try { 
           int elixir=c.elixir();
           fail("Did not throw exception");
        } catch (ClashRoyaleException e) {
            assertEquals(ClashRoyaleException.ELIXIR_UNKNOWN,e.getMessage());
        }    
    }  
    
}