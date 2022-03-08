package csc439teamllama.cardgame;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static com.google.common.truth.Truth.*;

class playingCardTest {
    playingCard deck[];

    @BeforeEach
    void setDeck(){
        deck =  playingCard.createDeck();
    }

    @Test
    void testFlip(){
        assertThat(deck[0].getFacing().getFaceBool()).isEqualTo(playingCard.Facing.DOWN);
        deck[0].flipCard();
        assertThat(deck[0].getFacing().getFaceBool()).isEqualTo(playingCard.Facing.UP);
        deck[0].flipCard();
        assertThat(deck[0].getFacing().getFaceBool()).isEqualTo(playingCard.Facing.DOWN);
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
        //playingCard(playingCard.Facing facing, playingCard.Suit suit, playingCard.Number number)
    }

    @Test
    void setFacing() {

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
    }

    @Test
    void getNumber() {
    }

    @Test
    void setNumber() {
    }

    @Test
    void testSuitNumOf(){
        playingCard numTestCard = new playingCard(playingCard.Facing.DOWN,playingCard.Suit.suitNumOf(4), playingCard.Number.JACK);
        assertThat(numTestCard.getSuit()).isEqualTo(playingCard.Suit.HEARTS);
    }

    @Test
    void testFaceBoolOf(){
        playingCard numTestCard = new playingCard(playingCard.Facing.faceBoolOf(false),playingCard.Suit.HEARTS, playingCard.Number.JACK);
        assertThat(numTestCard.getFacing()).isEqualTo(playingCard.Facing.DOWN);
    }

    @Test
    void testNumberNumOf(){
        playingCard numTestCard = new playingCard(playingCard.Facing.faceBoolOf(false),playingCard.Suit.HEARTS, playingCard.Number.numOf(11));
        assertThat(numTestCard.getNumber()).isEqualTo(playingCard.Number.JACK);
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