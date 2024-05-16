import java.util.ArrayList;
import java.util.Collections;
 
public class Deck { //Where the deck is created and accessed
    
    private ArrayList<Card> deck;
    private String[] colors;


    public Deck (){
        deck = new ArrayList<Card>();
        colors = new String [] {"Red", "Yellow", "Green", "Blue", "Black"};
    }

    public void createDeck(){
    for (int t = 0; t<2; t++){
        for (int i = 0; i<colors.length; i++){ //for each color, add the cards necessary.
            if (i!=4){ //first add the power cards
                deck.add(new Card("Normal +2", colors[i], true));
                deck.add(new Card("Reverse", colors[i], "reverseID"));
                deck.add(new Card("Skip", colors[i], "s"));
                
                if (t ==1){ //on the second loop, add normal cards 1-9
                    for (int j = 1; j<=9; j++){
                        deck.add(new Card("Normal", colors[i], j));
                    }
                }
                else{
                    for (int j = 0; j<=9; j++){ //on the first loop, add normal cards 0-9
                        deck.add(new Card("Normal", colors[i], j));
                    }
                } 
            }
            else if (i == 4 && t == 1){ //the super duper cards
                for (int k = 0; k<4; k++){
                    deck.add(new Card("Wild", "wildID"));
                    deck.add(new Card("Wild +4"));
                }
            } 
          
        }
    }
       //debugging to test if cards were added properly
    }

    //Collections is a super method that allows us to shuffle deck in part of ArrayList class
    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    //Access the Deck
    public ArrayList<Card> getDeck() {
        return deck;
    }

    //If we need to check the deck size
    public int currentDeckSize(){
        return deck.size();
    }
  
   


}
