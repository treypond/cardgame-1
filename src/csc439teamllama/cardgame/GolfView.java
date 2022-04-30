package csc439teamllama.cardgame;

import java.io.IOException;

public abstract class GolfView {

    abstract protected void titleScreen();
//  As a player, I’d like to be presented with a title screen and then be able to select
//  how many  players there are, so that I can play the game in multiple
//  configurations
    abstract protected int[] gameStartOptions();

//  4 As a player, on each turn, I would like the game to display who’s turn it is, their hand, the
//  draw pile and last discarded card
    abstract protected void displayGameState(GolfGameModel game);

//    4 list of actions they may take, so it is clear I
//    know what I am doing (include choice to redisplay the game state)
//
//    5 I should be asked to draw a card from the draw pile or discard
//    pile

//    8 As a player, I should be able to quit the game with a command on each turn, so that
//    the game doesn’t get into an infinite loop
    abstract protected int promptDecision();

//  5 be shown the card, and then allowed to select what card to replace, so that I can take
//  my turn

//  7 As a player, if I draw the card on top of the discard pile, I shouldn’t be allowed to place
//  that  card back in the discard pile, and should be forced to replace a card in my hand,
//  so that I don’t  break the game’s rules unintentionally
    abstract protected int promptDiscard(GolfGameModel game);

//  used as main way for controller to send arbitray cli messages to player
    abstract protected void sendMessageToPlayer(String message);

    abstract protected void displayHand(GolfGameModel game);

    abstract protected void displayScoreBoard(GolfGameModel game);

    abstract protected String promptPlayerName(int playerNum);

    abstract protected void clearScanner();

    abstract protected int promptFlip(GolfGameModel game);

    abstract protected void spacer();
}
