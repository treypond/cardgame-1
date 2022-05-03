package csc439teamllama.cardgame;

public class EndPhase implements Phase{
    @Override
    public void execute(GolfGameModel game, GolfView view) {
        game.turnOver = true;
        game.drawnCard = null;
        game.drawnFrom = "";
        game.drawResponse = 0;
        boolean holeOver = true;
        for (playingCard card: game.players[game.playerIndex()].hand) {
            if (card.getFacing() == playingCard.Facing.DOWN)
                holeOver = false;
        }
        game.scoreboardUpdate();
        if (holeOver){
            view.displayGameState(game);
            view.sendMessageToPlayer(game.players[game.playerIndex()].name+"'s turn over, as well as the hole! Press enter");
            view.spacer();
            game.phase = new HoleEndPhase();
        }
        else {
            view.displayHand(game);
            game.phase = new FlipPhase();
            view.sendMessageToPlayer(game.players[game.playerIndex()].name+"'s turn over, press enter to move on to next player");
            view.spacer();
            game.turn++;
        }
    }
}
