import java.util.*;

public class Player {  //Properties and cards in each player
    
    private String playerName;
    private int wins;
    private boolean activeTurn;
    private ArrayList<Card> playerCards;
    

    
//Actual Player
public Player (String name, Boolean active){
    playerName = name;
    activeTurn = active;
    wins = 0;
    playerCards = new ArrayList<Card>();
}


//Methods

public String getPlayerName(){
    return playerName;
}

public Boolean getActiveTurn(){
    return activeTurn;
}


public void setPlayerCards(ArrayList<Card> deck){
    while(playerCards.size() !=7){
        playerCards.add(deck.get(0));
        deck.remove(0);
    }
}

public ArrayList<Card> getPlayerCards(){
    return playerCards;
}










}
