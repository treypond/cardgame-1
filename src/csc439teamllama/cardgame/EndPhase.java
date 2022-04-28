package csc439teamllama.cardgame;

public class EndPhase implements Phase{
    @Override
    public void execute(GolfGameModel game, GolfView view) {
        game.turnOver = true;
        game.drawnCard = null;
        game.drawnFrom = "";
        game.turn++;
        game.drawResponse = 0;
        game.phase = new DrawPhase();
        game.ScoreboardUpdate();
    }
}
