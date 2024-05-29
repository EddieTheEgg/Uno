import java.util.*; 
public class Game { //Where the main game happens 
    static Scanner reader = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        
        //Creating Players
        ArrayList<Player> playerList = startGame();
    
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
       
        //Start Game
        System.out.println("");
        System.out.println("🃏 Welcome to UNO " + playerList.get(0).getPlayerName() + "!🃏");
        System.out.println("");

        simulateGame(deck, cardPile, playerList, possibleColors);
    }














    
        
//-----------------FUNCTIONS------------------

//----------FUNCTION TO SIMULATE THE GAME AUTOMATICALLY-----------
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
            System.out.println("\n");
        
            System.out.println("------ Information about Current Round: ------");
            System.out.println("⭐ CURRENT PLAYER: " + playerList.get(activeIndex).getPlayerName());
            System.out.print("⭕ CURRENT CARD IN PILE: "); 
            getCurrentCard(cardPile, 0);
            System.out.println("\n");

            System.out.println("Press enter to continue");
            reader.nextLine();

            if (playerList.get(activeIndex).isPlayer() == true){
                previousActiveIndex = activeIndex;
                System.out.println("----- " + playerList.get(activeIndex).getPlayerName() + " Cards -----");
                activeIndex = placeCardHuman(playerList.get(activeIndex).getPlayerCards(), deck, cardPile, playerList, possibleColors, activeIndex, false);
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
        System.out.println("WE HAVE A WINNER!!!! :D");
        System.out.println("🎉 Congrats " + playerList.get(previousActiveIndex).getPlayerName() + " ! 🎉");
        
    }













//---------FUNCTIONS TO SETUP THE GAME----------

    //Function: Start the game by creating the amount of computer players and the player based on user input
    public static ArrayList<Player> startGame(){
        int cpuCount = 0;
            System.out.println("How many computer players would you like? (Integers only and above 0)");
            try { //attempts to put whatever user inputs is in here. If errors InputMismatchException, catch block takes the input and wipes it
                cpuCount = reader.nextInt();
                reader.nextLine(); // Consume leftover newline
                if (cpuCount < 1) {
                    System.out.println("Invalid number. Please enter an integer above 0.");
                    return startGame();
                }
            } 
            catch (InputMismatchException e) { //e is an object containing error information. This is the same as saying if the error is type InputMismatchEzception, execute this code.
                System.out.println("Invalid input. Please enter a valid integer above 0.");
                reader.next(); // Clears the invalid input
                return startGame();
            }
        System.out.println("What is your name?");
        String playerName = reader.nextLine();
        return createPlayers(cpuCount, playerName);
    }

    //Function: Creates the user first, and then the remaining CPU bots based on user input.
    public static ArrayList<Player> createPlayers(int cpuCount, String playerName){
        ArrayList<Player> playerList = new ArrayList<Player>(); //creates playerList
       
        playerList.add(new Player(playerName, true)); //adds in human user
        
        for (int i = 1;i < cpuCount+1; i++){ //adds in the bots
            playerList.add(new Player("Bot" + i, false));
        }
        return playerList;
    }

    //Function: Distributes 7 cards evenly to players in the game.
    public static void distributeCards(ArrayList<Player>playerList, ArrayList<Card>deck){
        for (int i = 0; i<playerList.size(); i++){
            playerList.get(i).setPlayerCards(deck);
            }
    }

    //Function: Places down the first card of the game into the card pile.
    public static void startCard(ArrayList<Card> deck, ArrayList<Card> cardPile){
        for(int i = 0; i<deck.size(); i++){
            if (deck.get(i).getCardType().equals("Normal")){
                cardPile.add(deck.get(i));
                deck.remove(i);
                break;
            }
        }
    }














//---------FUNCTIONS TO GET GAME INFORMATION----------
    //Function: Prints order of players with arrows.
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

    //Function: Prints out each player's current amount of cards
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
    
    //Function: Prints out current card given index of the user cards.
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













//------------FUNCTIONS TO MODIFY CARDS--------------
    
    //Function: If active player is human, get user input on a card or draw.
    public static int placeCardHuman(ArrayList<Card> userCards, ArrayList<Card> deck, ArrayList<Card> cardPile, ArrayList<Player> playerList, String[] possibleColors, int activeIndex, boolean saidUno) {
        //Display Cards
        displayUserCards(userCards);
        
        //Get user input
        System.out.println("\n");
        System.out.println("Type the card (exact name) that you want to use! Type `Draw` to draw a card. Type `Uno` to activate Uno.");
        String response = reader.nextLine();
        String[] input = response.split(" ");
        System.out.println("");

        //Checking useInputs
        if (response.equals("Uno") && userCards.size() == 2){
            System.out.println(playerList.get(activeIndex).getPlayerName() + " ACTIVATED UNO!!");
            saidUno = true;
            System.out.println("Write the card (exact name) that you want to use! Type `Draw` to draw a card. Type `Uno` to activate Uno.");
            response = reader.nextLine();
            input = response.split(" ");
            System.out.println("");
        }
        else if (response.equals("Uno") && userCards.size() > 2){
            System.out.println("Uhm, you have more than 2 cards and said Uno. You have to draw 2 cards now :(");
            drawCard(deck, playerList.get(activeIndex).getPlayerCards(), 2, playerList.get(activeIndex).getPlayerName());
            return nextTurn(playerList, activeIndex, 1);
        }
        if (response.equals("Draw")){
            System.out.println("Okidoki drawing a card for you...");
            drawCard(deck, playerList.get(activeIndex).getPlayerCards(), 1, playerList.get(activeIndex).getPlayerName());
            return nextTurn(playerList, activeIndex, 1);
        }
        else if (input.length < 2){
            System.out.println("Your input should have two parts or you made a typo. Ex.) Green 0, Wild Black");
            return placeCardHuman(playerList.get(activeIndex).getPlayerCards(), deck, cardPile, playerList, possibleColors, activeIndex, false);
        }

        //Checks User Card choice Validity
        for (int i = 0; i < userCards.size(); i++) {
            Card card = userCards.get(i);
            
            // Place Normal Card
            if (card.getCardType().equals("Normal") && input[0].equals(card.getColor()) && input[1].equals(String.valueOf(card.getCardValue())) && isCardValidNormal(input, cardPile)) {
                return placeCard(userCards, cardPile, playerList, activeIndex, card, 1, response, playerList.get(activeIndex).getPlayerName(), saidUno, deck);
            }
            // Place Wild Card
            else if (card.getCardType().equals("Wild") && input[0].equals(card.getCardType()) && input[1].equals(card.getColor())) {
                card.wildCardUser(possibleColors);
                response = "Wild " + card.getColor();
                return placeCard(userCards, cardPile, playerList, activeIndex, card, 1, response, playerList.get(activeIndex).getPlayerName(), saidUno, deck);
            }
            // Place Wild +4 Card
            else if (card.getCardType().equals("Wild +4") && response.contains("Wild +4")) {
                card.wildCardUser(possibleColors);
                int victimIndex = nextTurn(playerList, activeIndex, 1);
                int newActiveIndex = placeCard(userCards, cardPile, playerList, activeIndex, card, 2, response, playerList.get(activeIndex).getPlayerName(), saidUno, deck);
                drawCard(deck, playerList.get(victimIndex).getPlayerCards(), 4, playerList.get(victimIndex).getPlayerName());
                return newActiveIndex;
            }
            // Place +2 Card
            else if (card.getCardType().equals("Normal +2") && (response.equals(userCards.get(i).getColor() + " +2")) && isCardValidNonNormal(input, cardPile)){
                int victimIndex = nextTurn(playerList, activeIndex, 1);
                int newActiveIndex = placeCard(userCards, cardPile, playerList, activeIndex, card, 2, response, playerList.get(activeIndex).getPlayerName(), saidUno, deck);
                drawCard(deck, playerList.get(victimIndex).getPlayerCards(), 2, playerList.get(victimIndex).getPlayerName());
                return newActiveIndex;
            }
            // Place Skip Card
            else if (card.getCardType().equals("Skip") && input[0].equals(card.getColor()) && input[1].equals("Skip") && isCardValidNonNormal(input, cardPile)) {
                int victimIndex = nextTurn(playerList, activeIndex, 1);
                int newActiveIndex = placeCard(userCards, cardPile, playerList, activeIndex, card, 2, response, playerList.get(activeIndex).getPlayerName(), saidUno, deck);
                System.out.println("O NAH, " + playerList.get(activeIndex).getPlayerName() + " skipped " + playerList.get(victimIndex).getPlayerName() + "!");
                return newActiveIndex;
            }
            // Place Reverse Card
            else if (card.getCardType().equals("Reverse") && input[0].equals(card.getColor()) && input[1].equals("Reverse") && isCardValidNonNormal(input, cardPile)) {
                String oldActiveIndex = playerList.get(activeIndex).getPlayerName();
                activeIndex = reverseAtIndex(playerList, activeIndex);
                return placeCard(userCards, cardPile, playerList, activeIndex, card, 1, response, oldActiveIndex, saidUno, deck);
            }
        }
        // Typo or Draw Part 2
        System.out.println("You might've made a typo or your card is invalid");
        System.out.println("Do you want to draw a card instead? Type Yes or No.");
        String answer = reader.nextLine();
        if (answer.equals("Yes")) {
            System.out.println("");
            System.out.println("Okidoki drawing a card for you...");
            drawCard(deck, playerList.get(activeIndex).getPlayerCards(), 1, playerList.get(activeIndex).getPlayerName());
            return nextTurn(playerList, activeIndex, 1);
        } else {
            return placeCardHuman(playerList.get(activeIndex).getPlayerCards(), deck, cardPile, playerList, possibleColors, activeIndex, true); // when I call this, and somehow make saidUno auto true?;
        }
    }

    //Function: If active player is robot, automate card choice or draw.
    public static int placeCardRobot(ArrayList<Card> userCards, ArrayList<Card> deck, ArrayList<Card> cardPile, ArrayList<Player> playerList, String[] possibleColors, int activeIndex) {
    Card deckCard = cardPile.get(0); // Current card in the pile
    String response = "";
    boolean saidUno = false;

    if (playerList.get(activeIndex).getPlayerCards().size() == 2){
        System.out.println(playerList.get(activeIndex).getPlayerName() + " ACTIVATED UNO!!");
        saidUno = true;
    }

    for (int i = 0; i < userCards.size(); i++) {
        Card card = userCards.get(i);

        if (card.getCardType().equals("Normal") && (card.getColor().equals(deckCard.getColor()) || card.getCardValue() == deckCard.getCardValue())) {
            response = card.getColor() + " " + card.getCardValue();
            return placeCard(userCards, cardPile, playerList, activeIndex, card, 1, response, playerList.get(activeIndex).getPlayerName(), saidUno, deck);
        } else if (card.getCardType().equals("Wild") && card.getColor().equals("Black")) {
            card.wildCardRobot(possibleColors);
            response = card.getCardType() + " " + card.getColor();
            return placeCard(userCards, cardPile, playerList, activeIndex, card, 1, response, playerList.get(activeIndex).getPlayerName(), saidUno, deck);
        } else if (card.getCardType().equals("Wild +4") && card.getColor().equals("Black")) {
            card.wildCardRobot(possibleColors);
            response = card.getCardType() + " " + card.getColor();
            int victimIndex = nextTurn(playerList, activeIndex, 1);
            int newActiveIndex = placeCard(userCards, cardPile, playerList, activeIndex, card, 2, response, playerList.get(activeIndex).getPlayerName(), saidUno, deck);
            drawCard(deck, playerList.get(victimIndex).getPlayerCards(), 4, playerList.get(victimIndex).getPlayerName());
            return newActiveIndex;
        } else if (card.getCardType().equals("Normal +2") && (card.getCardType().equals(deckCard.getCardType()) || card.getColor().equals(deckCard.getColor()))){
            response = card.getColor() + " +2";
            int victimIndex = nextTurn(playerList, activeIndex, 1);
            int newActiveIndex = placeCard(userCards, cardPile, playerList, activeIndex, card, 2, response, playerList.get(activeIndex).getPlayerName(), saidUno, deck);
            drawCard(deck, playerList.get(victimIndex).getPlayerCards(), 2, playerList.get(victimIndex).getPlayerName());
            return newActiveIndex;
        } else if (card.getCardType().equals("Skip") && (card.getCardType().equals(deckCard.getCardType()) || card.getColor().equals(deckCard.getColor()))) {
            response = card.getColor() + " " + card.getCardType();
            int victimIndex = nextTurn(playerList, activeIndex, 1);
            int newActiveIndex = placeCard(userCards, cardPile, playerList, activeIndex, card, 2, response, playerList.get(activeIndex).getPlayerName(), saidUno, deck);
            System.out.println("O NAH, " + playerList.get(activeIndex).getPlayerName() + " skipped " + playerList.get(victimIndex).getPlayerName() + "!");
            return newActiveIndex;
        } else if (card.getCardType().equals("Reverse") && (card.getCardType().equals(deckCard.getCardType()) || card.getColor().equals(deckCard.getColor()))) {
            String oldActiveIndex = playerList.get(activeIndex).getPlayerName();
            int newActiveIndex = reverseAtIndex(playerList, activeIndex);
            response = card.getColor() + " " + card.getCardType();
            return placeCard(userCards, cardPile, playerList, newActiveIndex, card, 1, response, oldActiveIndex, saidUno, deck);
        }
    }
        drawCard(deck, userCards, 1, playerList.get(activeIndex).getPlayerName());
        return nextTurn(playerList, activeIndex, 1);
    }

    //Function: Places a card down and moves to next turn. (Uno considered too)
    public static int placeCard(ArrayList<Card> userCards, ArrayList<Card> cardPile, ArrayList<Player> playerList, int activeIndex, Card card, int turnIncrement, String response, String userName, boolean saidUno, ArrayList<Card> deck) {
        if (saidUno == false && playerList.get(activeIndex).getPlayerCards().size() == 2){
            System.out.println("You did not say UNO before placing a card down! You now need to draw 2 cards!");
            drawCard(deck, userCards, 2, playerList.get(activeIndex).getPlayerName());
            return nextTurn(playerList, activeIndex, 1);
        }
        else{
            System.out.println("--- Placing Card... ---");
            System.out.println(userName + " placed down: " + response);
            cardPile.add(0, card);
            userCards.remove(card);
            activeIndex = nextTurn(playerList, activeIndex, turnIncrement);
            return activeIndex;
        }
    }

    //Function: Draws a certain amount of card based on known input (+2, Wild +4, etc)
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








//------------MISC FUNCTIONS--------------
    
    //Returns the next player based on incrementAmount. Precondition is that we know the incrementAmount
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

    //Function: to check Card Validities
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
}