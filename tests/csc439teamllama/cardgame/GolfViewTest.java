package csc439teamllama.cardgame;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;
import static com.google.common.truth.Truth.*;

class GolfViewTest {


    @Test
    void TitleScreenTest(){
        GolfController controllerTest = new GolfController(new FakeGolfCLIView());
        controllerTest.view.titleScreen();
        assertThat(((FakeGolfCLIView) controllerTest.view).output).isEqualTo(
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
        GolfController controllerTest = new GolfController(new FakeGolfCLIView());
        Collections.addAll(((FakeGolfCLIView)controllerTest.view).input, "10","-1");
        assertThat(controllerTest.view.gameStartOptions()).isEqualTo(10);
        assertThat(((FakeGolfCLIView) controllerTest.view).output).isEqualTo(
        "How many players for this game: "
        );
    }

    @Test
    void displayGameStateWithEmptyDiscard() {
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll((view).input, "2");
        GolfController controllerTest = new GolfController(view);
        controllerTest.gameStart();
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
                        "The discard pile is empty\n"+
                "1. Print Game State Again\n"+
        "2. Pick Up From Deck\n"+
        "3. Pick Up From Discard\n"+
                        "Or Enter -1 To Exit\n"+
        "Enter Number To Proceed: "

        );
    }

    @Test
    void displayGameStateWithoutEmptyDiscard() {
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll((view).input, "2");
        GolfController controllerTest = new GolfController(view);
        controllerTest.gameStart();
        controllerTest.game.discard.add(controllerTest.game.deck.remove(controllerTest.game.deck.size()-1));
        controllerTest.game.discard.get(controllerTest.game.discard.size()-1).flipCard();
        controllerTest.game.turn=1;
        view.displayGameState(controllerTest.game);
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
                        "     `8888888P'        `8888888P'     8 888888888888 8 8888         \n\n"+
                        "How many players for this game: "+"Player 1's Turn\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "The deck has 40 remaining\n"+
                        "The discard pile is empty\n"+
                        "1. Print Game State Again\n"+
                        "2. Pick Up From Deck\n"+
                        "3. Pick Up From Discard\n"+
                        "Or Enter -1 To Exit\n"+
                        "Enter Number To Proceed: Player 1's Turn\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "The deck has 39 remaining\n"+
                        "The top card on the discard pile is hearts,ace\n"

        );

    }

    @Test
    void promptDecision() throws InputMismatchException {
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll((view).input, "2","3","-1","0","a");
        assertThat(view.promptDecision()).isEqualTo(2);
        assertThat(view.promptDecision()).isEqualTo(3);
        assertThat(view.promptDecision()).isEqualTo(-1);
        InputMismatchException error = assertThrows(InputMismatchException.class,()->{
           view.promptDecision();
        });
        error = assertThrows(InputMismatchException.class,()->{
            view.promptDecision();
        });
        assertThat(view.output).isEqualTo(
                "1. Print Game State Again\n"+
        "2. Pick Up From Deck\n"+
        "3. Pick Up From Discard\n"+
        "Or Enter -1 To Exit\n"+
        "Enter Number To Proceed: "+                "1. Print Game State Again\n"+
        "2. Pick Up From Deck\n"+
                "3. Pick Up From Discard\n"+
                "Or Enter -1 To Exit\n"+
                "Enter Number To Proceed: "+                "1. Print Game State Again\n"+
        "2. Pick Up From Deck\n"+
                "3. Pick Up From Discard\n"+
                "Or Enter -1 To Exit\n"+
                "Enter Number To Proceed: "+                "1. Print Game State Again\n"+
                        "2. Pick Up From Deck\n"+
                        "3. Pick Up From Discard\n"+
                        "Or Enter -1 To Exit\n"+
                        "Enter Number To Proceed: "+                "1. Print Game State Again\n"+
                        "2. Pick Up From Deck\n"+
                        "3. Pick Up From Discard\n"+
                        "Or Enter -1 To Exit\n"+
                        "Enter Number To Proceed: "
        );
    }

    @Test
    void promptDiscard() {
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll((view).input, "4","-1","2","3","-1","0","a");
        GolfController controller = new GolfController(view);
        controller.gameStart();
        ((FakeGolfCLIView)controller.view).output = "";
        controller.game.turn=1;
        assertThat(view.promptDiscard(controller.game)).isEqualTo(2);
        assertThat(view.promptDiscard(controller.game)).isEqualTo(3);
        assertThat(view.promptDiscard(controller.game)).isEqualTo(-1);
        InputMismatchException error = assertThrows(InputMismatchException.class,()->{
            view.promptDiscard(controller.game);
        });
        error = assertThrows(InputMismatchException.class,()->{
            view.promptDiscard(controller.game);
        });
        assertThat(((FakeGolfCLIView)controller.view).output).isEqualTo(
                "Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                        "Enter 7 To Discard Chosen Card.\n"+
        "Or -1 To Exit\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
        "Enter Number To Proceed: "+

                        "Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                        "Enter 7 To Discard Chosen Card.\n"+
                        "Or -1 To Exit\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "Enter Number To Proceed: "+

                        "Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                        "Enter 7 To Discard Chosen Card.\n"+
                        "Or -1 To Exit\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "Enter Number To Proceed: "+

                        "Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                        "Enter 7 To Discard Chosen Card.\n"+
                        "Or -1 To Exit\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "Enter Number To Proceed: "+

                        "Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                        "Enter 7 To Discard Chosen Card.\n"+
                        "Or -1 To Exit\n"+
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "Enter Number To Proceed: "
        );
    }

    @Test
    void sendMessageToPlayer() {
        FakeGolfCLIView view = new FakeGolfCLIView();
        view.sendMessageToPlayer("This Will Be Output");
        assertThat(view.output).isEqualTo("This Will Be Output\n");
    }
    @Test
    void testDisplayHand(){
        GolfController controllerTest = new GolfController(new FakeGolfCLIView());
        ((FakeGolfCLIView)controllerTest.view).input.add("4");
        controllerTest.gameStart();
        ((FakeGolfCLIView)controllerTest.view).clearVars();
        controllerTest.game.turn = 1;
        controllerTest.view.displayHand(controllerTest.game);
        assertThat(((FakeGolfCLIView)controllerTest.view).output).isEqualTo(
                "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"
        );
    }
}