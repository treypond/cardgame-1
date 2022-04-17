package csc439teamllama.cardgame;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.google.common.truth.Truth.assertThat;

import static org.junit.jupiter.api.Assertions.*;

class GolfGameModelTest {

    @Test
    void playerIndex() {
        GolfGameModel game = new GolfGameModel(2);
        assertThat(game.PlayerIndex()).isEqualTo(0);
        game.turn=2;
        assertThat(game.PlayerIndex()).isEqualTo(1);
        game.turn = 25;
        assertThat(game.PlayerIndex()).isEqualTo(0);
    }
}