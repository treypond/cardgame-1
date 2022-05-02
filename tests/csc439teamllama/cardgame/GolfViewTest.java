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
        assertThat(controllerTest.view.gameStartOptions()).isEqualTo(new int[]{10, -1});
        assertThat(((FakeGolfCLIView) controllerTest.view).output).isEqualTo(
        "\nHow many players for this game: How many holes for this game: "
        );
    }

    @Test
    void displayGameStateWithEmptyDiscard() {
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll((view).input, "2","2","jo","jay","-1","1","2");
        GolfController controllerTest = new GolfController(view);
        controllerTest.gameStart();
        ((FakeGolfCLIView)controllerTest.view).output = "";
        controllerTest.game.gameOver = false;
        controllerTest.game.turnOver = false;
        controllerTest.game.phase = new FlipPhase();
        controllerTest.game.deck.clear();
        Collections.addAll(controllerTest.game.deck,playingCard.createDeck());
        for(int i=0; i<controllerTest.game.players.length;i++){
            for(int j=0; j<6; j++ ){
                controllerTest.game.players[i].hand[j]=controllerTest.game.deck.remove(controllerTest.game.deck.size()-1);
            }
        }
        controllerTest.gameRunner();
        assertThat((view).output).isEqualTo(
                        "Player jo must flip 2 cards before they start their turn, their hand is:\n"+
                    "1.Card is face down. 2.Card is face down. 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+

                        "\nPlease pick a card in you hand to flip: Player jo must flip 2 cards before they start their turn, their hand is:\n"+
                "1.hearts,king 2.Card is face down. 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+

                        "\nPlease pick a card in you hand to flip: Player jo must flip 2 cards before they start their turn, their hand is:\n"+

                        "\n\njo's current hand:\n" +
                                "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                                "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "\njay's current hand:\n"+
                                "1.Card is face down. 2.Card is face down. 3.Card is face down.\n"+
                                "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                                "\nThe deck has 40 remaining\n"+
                        "The discard pile is empty\n" +
                        "Player jo's Turn\n"+
                        "1. Print Game State Again\n"+
                        "2. Pick Up From Deck\n"+
                        "3. Pick Up From Discard\n"+
                        "4. Display Current Scores\n"+
                        "Or Enter -1 To Exit\n"+
                        "Enter Number To Proceed: "
        );
    }

    @Test
    void displayGameStateWithoutEmptyDiscard() {
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll((view).input, "2","2","jo","jay","-1","1","clear","2");
        GolfController controllerTest = new GolfController(view);
        controllerTest.gameStart();
//        ((FakeGolfCLIView)controllerTest.view).output = "";
        controllerTest.game.gameOver = false;
        controllerTest.game.turnOver = false;
        controllerTest.game.phase = new FlipPhase();
        controllerTest.game.deck.clear();
        Collections.addAll(controllerTest.game.deck,playingCard.createDeck());
        for(int i=0; i<controllerTest.game.players.length;i++){
            for(int j=0; j<6; j++ ){
                controllerTest.game.players[i].hand[j]=controllerTest.game.deck.remove(controllerTest.game.deck.size()-1);
            }
        }
        controllerTest.game.discard.add(controllerTest.game.deck.remove(controllerTest.game.deck.size()-1));
        controllerTest.game.discard.get(controllerTest.game.discard.size()-1).flipCard();
        controllerTest.gameRunner();
        assertThat(((FakeGolfCLIView)controllerTest.view).output).isEqualTo(
                        "Player jo must flip 2 cards before they start their turn, their hand is:\n"+
                "\n\njo's current hand:\n" +
                                "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                                "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "\njay's current hand:\n"+
                                "1.Card is face down. 2.Card is face down. 3.Card is face down.\n"+
                                "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                                "\nThe deck has 39 remaining\n"+
                        "The top card on the discard pile is hearts,ace\n" +
                        "Player jo's Turn\n"+
                        "1. Print Game State Again\n"+
                        "2. Pick Up From Deck\n"+
                        "3. Pick Up From Discard\n"+
                        "4. Display Current Scores\n"+
                        "Or Enter -1 To Exit\n"+
                        "Enter Number To Proceed: "
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
                        "4. Display Current Scores\n"+
        "Or Enter -1 To Exit\n"+
        "Enter Number To Proceed: "+                "1. Print Game State Again\n"+
        "2. Pick Up From Deck\n"+
                "3. Pick Up From Discard\n"+
                        "4. Display Current Scores\n"+
                "Or Enter -1 To Exit\n"+
                "Enter Number To Proceed: "+                "1. Print Game State Again\n"+
        "2. Pick Up From Deck\n"+
                "3. Pick Up From Discard\n"+
                        "4. Display Current Scores\n"+
                "Or Enter -1 To Exit\n"+
                "Enter Number To Proceed: "+                "1. Print Game State Again\n"+
                        "2. Pick Up From Deck\n"+
                        "3. Pick Up From Discard\n"+
                        "4. Display Current Scores\n"+
                        "Or Enter -1 To Exit\n"+
                        "Enter Number To Proceed: "+                "1. Print Game State Again\n"+
                        "2. Pick Up From Deck\n"+
                        "3. Pick Up From Discard\n"+
                        "4. Display Current Scores\n"+
                        "Or Enter -1 To Exit\n"+
                        "Enter Number To Proceed: "
        );
    }

    @Test
    void promptDiscard() {
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll((view).input, "4", "1", "jo", "jay", "josh", "jim");
        GolfController controller = new GolfController(view);
        controller.gameStart();
        controller.game.turn = 1;
        controller.game.gameOver = false;
        controller.game.turnOver = false;
        controller.game.phase = new FlipPhase();
        controller.game.deck.clear();
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        for (int i = 0; i < controller.game.players.length; i++) {
            for (int j = 0; j < 6; j++) {
                controller.game.players[i].hand[j] = controller.game.deck.remove(controller.game.deck.size() - 1);
            }
        }
            Collections.addAll(((FakeGolfCLIView)controller.view).input,"1", "2", "-1");
            controller.gameRunner();
            Collections.addAll(((FakeGolfCLIView)controller.view).input,"2", "3", "-1", "0", "a");
            ((FakeGolfCLIView) controller.view).output = "";
            assertThat(view.promptDiscard(controller.game)).isEqualTo(2);
            assertThat(view.promptDiscard(controller.game)).isEqualTo(3);
            assertThat(view.promptDiscard(controller.game)).isEqualTo(-1);
            InputMismatchException error = assertThrows(InputMismatchException.class, () -> {
                controller.view.promptDiscard(controller.game);
            });
            error = assertThrows(InputMismatchException.class, () -> {
                controller.view.promptDiscard(controller.game);
            });
            assertThat(((FakeGolfCLIView) controller.view).output).isEqualTo(
                    "Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                            "Enter 7 To Discard Chosen Card.\n" +
                            "Or -1 To Exit\n" +
                            "1.hearts,king 2.hearts,queen 3.Card is face down.\n" +
                            "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +
                            "Enter Number To Proceed: " +

                            "Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                            "Enter 7 To Discard Chosen Card.\n" +
                            "Or -1 To Exit\n" +
                            "1.hearts,king 2.hearts,queen 3.Card is face down.\n" +
                            "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +
                            "Enter Number To Proceed: " +

                            "Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                            "Enter 7 To Discard Chosen Card.\n" +
                            "Or -1 To Exit\n" +
                            "1.hearts,king 2.hearts,queen 3.Card is face down.\n" +
                            "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +
                            "Enter Number To Proceed: " +

                            "Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                            "Enter 7 To Discard Chosen Card.\n" +
                            "Or -1 To Exit\n" +
                            "1.hearts,king 2.hearts,queen 3.Card is face down.\n" +
                            "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +
                            "Enter Number To Proceed: " +

                            "Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                            "Enter 7 To Discard Chosen Card.\n" +
                            "Or -1 To Exit\n" +
                            "1.hearts,king 2.hearts,queen 3.Card is face down.\n" +
                            "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +
                            "Enter Number To Proceed: "
            );
        }

    @Test
    void promptFlip() {
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll((view).input, "4", "1", "jo", "jay", "josh", "jim");
        GolfController controller = new GolfController(view);
        controller.gameStart();
        controller.game.turn = 1;
        controller.game.gameOver = false;
        controller.game.turnOver = false;
        controller.game.phase = new FlipPhase();
        controller.game.deck.clear();
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        for (int i = 0; i < controller.game.players.length; i++) {
            for (int j = 0; j < 6; j++) {
                controller.game.players[i].hand[j] = controller.game.deck.remove(controller.game.deck.size() - 1);
            }
        }
        Collections.addAll(((FakeGolfCLIView)controller.view).input,"1", "2", "-1");
        controller.gameRunner();
        Collections.addAll(((FakeGolfCLIView)controller.view).input,"2", "3", "-1", "0", "a","7","-434");
        ((FakeGolfCLIView) controller.view).output = "";
        assertThat(view.promptFlip(controller.game)).isEqualTo(2);
        assertThat(view.promptFlip(controller.game)).isEqualTo(3);
        assertThat(view.promptFlip(controller.game)).isEqualTo(-1);
        InputMismatchException error = assertThrows(InputMismatchException.class, () -> {
            controller.view.promptFlip(controller.game);
        });
        error = assertThrows(InputMismatchException.class, () -> {
            controller.view.promptFlip(controller.game);
        });
        error = assertThrows(InputMismatchException.class, () -> {
            controller.view.promptFlip(controller.game);
        });
        assertThat(((FakeGolfCLIView) controller.view).output).isEqualTo(
                "\nPlease pick a card in you hand to flip: "+
                        "\nPlease pick a card in you hand to flip: "+
                        "\nPlease pick a card in you hand to flip: "+
                        "\nPlease pick a card in you hand to flip: "+
                        "\nPlease pick a card in you hand to flip: "+
                        "\nPlease pick a card in you hand to flip: "
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
        Collections.addAll(((FakeGolfCLIView)controllerTest.view).input,"4", "1", "jo", "jay", "josh", "jim","-1");
        controllerTest.gameStart();
        ((FakeGolfCLIView)controllerTest.view).clearVars();
        controllerTest.game.turn = 1;
        controllerTest.game.gameOver = false;
        controllerTest.game.turnOver = false;
        controllerTest.game.phase = new FlipPhase();
        controllerTest.game.deck.clear();
        Collections.addAll(controllerTest.game.deck, playingCard.createDeck());
        for (int i = 0; i < controllerTest.game.players.length; i++) {
            for (int j = 0; j < 6; j++) {
                controllerTest.game.players[i].hand[j] = controllerTest.game.deck.remove(controllerTest.game.deck.size() - 1);
            }
        }
        Collections.addAll(((FakeGolfCLIView)controllerTest.view).input,"1","2");
        controllerTest.gameRunner();
        ((FakeGolfCLIView)controllerTest.view).clearVars();
        controllerTest.view.displayHand(controllerTest.game);
        assertThat(((FakeGolfCLIView)controllerTest.view).output).isEqualTo(
                "1.hearts,king 2.hearts,queen 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"
        );
    }

    @Test
    void displayScoreBoard(){
        GolfGameModel game = new GolfGameModel(2,1);
        FakeGolfCLIView view = new FakeGolfCLIView();
        GolfPlayerModel player = new GolfPlayerModel("jay");
        GolfPlayerModel player1 = new GolfPlayerModel("josh");
        GolfPlayerModel[] score = {player,player1};
        game.scoreBoard = score;
        view.displayScoreBoard(game);
        assertThat(view.output).isEqualTo("\n-----SCOREBOARD-----\n" +
                        "1. jay:   score for the hole: 0 points   score for the rest of the game: 0 points\n" +
                        "2. josh:   score for the hole: 0 points   score for the rest of the game: 0 points\n"+
                        "You are currently on hole 1 of a 1 hole game.\n"
                );

    }

    @Test
    void spacerTest(){
        FakeGolfCLIView view = new FakeGolfCLIView();
        view.spacer();
        assertThat(((FakeGolfCLIView)view).output).isEqualTo("\n");
    }
}