import java.util.*;

public class Player {  //Properties and cards in each player
    
    private String playerName;
    private int wins;
    private boolean isPlayer;
    private ArrayList<Card> playerCards;
    

    
//Actual Player
public Player (String name, Boolean active){
    playerName = name;
    isPlayer= active;
    wins = 0;
    playerCards = new ArrayList<Card>();
}


//Methods

public String getPlayerName(){
    return playerName;
}

public Boolean isPlayer(){
    return isPlayer;
}


public void setPlayerCards(ArrayList<Card> deck){
    while(playerCards.size() != 7){
        playerCards.add(deck.get(0));
        deck.remove(0);
    }
}

public ArrayList<Card> getPlayerCards(){
    return playerCards;
}

public void addPlayerCards(ArrayList<Card> deck, int amount){
    for ( int i = 0; i< amount; i++){
        playerCards.add(deck.get(0));
        deck.remove(0);
    }
}












}
