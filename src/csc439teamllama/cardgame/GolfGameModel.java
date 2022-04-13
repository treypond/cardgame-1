package csc439teamllama.cardgame;

import java.util.ArrayList;
import java.util.Collections;

public class GolfGameModel {
    protected GolfView view;
    protected ArrayList<playingCard> deck,discard;
    protected GolfPlayerModel[] players;
    protected int turn;
    protected boolean gameOver;

//  player size determines deck size, 52 card deck for 4 or less, double for 5 or more.
//  create and fill player array based on player number, use game rules to disperse
//  correct number of cards to hand.
    public GolfGameModel(int playerNum) {
        deck = new ArrayList<>();
        Collections.addAll(deck,playingCard.createDeck());
        if (playerNum > 4){
            Collections.addAll(deck,playingCard.createDeck());
        }
        discard = new ArrayList<>();
        gameOver = false;
        int turn = 1;
        players = new GolfPlayerModel[playerNum];
    }

//  gets the player number fro turn from round
    public int GetCurrentPlayerIndex(){
        return ((turn-1)%(players.length));
    }
}
