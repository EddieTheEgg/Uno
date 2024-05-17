import java.util.ArrayList;

public class Card {
    
    private String type;
    private String color;
    private int cardValue;


    //whole lotta constructors, whole lotta red

    //Normal Card
    public Card(String normal, String color, int cardValue){
        type = normal;
        this.color = color;
        this.cardValue = cardValue;
    }

    //Normal +2 Card
    public Card (String plusTwo, String color, boolean unique){
        type = plusTwo;
        this.color = color;
    }

    //Reverse Card
    public Card (String reverse, String color, String unique){ //as long as parameters are different, the card is called
        type = reverse;
        this.color = color;
    }

    //Skip Card
    public Card (String skip, String color, char unique){
        type = skip;
        this.color = color;
    }

    //Wild Card
    public Card (String wild, String unique){
        type = wild;
        color = "black";
    }

    //Wild +4 Card
    public Card (String plusFour){
        type = plusFour;
        color = "black";
    }



    //Get Methods

    public String getCardType(){
        return type;
    }

    public String getColor(){
        return color;
    }

    public int getCardValue(){
        return cardValue;
    }

    //Set Methods

    

    //Card Effects

    //Normal Card when called moves to next person.
    


    //This is for Wild and +4 cards
    public void setCardColor(String userColorChoice){
        color = userColorChoice;
    }
   
    public void setRobotCardColor(String robotColorChoice){
        color = robotColorChoice;
    }

    //Special Card Effects

    public void wildCardRobot (String [] possibleColors){
        int colorRandom = (int)(Math.random() * 4);
        setRobotCardColor(possibleColors[colorRandom]);
    }

    public void wildCardUser (String [] possibleColors){
        
    }
    

    

    

     

}
