package csc439teamllama.cardgame;

public class GolfPlayerModel {
    protected playingCard[] hand;

    /**
     * Player Constructor which is initialized with an empty hand of playing cards
     */
    public GolfPlayerModel(){
        hand = new playingCard[6];
    }
}
