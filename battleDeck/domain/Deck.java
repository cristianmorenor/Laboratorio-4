package domain;  
 
import java.util.ArrayList;

public class Deck extends Thing{
   
    private ArrayList<Card> cards;
    
    /**
     * Constructs a new deck
     * @param name
     * @param type
     */
    public Deck(String name, String type){
        super(name, type);
        cards= new ArrayList<Card>();
    }


     /**
     * Add a new Card
     * @param c
     */   
    public void addCard(Card c){
        cards.add(c);
    }
       
 
    
    @Override
    public int elixir() throws ClashRoyaleException{
        return 0;
    };
    

    /**
    * Calculate the average elixir of the cards in the deck considering only the cards with valid values
    * @return
    * @throws ClashRoyaleException IMPOSSIBLE, If there are no valid values
    */
    public int knownElixir() throws ClashRoyaleException{
        return 0;
    };    
    
    

    public int estimateElixir(String unknown, String error) throws ClashRoyaleException{
        return 0;
    } 
    

    @Override
    public int resistance() throws ClashRoyaleException{
       return 0;
    }  
    
    
    @Override
    public String data() throws ClashRoyaleException{
        StringBuffer answer=new StringBuffer();
        answer.append(name+": "+type+". Elixir:" +elixir()+"   Resistencia:"+resistance());
        for(Card c: cards) {
            answer.append("\n\t"+c.data());
        }
        return answer.toString();
    } 
    

}
