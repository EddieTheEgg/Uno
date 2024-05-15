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




     

}
