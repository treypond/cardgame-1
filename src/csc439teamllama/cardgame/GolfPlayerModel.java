package csc439teamllama.cardgame;

public class GolfPlayerModel implements Comparable<GolfPlayerModel>{
    protected playingCard[] hand;
    protected int score, totalscore;
    protected String name;

    /**Player Constructor which is initialized with an empty hand of playing cards*/
    public GolfPlayerModel(String playerName){
        hand = new playingCard[6];
        name = playerName;
    }

    /**
     *
     * @param other
     * @return A positive value if this player has a higher score than the "other", and a negative value if the
     * "other" player has a greater value than this one.
     * Returns a value of 0 if player scores are equal.
     */
    @Override
    public int compareTo(GolfPlayerModel other){
        return (this.score+this.totalscore)-(other.score+other.totalscore);
    }
}
