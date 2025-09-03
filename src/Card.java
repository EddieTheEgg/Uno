import java.util.*;

public class Card {
    Scanner reader = new Scanner(System.in);
    private String type;
    private String color;
    private int cardValue;

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
        cardValue = 50;
    }

    //Reverse Card
    public Card (String reverse, String color, String unique){ //as long as parameters are different, the card targeted is constructed
        type = reverse;
        this.color = color;
        cardValue = 20;
    }

    //Skip Card
    public Card (String skip, String color, char unique){
        type = skip;
        this.color = color;
        cardValue = 60;
    }

    //Wild Card
    public Card (String wild, String unique){
        type = wild;
        color = "Black";
        cardValue = 80;
    }

    //Wild +4 Card
    public Card (String plusFour){
        type = plusFour;
        color = "Black";
        cardValue = 90;
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


    //Special Card Effects

    public void wildCardRobot (String [] possibleColors){
        int colorRandom = (int)(Math.random() * 4);
        color = possibleColors[colorRandom];
    }

    //Sets the color of the card from Black to user choice
    public void wildCardUser (String[] possibleColors){
        // Assuming the user inputs the correct color. Maybe do a check if statement if response valid later.
        System.out.println("What color would you like to choose? Red, Yellow, Green, or Blue");
        String colorResponse = reader.nextLine().trim(); // Trim the input to remove leading/trailing whitespace
        boolean isValidColor = false;
        
        for (String possibleColor : possibleColors) {
            if (colorResponse.equalsIgnoreCase(possibleColor)) {
                color = possibleColor; // Case insensitive comparison
                isValidColor = true;
                break;
            }
        }
        
        if (!isValidColor) { //or isValidColor = false
            System.out.println("That is not a color in UNO or you made a typo! Please try again.");
            wildCardUser(possibleColors); // Recursively call the method again if the input is invalid
        }
    }

    

    

    

     

}
