import java.util.*; 


public class Game { //Where the main game happens 
    static Scanner reader = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        
        //Creating Players
        int activeIndex = 0;
        ArrayList<Player> playerList = startGame();
        //Order is always 0->1->2->3
    
        //Deck Creation
        Deck game = new Deck();

        game.createDeck();
        game.shuffleDeck();

        //Main Deck of cards to draw from
        ArrayList<Card> deck = game.getDeck(); //deck is the card deck

        //Distribute the deck of cards to players
        distributeCards(playerList, deck);

        //Main Card Pile
        ArrayList<Card> cardPile = new ArrayList<Card>();  //cardPile is the current card pile being played on
        startCard(deck, cardPile); //adds the starting card to the pile
       
        //Display Current Card and User Cards

        System.out.println("Welcome to UNO " + playerList.get(0).getPlayerName() + "!");
        System.out.println("");

        System.out.println("Current Card On Top of the Pile: "); 
        getCurrentCard(cardPile, 0);
        System.out.println(" ");
        System.out.println(" ");
        
        displayUserCards(playerList.get(0).getPlayerCards());

        //Simulate one round


        


     
       
        
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

    //goes to the next player
    public static int nextNormalTurn(ArrayList<Player> playerList, int activeIndex){
        activeIndex++;
        if(activeIndex == playerList.size()){
            activeIndex = 0;
            return activeIndex;
        }
        else{
            return activeIndex++;
        }
    }

    //Function:Prints out ONLY the player's cards when called.
    public static void displayUserCards(ArrayList<Card> playerCards){
       System.out.println("This is your current cards: ");
        if (playerCards.size() == 0 ){
        System.out.println("YOU WON!"); //Might make a function with uno here
       }
       else{
        for(int i = 0; i<playerCards.size(); i++){
            getCurrentCard(playerCards, i);
            System.out.print(" | ");
        }
       }
    }

    //User or Robot wil use this to place a card. Depending on the card, this will call card class methods to affect gameplay
    //Also the precondition is that the userCards passed in is from an active player
    //Precondition is that we know if the player placing is a bot or player
    //We have the card list of the active person
    //We have the card pile currenly in game
    public static void placeCard(boolean isPlayer, ArrayList<Card> userCards, ArrayList<Card> cardPile){
        if (isPlayer == false){ //Robot player
            for(int i = 0; i<userCards.size(); i++){
                if (userCards.get(i).getCardType().equals("Normal")){
                    //we might need global variables to limit the need to check every condition
                    
                }
            }
            //Place the first valid card down. (Not gonna be some AI bot)
        }
    }



    public static void getCurrentCard(ArrayList<Card> cards, int index){
        switch (cards.get(index).getCardType()){
            case "Normal":
                System.out.print(cards.get(index).getColor() + " " + cards.get(index).getCardValue());
                break;
            case "Wild":
                System.out.print(cards.get(index).getCardType());
                break;
            case "Wild +4":
                System.out.print(cards.get(index).getCardType());
                break;
            case "Normal +2":
                System.out.print(cards.get(index).getColor() + " +2");
                break;
            case "Reverse":
                System.out.print(cards.get(index).getColor() + " Reverse");
                break;
            case "Skip":
                System.out.print(cards.get(index).getColor() + " Skip");
                break;
        }
    }


}


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