package csc439teamllama.cardgame;

import java.util.ArrayList;
import java.util.Collections;

public class GolfGameModel {
    protected GolfView view;
    protected ArrayList<playingCard> deck,discard;
    protected GolfPlayerModel[] players;
    protected int turn,drawResponse;
    protected boolean gameOver,turnOver;
    protected String drawnFrom;
    protected playingCard drawnCard;
    protected Phase phase;


//  player size determines deck size, 52 card deck for 4 or less, double for 5 or more.
//  create and fill player array based on player number, use game rules to disperse
//  correct number of cards to hand.

    /**
     * Constructor for the game. It creates a game based on the number of player playing the game.
     * @param playerNum
     */
    public GolfGameModel(int playerNum) {
        phase = new DrawPhase();
        deck = new ArrayList<>();
        Collections.addAll(deck,playingCard.createDeck());
        if (playerNum > 4){
            Collections.addAll(deck,playingCard.createDeck());
        }
        discard = new ArrayList<>();
        gameOver = false;
        turn = 1;
        players = new GolfPlayerModel[playerNum];
    }

//  gets the player number for turn from round

    /**
     * Decides which players turn it is
     * @return index of the player whose turn it is.
     */
    public int playerIndex(){
        return ((turn-1)%(players.length));
    }
}
