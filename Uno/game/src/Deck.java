import java.util.ArrayList;

public class Deck {
    
    private ArrayList<Object> deck;
    private String[] colors;


    public Deck (){
        deck = new ArrayList<Object>();
        colors = new String [] {"Red", "Yellow", "Green", "Blue", "Black"};
    }

    public int createDeck(){
    for (int t = 0; t<2; t++){
        for (int i = 0; i<colors.length; i++){ //for each color, add the cards necessary.
            if (i!=4){ //first add the power cards
                deck.add(new Card("plusTwo", colors[i], true));
                deck.add(new Card("reverse", colors[i], "reverseID"));
                deck.add(new Card("skip", colors[i], "s"));
                
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
                    deck.add(new Card("wild", "wildID"));
                    deck.add(new Card("plusFour"));
                }
            } 
          
        }
    }
        return deck.size();
    }

  
  
    public void normalCards(){

    }


}
