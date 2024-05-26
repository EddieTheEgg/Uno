import java.util.*; 


public class Game { //Where the main game happens 
    static Scanner reader = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        
        //Creating Players
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

        System.out.println("");
        System.out.println("Welcome to UNO " + playerList.get(0).getPlayerName() + "!");
        System.out.println("");

        simulateGame(deck, cardPile, playerList, possibleColors);
        
        /*
         * getPlayerOrder(playerList);

        getPlayerCardCount(playerList);

        System.out.println("");
        
        System.out.println("");
        //Debugging area for simulating one round
        activeIndex = placeCardHuman(playerList.get(activeIndex).getPlayerCards(), deck, cardPile, playerList, possibleColors, activeIndex);

        getPlayerOrder(playerList);

        //Displays human cards after placement or draw
        displayUserCards(playerList.get(0).getPlayerCards());
       
        //Displays current card in pile after human placement
        System.out.println(""); 
        System.out.println("Current Card in Pile: ");
        getCurrentCard(cardPile, 0);

        //Displays current player (The Bot)
        System.out.println("");
        System.out.println("Get New Current Player:" + playerList.get(activeIndex).getPlayerName());
        displayUserCards(playerList.get(activeIndex).getPlayerCards());
       
        //Robot placing card down
        activeIndex = placeCardRobot(playerList.get(activeIndex).getPlayerCards(),deck, cardPile, playerList, possibleColors, activeIndex );

        System.out.println(""); 
        System.out.println("Current Card in Pile: ");
        getCurrentCard(cardPile, 0);

        //Displays current player
        System.out.println("");
        System.out.println("Get New Current Player:" + playerList.get(activeIndex).getPlayerName());

        
        //Bot1 Info
        getPlayerOrder(playerList);
         */
        


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
            activeIndex = activeIndex - playerList.size();
            return activeIndex;
        }
        else{
            return activeIndex;
        }
    }

    //Simualtes the game
    public static void simulateGame(ArrayList<Card> deck, ArrayList<Card> cardPile, ArrayList<Player> playerList, String [] possibleColors){
        int activeIndex = 0;
        int previousActiveIndex = 0;
        int roundCount = 1;
        while (playerList.get(previousActiveIndex).getPlayerCards().size()!= 0){
            System.out.println("\n\n\n\n\n\n");
            System.out.println("-------------------------------------------------------------------------------------------- (Round " + roundCount + ") ");
            System.out.println("------ Information about Game Stats: ------");
           
            getPlayerOrder(playerList);
            System.out.println("");

            getPlayerCardCount(playerList);
            System.out.println("\n\n");
           

            System.out.println("------ Information about Current Round: ------");

            System.out.println("CURRENT PLAYER: " + playerList.get(activeIndex).getPlayerName());

            System.out.print("CURRENT CARD IN PILE: "); 
            getCurrentCard(cardPile, 0);
            System.out.println(" ");
            System.out.println(" ");

            System.out.println("Press enter to continue");
            reader.nextLine();

            if (playerList.get(activeIndex).isPlayer() == true){
                previousActiveIndex = activeIndex;
                System.out.println("----- " + playerList.get(activeIndex).getPlayerName() + " Cards -----");
                activeIndex = placeCardHuman(playerList.get(activeIndex).getPlayerCards(), deck, cardPile, playerList, possibleColors, activeIndex);
                System.out.println("Press enter to continue");
                reader.nextLine();
                System.out.println("");
                roundCount ++;
            }
            else{
                previousActiveIndex = activeIndex;
                activeIndex = placeCardRobot(playerList.get(activeIndex).getPlayerCards(),deck, cardPile, playerList, possibleColors, activeIndex);
                System.out.print("Press enter to continue");
                reader.nextLine();
                roundCount ++;
            }
        }
        System.out.println("WE HAVE A WINNER!!!!  :D");
        System.out.println("Congrats " + playerList.get(previousActiveIndex).getPlayerName());
        
    }


    public static void getPlayerOrder(ArrayList<Player> playerList){
        System.out.println("Current Order of Players:");
        for (int i = 0; i< playerList.size(); i++){
            if(i == playerList.size()-1){
                System.out.println(playerList.get(i).getPlayerName());
            }
            else{
                System.out.print(playerList.get(i).getPlayerName() + " -> ");
            }
        }
    }
    //Function:Prints out ONLY the player's cards when called.
    public static void displayUserCards(ArrayList<Card> playerCards){
       System.out.println("These are your current card(s): ");
        if (playerCards.size() == 0 ){
        System.out.println("YOU WON!"); //Might make a function with uno here
       }
       else{
        for(int i = 0; i<playerCards.size(); i++){
            if(i == playerCards.size()-1){
                getCurrentCard(playerCards, i);
            }
            else{
                getCurrentCard(playerCards, i);
                System.out.print(" | ");
            }
        }
       }
    }

    public static void getPlayerCardCount(ArrayList<Player> playerList){
        System.out.println("Card Amount for Each Player Currently:");
        for (int i = 0; i<playerList.size(); i++){
            if(i == playerList.size()-1){
                System.out.print(playerList.get(i).getPlayerName() + " (" + playerList.get(i).getPlayerCards().size() + " cards) ");
            }
            else{
                System.out.print(playerList.get(i).getPlayerName() + " (" + playerList.get(i).getPlayerCards().size() + " cards), ");
            }
            
        }
    }

    //User or Robot wil use this to place a card. Depending on the card, this will call card class methods to affect gameplay
    //Also the precondition is that the userCards passed in is from an active player
    //Precondition is that we know if the player placing is a bot or player
    //We have the card list of the active person
    //We have the card pile currenly in game

    //precondition: we have the user cards, and this is a human.
    public static int placeCardHuman(ArrayList<Card> userCards, ArrayList<Card> deck, ArrayList<Card> cardPile, ArrayList<Player> playerList, String[] possibleColors, int activeIndex) {
        displayUserCards(userCards);
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Write the card (exact name) that you want to use! Type `Draw` to draw a card.");
        String response = reader.nextLine();
        String[] input = response.split(" ");
        System.out.println("");

        if (response.equals("Draw")){
            System.out.println("Okidoki drawing a card for you...");
            drawCard(deck, playerList.get(activeIndex).getPlayerCards(), 1, playerList.get(activeIndex).getPlayerName());
            return nextTurn(playerList, activeIndex, 1);
        }
        else if (input.length < 2){
            System.out.println("Your input needs to have two types. Ex.) Red 0, or Wild Black");
            return placeCardHuman(playerList.get(activeIndex).getPlayerCards(), deck, cardPile, playerList, possibleColors, activeIndex);
        }
    
        //Checks User Card choice Validity
        for (int i = 0; i < userCards.size(); i++) {
            Card card = userCards.get(i);
            
            // Place Normal Card
            if (card.getCardType().equals("Normal") && input[0].equals(card.getColor()) && input[1].equals(String.valueOf(card.getCardValue())) && isCardValidNormal(input, cardPile)) {
                return placeCard(userCards, cardPile, playerList, activeIndex, card, 1, response, playerList.get(activeIndex).getPlayerName());
            }
            // Place Wild Card
            else if (card.getCardType().equals("Wild") && input[0].equals(card.getCardType()) && input[1].equals(card.getColor())) {
                card.wildCardUser(possibleColors);
                response = "Wild " + card.getColor();
                return placeCard(userCards, cardPile, playerList, activeIndex, card, 1, response, playerList.get(activeIndex).getPlayerName());
            }
            // Place Wild +4 Card
            else if (card.getCardType().equals("Wild +4") && response.contains("Wild +4")) {
                card.wildCardUser(possibleColors);
                int victimIndex = nextTurn(playerList, activeIndex, 1);
                int newActiveIndex = placeCard(userCards, cardPile, playerList, activeIndex, card, 2, response, playerList.get(activeIndex).getPlayerName());
                drawCard(deck, playerList.get(victimIndex).getPlayerCards(), 4, playerList.get(victimIndex).getPlayerName());
                return newActiveIndex;
            }
            // Place +2 Card
            else if (card.getCardType().equals("Normal +2") && (response.equals(userCards.get(i).getColor() + " +2")) && isCardValidNonNormal(input,  cardPile)){
                int victimIndex = nextTurn(playerList, activeIndex, 1);
                int newActiveIndex = placeCard(userCards, cardPile, playerList, activeIndex, card, 2, response, playerList.get(activeIndex).getPlayerName());
                drawCard(deck, playerList.get(victimIndex).getPlayerCards(), 2, playerList.get(victimIndex).getPlayerName());
                return newActiveIndex;
            }
            // Place Skip Card
            else if (card.getCardType().equals("Skip") && input[0].equals(card.getColor()) && input[1].equals("Skip") && isCardValidNonNormal(input, cardPile)) {
                int victimIndex = nextTurn(playerList, activeIndex, 1);
                int newActiveIndex = placeCard(userCards, cardPile, playerList, activeIndex, card, 2, response, playerList.get(activeIndex).getPlayerName());
                System.out.println("O NAH, " + playerList.get(activeIndex).getPlayerName() + " skipped " + playerList.get(victimIndex).getPlayerName() + "!");
                return newActiveIndex;
            }
            // Place Reverse Card
            else if (card.getCardType().equals("Reverse") && input[0].equals(card.getColor()) && input[1].equals("Reverse") && isCardValidNonNormal(input, cardPile)) {
                String oldActiveIndex = playerList.get(activeIndex).getPlayerName();
                activeIndex = reverseAtIndex(playerList, activeIndex);
                return placeCard(userCards, cardPile, playerList, activeIndex, card, 1, response, oldActiveIndex);
            }
        }
        
        System.out.println("You might've made a typo or your card is invalid");
        System.out.println("Do you want to draw a card instead? Type Yes or No.");
        String answer = reader.nextLine();
        if (answer.equals("Yes")) {
            System.out.println("");
            System.out.println("Okidoki drawing a card for you...");
            drawCard(deck, playerList.get(activeIndex).getPlayerCards(), 1, playerList.get(activeIndex).getPlayerName());
            return nextTurn(playerList, activeIndex, 1);
        } else {
            return placeCardHuman(playerList.get(activeIndex).getPlayerCards(), deck, cardPile, playerList, possibleColors, activeIndex);
        }
    }

    //Robot placing card
    public static int placeCardRobot(ArrayList<Card> userCards, ArrayList<Card> deck, ArrayList<Card> cardPile, ArrayList<Player> playerList, String[] possibleColors, int activeIndex) {
        Card deckCard = cardPile.get(0); // Current card in the pile
        String response = "";

        for (int i = 0; i < userCards.size(); i++) {
            Card card = userCards.get(i);
    
            if (card.getCardType().equals("Normal") && (card.getColor().equals(deckCard.getColor()) || card.getCardValue() == deckCard.getCardValue())) {
                response = card.getColor() + " " + card.getCardValue();
                return placeCard(userCards, cardPile, playerList, activeIndex, card, 1, response, playerList.get(activeIndex).getPlayerName());
            } else if (card.getCardType().equals("Wild") && card.getColor().equals("Black")) {
                card.wildCardRobot(possibleColors);
                response = card.getCardType() + " " + card.getColor();
                return placeCard(userCards, cardPile, playerList, activeIndex, card, 1, response, playerList.get(activeIndex).getPlayerName());
            } else if (card.getCardType().equals("Wild +4") && card.getColor().equals("Black")) {
                card.wildCardRobot(possibleColors);
                response = card.getCardType() + " " + card.getColor();
                int victimIndex = nextTurn(playerList, activeIndex, 1);
                int newActiveIndex = placeCard(userCards, cardPile, playerList, activeIndex, card, 2, response, playerList.get(activeIndex).getPlayerName());
                drawCard(deck, playerList.get(victimIndex).getPlayerCards(), 4, playerList.get(victimIndex).getPlayerName());
                return newActiveIndex;
            } else if (card.getCardType().equals("Normal +2") && (card.getCardType().equals(deckCard.getCardType()) || card.getColor().equals(deckCard.getColor()))){
                response = card.getColor() + " +2";
                int victimIndex = nextTurn(playerList, activeIndex, 1);
                int newActiveIndex = placeCard(userCards, cardPile, playerList, activeIndex, card, 2, response, playerList.get(activeIndex).getPlayerName());
                drawCard(deck, playerList.get(victimIndex).getPlayerCards(), 2, playerList.get(victimIndex).getPlayerName());
                return newActiveIndex;
            } else if (card.getCardType().equals("Skip") && (card.getCardType().equals(deckCard.getCardType()) || card.getColor().equals(deckCard.getColor()))) {
                response = card.getColor() + " " + card.getCardType();
                int victimIndex = nextTurn(playerList, activeIndex, 1);
                int newActiveIndex = placeCard(userCards, cardPile, playerList, activeIndex, card, 2, response, playerList.get(activeIndex).getPlayerName());
                System.out.println("O NAH, " + playerList.get(activeIndex).getPlayerName() + " skipped " + playerList.get(victimIndex).getPlayerName() + "!");
                return newActiveIndex;
            } else if (card.getCardType().equals("Reverse") && (card.getCardType().equals(deckCard.getCardType()) || card.getColor().equals(deckCard.getColor()))) {
                String oldActiveIndex = playerList.get(activeIndex).getPlayerName();
                int newActiveIndex = reverseAtIndex(playerList, activeIndex);
                response = card.getColor() + " " + card.getCardType();
                return placeCard(userCards, cardPile, playerList, newActiveIndex, card, 1, response, oldActiveIndex);
            }
        }
    
        drawCard(deck, userCards, 1, playerList.get(activeIndex).getPlayerName());
        return nextTurn(playerList, activeIndex, 1);
    }
        
        
   
    

    //Functions to check Card Validities
    public static boolean isCardValidNormal(String[] input, ArrayList<Card> cardPile) {
        Card topCard = cardPile.get(0);
        // Check if color matches or value matches
        if (input[0].equals(topCard.getColor()) || input[1].equals(String.valueOf(topCard.getCardValue()))) {
            return true;
        }
        return false;
    }

    public static boolean isCardValidNormalForRobot(Card robotCard, ArrayList<Card> cardPile) {
        Card topCard = cardPile.get(0);
    
        // If the top card is a Wild, Wild +4, Reverse, card, only match the color.
        if (topCard.getCardType().contains("Wild") || topCard.getCardType().contains("Wild +4") || topCard.getCardType().contains("Reverse")) {
            return robotCard.getColor().equals(topCard.getColor());
        }
        // For other cases, match color or value.
        return robotCard.getColor().equals(topCard.getColor()) || robotCard.getCardValue() == topCard.getCardValue();
    }


    public static boolean isCardValidNonNormal (String [] input, ArrayList<Card> cardPile){
        if (input[0].equals(cardPile.get(0).getColor()) || input[1].equals(String.valueOf(cardPile.get(0).getCardType()))){
            return true;
        }
        return false;
    }
   
    public static int reverseAtIndex(ArrayList<Player> playerList, int activeIndex) {
        // Calculate the new active index before reversing
        int newActiveIndex = playerList.size() - 1 - activeIndex; //Eddie, Bot1, Bot2, Bot3 //Bot3, Bot2, Bot1, Eddie
        
        // Reverse the player list
        Collections.reverse(playerList);
        
        // Return the new active index
        return newActiveIndex;
    }

    public static int placeCard(ArrayList<Card> userCards, ArrayList<Card> cardPile, ArrayList<Player> playerList, int activeIndex, Card card, int turnIncrement, String response, String userName) {
    
        System.out.println("--- Placing Card... ---");
        System.out.println(userName + " placed down: " + response);
        cardPile.add(0, card);
        userCards.remove(card);
        activeIndex = nextTurn(playerList, activeIndex, turnIncrement);
        return activeIndex;
}

    public static void drawCard(ArrayList<Card> deck, ArrayList<Card> userCards, int drawAmount, String userName){
        for(int i = 0 ; i<drawAmount; i++){
            if(deck.isEmpty()) {
                System.out.println("The deck is empty. Creating a new deck...");
                Deck newDeck = new Deck();  
                newDeck.createDeck();       
                newDeck.shuffleDeck();      
                deck.addAll(newDeck.getDeck());   //new method feature built in ArrayList
            }
            userCards.add(deck.get(0));
            deck.remove(0);
        }
        System.out.println("Dang, " + userName + " has drawn " + drawAmount + " card(s)");
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