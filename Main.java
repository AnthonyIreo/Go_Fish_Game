
/*
The fish game is designed to play with the computer.
The user can be one of the player and player the fish game with the computer.
When the deck is empty, the game is over.
The winner should be the player who has more books.
*/
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Scanner scan = new Scanner( System.in );
        System.out.println("Hi, my name is Tim. Let's play a fish game!\nPlease enter your name to join:");
        String name = scan.nextLine();
        
        // create a player for user
        Player user = new Player( name );
        // create a player for computer
        Player com = new Player( "Tim" );
        
        // ask the user for diffculty
        String diffculty = "";
        System.out.println("Please enter the diffculty you want to play:");
        System.out.println("Enter \'a\' for easy\nEnter \'b\' for hard");
        // record the diffculty the user choose
        String diffcultyInput = scan.nextLine();
        if ( diffcultyInput.equals("a") ){
            diffculty = "easy";
        }
        else{
            diffculty = "diffcult";
        }
        
        System.out.println(user);
        System.out.println(com);
        
        // clean up the table, set upt the deck
        Deck deck = new Deck();
        System.out.println(deck);
        System.out.println("++++++++++++++++++++++++++ Game starts! +++++++++++++++++++++++++");
        
        // set up the hands by sending each player 7 cards
        for ( int i = 0; i < 7; i++){
            Card card1 = deck.fish();
            user.addCard( card1 );
            Card card2 = deck.fish();
            com.addCard( card2 );
        }
        
        // follow up user, com, deck 
        // System.out.println(user);
        // System.out.println(com);
        // System.out.println(deck);
        
        // record the current player, if forms a book, one more round for the current player 
        boolean isUser = true;
        
        while( true ){
            // if pool in empty, game over 
            if ( deck.getPoolSize() == 0 ){
                break;
            }
            
            
            // game continues
            if ( isUser ){
                // user's turn
                // check if the user want to check hands
                System.out.println("\nuser's turn ====================\n");
                System.out.println("Enter \'yes\' or \'Y\' if you want to check your hands and books:");
                String wantCheck = scan.nextLine();
                if ( wantCheck.equals("yes") || wantCheck.equals("Y") ){
                    System.out.println(user);
                }
                else{
                    System.out.println("All right.");
                }
                
                // ask the user to input a value
                System.out.println("Enter a value you want: ");
                String userAskValue = scan.nextLine().toUpperCase();
                // user asks computer for the value 
                System.out.println( user.getName() + " asks - Do you have a " + userAskValue + " ? ");
                // check if the computer has the value 
                Card returnedCard = com.checkCard( userAskValue );
                
                // variable to store if draw or book 
                String drawOrBook = "";
                // if card has a value, add the card into player's hands
                // if the value of the card is 'fish', go fish
                if ( !returnedCard.getValue().equals("fish") ){
                    System.out.println( com.getName() + " says - Yes. I have a " + userAskValue + ". ");
                    drawOrBook = user.addCard( returnedCard );
                }
                else{
                    // fish
                    System.out.println( com.getName() + " says - Go Fish ");
                    Card newCardToAdd = deck.fish();
                    
                    // if the value of the card is 'fish', pool is empty, game over 
                    if ( newCardToAdd.getValue().equals("fish") ){
                        break;
                    }
                    
                    // add the card into hands
                    drawOrBook = user.addCard( newCardToAdd );
                }
                System.out.println( drawOrBook );
                // check if forms a book, one more trun, if not, computer player's turn 
                if ( drawOrBook.indexOf( "book" ) == -1){
                    isUser = false;
                }
            }
            else{
                // computer's turn
                // System.out.println(user);
                // System.out.println(com);
                // System.out.println(deck);
                System.out.println("\ncomputer's turn ====================\n");
                
                // define the value asked depends on the diffculty
                String comAskValue = "";
                if ( diffculty.equals("easy") ){
                    comAskValue = deck.getRandomValue();
                }
                else{
                    comAskValue = user.getValueFromHands();
                }
                System.out.println( com.getName() + " asks - Do you have a " + comAskValue + " ? ");
                // check if the user has the value 
                Card returnedCard = user.checkCard( comAskValue );
                
                // variable to store if draw or book 
                String drawOrBook = "";
                // if card has a value, add the card into computer player's hands
                // if the value of the card is 'fish', go fish
                if ( !returnedCard.getValue().equals("fish") ){
                    System.out.println( user.getName() + " says - Yes. I have a " + comAskValue + ".");
                    drawOrBook = com.addCard( returnedCard );
                }
                else{
                    // fish
                    System.out.println( user.getName() + " says - Go Fish ");
                    Card newCardToAdd = deck.fish();
                    
                    // if the value of the card is 'fish', pool is empty, game over 
                    if ( newCardToAdd.getValue().equals("fish") ){
                        break;
                    }
                    
                    // add the card into hands
                    drawOrBook = com.addCard( newCardToAdd );
                }
                System.out.println( drawOrBook );
                // check if forms a book, one more trun, if not, user's turn 
                if ( drawOrBook.indexOf( "book" ) == -1){
                    isUser = true;
                }
            } // end if user's turn
            
        } // end while
        
        // game over 
        System.out.println("Game over:");
        System.out.println( user.getName() + " has " + user.getNumberOfBooks() + " books" );
        System.out.println( com.getName() + " has " + com.getNumberOfBooks() + " books" );
        // check who wins 
        if ( user.getNumberOfBooks() >= com.getNumberOfBooks() ){
            System.out.println( user.getName() + " is the winner");
        }
        else{
            System.out.println( com.getName() + " is the winner");
        }
        
    }
}
