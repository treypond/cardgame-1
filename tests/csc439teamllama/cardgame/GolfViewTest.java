package csc439teamllama.cardgame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;

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
        "How many players for this game: "
        );
    }

    @Test
    void displayGameStateWithEmptyDiscard() {
        GolfCLITestView view = new GolfCLITestView();
        Collections.addAll((view).input, "2");
        GolfController controllerTest = new GolfController(view);
        controllerTest.GameStart();
        view.DisplayGameState(controllerTest.game);



        assertThat((view).output).isEqualTo(


                "     ,o888888o.        ,o888888o.     8 8888         8 8888888888   \n" +
                        "    8888     `88.   . 8888     `88.   8 8888         8 8888         \n" +
                        " ,8 8888       `8. ,8 8888       `8b  8 8888         8 8888         \n" +
                        " 88 8888           88 8888        `8b 8 8888         8 8888         \n" +
                        " 88 8888           88 8888         88 8 8888         8 888888888888 \n" +
                        " 88 8888           88 8888         88 8 8888         8 8888         \n" +
                        " 88 8888   8888888 88 8888        ,8P 8 8888         8 8888         \n" +
                        " `8 8888       .8' `8 8888       ,8P  8 8888         8 8888         \n" +
                        "    8888     ,88'   ` 8888     ,88'   8 8888         8 8888         \n" +
                        "     `8888888P'        `8888888P'     8 888888888888 8 8888         \n\n"
                        +"How many players for this game: "
                        +"Player 1's Turn\n"+
        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
        "The deck has 40 remaining\n"+
                        "The discard pile is empty\n"

        );
    }

    @Test
    void displayGameStateWithoutEmptyDiscard() {
        GolfCLITestView view = new GolfCLITestView();
        Collections.addAll((view).input, "2");
        GolfController controllerTest = new GolfController(view);
        controllerTest.GameStart();
        controllerTest.game.discard.add(controllerTest.game.deck.remove(controllerTest.game.deck.size()-1));
        controllerTest.game.discard.get(controllerTest.game.discard.size()-1).flipCard();
        view.DisplayGameState(controllerTest.game);

        assertThat((view).output).isEqualTo(

                "     ,o888888o.        ,o888888o.     8 8888         8 8888888888   \n" +
                        "    8888     `88.   . 8888     `88.   8 8888         8 8888         \n" +
                        " ,8 8888       `8. ,8 8888       `8b  8 8888         8 8888         \n" +
                        " 88 8888           88 8888        `8b 8 8888         8 8888         \n" +
                        " 88 8888           88 8888         88 8 8888         8 888888888888 \n" +
                        " 88 8888           88 8888         88 8 8888         8 8888         \n" +
                        " 88 8888   8888888 88 8888        ,8P 8 8888         8 8888         \n" +
                        " `8 8888       .8' `8 8888       ,8P  8 8888         8 8888         \n" +
                        "    8888     ,88'   ` 8888     ,88'   8 8888         8 8888         \n" +
                        "     `8888888P'        `8888888P'     8 888888888888 8 8888         \n\n"
                        +"How many players for this game: "
                        +"Player 1's Turn\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "The deck has 39 remaining\n"+
                        "The top card on the discard pile is hearts,ace\n"

        );

    }

    @Test
    void promptDecision() {
        GolfCLITestView view = new GolfCLITestView();
        Collections.addAll((view).input, "2","3","0","a");
        assertThat(view.PromptDecision()).isEqualTo(2);
        assertThat(view.PromptDecision()).isEqualTo(3);
        InputMismatchException error = assertThrows(InputMismatchException.class,()->{
           view.PromptDecision();
        });
        error = assertThrows(InputMismatchException.class,()->{
            view.PromptDecision();
        });

    }

    @Test
    void promptDiscard() {
    }

    @Test
    void sendMessageToPlayer() {
    }
}