package csc439teamllama.cardgame;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.stream.IntStream;

import static com.google.common.truth.Truth.*;

class playingCardTest {
    playingCard deck[];

    @BeforeAll
    @Test
    void testCreatedeck(){
        deck = playingCard.createDeck();
        IntStream.range(0, 52).forEach(i -> assertThat(deck[i].getFacing()).isEqualTo(playingCard.Facing.DOWN));
        for(int i = 0;i<13;i++){

        }
    }

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

    @org.junit.jupiter.api.Test
    void getFacing() {
        //playingCard(playingCard.Facing facing, playingCard.Suit suit, playingCard.Number number)
    }

    @org.junit.jupiter.api.Test
    void setFacing() {
    }

    @org.junit.jupiter.api.Test
    void getSuit() {
    }

    @org.junit.jupiter.api.Test
    void setSuit() {
    }

    @org.junit.jupiter.api.Test
    void getNumber() {
    }

    @org.junit.jupiter.api.Test
    void setNumber() {
    }
}