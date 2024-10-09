
import java.util.HashMap;
import java.util.ArrayList;

public class Player{
    
    private String name;
    
    private HashMap<String, ArrayList<String> > hands;
    private ArrayList<String> books;
    
    public Player( String theName ){
        name = theName;
        hands = new HashMap<String,  ArrayList<String> >();
        books = new ArrayList<String>();
    }
    
    // add a card into hands
    // return: String "** draws **" or "** books the *"
    public String addCard( Card card ){
        // if the player have a card with same value as the card 
        if ( hands.containsKey( card.getValue() ) ){
            // add it into hand 
            hands.get( card.getValue() ).add( card.getSuit() );
            
            // check if forms a book 
            String ifBooks = checkHand();
            if (  !ifBooks.equals("-1") ){
                // return "** books the *"
                return name + " books the " + ifBooks + " .";
            }// end if forms a book
            
        }
        else{
            // create a arraylist to store the suits, and add the current suit into the list 
            ArrayList<String> arrSuits = new ArrayList<String>();
            arrSuits.add( card.getSuit() );
            // store the list into map 
            hands.put( card.getValue(), arrSuits );
        }
        // return "** draws **"
        return name + " draws " + card.getValue() + card.getSuit();
    }// addCard end
    
    // when asked, check if have a card with the value, if the card exists, remove it  
    // return: a Card with value if exists
    //         a Card with { "fish", "fish" } if not exist
    public Card checkCard( String value ){
        if ( hands.containsKey( value ) ){
            // create a new card to return 
            Card returnedCard = new Card( value, hands.get( value ).remove(0) );
            // check if number of the cards with that value on hand is empty, remove
            if ( hands.get( value ).size() == 0 ){
                // remove the key 
                hands.remove( value ); 
            }
            return returnedCard;
        }
        else{
            // go fish
            return new Card( "fish", "fish" );
        }
    }
    
    // check if there is a value with 4 suits in the hand
    // if forms a book, return a the value to form a book 
    // if not, return a "-1"
    public String checkHand(){
        String ifBooks = "-1";
        // create an arraylist to record the remove 
        ArrayList<String> needRemove = new ArrayList<String>();
        for ( String value : hands.keySet() ){
            if ( hands.get(value).size() == 4 ){
                // add it into books and add it into remove list 
                books.add( value );
                needRemove.add( value );
                // record the value to return
                ifBooks = value;
            }
        }
        // remove all elements from remove list 
        for ( String value : needRemove ){
            hands.remove( value );
        }
        return ifBooks;
    }
    
    // get the number of books to check if win 
    public int getNumberOfBooks(){
        return books.size();
    }
    
    // get the player name 
    public String getName(){
        return name;
    }
    
    // get a value from hands
    public String getValueFromHands(){
        for ( String value : hands.keySet() ){
            return value;
        }
        return "";
    }
    
    // return "** hands:{} \n books:{}"
    public String toString(){
        return name + "'s hand: " + hands + ",\nbooks: " + books + "\n";
    }
}
