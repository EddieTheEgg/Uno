import java.util.*; 


public class Game { //Where the main game happens 
    static Scanner reader = new Scanner(System.in);
    public static void main(String[] args) throws Exception {

        Deck game = new Deck();

        game.createDeck();
        game.shuffleDeck();

        ArrayList<Card> deck = game.getDeck();


        //Debugging Checkpoint
        for (int i = 0; i< deck.size(); i++){
            switch (deck.get(i).getCardType()){
                case "Normal":
                    System.out.println(deck.get(i).getColor() + " " + deck.get(i).getCardValue());
                    break;
                case "Wild":
                    System.out.println(deck.get(i).getCardType());
                    break;
                case "Wild +4":
                    System.out.println(deck.get(i).getCardType());
                    break;
                case "Normal +2":
                    System.out.println(deck.get(i).getColor() + " +2");
                    break;
                case "Reverse":
                    System.out.println(deck.get(i).getColor() + " Reverse");
                    break;
                case "Skip":
                    System.out.println(deck.get(i).getColor() + " Skip");
                    break;
            }
        }
    
    }

    public static void startGame(){
    //Precondition: Assuming player does not put in more than 3 CPU players
    System.out.println("How many computer players would you like? Max: 3");
    int cpuCount = reader.nextInt();

    }


    public static void createPlayers(){
        
    }
}
