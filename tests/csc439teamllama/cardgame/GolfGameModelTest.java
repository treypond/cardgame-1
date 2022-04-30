package csc439teamllama.cardgame;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.google.common.truth.Truth.assertThat;

import static org.junit.jupiter.api.Assertions.*;

class GolfGameModelTest {

    @Test
    void playerIndex() {
        GolfGameModel game = new GolfGameModel(2,5);
        assertThat(game.playerIndex()).isEqualTo(0);
        game.turn=2;
        assertThat(game.playerIndex()).isEqualTo(1);
        game.turn = 25;
        assertThat(game.playerIndex()).isEqualTo(0);
    }

    @Test
    void scoreboardUpdate() {
        GolfGameModel game = new GolfGameModel(4, 4);

        game.players[0] = new GolfPlayerModel("Joe");
        game.players[1] = new GolfPlayerModel("Jay");
        game.players[2] = new GolfPlayerModel("Josh");
        game.players[3] = new GolfPlayerModel("Jameson");

        //Score for this player should be 3
        game.players[0].hand = new playingCard[]{
                new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.KING),
                new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.KING),
                new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.KING),
                new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.ACE),
                new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.ACE),
                new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.ACE)
        };

        //Score for this player should be 30
        game.players[1].hand = new playingCard[]{
                new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.KING),
                new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.KING),
                new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.KING),
                new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.JACK),
                new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.JACK),
                new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.JACK)
        };

        //Score for this player should be 6
        game.players[2].hand = new playingCard[]{
                new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.KING),
                new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.KING),
                new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.KING),
                new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.QUEEN),
                new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.TWO),
                new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.TWO)
        };

        //Score for this player should be 0
        game.players[3].hand = new playingCard[]{
                new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.JACK),
                new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.QUEEN),
                new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.ACE),
                new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.JACK),
                new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.QUEEN),
                new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.ACE)
        };


        game.scoreboardUpdate();

        assertEquals(game.players[0].score, 3);
        assertEquals(game.players[1].score, 30);
        assertEquals(game.players[2].score, 6);
        assertEquals(game.players[3].score, 0);

        assertEquals(game.scoreBoard[0], game.players[3]);
        assertEquals(game.scoreBoard[1], game.players[0]);
        assertEquals(game.scoreBoard[2], game.players[2]);
        assertEquals(game.scoreBoard[3], game.players[1]);


    }
}