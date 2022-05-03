package csc439teamllama.cardgame;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.google.common.truth.Truth.assertThat;

class GolfControllerTest {

    @Test
    void gameStart() {
        GolfController controller = new GolfController(new FakeGolfCLIView());
        Collections.addAll(((FakeGolfCLIView) controller.view).input, "10", "1", "p1", "P2", "P3", "P4", "p5", "p6", "p7", "p8", "p9", "p10", "-1", "1", "2", "-1", "2", "1", "p1", "p2", "-1");
        controller.gameStart();
        controller.game.gameOver = false;
        controller.game.turnOver = false;
        controller.game.turn = 1;
        controller.game.phase = new FlipPhase();
        controller.game.deck.clear();
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        for (int i = 0; i < controller.game.players.length; i++) {
            for (int j = 0; j < 6; j++) {
                controller.game.players[i].hand[j] = controller.game.deck.remove(controller.game.deck.size() - 1);
            }
        }
        controller.gameRunner();
        assertThat(controller.game.deck.size()).isEqualTo(44);
        assertThat(controller.game.players[0].hand[0].getFacing()).isEqualTo(playingCard.Facing.UP);
        assertThat(controller.game.players[5].hand[5].getFacing()).isEqualTo(playingCard.Facing.DOWN);
        assertThat(controller.game.players[0].hand[0].getSuit()).isEqualTo(playingCard.Suit.HEARTS);
        assertThat(controller.game.players[0].hand[0].getNumber()).isEqualTo(playingCard.Number.KING);
        assertThat(controller.game.players[6].hand[4].getSuit()).isEqualTo(playingCard.Suit.SPADES);
        assertThat(controller.game.players[6].hand[4].getNumber()).isEqualTo(playingCard.Number.QUEEN);
        assertThat(controller.game.players[9].hand[3].getSuit()).isEqualTo(playingCard.Suit.HEARTS);
        assertThat(controller.game.players[9].hand[3].getNumber()).isEqualTo(playingCard.Number.EIGHT);
        controller.gameStart();
        assertThat(controller.game.deck.size()).isEqualTo(40);
    }

    @Test
    void ControllerPromptReDisplay() {
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll(view.input, "4", "1", "p1", "P2", "P3", "P4", "-1", "1","clear", "1");
        GolfController controller = new GolfController(view);
        controller.gameStart();
        controller.game.gameOver = false;
        controller.game.turnOver = false;
        controller.game.turn = 1;
        controller.game.phase = new FlipPhase();
        controller.game.deck.clear();
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        for (int i = 0; i < controller.game.players.length; i++) {
            for (int j = 0; j < 6; j++) {
                controller.game.players[i].hand[j] = controller.game.deck.remove(controller.game.deck.size() - 1);
            }
        }
        controller.game.players[0].hand = new playingCard[]{new playingCard(playingCard.Facing.UP, playingCard.Suit.SPADES, playingCard.Number.ACE),
                new playingCard(playingCard.Facing.UP, playingCard.Suit.DIAMONDS, playingCard.Number.KING),
                new playingCard(playingCard.Facing.UP, playingCard.Suit.CLUBS, playingCard.Number.TWO),
                new playingCard(true, 4, 12),
                new playingCard(true, 4, 3),
                new playingCard(true, 3, 11)};
        controller.gameRunner();
        assertThat(((FakeGolfCLIView) controller.view).output).isEqualTo(
                "\n\np1's current hand:\n" +
                        "1.spades,ace 2.diamonds,king 3.clubs,two\n"+
                        "4.hearts,queen 5.hearts,three 6.clubs,jack\n"+
                        "\nP2's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "\nP3's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "\nP4's current hand:\n"+
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n" +
                                "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "\nThe deck has 28 remaining\n"+
                        "The discard pile is empty\n" +
                        "Player p1's Turn\n" +
                        "1. Print Game State Again\n" +
                        "2. Pick Up From Deck\n" +
                        "3. Pick Up From Discard\n" +
                        "4. Display Current Scores\n" +
                        "Or Enter -1 To Exit\n" +
                        "Enter Number To Proceed: ");
    }

    @Test
    void PlayerExitsDuringGameOptions() {
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll(view.input, "-1");
        GolfController controller = new GolfController(view);
        controller.gameStart();
        assertThat(((FakeGolfCLIView) controller.view).output).isEqualTo(
                "     ,o888888o.        ,o888888o.     8 8888         8 8888888888   \n" +
                        "    8888     `88.   . 8888     `88.   8 8888         8 8888         \n" +
                        " ,8 8888       `8. ,8 8888       `8b  8 8888         8 8888         \n" +
                        " 88 8888           88 8888        `8b 8 8888         8 8888         \n" +
                        " 88 8888           88 8888         88 8 8888         8 888888888888 \n" +
                        " 88 8888           88 8888         88 8 8888         8 8888         \n" +
                        " 88 8888   8888888 88 8888        ,8P 8 8888         8 8888         \n" +
                        " `8 8888       .8' `8 8888       ,8P  8 8888         8 8888         \n" +
                        "    8888     ,88'   ` 8888     ,88'   8 8888         8 8888         \n" +
                        "     `8888888P'        `8888888P'     8 888888888888 8 8888         \n\n" +
                        "\nHow many players for this game: How many holes for this game: "
        );
        assertThat(controller.game).isNull();
    }

    @Test
    void PlayerExitsDuringDecision() {
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll(view.input, "5", "1", "p1", "P2", "P3", "P4", "p5", "-1", "1", "2");
        GolfController controller = new GolfController(view);
        controller.gameStart();
        controller.game.gameOver = false;
        controller.game.turnOver = false;
        controller.game.turn = 1;
        controller.game.phase = new FlipPhase();
        controller.game.deck.clear();
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        for (int i = 0; i < controller.game.players.length; i++) {
            for (int j = 0; j < 6; j++) {
                controller.game.players[i].hand[j] = controller.game.deck.remove(controller.game.deck.size() - 1);
            }
        }
        controller.gameRunner();
        ((FakeGolfCLIView) controller.view).output = "";
        controller.game.gameOver = false;
        controller.game.turnOver = false;
        controller.game.turn = 1;
        controller.game.phase = new FlipPhase();
        controller.gameRunner();
        assertThat(((FakeGolfCLIView) controller.view).output).isEqualTo(
                "\n\np1's current hand:\n" +
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +

                        "\nP2's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +

                        "\nP3's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +

                        "\nP4's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +

                        "\np5's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +
                        "\nThe deck has 22 remaining\n" +
                        "The discard pile is empty\n" +
                        "Player p1's Turn\n" +
                        "1. Print Game State Again\n" +
                        "2. Pick Up From Deck\n" +
                        "3. Pick Up From Discard\n" +
                        "4. Display Current Scores\n" +
                        "Or Enter -1 To Exit\n" +
                        "Enter Number To Proceed: ");
    }

    @Test
    void PlayerExitsDuringGameDiscard() {
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll(view.input, "5", "2", "-1");
        GolfController controller = new GolfController(view);
        controller.gameStart();
        assertThat(((FakeGolfCLIView) controller.view).output).isEqualTo(
                "     ,o888888o.        ,o888888o.     8 8888         8 8888888888   \n" +
                        "    8888     `88.   . 8888     `88.   8 8888         8 8888         \n" +
                        " ,8 8888       `8. ,8 8888       `8b  8 8888         8 8888         \n" +
                        " 88 8888           88 8888        `8b 8 8888         8 8888         \n" +
                        " 88 8888           88 8888         88 8 8888         8 888888888888 \n" +
                        " 88 8888           88 8888         88 8 8888         8 8888         \n" +
                        " 88 8888   8888888 88 8888        ,8P 8 8888         8 8888         \n" +
                        " `8 8888       .8' `8 8888       ,8P  8 8888         8 8888         \n" +
                        "    8888     ,88'   ` 8888     ,88'   8 8888         8 8888         \n" +
                        "     `8888888P'        `8888888P'     8 888888888888 8 8888         \n\n" +
                        "How many players for this game: " + "Player 1's Turn\n" +
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +
                        "The deck has 74 remaining\n" +
                        "The discard pile is empty\n" +
                        "1. Print Game State Again\n" +
                        "2. Pick Up From Deck\n" +
                        "3. Pick Up From Discard\n" +
                        "Or Enter -1 To Exit\n" +
                        "Enter Number To Proceed: " +
                        "Drawn card: diamonds,nine From: Deck\n" +
                        "Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                        "Enter 7 To Discard Chosen Card.\n" +
                        "Or -1 To Exit\n" +
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +
                        "Enter Number To Proceed: ");
    }

    @Test
    void TryToDrawDiscardEmpty() {
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll(view.input, "5", "1", "p1", "P2", "P3", "P4", "p5", "-1", "1", "2", "-1", "3");
        GolfController controller = new GolfController(view);
        controller.gameStart();
        controller.game.gameOver = false;
        controller.game.turnOver = false;
        controller.game.turn = 1;
        controller.game.phase = new FlipPhase();
        controller.game.deck.clear();
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        for (int i = 0; i < controller.game.players.length; i++) {
            for (int j = 0; j < 6; j++) {
                controller.game.players[i].hand[j] = controller.game.deck.remove(controller.game.deck.size() - 1);
            }
        }
        controller.gameRunner();
        ((FakeGolfCLIView) controller.view).output = "";
        controller.game.gameOver = false;
        controller.game.turnOver = false;
        controller.game.turn = 1;
        controller.game.phase = new FlipPhase();
        controller.gameRunner();
        assertThat(((FakeGolfCLIView) controller.view).output).isEqualTo(
                "\n\np1's current hand:\n" +
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +

                        "\nP2's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +

                        "\nP3's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +

                        "\nP4's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +

                        "\np5's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +
                        "\nThe deck has 74 remaining\n" +
                        "The discard pile is empty\n" +
                        "Player p1's Turn\n" +
                        "1. Print Game State Again\n" +
                        "2. Pick Up From Deck\n" +
                        "3. Pick Up From Discard\n" +
                        "4. Display Current Scores\n" +
                        "Or Enter -1 To Exit\n" +
                        "Enter Number To Proceed: " +
                        "Discard Is Empty! Please Draw from Deck\n" +
                        "1. Print Game State Again\n" +
                        "2. Pick Up From Deck\n" +
                        "3. Pick Up From Discard\n" +
                        "4. Display Current Scores\n" +
                        "Or Enter -1 To Exit\n" +
                        "Enter Number To Proceed: "
        );
    }

    @Test
    void TryToDrawDeckEmpty() {
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll(view.input, "5", "1", "p1", "P2", "P3", "P4", "p5", "-1", "1", "2", "-1", "2");
        GolfController controller = new GolfController(view);
        controller.gameStart();
        controller.game.gameOver = false;
        controller.game.turnOver = false;
        controller.game.turn = 1;
        controller.game.phase = new FlipPhase();
        controller.game.deck.clear();
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        for (int i = 0; i < controller.game.players.length; i++) {
            for (int j = 0; j < 6; j++) {
                controller.game.players[i].hand[j] = controller.game.deck.remove(controller.game.deck.size() - 1);
            }
        }
        controller.gameRunner();
        ((FakeGolfCLIView) controller.view).output = "";
        controller.game.gameOver = false;
        controller.game.turnOver = false;
        controller.game.turn = 1;
        controller.game.phase = new FlipPhase();
        controller.game.discard.addAll(controller.game.deck);
        controller.game.deck.clear();
        controller.gameRunner();
        assertThat(((FakeGolfCLIView) controller.view).output).isEqualTo(
                "\n\np1's current hand:\n" +
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +

                        "\nP2's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +

                        "\nP3's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +

                        "\nP4's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +

                        "\np5's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +
                        "\nThe deck has 0 remaining\n" +
                        "The top card on the discard pile is Card is face down.\n" +
                        "Player p1's Turn\n" +
                        "1. Print Game State Again\n" +
                        "2. Pick Up From Deck\n" +
                        "3. Pick Up From Discard\n" +
                        "4. Display Current Scores\n" +
                        "Or Enter -1 To Exit\n" +
                        "Enter Number To Proceed: " +
                        "Deck Is Empty! Please Draw from Discard\n" +
                        "1. Print Game State Again\n" +
                        "2. Pick Up From Deck\n" +
                        "3. Pick Up From Discard\n" +
                        "4. Display Current Scores\n" +
                        "Or Enter -1 To Exit\n" +
                        "Enter Number To Proceed: "
        );
    }

    @Test
    void OutOfBoundsGameOptions() {
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll(view.input, "0", "-2", "sddsddsd");
        GolfController controller = new GolfController(view);
        controller.gameStart();
        assertThat(((FakeGolfCLIView) controller.view).output).isEqualTo(
                "     ,o888888o.        ,o888888o.     8 8888         8 8888888888   \n" +
                        "    8888     `88.   . 8888     `88.   8 8888         8 8888         \n" +
                        " ,8 8888       `8. ,8 8888       `8b  8 8888         8 8888         \n" +
                        " 88 8888           88 8888        `8b 8 8888         8 8888         \n" +
                        " 88 8888           88 8888         88 8 8888         8 888888888888 \n" +
                        " 88 8888           88 8888         88 8 8888         8 8888         \n" +
                        " 88 8888   8888888 88 8888        ,8P 8 8888         8 8888         \n" +
                        " `8 8888       .8' `8 8888       ,8P  8 8888         8 8888         \n" +
                        "    8888     ,88'   ` 8888     ,88'   8 8888         8 8888         \n" +
                        "     `8888888P'        `8888888P'     8 888888888888 8 8888         \n\n" +
                        "How many players for this game: " +
                        "please input a number: greater than 0\n" +
                        "How many players for this game: " +
                        "please input a number: greater than 0\n" +
                        "How many players for this game: " +
                        "please input a number: greater than 0\n" +
                        "How many players for this game: "
        );
        assertThat(controller.game).isNull();
    }

    @Test
    void OutOfBoundsDecision() {
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll(view.input, "3", "2", "joe", "jay", "josh","-1","1","1","clear","-44","2","-3");
        GolfController controller = new GolfController(view);
        controller.gameStart();
        controller.game.gameOver = false;
        controller.game.turnOver = false;
        controller.game.turn = 1;
        controller.game.phase = new FlipPhase();
        controller.game.deck.clear();
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        for (int i = 0; i < controller.game.players.length; i++) {
            for (int j = 0; j < 6; j++) {
                controller.game.players[i].hand[j] = controller.game.deck.remove(controller.game.deck.size() - 1);
            }
        }
        controller.gameRunner();
        assertThat(((FakeGolfCLIView) controller.view).output).isEqualTo(
                "Please enter a number between 1 and 6\n"+
                        "Player joe must flip 2 cards before they start their turn, their hand is:\n"+
                        "1.hearts,king 2.Card is face down. 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "\nPlease pick a card in you hand to flip: \n"+
                        "\njoe's current hand:\n" +
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +

                        "\njay's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +

                        "\njosh's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +
                        "\nThe deck has 34 remaining" +
                        "\nThe discard pile is empty" +
                        "\nPlayer joe's Turn\n" +
                        "1. Print Game State Again\n" +
                        "2. Pick Up From Deck\n" +
                        "3. Pick Up From Discard\n" +
                        "4. Display Current Scores\n" +
                        "Or Enter -1 To Exit\n" +
                        "Enter Number To Proceed: "+
                        "please input a number between 1 and 4\n"+
                "1. Print Game State Again\n" +
                        "2. Pick Up From Deck\n" +
                        "3. Pick Up From Discard\n" +
                        "4. Display Current Scores\n" +
                        "Or Enter -1 To Exit\n" +
                        "Enter Number To Proceed: "

        );
    }

    @Test
    void OutOfBoundsDiscard() {
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll(view.input, "6","1","p1", "P2", "P3", "P4", "p5", "p6", "-1","1","clear","2", "2", "0", "44", "abassjhd");
        GolfController controller = new GolfController(view);
        controller.gameStart();
        controller.game.gameOver = false;
        controller.game.turnOver = false;
        controller.game.turn = 1;
        controller.game.phase = new FlipPhase();
        controller.game.deck.clear();
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        for (int i = 0; i < controller.game.players.length; i++) {
            for (int j = 0; j < 6; j++) {
                controller.game.players[i].hand[j] = controller.game.deck.remove(controller.game.deck.size() - 1);
            }
        }
        controller.gameRunner();
        assertThat(((FakeGolfCLIView) controller.view).output).isEqualTo(
                "\n\np1's current hand:\n" +
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +

                        "\nP2's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +

                        "\nP3's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +

                        "\nP4's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +

                        "\np5's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +

                        "\np6's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +

                        "\nThe deck has 68 remaining\n" +
                        "The discard pile is empty\n" +
                        "Player p1's Turn\n"+
                        "1. Print Game State Again\n" +
                        "2. Pick Up From Deck\n" +
                        "3. Pick Up From Discard\n" +
                        "4. Display Current Scores\n" +
                        "Or Enter -1 To Exit\n" +
                        "Enter Number To Proceed: " +
                        "Drawn card: diamonds,three From: Deck\n" +
                        "Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                        "Enter 7 To Discard Chosen Card.\n" +
                        "Or -1 To Exit\n" +
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +
                        "Enter Number To Proceed: " +
                        "please input a number between 1 and 7\n" +
                        "Drawn card: diamonds,three From: Deck\n" +
                        "Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                        "Enter 7 To Discard Chosen Card.\n" +
                        "Or -1 To Exit\n" +
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +
                        "Enter Number To Proceed: " +
                        "please input a number between 1 and 7\n" +
                        "Drawn card: diamonds,three From: Deck\n" +
                        "Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                        "Enter 7 To Discard Chosen Card.\n" +
                        "Or -1 To Exit\n" +
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +
                        "Enter Number To Proceed: " +
                        "please input a number between 1 and 7\n" +
                        "Drawn card: diamonds,three From: Deck\n" +
                        "Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                        "Enter 7 To Discard Chosen Card.\n" +
                        "Or -1 To Exit\n" +
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +
                        "Enter Number To Proceed: "
        );
    }

    @Test
    void discardToDiscardFromItself() {
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll(view.input, "4","1","p1", "P2", "P3", "P4", "p5","-1","1","2","3","clear","7");
        GolfController controller = new GolfController(view);
        controller.gameStart();
        controller.game.gameOver = false;
        controller.game.turnOver = false;
        controller.game.turn = 1;
        controller.game.phase = new FlipPhase();
        controller.game.deck.clear();
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        for (int i = 0; i < controller.game.players.length; i++) {
            for (int j = 0; j < 6; j++) {
                controller.game.players[i].hand[j] = controller.game.deck.remove(controller.game.deck.size() - 1);
            }
        }
        ((FakeGolfCLIView) controller.view).output = "";
        controller.game.discard.add(new playingCard(true, 1, 1));
        controller.gameRunner();
        assertThat(((FakeGolfCLIView) controller.view).output).isEqualTo(
                        "You Picked This Card From Discard! Please Discard A Card From Your Hand.\n" +
                        "Drawn card: spades,ace From: Discard\n" +
                        "Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                        "Enter 7 To Discard Chosen Card.\n" +
                        "Or -1 To Exit\n" +
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +
                        "Enter Number To Proceed: "
        );
    }

    @Test
    void drawDeckDiscardFromDeck() {
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll(view.input, "5","1", "p1", "P2", "P3", "P4", "p5","-1","1","2","clear", "2", "7");
        GolfController controller = new GolfController(view);
        controller.gameStart();
        controller.game.gameOver = false;
        controller.game.turnOver = false;
        controller.game.turn = 1;
        controller.game.phase = new FlipPhase();
        controller.game.deck.clear();
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        for (int i = 0; i < controller.game.players.length; i++) {
            for (int j = 0; j < 6; j++) {
                controller.game.players[i].hand[j] = controller.game.deck.remove(controller.game.deck.size() - 1);
            }
        }
        controller.gameRunner();
        assertThat(controller.game.discard.remove(0).toString()).isEqualTo("diamonds,nine");
        assertThat(((FakeGolfCLIView) controller.view).output).isEqualTo(
                "Drawn card: diamonds,nine From: Deck\n" +
                        "Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                        "Enter 7 To Discard Chosen Card.\n" +
                        "Or -1 To Exit\n" +
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +
                        "Enter Number To Proceed: " +
                        "\n\np1's current hand:\n" +
                        "1.hearts,king 2.hearts,queen 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +

                        "\nP2's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +

                        "\nP3's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +

                        "\nP4's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +

                        "\np5's current hand:\n" +
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n" +
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n" +
                        "\nThe deck has 73 remaining\n" +
                        "The top card on the discard pile is diamonds,nine\n" +
                        "Player p1's Turn\n" +
                        "p1's turn over, press enter to move on to next player\n"+
                        "\nPlayer P2 must flip 2 cards before they start their turn, their hand is:\n"+
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                "\nPlease pick a card in you hand to flip: "
        );
    }

    @Test
    void DrawDeckDiscardFromHand() {
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll(view.input, "3","1", "p1", "P2", "P3","-1","1","2","2", "3");
        GolfController controller = new GolfController(view);
        controller.gameStart();
        controller.game.gameOver = false;
        controller.game.turnOver = false;
        controller.game.turn = 1;
        controller.game.phase = new FlipPhase();
        controller.game.deck.clear();
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        for (int i = 0; i < controller.game.players.length; i++) {
            for (int j = 0; j < 6; j++) {
                controller.game.players[i].hand[j] = controller.game.deck.remove(controller.game.deck.size() - 1);
            }
        }
        controller.gameRunner();
        assertThat(controller.game.discard.remove(0).toString()).isEqualTo("hearts,jack");
        assertThat(controller.game.players[0].hand[2].toString()).isEqualTo("clubs,eight");
        controller.game.players[2].hand[5].flipCard();
        assertThat(controller.game.players[2].hand[5].toString()).isEqualTo("clubs,nine");
    }

    @Test
    void DrawDiscardPileDiscardFromHand() {
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll(view.input, "6", "1", "p1", "P2", "P3", "P4", "p5", "p6", "-1", "1", "2", "2", "6", "1", "2", "3", "6", "-1");
        GolfController controller = new GolfController(view);
        controller.gameStart();
        controller.game.gameOver = false;
        controller.game.turnOver = false;
        controller.game.turn = 1;
        controller.game.phase = new FlipPhase();
        controller.game.deck.clear();
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        for (int i = 0; i < controller.game.players.length; i++) {
            for (int j = 0; j < 6; j++) {
                controller.game.players[i].hand[j] = controller.game.deck.remove(controller.game.deck.size() - 1);
            }
        }
        controller.gameRunner();
        assertThat(controller.game.discard.remove(0).toString()).isEqualTo("hearts,two");
        assertThat(controller.game.players[0].hand[5].toString()).isEqualTo("diamonds,three");
        assertThat(controller.game.players[1].hand[5].toString()).isEqualTo("hearts,eight");
    }

    @Test
    void TurnChangeTest() {
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll(view.input, "6", "1", "p1", "P2", "P3", "P4", "p5", "p6", "-1", "1", "2", "2", "6","1","2", "3", "6","1","2", "2", "7");
        GolfController controller = new GolfController(view);
        controller.gameStart();
        controller.game.gameOver = false;
        controller.game.turnOver = false;
        controller.game.turn = 1;
        controller.game.phase = new FlipPhase();
        controller.game.deck.clear();
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        for (int i = 0; i < controller.game.players.length; i++) {
            for (int j = 0; j < 6; j++) {
                controller.game.players[i].hand[j] = controller.game.deck.remove(controller.game.deck.size() - 1);
            }
        }
        controller.gameRunner();
        assertThat(controller.game.turn).isEqualTo(4);
        assertThat(controller.game.players[0].hand[5].toString()).isEqualTo("diamonds,three");
        assertThat(controller.game.players[1].hand[5].toString()).isEqualTo("hearts,eight");
        assertThat(controller.game.discard.get(controller.game.discard.size() - 1).toString()).isEqualTo("diamonds,two");

    }

    //make test for option 4 in draw phase, displayscoreboard
    @Test
    void optionDisplayScoreboard(){
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll(view.input, "2", "2", "p1", "p2", "-1","-1","4");
        GolfController controller = new GolfController(view);
        controller.gameStart();

        controller.game.gameOver = false;
        controller.game.turnOver = false;
        controller.game.turn = 1;
        controller.game.phase = new EndPhase();
        controller.game.deck.clear();
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        for (int i = 0; i < controller.game.players.length; i++) {
            for (int j = 0; j < 6; j++) {
                controller.game.players[i].hand[j] = controller.game.deck.remove(controller.game.deck.size() - 1);
                controller.game.players[i].hand[j].setFacing(playingCard.Facing.UP);
            }
        }
        controller.gameRunner();
        controller.game.gameOver = false;
        controller.game.turnOver = false;
        controller.game.phase = new FlipPhase();
        controller.game.deck.clear();
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        Collections.reverse(controller.game.deck);
        for (int i = 0; i < controller.game.players.length; i++) {
            for (int j = 0; j < 6; j++) {
                controller.game.players[i].hand[j] = controller.game.deck.remove(controller.game.deck.size() - 1);
                controller.game.players[i].hand[j].setFacing(playingCard.Facing.UP);
            }
        }
        ((FakeGolfCLIView) controller.view).output = "";
        controller.gameRunner();
        assertThat(((FakeGolfCLIView) controller.view).output).isEqualTo(
                "\n\np1's current hand:\n"+
                        "1.spades,ace 2.spades,two 3.spades,three\n" +
                        "4.spades,four 5.spades,five 6.spades,six\n"+

                        "\np2's current hand:\n" +
                        "1.spades,seven 2.spades,eight 3.spades,nine\n" +
                        "4.spades,ten 5.spades,jack 6.spades,queen\n" +

                        "\nThe deck has 40 remaining\n" +
                        "The discard pile is empty\n" +
                        "Player p1's Turn\n" +
                        "1. Print Game State Again\n" +
                        "2. Pick Up From Deck\n" +
                        "3. Pick Up From Discard\n" +
                        "4. Display Current Scores\n" +
                        "Or Enter -1 To Exit\n" +
                        "Enter Number To Proceed: " +
                        "\n-----SCOREBOARD-----\n" +
                        "1. p1:   score for the hole: 17 points   score for the rest of the game: 47 points\n" +
                        "2. p2:   score for the hole: 54 points   score for the rest of the game: 23 points\n" +
                        "You are currently on hole 2 of a 2 hole game.\n"+
                        "1. Print Game State Again\n" +
                        "2. Pick Up From Deck\n" +
                        "3. Pick Up From Discard\n" +
                        "4. Display Current Scores\n" +
                        "Or Enter -1 To Exit\n" +
                        "Enter Number To Proceed: "
        );
    }
    //make simple output test for end of hole
    @Test
    void endHoleOutputWithMoreHoles(){
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll(view.input, "2","2", "p1", "p2","-1");
        GolfController controller = new GolfController(view);
        controller.gameStart();
        ((FakeGolfCLIView)controller.view).output = "";
        controller.game.gameOver = false;
        controller.game.turnOver = false;
        controller.game.turn = 1;
        controller.game.phase = new EndPhase();
        controller.game.deck.clear();
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        for (int i = 0; i < controller.game.players.length; i++) {
            for (int j = 0; j < 6; j++) {
                controller.game.players[i].hand[j] = controller.game.deck.remove(controller.game.deck.size() - 1);
                controller.game.players[i].hand[j].setFacing(playingCard.Facing.UP);
            }
        }
        controller.gameRunner();
        assertThat(((FakeGolfCLIView)controller.view).output).isEqualTo(
                "\n\np1's current hand:\n"+
                        "1.hearts,king 2.hearts,queen 3.hearts,jack\n" +
                        "4.hearts,ten 5.hearts,nine 6.hearts,eight\n" +

                        "\np2's current hand:\n" +
                        "1.hearts,seven 2.hearts,six 3.hearts,five\n" +
                        "4.hearts,four 5.hearts,three 6.hearts,two\n" +

                        "\nThe deck has 40 remaining\n" +
                        "The discard pile is empty\n" +
                        "Player p1's Turn\n"+
                        "p1's turn over, as well as the hole! Press enter\n"+
                        "\nHole 1 over, moving to next hole.\n"+

                        "\nPlayer p1 must flip 2 cards before they start their turn, their hand is:\n"+
                        "1.Card is face down. 2.Card is face down. 3.Card is face down.\n"+
                        "4.Card is face down. 5.Card is face down. 6.Card is face down.\n"+
                        "\nPlease pick a card in you hand to flip: "
        );
    }

    @Test
    void endHoleOutputFinalHole() {
        FakeGolfCLIView view = new FakeGolfCLIView();
        Collections.addAll(view.input, "2", "2", "p1", "p2", "-1");
        GolfController controller = new GolfController(view);
        controller.gameStart();

        controller.game.gameOver = false;
        controller.game.turnOver = false;
        controller.game.turn = 1;
        controller.game.phase = new EndPhase();
        controller.game.deck.clear();
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        for (int i = 0; i < controller.game.players.length; i++) {
            for (int j = 0; j < 6; j++) {
                controller.game.players[i].hand[j] = controller.game.deck.remove(controller.game.deck.size() - 1);
                controller.game.players[i].hand[j].setFacing(playingCard.Facing.UP);
            }
        }
        controller.gameRunner();
        controller.game.gameOver = false;
        controller.game.turnOver = false;
        controller.game.phase = new EndPhase();
        controller.game.deck.clear();
        Collections.addAll(controller.game.deck, playingCard.createDeck());
        Collections.reverse(controller.game.deck);
        for (int i = 0; i < controller.game.players.length; i++) {
            for (int j = 0; j < 6; j++) {
                controller.game.players[i].hand[j] = controller.game.deck.remove(controller.game.deck.size() - 1);
                controller.game.players[i].hand[j].setFacing(playingCard.Facing.UP);
            }
        }
        ((FakeGolfCLIView) controller.view).output = "";
        controller.gameRunner();
        assertThat(((FakeGolfCLIView) controller.view).output).isEqualTo(
        "\n\np1's current hand:\n"+
                "1.spades,ace 2.spades,two 3.spades,three\n" +
                "4.spades,four 5.spades,five 6.spades,six\n"+

                "\np2's current hand:\n" +
                "1.spades,seven 2.spades,eight 3.spades,nine\n" +
                "4.spades,ten 5.spades,jack 6.spades,queen\n" +

                "\nThe deck has 40 remaining\n" +
                "The discard pile is empty\n" +
                "Player p1's Turn\n" +
                "p1's turn over, as well as the hole! Press enter\n" +

                "\nCongrats p1!\n" +

                "\n-----SCOREBOARD-----\n" +
                "1. p1:   score for the hole: 0 points   score for the rest of the game: 64 points\n" +
                "2. p2:   score for the hole: 0 points   score for the rest of the game: 77 points\n" +
                "You are currently on hole 2 of a 2 hole game.\n"
        );
    }
}