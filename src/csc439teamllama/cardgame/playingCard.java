package csc439teamllama.cardgame;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * A playing card
 * <P>
 *     Playing card has a number of methods, including getters and setters for determine suit, card number, and direction
 *     it is facing. These values are stored as enums, with equivalent int values for each value, in the case of suit and
 *     number, and boolean, in the case of if it is either facing up, or facing down. This is for ease of use of the user,
 *     as well as significantly speeding up large scale manipulation, as seen with the create deck staic function, allowing
 *     the creation of a deck with 52 cards face down, all unique with a very simple logic.
 * </P>
 * CSC-439
 * @author Isaac Sims
 * @version 1.0
 */

public class playingCard {
    private Facing facing;
    private Suit suit;
    private Number number;

    public playingCard(Facing facing, Suit suit, Number number) {
        this.facing = facing;
        this.suit = suit;
        this.number = number;
    }

    public playingCard(boolean facing, int suit, int number) {
        this.facing = Facing.faceBoolOf(facing);
        this.suit = Suit.suitNumOf(suit);
        this.number = Number.numOf(number);
    }

    public Facing getFacing() {
        return facing;
    }

    public void setFacing(Facing facing) {
        this.facing = facing;
    }

    public void setFacing(Boolean facing) {
        this.facing = Facing.faceBoolOf(facing);
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public void setSuit(int suit) {
        this.suit = Suit.suitNumOf(suit);
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    public void setNumber(int number) {
        this.number = Number.numOf(number);
    }

    //  put is black, is red here
    public boolean isBlack(){
        return false;
    }

    public boolean isRed(){
        return false;
    }

    //  random card method
    public playingCard randomCard(){
        Random rand = new Random(1);
        int wild = rand.nextInt(3)+1;
        if(true){
            wild = rand.nextInt(12)+1;
        }
        return new playingCard(false,1,1);
    }

    public playingCard randomCard(int seed){
        Random rand = new Random(seed);
        int wild = rand.nextInt(3)+1;
        if(true){
            wild = rand.nextInt(12)+1;
        }
        return new playingCard(false,1,1);
    }

    /**
     * Turns over the playing card
     * <P>
     *     flips playing card so that the other side is facing up.
     * </P>
     * CSC-439
     * @author Isaac Sims
     * @version 1.0
     */
    public void flipCard(){
        facing = Facing.faceBoolOf(!getFacing().getFaceBool());
    }

    /**
     * A method to generate a playing card deck with 52 cards in new deck order
     * <P>
     *     creates a playing card deck with all possible unique combinations of suites and numbers, face down. Does so by
     *     making extensive use of of the enum value maps created in our enum classes, further explained in the
     *     enum documentation. New deck order is suits in order Spades, Diamonds, Clubs, Hearts, with each in Ace-King
     *     order.
     * </P>
     * CSC-439
     * @author Isaac Sims
     * @version 1.0
     * @return A deck of 52 playing cards
     */
    public static playingCard[] createDeck(){
        playingCard deck[] = new playingCard[52];
        //  outer loop for iterating through suits spades-hearts
        for(int i = 0; i<4 ;i++){
            //  inner loop for iterating through numbers, ace-king
            for (int j = 0; j<13 ;j++){
                deck[((i*13)+j)] = new playingCard(false,i+1,j+1);
            }
        }
        return deck;
    }

    /**
     * Enum, for the direction the card is lying, either face up, or face down.
     * <P>
     *     When the card is face down, it has an internal value of false, and when facing up, it has an internal value of true
     * </P>
     * CSC-439
     * @author Isaac Sims
     * @version 1.0
     */
    public enum Facing{
        //  Down is false as it is unreadble, as in the face is not viewable, so logically face would be false
        DOWN(false),UP(true);

        private final boolean faceBool;
        private final static Map map = new HashMap();

        Facing(boolean faceBool){
            this.faceBool = faceBool;
        }

        /**
         * The section mapping each enum to it's boolean value, allowing us to convert the value to the enum in the
         * upcoming method.
         * CSC-439
         * @author Isaac Sims
         * @version 1.0
         */
        static {
            for (Facing faces : Facing.values()) {
                map.put(faces.faceBool, faces);
            }
        }

        /**
         * uses our hashmap to retrieve the proper enum for our entered value
         * @param bool  This is the side the card is lying on, represented by a boolean false if the card face is on
         *              facing down, and true if the card face is facing up.
         * @return the correct enum for the supplied boolean value for the side the card is lying on
         * @author Isaac Sims
         * @version 1.0
         */
        public static Facing faceBoolOf(boolean bool) {
            return (Facing) map.get(bool);
        }

        public boolean getFaceBool() {
            return faceBool;
        }
    }

    /**
     * Enum, for the suit of each playing card: spades, diamonds, clubs, and hearts.
     * <P>
     *     Theses enums go up sequentially in value for the starting deck order, simplifies deck creation.
     *     This also assists in deck creation and manipulation, and is achieved with a hash map mapping the specific
     *     enum value to it's relative suit value so we can both get the number value as an int, but get the enum
     *     value from the corresponding int.
     * </P>
     * CSC-439
     * @author Isaac Sims
     * @version 1.0
     */
    public enum Suit{
        SPADES(1),DIAMONDS(2),CLUBS(3),HEARTS(4);

        private final int suitNum;
        private final static Map map = new HashMap();

        Suit(int suitNum){
            this.suitNum = suitNum;
        }

        /**
         * The section mapping each enum to it's number value, allowing us to convert the value to the enum in the
         * upcoming method.
         * CSC-439
         * @author Isaac Sims
         * @version 1.0
         */
        static {
            for (Suit suits : Suit.values()) {
                map.put(suits.suitNum, suits);
            }
        }

        /**
         * uses our hashmap to retrieve the proper enum for our entered value
         * @param num  This is the side the suit of the card, represented by the value of the suits in new deck order
         *             starting with spade being represented by 4, until heart represented by 4
         * @return the correct enum for the supplied boolean value for the side the card is lying on
         * @author Isaac Sims
         * @version 1.0
         */
        public static Suit suitNumOf(int num) {
            return (Suit) map.get(num);
        }

        public int getSuitNum() {
            return suitNum;
        }
    }


    /**
     * Enum, for the number of each card
     * <P>
     *     the number cards in order of value from 2 to 10, the face cards having values higher than 10 in order of
     *     precedence, and ace placed as the first value, as it can be 1 or 14 and makes for proper new deck order.
     *     This also assists in deck creation and manipulation, and is achieved with a hash map mapping the specific
     *     enum value to it's relative number value so we can both get the number value as an int, but get the enum
     *     value from the corresponding int.
     * </P>
     * CSC-439
     * @author Isaac Sims
     * @version 1.0
     */
    public enum Number{

        ACE(1),TWO(2),THREE(3),FOUR(4),FIVE(5),SIX(6),SEVEN(7),
        EIGHT(8),NINE(9),TEN(10),JACK(11),QUEEN(12),KING(13);

        private final int cardNum;
        private final static Map map = new HashMap();

        Number(int cardNum){
            this.cardNum = cardNum;
        }

        /**
         * The section mapping each enum to it's number value, allowing us to convert the value to the enum in the
         * upcoming method.
         * CSC-439
         * @author Isaac Sims
         * @version 1.0
         */
        static {
            for (Number num : Number.values()) {
                map.put(num.cardNum, num);
            }
        }

        /**
         * uses our hashmap to retrieve the proper enum for our entered value
         * @param num  This is the number of the card, represented by the value of the number of the card through
         *             in increasing value, with 1 corresponding to one, and 13 corresponding to king.
         * @return the correct enum for the supplied number value for number or face on the card
         * @author Isaac Sims
         * @version 1.0
         */
        public static Number numOf(int num) {
            return (Number) map.get(num);
        }

        public int getNum() {
            return cardNum;
        }
    }
}