package project.game;

import java.util.*;

public class EightsGame {

    CardHand[] playerHands = new CardHand[4];
    static int playerCount = 0;
    static int[] playerScores = {0, 0, 0, 0};
    static int playerTurn = 0;
    static boolean reverse = false;
    static int newDealer = 0;

    public EightsGame() {
        for (int i = 0; i < playerHands.length; i++){
            playerHands[i] = new CardHand();
        }
    }

    ArrayList<String> listOfCards = new ArrayList<>(Arrays.asList(
            "1H", "2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "10H", "JH", "QH", "KH",
            "1C", "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "10C", "JC", "QC", "KC",
            "1D", "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "10D", "JD", "QD", "KD",
            "1S", "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "10S", "JS", "QS", "KS"));

    //Stacks for the drawPile (where player retrieves cards) and discardPile (where players play their cards)
    static Stack<String> drawPile = new Stack<>();
    static Stack<String> discardPile = new Stack<>();

    //Initialize the deck
    public void initDeck() {
        reverse = false;
        Collections.shuffle(listOfCards); //shuffle cards
        drawPile.clear();
        discardPile.clear();

        //Clear all hands
        for (int i = 0; i < playerHands.length; i++){
            playerHands[i].clearHand();
        }
        //Push the collection of shuffle cards into the drawPile stack
        for (int i = 0; i < listOfCards.size(); i++){
            drawPile.push(listOfCards.get(i));
        }
        //peek() retrieves the first element, in this context, top card on deck
        //The first discarded card cannot have a rank of 8, so the deck will be shuffled again.
        while(drawPile.peek().charAt(0) == '8') {
            Collections.shuffle(drawPile);
        }
        discardPile.push(drawPile.pop());

    }

    //Getters and Setters
    public Stack<String> getDrawPile() { return drawPile; }
    public String getHand (int player) { return playerHands[player].getHand(); }
    public Stack<String> getDiscardPile() { return discardPile; }
    public int getTurn() { return playerTurn; }
    public String getTopCardFromDiscardPile(){ return discardPile.peek(); }
    public String getDirection() {
        if (reverse){
            return "right";
        }
        else {
            return "left";
        }
    }
    public int[] getPlayerScores() {
        return playerScores;
    }

    public void setReverse() { reverse = !reverse; }
    public void setDiscardPile(String card){ discardPile.push(card); }
    public void setTurn(int turn) { playerTurn = turn; }

    public void addPlayer() { playerCount++; }
    public void addTurn(){
        if (!reverse){
            playerTurn++;
            if (playerTurn >= playerCount){
                playerTurn = 0;
            }
        }
        else {
            playerTurn--;
            if (playerTurn <= -1){
                playerTurn = playerCount - 1;
            }
        }
    }

    public void resetTurn() { playerTurn = 0; }
    public void resetNewDealer() { newDealer = 0; }
    public void resetPlayers(){
        playerCount = 0;
        for (int i = 0; i < 4; i++){
            playerScores[i] = 0;
        }
    }

    //for testing purposes, we need a function that allows us to rig the deck
    public void rigDeck(String[] rigInput){
        drawPile.clear();
        for (int i = rigInput.length-1; i >= 0; i--){
            drawPile.push(rigInput[i]);
        }
    }

    //determines whether the played card is allowed to be played
    public boolean cardIsPlayable(String card){
        //check to see that the top card on the discard pile has the same suit as the played card, or the same rank, or special condition for 8
        if ((card.charAt(0) == discardPile.peek().charAt(0) || card.charAt(1) == discardPile.peek().charAt(1) || card.charAt(0) == '8')){
            return true;
        }
        //cards with rank 10 need a separate case: same rank
        else if (card.length() == 3 && discardPile.peek().length() == 3){
            return true;
        }
        //ONE card with rank 10: same suit as other card
        else if (card.length() == 3 && discardPile.peek().length() == 2){
            return discardPile.peek().charAt(1) == card.charAt(2);
        }
        //ONE card with rank 10: same suit as other card (opposite)
        else if (card.length() == 2 && discardPile.peek().length() == 3){
            return discardPile.peek().charAt(2) == card.charAt(1);
        }
        System.out.println("Card is not a valid play");
        return false;
    }

    public void dealHand() {
        for (int i = 0; i < playerCount; i++){
            for (int j = 0; j < 5; j++){ //max 4 players
                playerHands[i].addCardToHand(drawPile.pop());
            }
        }
    }

    //Function that allows the player to play a selected card, if valid, pushes it onto the discard stack
    public boolean playCard(String card, int player){
        if (cardIsPlayable(card)){ //check if card is allowed to be played
            if (playerHands[player].removeCardFromHand(card)) { //check if player actually holds the card
                discardPile.push(card);
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    //Function that draws a card
    public boolean drawCard(int player){
        for (int i = 0; i < 3; i++) {
            if (drawPile.size() > 0){
                //if player picks up a card that is playable, then it is immediately played
                if (cardIsPlayable(drawPile.peek())) {
                    discardPile.push(drawPile.pop());
                    return true;
                }
                else {
                    playerHands[player].addCardToHand(drawPile.pop());
                }
            }
            else {
                return false;
            }
        }
        return true;
    }

    public boolean changeSuit(String suit){
        //check to make sure player entered a valid suit
        if (suit.equals("H") || suit.equals("C") ||  suit.equals("D") ||  suit.equals("S")){
            //place the 8 card with the changed suit on top of the discard pile
            setDiscardPile("8" + suit);
            return true;
        }
        return false;
    }

    public void updateScore(){
        int score = 0;
        for (int i = 0; i < playerCount; i++){
            System.out.println(playerScores[i]);
            for (int j = 0; j < playerHands[i].getHandList().size(); j++){
                if (playerHands[i].getHandList().get(j).length() == 3){
                    playerScores[i] += 10;
                }
                else {
                    System.out.println("Current player " + i + "'s card: " + playerHands[i].getHandList().get(j).charAt(0));
                    //special case for 10 rank
                    if (playerHands[i].getHandList().get(j).length() == 3) {
                        playerScores[i] += 10;
                    }
                    switch (playerHands[i].getHandList().get(j).charAt(0)) {
                        case '1' -> playerScores[i] += 1;
                        case '2' -> playerScores[i] += 2;
                        case '3' -> playerScores[i] += 3;
                        case '4' -> playerScores[i] += 4;
                        case '5' -> playerScores[i] += 5;
                        case '6' -> playerScores[i] += 6;
                        case '7' -> playerScores[i] += 7;
                        case '8' -> playerScores[i] += 50;
                        case '9' -> playerScores[i] += 9;
                        case 'J', 'Q', 'K' -> playerScores[i] += 10;
                    }
                }
            }
        }
    }

    public String checkScore(){
        boolean winnerExists = false;
        String tempWinner;
        int minimumScore = 100;
        for (int i = 0; i < playerCount; i++){
            //if any player has a score greater than 100, the game is over
            if (playerScores[i] >= 100){
                winnerExists = true;
                break;
            }
        }
        if (winnerExists) {
            for (int i = 0; i <playerCount; i++){
                if (playerScores[i] < minimumScore){
                    minimumScore = playerScores[i];
                }
            }
            tempWinner = "The winner is: ";
            for (int i = 0; i < playerCount; i++){
                if (playerScores[i] == minimumScore){
                    tempWinner += (i + 1) + "!";
                }
            }
        }
        else{
            tempWinner = "";
        }
        return tempWinner;
    }

    public void newDealerTurn() {
        newDealer++;
        if (newDealer >= playerCount) {
            newDealer = 0;
        }
        playerTurn = newDealer - 1;
    }

}
