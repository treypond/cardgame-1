package csc439teamllama.cardgame;

import java.util.InputMismatchException;

public class DiscardPhase implements Phase{

    @Override
    public void execute(GolfGameModel game, GolfView view) {
            try {
                view.SendMessageToPlayer("Drawn card: "+game.drawnCard.toString()+" From: "+game.drawnFrom);
                int discardResponse = view.PromptDiscard(game);
                //exit code
                if (discardResponse == -1){
                    game.gameOver = true;
                    return;
                }
                //this means we are discarding from player hand, this is legal no matter where we drew
                //from
                else if (discardResponse < 7) {
                    game.phase = new EndPhase();
                    //set the discarded card from hand to face up
                    game.players[game.PlayerIndex()].hand[discardResponse-1].setFacing(playingCard.Facing.UP);
                    //move the card from hand to discard and the popped drawn card to hand
                    game.discard.add(game.players[game.PlayerIndex()].hand[discardResponse-1]);
                    game.players[game.PlayerIndex()].hand[discardResponse-1] = game.drawnCard;
                }
                //this is the case where we discard drawn card, not legal if we drew from discard
                else {
                    //drawn from deck so legal
                    if (game.drawResponse == 2) {
                        game.discard.add(game.drawnCard);
                        game.phase = new EndPhase();
                    }
                    //drawn from discard, so we ask them to choose a card from their hand to discard
                    else {
                        view.SendMessageToPlayer("You Picked This Card From Discard! Please Discard A Card From Your Hand.");
                    }
                }
            }
            //catch for out of bounds response to prompt discard, clears error from scanner
            catch (InputMismatchException e) {
                view.SendMessageToPlayer("please input a number between 1 and 7");
                view.ClearScanner();
            }
    }
}
