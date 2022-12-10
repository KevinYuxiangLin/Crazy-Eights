// small helper function for selecting element by id
let id = id => document.getElementById(id);

//Establish the WebSocket connection and set up event handlers
let ws = new WebSocket("ws://" + location.hostname + ":" + location.port + "/chat");
ws.onmessage = msg => updateChat(msg);
ws.onclose = () => alert("WebSocket connection closed");

// Add event listeners to button and input field
id("send").addEventListener("click", () => sendAndClear(id("message").value));
id("newGame").addEventListener("click", () => Send());
id("drawCard").addEventListener("click", () => SendDraw());
id("message").addEventListener("keypress", function (e) {
    if (e.keyCode === 13) { // Send message if enter is pressed in input field
        sendAndClear(e.target.value);
    }
});

function sendAndClear(message) {
    if (message !== "") {
        ws.send(message);
        id("message").value = "";
    }
}

//rigging functionality
function rigPlayerHand(message){
    ws.send("RIGGED " + message);
}

function Send(){
    ws.send("NEW_GAME");
    console.log("New Game sent");
}

function SendDraw(){
    ws.send("DRAW_CARD");
    console.log("Draw Card sent");
}

function updateChat(msg) { // Update chat-panel and list of connected users
    let data = JSON.parse(msg.data);
    console.log(Object.keys(data) + " is this data even coming thru??");
    document.getElementById("chat").innerHTML = ("afterbegin", data.userMessage);
    document.getElementById("turn").innerHTML = (data.turn);
    document.getElementById("currentCard").innerHTML = (data.discardPile);
    document.getElementById("playerId").innerHTML = (data.playerId);
    document.getElementById("eightPlayed").innerHTML = (data.changeSuitMsg);
    document.getElementById("drawPile").innerHTML = (data.cardsRemaining);
    document.getElementById("direction").innerHTML = (data.direction);

}