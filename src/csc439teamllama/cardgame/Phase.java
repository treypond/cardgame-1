package csc439teamllama.cardgame;

@FunctionalInterface
public interface Phase {
    public void execute(GolfGameModel game, GolfView view);
}
