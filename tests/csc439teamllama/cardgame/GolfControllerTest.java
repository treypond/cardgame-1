package csc439teamllama.cardgame;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GolfControllerTest {

    @Test
    void gameStart() {
        GolfController controllerTest = new GolfController(new GolfCLITestView());
        Collections.addAll(((GolfCLITestView)controllerTest.view).input, "10","2");
        controllerTest.GameStart();
        assertThat(controllerTest.game.deck.size()).isEqualTo(44);
        assertThat(controllerTest.game.players[0].hand[0].getFacing()).isEqualTo(playingCard.Facing.UP);
        assertThat(controllerTest.game.players[3].hand[1].getFacing()).isEqualTo(playingCard.Facing.UP);
        assertThat(controllerTest.game.players[5].hand[5].getFacing()).isEqualTo(playingCard.Facing.DOWN);
        assertThat(controllerTest.game.players[0].hand[0].getSuit()).isEqualTo(playingCard.Suit.HEARTS);
        assertThat(controllerTest.game.players[0].hand[0].getNumber()).isEqualTo(playingCard.Number.KING);
        assertThat(controllerTest.game.players[6].hand[4].getSuit()).isEqualTo(playingCard.Suit.SPADES);
        assertThat(controllerTest.game.players[6].hand[4].getNumber()).isEqualTo(playingCard.Number.QUEEN);
        assertThat(controllerTest.game.players[9].hand[3].getSuit()).isEqualTo(playingCard.Suit.HEARTS);
        assertThat(controllerTest.game.players[9].hand[3].getNumber()).isEqualTo(playingCard.Number.EIGHT);
        controllerTest.GameStart();
        assertThat(controllerTest.game.deck.size()).isEqualTo(40);
        assertThat(((GolfCLITestView) controllerTest.view).output).isEqualTo(
                "     ,o888888o.        ,o888888o.     8 8888         8 8888888888   \n" +
                        "    8888     `88.   . 8888     `88.   8 8888         8 8888         \n" +
                        " ,8 8888       `8. ,8 8888       `8b  8 8888         8 8888         \n" +
                        " 88 8888           88 8888        `8b 8 8888         8 8888         \n" +
                        " 88 8888           88 8888         88 8 8888         8 888888888888 \n" +
                        " 88 8888           88 8888         88 8 8888         8 8888         \n" +
                        " 88 8888   8888888 88 8888        ,8P 8 8888         8 8888         \n" +
                        " `8 8888       .8' `8 8888       ,8P  8 8888         8 8888         \n" +
                        "    8888     ,88'   ` 8888     ,88'   8 8888         8 8888         \n" +
                        "     `8888888P'        `8888888P'     8 888888888888 8 8888         \n\n"+
                        "How many players for this game:  "+
                        "     ,o888888o.        ,o888888o.     8 8888         8 8888888888   \n" +
                        "    8888     `88.   . 8888     `88.   8 8888         8 8888         \n" +
                        " ,8 8888       `8. ,8 8888       `8b  8 8888         8 8888         \n" +
                        " 88 8888           88 8888        `8b 8 8888         8 8888         \n" +
                        " 88 8888           88 8888         88 8 8888         8 888888888888 \n" +
                        " 88 8888           88 8888         88 8 8888         8 8888         \n" +
                        " 88 8888   8888888 88 8888        ,8P 8 8888         8 8888         \n" +
                        " `8 8888       .8' `8 8888       ,8P  8 8888         8 8888         \n" +
                        "    8888     ,88'   ` 8888     ,88'   8 8888         8 8888         \n" +
                        "     `8888888P'        `8888888P'     8 888888888888 8 8888         \n\n"+
                        "How many players for this game:  "
        );
    }
}