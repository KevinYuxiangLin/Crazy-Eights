# COMP4004 Crazy Eights (Assignment 3)
Kevin Lin
101110242
kevinlin5@cmail.carleton.ca

This is my attempt at making a networked, 3 and 4 player crazy eight game! Written in Java with IntelliJ.

To play the game, run an instance of JavalinWebsocketExampleApp.java, and then 3 instances of Player.java.
Launch web browsers such as Chrome, and access localhost:3000. 
Player 1 presses the new game button once all players are connected to start the game.
Turns will be taken until a player depletes all their cards or the draw pile is depleted.
Rounds will be played until a player has reached over 100 points. The game is then stopped and a winner is decided.

java version "17.0.2"

IntelliJ version 2021.2.2.0

Other notes:

Cards with rank 2 functionality was not implemented. Part 3 test uses the alternate grid provided to us.