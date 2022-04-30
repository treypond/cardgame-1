package csc439teamllama.cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class GolfGameModel {
    protected GolfView view;
    protected ArrayList<playingCard> deck,discard;
    protected GolfPlayerModel[] players;
    protected int turn,drawResponse;
    protected boolean gameOver,turnOver;
    protected String drawnFrom;
    protected playingCard drawnCard;
    protected Phase phase;

    protected GolfPlayerModel[] scoreBoard;


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

        //Adding a scoreboard array here to assist in printing out scores
        GolfPlayerModel[] scoreBoard = players;
    }

//  gets the player number for turn from round

    /**
     * Decides which players turn it is
     * @return index of the player whose turn it is.
     */
    public int playerIndex(){
        return ((turn-1)%(players.length));
    }

    /**
     * This method updates the scoreboard array to have the players in the correct order
     * and prints it to the screen.
     */
    protected void ScoreboardUpdate(){
        for(int i = 0; i < players.length; i++){
            scoreBoard[i] = players[i];
        }

        Arrays.sort(scoreBoard);

        //This uses the displayScoreBoard method that Tedla created. It is commented out at the moment.
        //view.displayScoreBoard(this);
    }
}
