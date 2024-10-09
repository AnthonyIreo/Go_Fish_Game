

import java.util.ArrayList;

public class Deck{
    
    private ArrayList<Card> pool;
    private final String[] VALUES = {"1","2","3","4","5","6","7","8","9","10","J","Q","K"};
    private final String[] SUITS = {"a","b","c","d"};
    
    // initialize the deck (pool)
    public Deck(){
        // pool created with full cards
        pool = new ArrayList<Card>();
        // add cards into the deck 
        for ( String value : VALUES ){
            for ( String suit : SUITS ){
                pool.add( new Card( value, suit ) );
            }
        }// now pool has 52 cards
    }// end constructor
    
    // method to go fish
    public Card fish(){
        // when pool is empty, no fishing
        if ( pool.size() == 0 ){
            return new Card("empty","empty");
        }
        // remove a random card from pool
        Card card = pool.remove( (int)(Math.random()*pool.size() ) );
        return card;
    }
    
    // check the pool size to stop the game
    public int getPoolSize(){
        return pool.size();
    }
    
    // return random value in VALUES, designed for easy mode
    public String getRandomValue(){
        return VALUES[ (int)(Math.random() * VALUES.length) ];
    }
    
    // display the deck on the table 
    public String toString(){
        String s = "";
        for ( Card card : pool ){
            s += "[" + card.getValue() + "," + card.getSuit() + "] ";
        }
        return s;
    }
    
}
