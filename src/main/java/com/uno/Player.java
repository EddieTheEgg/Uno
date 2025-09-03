package com.uno;

import java.util.*;

public class Player {  //Properties and cards in each player
    
    private String playerName;
    private boolean isPlayer;
    private ArrayList<Card> playerCards;
    
    
//Player Constructor
public Player (String name, Boolean active){
    playerName = name;
    isPlayer= active;
    playerCards = new ArrayList<Card>();
}

//Methods
public String getPlayerName(){
    return playerName;
}

public Boolean isPlayer(){
    return isPlayer;
}

public ArrayList<Card> getPlayerCards(){
    return playerCards;
}

//Distribute Cards
public void setPlayerCards(ArrayList<Card> deck){
    while(playerCards.size() != 7){
        playerCards.add(deck.get(0));
        deck.remove(0);
    }
}


}
