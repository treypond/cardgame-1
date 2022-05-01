package csc439teamllama.cardgame;

import java.util.InputMismatchException;

public class FlipPhase implements Phase{
    @Override
    public void execute(GolfGameModel game, GolfView view) {
        int playerNum = game.playerIndex()+1;
        view.sendMessageToPlayer("Player " +game.players[game.playerIndex()].name+ " must flip 2 cards before they start their turn, their hand is:");
        int turnedCards = 0;
        for (playingCard card: game.players[game.playerIndex()].hand) {
            if (card.getFacing() == playingCard.Facing.UP)
                turnedCards++;
        }
        if (turnedCards <2) {
            try {
                view.displayHand(game);
                int option = view.promptFlip(game)-1;
                if (option == -2){
                    game.gameOver = true;
                    return;
                }
                if (game.players[game.playerIndex()].hand[option].getFacing() == playingCard.Facing.UP)
                    view.sendMessageToPlayer("That card is already flipped up! please choose another.");
                else
                    game.players[game.playerIndex()].hand[option].flipCard();
            } catch (InputMismatchException e) {
                view.sendMessageToPlayer("Please enter a number between 1 and 6");
            }
        }
        else {
            view.displayGameState(game);
            game.phase = new DrawPhase();
        }
    }
}
