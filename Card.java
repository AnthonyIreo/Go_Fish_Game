
public class Card{
    
    private String value;
    private String suit;
    
    public Card(){}
    public Card( String theValue, String theSuit ){
        value = theValue;
        suit = theSuit;
    }
    
    // setter
    public void setValue( String theValue ){
        value = theValue;
    }
    public void setSuit( String theSuit ){
        suit = theSuit;
    }
    
    // getter
    public String getValue(){
        return value;
    }
    public String getSuit(){
        return suit;
    }
}
