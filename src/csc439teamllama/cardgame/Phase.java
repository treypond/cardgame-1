package csc439teamllama.cardgame;

import csc439teamllama.cardgame.GolfController;
import csc439teamllama.cardgame.GolfGameModel;

public interface Phase {
    public void execute(GolfGameModel game,GolfView view);
}
