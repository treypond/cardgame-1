package csc439teamllama.cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class GolfGameModel {
    protected ArrayList<playingCard> deck,discard;
    protected GolfPlayerModel[] players;
    protected int turn,drawResponse,currentHole,totalHoles;
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
    public GolfGameModel(int playerNum,int totalHoles) {
        phase = new FlipPhase();
        deck = new ArrayList<>();
        Collections.addAll(deck,playingCard.createDeck());
        if (playerNum > 4){
            Collections.addAll(deck,playingCard.createDeck());
        }
//        Collections.shuffle(deck);
        discard = new ArrayList<>();
        gameOver = false;
        turn = 1;
        this.totalHoles = totalHoles;
        currentHole = 1;
        players = new GolfPlayerModel[playerNum];

        //Adding a scoreboard array here to assist in printing out scores
        scoreBoard = new GolfPlayerModel[playerNum];
        for(int i = 0; i < players.length; i++){
            scoreBoard[i] = players[i];
        }
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
    protected void scoreboardUpdate(){

        //Update player scores
        updateScores();

        //Set the scoreBoard array to be equal to the players array
        for(int i = 0; i < players.length; i++){
            scoreBoard[i] = players[i];
        }
        //Sort the scoreBoard array
        Arrays.sort(scoreBoard);
    }

    /**
     * This method updates the scores of all players. It does so by incrementally setting every player's score to 0,
     * and then recalculating their true score according to the rules of Golf.
     */
    protected void updateScores(){

        for(int i = 0; i < players.length; i++){
            players[i].score = 0;
            for(int j = 0; j < 6; j++) {

                /*
                These lines compensate for columns that are equal. If the two cards in a column are equal, then their scores are skipped.
                These conditionals all start by checking that the cards in question are face up, then they check to see that the current card is
                equal to the one under it. If this is the case, then the loop continues on to the next iteration.
                */
                if (players[i].hand[0].getFacing() == playingCard.Facing.UP && players[i].hand[3].getFacing() == playingCard.Facing.UP &&
                        j == 0 && players[i].hand[0].getNumber().getNum() == players[i].hand[3].getNumber().getNum()){
                    continue;
                }
                if (players[i].hand[1].getFacing() == playingCard.Facing.UP && players[i].hand[4].getFacing() == playingCard.Facing.UP &&
                        j == 1 && players[i].hand[1].getNumber().getNum() == players[i].hand[4].getNumber().getNum()){
                    continue;
                }
                if (players[i].hand[2].getFacing() == playingCard.Facing.UP && players[i].hand[5].getFacing() == playingCard.Facing.UP &&
                        j == 2 && players[i].hand[2].getNumber().getNum() == players[i].hand[5].getNumber().getNum()){
                    continue;
                }
                if (players[i].hand[3].getFacing() == playingCard.Facing.UP && players[i].hand[0].getFacing() == playingCard.Facing.UP &&
                        j == 3 && players[i].hand[3].getNumber().getNum() == players[i].hand[0].getNumber().getNum()){
                    continue;
                }
                if (players[i].hand[4].getFacing() == playingCard.Facing.UP && players[i].hand[1].getFacing() == playingCard.Facing.UP &&
                        j == 4 && players[i].hand[4].getNumber().getNum() == players[i].hand[1].getNumber().getNum()){
                    continue;
                }
                if (players[i].hand[5].getFacing() == playingCard.Facing.UP && players[i].hand[2].getFacing() == playingCard.Facing.UP &&
                        j == 5 && players[i].hand[5].getNumber().getNum() == players[i].hand[2].getNumber().getNum()){
                    continue;
                }

                //If the card is face up, and the card isn't a 2, jack, queen, or king, then add that to the player's score
                if(players[i].hand[j].getFacing() == playingCard.Facing.UP && (((players[i].hand[j].getNumber().getNum() > 2 && players[i].hand[j].getNumber().getNum() < 11) || players[i].hand[j].getNumber().getNum() == 1))) {
                    players[i].score += players[i].hand[j].getNumber().getNum();
                }

                //If they have a 2, subtract that from their score
                else if (players[i].hand[j].getFacing() == playingCard.Facing.UP && players[i].hand[j].getNumber().getNum() == 2){
                    players[i].score -= players[i].hand[j].getNumber().getNum();
                }

                //If they have a jack or queen then add ten
                else if (players[i].hand[j].getFacing() == playingCard.Facing.UP && (players[i].hand[j].getNumber().getNum() == 11 || players[i].hand[j].getNumber().getNum() == 12)){
                    players[i].score += 10;
                }

                //If the card being looked at was a king then nothing gets added to the player's score

            }

        }

    }


}
