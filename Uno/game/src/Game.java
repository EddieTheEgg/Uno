import java.util.*; 


public class Game { //Where the main game happens 
    static Scanner reader = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
       
        //Creating Players
        ArrayList<Player> playerList = startGame();
        
        //Deck Creation
        Deck game = new Deck();

        game.createDeck();
        game.shuffleDeck();

        //Main Deck
        ArrayList<Card> deck = game.getDeck(); //deck is the card deck

        //Main Card pile
        ArrayList<Card> cardPile = new ArrayList<Card>();  //cardPile is the current card pile being played on
        startCard(deck, cardPile); //adds the starting card to the pile
       
         //Debug Test
         System.out.println("This is the current card in the pile: "); 
         getCurrentCard(cardPile, 0);


        /*
         * 
         
        //Debugging Checkpoint to check if cards are printin correctly
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
        */
       
        
    }

        
//-----------------FUNCTIONS------------------


    //Precondition: Assuming player does not put in more than 3 CPU players
    public static ArrayList<Player> startGame(){
        System.out.println("How many computer players would you like? Max: 3");
        int cpuCount = reader.nextInt();

        System.out.println("What is your name?");
        String playerName = reader.next();
        
        return createPlayers(cpuCount, playerName);
    }

    //Function: Creates the user first, and then the remaining CPU bots based on user input.
    public static ArrayList<Player> createPlayers(int cpuCount, String playerName){
        ArrayList<Player> playerList = new ArrayList<Player>();
        
        playerList.add(new Player(playerName, true));
        
        for (int i = 1;i < cpuCount+1; i++){
            playerList.add(new Player("Bot" + i, false));
        }
    
        return playerList;
    }

    //Function: Distributes the cards evenly into 7 cards
    public static void distributeCards(ArrayList<Player>playerList, ArrayList<Card>deck){
        for (int i = 0; i<playerList.size(); i++){
            playerList.get(i).setPlayerCards(deck);
            }
    }

    //Function: Starts the game by placing down a valid card
    public static void startCard(ArrayList<Card> deck, ArrayList<Card> cardPile){
        for(int i = 0; i<deck.size(); i++){
            if (deck.get(i).getCardType().equals("Normal")){
                cardPile.add(deck.get(i));
                deck.remove(i);
                break;
            }
        }
    }


    public static void getCurrentCard(ArrayList<Card> cards, int index){
        switch (cards.get(index).getCardType()){
            case "Normal":
                System.out.println(cards.get(index).getColor() + " " + cards.get(index).getCardValue());
                break;
            case "Wild":
                System.out.println(cards.get(index).getCardType());
                break;
            case "Wild +4":
                System.out.println(cards.get(index).getCardType());
                break;
            case "Normal +2":
                System.out.println(cards.get(index).getColor() + " +2");
                break;
            case "Reverse":
                System.out.println(cards.get(index).getColor() + " Reverse");
                break;
            case "Skip":
                System.out.println(cards.get(index).getColor() + " Skip");
                break;
        }
    }


}
