package domain;



public abstract class Thing{
    protected String name;
    protected String type; //For cards [TROOP,SPELL,BUILDING] For decks [DEFENSIVE, AGRESSIVE, BALANCED]
    
    
    public Thing(String name, String type){
        this.name=name;
        this.type=type;
    }
    
    
    /**
     * Return the name
     * @return
     */
    public String name(){
        return name;
    }

 
    /**
     * Return the type
     * @return
     */
    public String type(){
        return type;
    }
    
    /**
     * Returns the elixir of a card or the average elixir of the cards in a deck
     * @return
     * @throws ClashRoyaleException, if any elixir data is unknown or has an error
     */
    public abstract int elixir() throws ClashRoyaleException;
    
    
    /**
     * Returns the resistance of a card or a deck 
     * @return
     * @throws ClashRoyaleException, if the resistance cannot be calculate
     */
    public abstract int resistance() throws ClashRoyaleException;    
   
    
    /**
     * Return the representation as string
     * @return
     * @throws ClashRoyaleException, if the data has problems (some unknown or erroneous data)
     */    
    public abstract String data() throws ClashRoyaleException;

}
