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

        System.out.println("");
        System.out.println("Welcome to UNO " + playerList.get(0).getPlayerName() + "!");
        System.out.println("");

        System.out.print("Current Card on Top of Pile: "); 
        getCurrentCard(cardPile, 0);
        System.out.println(" ");
        System.out.println(" ");
        
        System.out.println("Current Order of Players:");
        for (int i = 0; i< playerList.size(); i++){
            if(i == playerList.size()-1){
                System.out.print(playerList.get(i).getPlayerName());
            }
            else{
                System.out.print(playerList.get(i).getPlayerName() + ", ");
            }
        }
        System.out.println("");
        
        System.out.println("");
        //Debugging area for simulating one round
        activeIndex = placeCardHuman(playerList.get(activeIndex).getPlayerCards(), deck, cardPile, playerList, possibleColors, activeIndex);

        System.out.println("This is the order of the players currently:");

        for (int i = 0; i< playerList.size(); i++){
            System.out.println(playerList.get(i).getPlayerName());
        }

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
        System.out.println("This is the order of the players currently:");

        for (int i = 0; i< playerList.size(); i++){
            System.out.println(playerList.get(i).getPlayerName());
        }


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

    

    //Function:Prints out ONLY the player's cards when called.
    public static void displayUserCards(ArrayList<Card> playerCards){
       System.out.println("These are your current card(s): ");
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
                return placeCard(userCards, cardPile, playerList, activeIndex, userCards.get(i), 1, response, playerList.get(activeIndex).getPlayerName());
            }
            //Place Wild Card
            else if (input[0].equals(userCards.get(i).getCardType()) && input[1].equals(userCards.get(i).getColor())){
                userCards.get(i).wildCardUser(possibleColors);
                return placeCard(userCards, cardPile, playerList, activeIndex, userCards.get(i), 1, response, playerList.get(activeIndex).getPlayerName());
            }
            //Place Wild +4 Card
            else if (response.equals(userCards.get(i).getCardType() + " " + userCards.get(i).getColor())){
                userCards.get(i).wildCardUser(possibleColors);
                int victimIndex = nextTurn(playerList, activeIndex, 1);
                drawCard(deck, playerList.get(victimIndex).getPlayerCards(), 4, playerList.get(victimIndex).getPlayerName());
                return placeCard(userCards, cardPile, playerList, activeIndex, userCards.get(i), 2, response, playerList.get(activeIndex).getPlayerName());
            }
            //Place +2 Card
            else if (response.equals(userCards.get(i).getColor() + " +2") && isCardValidNonNormal(input, cardPile)){
                int victimIndex = nextTurn(playerList, activeIndex, 1);
                drawCard(deck, playerList.get(victimIndex).getPlayerCards(), 2, playerList.get(victimIndex).getPlayerName());
                return placeCard(userCards, cardPile, playerList, activeIndex, userCards.get(i), 2, response, playerList.get(activeIndex).getPlayerName());
            }
            //Place Skip Card 
            else if (response.equals(userCards.get(i).getColor() + " Skip") && isCardValidNonNormal(input, cardPile)){
                return placeCard(userCards, cardPile, playerList, activeIndex, userCards.get(i), 2, response, playerList.get(activeIndex).getPlayerName());
            }
            //Place Reverse Card
            else if (response.equals(userCards.get(i).getColor() + " Reverse") && isCardValidNonNormal(input, cardPile)){
                reverseAtIndex(playerList, activeIndex);
                return placeCard(userCards, cardPile, playerList, activeIndex, userCards.get(i), 1, response, playerList.get(activeIndex).getPlayerName());
            }
        }
        System.out.println("You might've made a typo or your card is invalid");
        System.out.println("Do you want to draw a card instead? Type Yes or No.");
        String answer = reader.nextLine();
        if (answer.equals("Yes")){
            System.out.println("Okidoki drawing a card for you...");
            drawCard(deck, playerList.get(activeIndex).getPlayerCards(), 1, playerList.get(activeIndex).getPlayerName());
            return nextTurn(playerList, activeIndex, 1);
        }
        else{
            return placeCardHuman(playerList.get(activeIndex).getPlayerCards(), deck, cardPile, playerList, possibleColors, activeIndex);
        }
    }

    //Robot placing card
    public static int placeCardRobot(ArrayList<Card>userCards, ArrayList<Card> deck, ArrayList<Card> cardPile, ArrayList<Player> playerList, String [] possibleColors, int activeIndex){
        Card deckCard = cardPile.get(0);
        String response = "";
        
        for (int i = 0; i<userCards.size(); i++){
            //Place Normal Card
            if (userCards.get(i).getCardType().equals("Normal") && (userCards.get(i).getColor().equals(deckCard.getColor()) || userCards.get(i).getCardValue() == deckCard.getCardValue())){
                response = userCards.get(i).getColor() + " " + userCards.get(i).getCardValue();
                return placeCard(userCards, cardPile, playerList, activeIndex, userCards.get(i), 1, response, playerList.get(activeIndex).getPlayerName());
            }
            //Place Wild Card
            else if (userCards.get(i).getCardType().equals("Wild") && userCards.get(i).getColor().equals("Black")){
                userCards.get(i).wildCardRobot(possibleColors);
                response = userCards.get(i).getCardType() + " " + userCards.get(i).getColor();
                return placeCard(userCards, cardPile, playerList, activeIndex, userCards.get(i), 1, response, playerList.get(activeIndex).getPlayerName());
            }
            //Place Wild +4 Card
            else if (userCards.get(i).getCardType().equals("Wild +4") && userCards.get(i).getColor().equals("Black")){
                userCards.get(i).wildCardRobot(possibleColors);
                int victimIndex = nextTurn(playerList, activeIndex, 1);
                drawCard(deck, playerList.get(victimIndex).getPlayerCards(), 4, playerList.get(victimIndex).getPlayerName());
                response = userCards.get(i).getCardType() + " " + userCards.get(i).getColor();
                return placeCard(userCards, cardPile, playerList, activeIndex, userCards.get(i), 2, response, playerList.get(activeIndex).getPlayerName());
            }
            //Place +2 Card
            else if (userCards.get(i).getCardType().equals("Normal +2") && (userCards.get(i).getCardType().equals(deckCard.getCardType()) || userCards.get(i).getColor().equals(deckCard.getColor()))){
                int victimIndex = nextTurn(playerList, activeIndex, 1);
                drawCard(deck, playerList.get(victimIndex).getPlayerCards(), 2, playerList.get(victimIndex).getPlayerName());
                response = userCards.get(i).getColor() + " +2"; 
                return placeCard(userCards, cardPile, playerList, activeIndex, userCards.get(i), 2, response, playerList.get(activeIndex).getPlayerName());
            }
            //Place Skip Card 
            else if (userCards.get(i).getCardType().equals("Skip") && userCards.get(i).getCardType().equals(deckCard.getCardType()) || userCards.get(i).getColor().equals(deckCard.getColor())){
                response = userCards.get(i).getColor() + " " + userCards.get(i).getCardType(); 
                return placeCard(userCards, cardPile, playerList, activeIndex, userCards.get(i), 2, response, playerList.get(activeIndex).getPlayerName());
            }
            //Place Reverse Card
            else if (userCards.get(i).getCardType().equals("Reverse") && userCards.get(i).getCardType().equals(deckCard.getCardType()) || userCards.get(i).getColor().equals(deckCard.getColor())){
                reverseAtIndex(playerList, activeIndex);
                response = userCards.get(i).getColor() + " " + userCards.get(i).getCardType(); 
                return placeCard(userCards, cardPile, playerList, activeIndex, userCards.get(i), 1, response, playerList.get(activeIndex).getPlayerName());
            }
        }
        drawCard(deck, userCards, 1 , playerList.get(activeIndex).getPlayerName());
        return nextTurn(playerList, activeIndex, 1);
        }
        
        
   
    

    //Functions to check Card Validities
    public static boolean isCardValidNormal(String [] input, ArrayList<Card> cardPile){
        if (input[0].equals(cardPile.get(0).getColor()) || input[1].equals(String.valueOf(cardPile.get(0).getCardValue()))){
            return true;
        }
        return false;
    }

    public static boolean isCardValidNonNormal (String [] input, ArrayList<Card> cardPile){
        if (input[0].equals(cardPile.get(0).getColor()) || input[1].equals(String.valueOf(cardPile.get(0).getCardType()))){
            return true;
        }
        return false;
    }
   
    public static void reverseAtIndex(ArrayList<Player> playerList, int activeIndex) {
       ArrayList<Player> tempList = new ArrayList<>();
       for (int i = activeIndex; i>=0; i--){
           tempList.add(playerList.get(i));
       }
       if(tempList.size() != playerList.size()){
        for (int j = playerList.size() - 1; j > activeIndex; j--){
            tempList.add(playerList.get(j));
        }
       }
       for (int k = 0; k < tempList.size(); k++) {
        playerList.set(k, tempList.get(k));
    }
    }

    public static int placeCard(ArrayList<Card> userCards, ArrayList<Card> cardPile, ArrayList<Player> playerList, int activeIndex, Card card, int turnIncrement, String response, String userName) {
        System.out.println(" ");
        System.out.println(userName + " placed down: " + response);
        cardPile.add(0, card);
        userCards.remove(card);
        activeIndex = nextTurn(playerList, activeIndex, turnIncrement);
        return activeIndex;
}

    public static void drawCard(ArrayList<Card> deck, ArrayList<Card> userCards, int drawAmount, String userName){
        for(int i = 0 ; i<drawAmount; i++){
            userCards.add(deck.get(0));
            deck.remove(0);
        }
        System.out.println(userName + " has drawn " + drawAmount + "cards");
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