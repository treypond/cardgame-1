package csc439teamllama.cardgame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.*;

class playingCardTest {
    playingCard deck[];

    @BeforeEach
    void setDeck(){
        deck =  playingCard.createDeck();
    }

    @Test
    void testFlip(){
        assertThat(deck[0].getFacing()).isEqualTo(playingCard.Facing.DOWN);
        deck[0].flipCard();
        assertThat(deck[0].getFacing()).isEqualTo(playingCard.Facing.UP);
        deck[0].flipCard();
        assertThat(deck[0].getFacing()).isEqualTo(playingCard.Facing.DOWN);

        deck[47] = new playingCard(playingCard.Facing.UP,deck[47].getSuit(),deck[47].getNumber());

        assertThat(deck[47].getFacing().getFaceBool()).isEqualTo(true);
        deck[47].flipCard();
        assertThat(deck[0].getFacing().getFaceBool()).isEqualTo(false);
        deck[47].flipCard();
        assertThat(deck[47].getFacing().getFaceBool()).isEqualTo(true);
    }

    @Test
    void testCreateDeck(){
        deck = playingCard.createDeck();
        assertThat(deck[0].getSuit()).isEqualTo(playingCard.Suit.SPADES);
        assertThat(deck[0].getFacing()).isEqualTo(playingCard.Facing.DOWN);
        assertThat(deck[0].getNumber()).isEqualTo(playingCard.Number.ACE);
        assertThat(deck[14].getSuit()).isEqualTo(playingCard.Suit.DIAMONDS);
        assertThat(deck[14].getFacing()).isEqualTo(playingCard.Facing.DOWN);
        assertThat(deck[14].getNumber()).isEqualTo(playingCard.Number.TWO);
        assertThat(deck[28].getSuit()).isEqualTo(playingCard.Suit.CLUBS);
        assertThat(deck[28].getFacing()).isEqualTo(playingCard.Facing.DOWN);
        assertThat(deck[28].getNumber()).isEqualTo(playingCard.Number.THREE);
        assertThat(deck[51].getSuit()).isEqualTo(playingCard.Suit.HEARTS);
        assertThat(deck[51].getFacing()).isEqualTo(playingCard.Facing.DOWN);
        assertThat(deck[51].getNumber()).isEqualTo(playingCard.Number.KING);
    }

    @Test
    void getFacing() {
        assertThat(deck[0].getFacing()).isEqualTo(playingCard.Facing.DOWN);
    }

    @Test
    void setFacing() {
        deck[48] = new playingCard(playingCard.Facing.UP,deck[48].getSuit(),deck[48].getNumber());

        assertThat(deck[1].getFacing()).isEqualTo(playingCard.Facing.DOWN);
        deck[1].setFacing(playingCard.Facing.UP);
        assertThat(deck[1].getFacing()).isEqualTo(playingCard.Facing.UP);
        deck[48].setFacing(playingCard.Facing.DOWN);
        assertThat(deck[48].getFacing()).isEqualTo(playingCard.Facing.DOWN);

        deck = playingCard.createDeck();
        deck[8] = new playingCard(playingCard.Facing.UP,deck[8].getSuit(),deck[8].getNumber());

        assertThat(deck[51].getFacing()).isEqualTo(playingCard.Facing.DOWN);
        deck[51].setFacing(playingCard.Facing.UP);
        assertThat(deck[51].getFacing()).isEqualTo(playingCard.Facing.UP);
        deck[8].setFacing(playingCard.Facing.DOWN);
        assertThat(deck[8].getFacing()).isEqualTo(playingCard.Facing.DOWN);
    }

    @Test
    void getSuit() {
        assertThat(deck[0].getSuit()).isEqualTo(playingCard.Suit.SPADES);
        assertThat(deck[13].getSuit()).isEqualTo(playingCard.Suit.DIAMONDS);
        assertThat(deck[26].getSuit()).isEqualTo(playingCard.Suit.CLUBS);
        assertThat(deck[39].getSuit()).isEqualTo(playingCard.Suit.HEARTS);
    }

    @Test
    void setSuit() {
        assertThat(deck[39].getSuit()).isEqualTo(playingCard.Suit.HEARTS);
        deck[39].setSuit(playingCard.Suit.SPADES);
        assertThat(deck[39].getSuit()).isEqualTo(playingCard.Suit.SPADES);
        assertThat(deck[8].getSuit()).isEqualTo(playingCard.Suit.SPADES);
        deck[8].setSuit(playingCard.Suit.CLUBS);
        assertThat(deck[8].getSuit()).isEqualTo(playingCard.Suit.CLUBS);
        assertThat(deck[17].getSuit()).isEqualTo(playingCard.Suit.DIAMONDS);
        deck[17].setSuit(playingCard.Suit.DIAMONDS);
        assertThat(deck[17].getSuit()).isEqualTo(playingCard.Suit.DIAMONDS);
        assertThat(deck[30].getSuit()).isEqualTo(playingCard.Suit.CLUBS);
        deck[30].setSuit(playingCard.Suit.HEARTS);
        assertThat(deck[30].getSuit()).isEqualTo(playingCard.Suit.HEARTS);

        deck = playingCard.createDeck();

        assertThat(deck[1].getSuit()).isEqualTo(playingCard.Suit.SPADES);
        deck[1].setSuit(1);
        assertThat(deck[1].getSuit()).isEqualTo(playingCard.Suit.SPADES);
        assertThat(deck[27].getSuit()).isEqualTo(playingCard.Suit.CLUBS);
        deck[27].setSuit(2);
        assertThat(deck[27].getSuit()).isEqualTo(playingCard.Suit.DIAMONDS);
        assertThat(deck[51].getSuit()).isEqualTo(playingCard.Suit.HEARTS);
        deck[51].setSuit(3);
        assertThat(deck[51].getSuit()).isEqualTo(playingCard.Suit.CLUBS);
        assertThat(deck[30].getSuit()).isEqualTo(playingCard.Suit.CLUBS);
        deck[30].setSuit(4);
        assertThat(deck[30].getSuit()).isEqualTo(playingCard.Suit.HEARTS);
    }

    @Test
    void getNumber() {
        assertThat(deck[14].getNumber()).isEqualTo(playingCard.Number.TWO);
    }

    @Test
    void setNumber() {
        assertThat(deck[32].getNumber()).isEqualTo(playingCard.Number.SEVEN);
        deck[32].setNumber(playingCard.Number.ACE);
        assertThat(deck[32].getNumber()).isEqualTo(playingCard.Number.ACE);
        assertThat(deck[44].getNumber()).isEqualTo(playingCard.Number.SIX);
        deck[44].setNumber(playingCard.Number.TWO);
        assertThat(deck[44].getNumber()).isEqualTo(playingCard.Number.TWO);
        assertThat(deck[13].getNumber()).isEqualTo(playingCard.Number.ACE);
        deck[13].setNumber(playingCard.Number.QUEEN);
        assertThat(deck[13].getNumber()).isEqualTo(playingCard.Number.QUEEN);

        deck = playingCard.createDeck();

        assertThat(deck[50].getNumber()).isEqualTo(playingCard.Number.QUEEN);
        deck[50].setNumber(3);
        assertThat(deck[50].getNumber()).isEqualTo(playingCard.Number.THREE);
        assertThat(deck[41].getNumber()).isEqualTo(playingCard.Number.THREE);
        deck[41].setNumber(4);
        assertThat(deck[41].getNumber()).isEqualTo(playingCard.Number.FOUR);
        assertThat(deck[32].getNumber()).isEqualTo(playingCard.Number.SEVEN);
        deck[32].setNumber(13);
        assertThat(deck[32].getNumber()).isEqualTo(playingCard.Number.KING);

    }

    @Test
    void testSuitNumOf(){
        playingCard numTestCard = new playingCard(playingCard.Facing.DOWN,playingCard.Suit.suitNumOf(4), playingCard.Number.JACK);
        assertThat(numTestCard.getSuit()).isEqualTo(playingCard.Suit.HEARTS);

        numTestCard = new playingCard(playingCard.Facing.DOWN,playingCard.Suit.suitNumOf(3), playingCard.Number.KING);
        assertThat(numTestCard.getSuit()).isEqualTo(playingCard.Suit.CLUBS);

        numTestCard = new playingCard(playingCard.Facing.UP,playingCard.Suit.suitNumOf(2), playingCard.Number.ACE);
        assertThat(numTestCard.getSuit()).isEqualTo(playingCard.Suit.DIAMONDS);

        numTestCard = new playingCard(playingCard.Facing.UP,playingCard.Suit.suitNumOf(1), playingCard.Number.SIX);
        assertThat(numTestCard.getSuit()).isEqualTo(playingCard.Suit.SPADES);
    }

    @Test
    void testFaceBoolOf(){
        playingCard numTestCard = new playingCard(playingCard.Facing.faceBoolOf(false),playingCard.Suit.HEARTS, playingCard.Number.JACK);
        assertThat(numTestCard.getFacing()).isEqualTo(playingCard.Facing.DOWN);

        numTestCard = new playingCard(playingCard.Facing.faceBoolOf(true),playingCard.Suit.CLUBS, playingCard.Number.SEVEN);
        assertThat(numTestCard.getFacing()).isEqualTo(playingCard.Facing.UP);
    }

    @Test
    void testNumberNumOf(){
        playingCard numTestCard = new playingCard(playingCard.Facing.DOWN,playingCard.Suit.HEARTS, playingCard.Number.numOf(11));
        assertThat(numTestCard.getNumber()).isEqualTo(playingCard.Number.JACK);

        numTestCard = new playingCard(playingCard.Facing.UP,playingCard.Suit.CLUBS, playingCard.Number.numOf(10));
        assertThat(numTestCard.getNumber()).isEqualTo(playingCard.Number.TEN);

        numTestCard = new playingCard(playingCard.Facing.DOWN,playingCard.Suit.SPADES, playingCard.Number.numOf(5));
        assertThat(numTestCard.getNumber()).isEqualTo(playingCard.Number.FIVE);
    }

    //  For clarification on default switch behavior
    @Test
    void testSwitchFunctionality() {
        String capture = "";
        switch (deck[0].getNumber()) {
            case ACE:
                capture = "We have ace!";
                break;
            default:
                capture = "Non-Ace case";
                break;
        }
        assertThat(capture).isEqualTo("We have ace!");
        switch (deck[1].getNumber()) {
            case ACE:
                capture = "We have ace!";
                break;
            default:
                capture = "Non-Ace case";
                break;
        }
        assertThat(capture).isEqualTo("Non-Ace case");
    }
    //Put Tedla's tests after this point:

}