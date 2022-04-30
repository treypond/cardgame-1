package csc439teamllama.cardgame;

import java.util.InputMismatchException;

public class DrawPhase implements Phase {
    @Override
    public void execute(GolfGameModel game, GolfView view) {
        try {
            //get user input from 1-3, or -1 to exit
            game.drawResponse = view.promptDecision();
            //-1 so we exit turn and end game
            if (game.drawResponse == -1){
                game.gameOver = true;
            }
            //1 means we redisplay game state and return to being of loop to prompt user once again
            else if (game.drawResponse == 1) {
                view.displayGameState(game);
            }
            //we enter here because the user has either entered 2 or three, so we know they have chosen to draw,
            //and we must handle it appropriately, including errors like drawing from empty piles.
            else {
                //2 means we are drawing from deck, if it is empty we prompt user and inform them as such
                if (game.drawResponse == 2) {
                    if (game.deck.isEmpty()){
                        view.sendMessageToPlayer("Deck Is Empty! Please Draw from Discard");
                    }
//                            if it is not empty it is allowed so we set our drawn card to the top of the deck
                    else {
                        game.deck.get(game.deck.size() - 1).flipCard();
                        game.drawnCard = game.deck.remove(game.deck.size() - 1);
                        game.drawnFrom = "Deck";
                        game.phase = new DiscardPhase();
                    }
                }
                //option 3, draw from discard, same logic as above except with discard
                else {
                    if (game.discard.isEmpty()){
                        view.sendMessageToPlayer("Discard Is Empty! Please Draw from Deck");
                    }
                    else {
                        game.drawnCard = game.discard.remove(game.discard.size() - 1);
                        game.drawnFrom = "Discard";
                        game.phase = new DiscardPhase();
                    }
                }
                //now that we have legally chosen a drawn card we choose which card to discard and replace from our hand
                //and if we drew deck possibly discard our drawn card
            }
        }
        //catch for out of bounds response to prompt to start turn, clears error from scanner
        catch (InputMismatchException e){
            view.sendMessageToPlayer("please input a number between 1 and 3");
            view.clearScanner();
        }
    }
}
