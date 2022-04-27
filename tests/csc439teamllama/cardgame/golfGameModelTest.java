package csc439teamllama.cardgame;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

class golfGameModelTest {

    @Test
    void playerIndex() {
        GolfGameModel game = new GolfGameModel(2);
        assertThat(game.playerIndex()).isEqualTo(0);
        game.turn=2;
        assertThat(game.playerIndex()).isEqualTo(1);
        game.turn = 25;
        assertThat(game.playerIndex()).isEqualTo(0);
    }
}