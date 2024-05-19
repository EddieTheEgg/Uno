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
        String [] possibleColors = game.getColors();

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
        
        activeIndex = placeCardHuman(playerList.get(activeIndex).getPlayerCards(), deck, cardPile, playerList, possibleColors, activeIndex);

        displayUserCards(playerList.get(0).getPlayerCards());
       

        System.out.println("");
        System.out.println("Current Card in Pile: ");

        getCurrentCard(cardPile, 0);

        //Simulate one round

        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Testing...");
        displayUserCards(playerList.get(1).getPlayerCards());

        


     
       
        
    }

        
//-----------------FUNCTIONS------------------


    //Precondition: Assuming player does not put in more than 3 CPU players
    public static ArrayList<Player> startGame(){
        System.out.println("How many computer players would you like? Max: 3");
        int cpuCount = reader.nextInt();
        reader.nextLine(); //This consumes leftover newline from nextInt. 
        //When we enter a number for nextInt, the "enter" is left as a leftover buffer.
        //nextLine will terminate this enter buffer by reading it as an empty string.

        System.out.println("What is your name?");
        String playerName = reader.nextLine();
        
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

    public static void addCard(ArrayList<Card> cardPile, Card card){
        cardPile.add(0, card);
    }

    //goes to the next player
    public static int nextTurn(ArrayList<Player> playerList, int activeIndex, int incrementAmount){
        activeIndex+= incrementAmount;
        if(activeIndex >= playerList.size()){
            activeIndex = 0;
            return activeIndex;
        }
        else{
            return activeIndex;
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

    //precondition: we have the user cards, and this is a human.
    public static int placeCardHuman (ArrayList<Card>userCards, ArrayList<Card> deck, ArrayList<Card> cardPile, ArrayList<Player> playerList, String [] possibleColors, int activeIndex){
        displayUserCards(userCards);
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("What card would you like to place down? Make sure your choice matches the card exact name!");
        String response = reader.nextLine();
        String [] input = response.split(" ");

    
        //Checks User Card choice Validity
        for(int i = 0; i< userCards.size(); i++){
            //Place Normal Card
            if (input[0].equals(userCards.get(i).getColor()) && input[1].equals(String.valueOf(userCards.get(i).getCardValue())) && isCardValidNormal(input, cardPile)){
                return placeCard(userCards, cardPile, playerList, activeIndex, userCards.get(i), 1, response);
            }
            //Place Wild Card
            else if (input[0].equals(userCards.get(i).getCardType()) && input[1].equals(userCards.get(i).getColor())){
                userCards.get(i).wildCardUser(possibleColors);
                return placeCard(userCards, cardPile, playerList, activeIndex, userCards.get(i), 1, response);
            }
            //Place Wild +4 Card
            else if (response.equals(userCards.get(i).getCardType() + " " + userCards.get(i).getColor())){
                userCards.get(i).wildCardUser(possibleColors);
                int victimIndex = nextTurn(playerList, activeIndex, 1);
                drawCard(deck, playerList.get(victimIndex).getPlayerCards(), 4);
                return placeCard(userCards, cardPile, playerList, activeIndex, userCards.get(i), 2, response);
            }
            //Place +2 Card
            else if (response.equals(userCards.get(i).getColor() + " +2") && isCardValidPlusTwo(input, cardPile)){
                int victimIndex = nextTurn(playerList, activeIndex, 1);
                drawCard(deck, playerList.get(victimIndex).getPlayerCards(), 2);
                return placeCard(userCards, cardPile, playerList, activeIndex, userCards.get(i), 2, response);
            }
            //Place Skip Card 
    

            //Place Reverse Card

        }
        return activeIndex;

    }

    //A function that will return true if card can be placed on the current cardpile or false if not.
    public static boolean isCardValidNormal(String [] input, ArrayList<Card> cardPile){
        if (input[0].equals(cardPile.get(0).getColor()) || input[1].equals(String.valueOf(cardPile.get(0).getCardValue()))){
            return true;
        }
        return false;
    }

    public static boolean isCardValidPlusTwo (String [] input, ArrayList<Card> cardPile){
        if (input[0].equals(cardPile.get(0).getColor()) || input[1].equals(String.valueOf(cardPile.get(0).getCardValue()))){
            return true;
        }
        return false;
    }
   
    public static int placeCard(ArrayList<Card> userCards, ArrayList<Card> cardPile, ArrayList<Player> playerList, int activeIndex, Card card, int turnIncrement, String response) {
        System.out.println(" ");
        System.out.println("You have successfully placed down: " + response);
        cardPile.add(0, card);
        userCards.remove(card);
        activeIndex = nextTurn(playerList, activeIndex, turnIncrement);
        return activeIndex;
}

    public static void drawCard(ArrayList<Card> deck, ArrayList<Card> userCards, int drawAmount){
        for(int i = 0 ; i<drawAmount; i++){
            userCards.add(deck.get(0));
            deck.remove(0);
        }
    }

    public static void placeCardRobot( ArrayList<Card> userCards, ArrayList<Card> cardPile, String [] possibleColors, ArrayList<Player> playerList, int activeIndex){
    int currentCardValue = cardPile.get(0).getCardValue();
    String currentCardColor = cardPile.get(0).getColor();
    String currentCardType = cardPile.get(0).getCardType();
    
        for(int i = 0; i<userCards.size(); i++){
                if (userCards.get(i).getCardType().equals("Wild")){
                    userCards.get(i).wildCardRobot(possibleColors); //sets wild card to a random color
                    addCard(cardPile, userCards.get(i));  
                    nextTurn(playerList, activeIndex, 1);
                    break;
                }
                else if (userCards.get(i).getCardType().equals("Wild +4")){
                    int colorRandom = (int)(Math.random() * 4);
                    userCards.get(i).setRobotCardColor(possibleColors[colorRandom]);
                }
            }
            //Place the first valid card down. (Not gonna be some AI bot)
        }



    public static void getCurrentCard(ArrayList<Card> cards, int index){
        switch (cards.get(index).getCardType()){
            case "Normal":
                System.out.print(cards.get(index).getColor() + " " + cards.get(index).getCardValue());
                break;
            case "Wild":
                System.out.print(cards.get(index).getCardType() + " " +  cards.get(index).getColor());
                break;
            case "Wild +4":
                System.out.print(cards.get(index).getCardType() +  " " + cards.get(index).getColor());
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