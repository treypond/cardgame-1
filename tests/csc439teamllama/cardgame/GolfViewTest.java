package csc439teamllama.cardgame;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static com.google.common.truth.Truth.*;

class GolfViewTest {

    @Test
    void TitleScreenTest(){
        GolfController controllerTest = new GolfController(new GolfCLITestView());
        controllerTest.view.TitleScreen();
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
                        "     `8888888P'        `8888888P'     8 888888888888 8 8888         \n\n");
    }

    @Test
    void gameStartOptions() {
        GolfController controllerTest = new GolfController(new GolfCLITestView());
        Collections.addAll(((GolfCLITestView)controllerTest.view).input, "10");
        assertThat(controllerTest.view.GameStartOptions()).isEqualTo(10);
        assertThat(((GolfCLITestView) controllerTest.view).output).isEqualTo(
        "How many players for this game:  "
        );
    }

    @Test
    void displayGameState() {
    }

    @Test
    void promptDecision() {
    }

    @Test
    void promptDiscard() {
    }

    @Test
    void sendMessageToPlayer() {
    }
}