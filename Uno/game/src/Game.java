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

        ArrayList<Player> playerList = startGame();


        //Adding the cards to the players
        for (int i = 0; i<playerList.size(); i++){
            playerList.get(i).setPlayerCards(deck);
            }
        }

        //Need to test if the cards are distirbuted correctly


    //Precondition: Assuming player does not put in more than 3 CPU players
    public static ArrayList<Player> startGame(){
        System.out.println("How many computer players would you like? Max: 3");
        int cpuCount = reader.nextInt();

        System.out.println("What is your name?");
        String playerName = reader.next();
        
        return createPlayers(cpuCount, playerName);
    }

    public static ArrayList<Player> createPlayers(int cpuCount, String playerName){
        ArrayList<Player> playerList = new ArrayList<Player>();
        
        playerList.add(new Player(playerName, true));
        
        for (int i = 1;i < cpuCount+1; i++){
            playerList.add(new Player("Bot" + i, false));
        }
    
        return playerList;
    }

    


}
