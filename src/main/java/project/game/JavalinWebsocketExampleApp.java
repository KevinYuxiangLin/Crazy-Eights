package project.game;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.websocket.WsContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static j2html.TagCreator.article;
import static j2html.TagCreator.p;

public class JavalinWebsocketExampleApp {

    private static int nextUserNumber = 0; // Assign to username for next connecting user
    public static boolean suitChanged = false;
    public static boolean playTwoCards = false;
    public static int turnSkipped = -1;

    //store player names with the websocket
    private static final Map<WsContext, String> userUsernameMap = new ConcurrentHashMap<>();
    public static final ArrayList<WsContext> tempPlayer = new ArrayList<>();

    //cards
    public static EightsGame game = new EightsGame();

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public", Location.CLASSPATH);
        }).start(3000);

        app.ws("/chat", ws -> {
            ws.onConnect(ctx -> {
                System.out.println("DEBUG: Player attempting to connect");
                if (tempPlayer.size() <= 3) {
                    String username = "" + nextUserNumber++;
                    System.out.println("DEBUG: Player has username = " + username);
                    userUsernameMap.put(ctx, username);
                    tempPlayer.add(ctx);
                    game.addPlayer();
                    broadcastMessage("Server", (username + " joined the chat"));
                }
            });
            ws.onClose(ctx -> {
                System.out.println("Player disconnected");
                nextUserNumber = 0;
                game.resetPlayers();
                userUsernameMap.clear();

                for (int i = tempPlayer.size() - 1; i >= 0; i--){
                    String username = userUsernameMap.get(ctx);
                    broadcastMessage("Server", (username + " left the chat"));
                    tempPlayer.get(i).session.close();
                    tempPlayer.remove(i);
                }
                game.resetTurn();
            });
            ws.onMessage(ctx -> {
                System.out.println("Player ID: " + userUsernameMap.get(ctx));
                if (Integer.parseInt(userUsernameMap.get(ctx)) == game.getTurn()) {
                    messageHandler(ctx.message());
                }
            });
        });
    }

    // Sends a message from one user to all users, along with a list of current usernames
    private static void broadcastMessage(String sender, String message) {
        userUsernameMap.keySet().stream().filter(ctx -> ctx.session.isOpen()).forEach(session -> {
            System.out.println("DEBUG SESSION: " + session);
            session.send(
                    Map.of(
                            "userMessage", createHtmlMessageFromSender(sender, message),
                            "userlist", userUsernameMap.values()
                    )
            );
        });
    }

    // Builds a HTML element with a sender-name, a message, and a timestamp
    private static String createHtmlMessageFromSender(String sender, String message) {
        return article(
                p(message)
        ).render();
    }

    public static void broadcastForAllUsers() {
        for (int i = 0; i < tempPlayer.size(); i++){
            System.out.println("Player's hand: ");
            tempPlayer.get(i).send(
                    Map.of("userMessage", createHtmlMessageFromSender("sender", game.getHand(i)),
                            "discardPile", "Current card: " + game.getTopCardFromDiscardPile(),
                            "turn","It is Player " + (game.getTurn() + 1) + "'s turn",
                            "playerId", "You are Player: " + Integer.toString(i + 1),
                            "changeSuitMsg", game.checkScore(),
                            "cardsRemaining", "There are " + game.getDrawPile().size() + " cards remaining in play",
                            "direction", "The current direction is " + game.getDirection(),
                            "scores", game.getPlayerScores(),
                            "playerCount", game.playerCount
                    ));
            System.out.println("DEBUG HAND: " + i + " " + game.getHand(i));
        }
    }

    public static void sendEightsMsg(){
        tempPlayer.get(game.getTurn()).send(
                Map.of("userMessage", createHtmlMessageFromSender("sender", game.getHand(game.getTurn())),
                        "discardPile", "Current card " + game.getTopCardFromDiscardPile(),
                        "turn","It is player " + (game.getTurn() + 1) + "'s turn",
                        "playerId", "You are Player " + Integer.toString(game.getTurn() + 1),
                        "changeSuitMsg", "Choose a suit to change to in the textbox",
                        "cardsRemaining", "There are " + game.getDrawPile().size() + " cards remaining in play",
                        "direction", "The current direction is " + game.getDirection(),
                        "scores", game.getPlayerScores(),
                        "playerCount", game.playerCount
                ));
    }

    public static void sendSkipMsg(int player){
        tempPlayer.get(player).send(
                Map.of("userMessage", createHtmlMessageFromSender("sender", game.getHand(player)),
                        "discardPile", "Current card " + game.getTopCardFromDiscardPile(),
                        "turn","It is player " + (game.getTurn() + 1) + "'s turn",
                        "playerId", "You are Player " + Integer.toString(game.getTurn() + 1),
                        "changeSuitMsg", "Your turn got skipped!",
                        "cardsRemaining", "There are " + game.getDrawPile().size() + " cards remaining in play",
                        "direction", "The current direction is " + game.getDirection(),
                        "scores", game.getPlayerScores(),
                        "playerCount", game.playerCount
                ));
    }

    //Main chunk of this class, function to handle received messages, the textbox on the html page
    public static void messageHandler(String Message) {

        //Rigging: specific player's hand
        if (Message.split(" ")[0].equals("RIGGED_HAND")){
            //position is the specific player
            String position = Message.split(" ")[1];
            String[] tempArr = Message.split(" ");
            String[] cardsArr = new String[tempArr.length -2];
            //assign card array values
            for (int i = 0; i < cardsArr.length; i++){
                System.out.println("DEBUG TEMP ARR: " + tempArr[i]);
                cardsArr[i] = tempArr[i + 2];
            }
            //set player's hand
            game.playerHands[Integer.parseInt(position)].setHand(cardsArr);
            broadcastForAllUsers();
        }

        //Rigging: the draw pile's deck.
        if (Message.split(" ")[0].equals("RIGGED_DECK")){
            String[] tempArr = Message.split(" ");
            System.out.println("DEBUG TEMP ARR after split: " + Arrays.toString(tempArr));
            String[] cardsArr = new String[tempArr.length -1];
            for (int i = 0; i < cardsArr.length; i++){
                System.out.println("DEBUG TEMP ARR: " + tempArr[i+1]);
                cardsArr[i] = tempArr[i+1];
            }
            game.rigDeck(cardsArr);
            System.out.println("DEBUG RIGGED DECK: " + game.getDrawPile());
            broadcastForAllUsers();
        }

        //Rigging: Discard/the top card on the discard pile
        if (Message.split(" ")[0].equals("RIGGED_DISCARD")){
            String rigDiscard = Message.split(" ")[1];
            //below part is used to change which player's turn it is
            if (Message.split(" ").length == 3){
                String position = Message.split(" ")[2];
                game.setTurn(Integer.parseInt(position));
            }
            //set's the top card
            game.setDiscardPile(rigDiscard);
            broadcastForAllUsers();
        }

        //New game functionality, calls initializing functions and resets data
        if (Message.equals("NEW_GAME")){
            System.out.println("Starting New Game!");
            //initialization stuff
            suitChanged = false;
            game.resetNewDealer();
            game.resetTurn();
            game.initDeck();
            game.dealHand();
            broadcastForAllUsers();
            System.out.println(game.getTurn());
        }

        else {
            if (suitChanged) {
                if (game.changeSuit(Message)){
                    suitChanged = false;
                    game.addTurn();
                    broadcastForAllUsers();
                }
            }

            //Draw card functionality
            else if (Message.equals("DRAW_CARD")){
                System.out.println("Draw Card");
                game.drawCard(game.getTurn());
                //draw pile is empty, round is over, tally scores
                if (game.getDrawPile().size() <= 0){
                    game.updateScore();
                    System.out.println("New Round!");
                    game.resetTurn();
                    game.initDeck();
                    game.dealHand();
                    broadcastForAllUsers();
                    System.out.println(game.getTurn());
                }
                //when draw pile isn't empty
                else{
                    //if current discard card is an 8
                    if(game.getDiscardPile().peek().charAt(0) == '8'){
                        suitChanged = true;
                        sendEightsMsg();
                    }
                    else{
                        game.addTurn();
                        broadcastForAllUsers();
                    }
                }
            }
            //i didnt end up implementing the play 2 cards function
            else if (!playTwoCards){
                if (game.playCard(Message, game.getTurn())){
                    // case: player depletes their hand
                    if (game.playerHands[game.getTurn()].getHandList().size() == 0){
                        game.updateScore();
                        System.out.println(Arrays.toString(game.getPlayerScores()));
                        System.out.println("Player " + game.playerHands[game.getTurn()] + " has played all cards! Round over.");
                        //change this into a for loop
                        System.out.println("Final Scores: " + "p1 " + game.getPlayerScores()[0] + "p2 " + game.getPlayerScores()[1] + "p3 " + game.getPlayerScores()[2]);
                        //next round begins, with the second joining player becoming the first to play
                        game.newDealerTurn();
                        suitChanged = false;
                        game.initDeck();
                        game.dealHand();
                        broadcastForAllUsers();
                    }
                    //case: player does not play an 8
                    if (Message.charAt(0) != '8') {
                        //case: 1
                        if (Message.charAt(0) == '1' && Message.length() == 2){
                            System.out.println("reverse played");
                            game.setReverse();
                        }
                        //case: 2, not implemented
                        if (Message.charAt(0) == '2'){
                            System.out.println("Next player picks up 2");
                        }
                        //case: Q
                        if (Message.charAt(0) == 'Q'){
                            game.addTurn();
                            turnSkipped = game.getTurn();
                            System.out.println("Skipped turn of player " + (game.getTurn() + 1));
                        }

                        game.addTurn();
                        broadcastForAllUsers();
                        //if a player is set to be skipped, send msg, then reset var
                        if (turnSkipped >= 0){
                            sendSkipMsg(turnSkipped);
                            turnSkipped = -1;
                        }
                    }

                    else if (Message.charAt(0) == '8'){
                        sendEightsMsg();
                        suitChanged = true;
                    }

                }

            }

        }

    }

}