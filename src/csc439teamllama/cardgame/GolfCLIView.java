package csc439teamllama.cardgame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GolfCLIView extends GolfView {
    private final Scanner in;

    public GolfCLIView() {
        in = new Scanner(System.in);
    }

    @Override
    protected void TitleScreen() {
        System.out.println(
                "     ,o888888o.        ,o888888o.     8 8888         8 8888888888   \n" +
                        "    8888     `88.   . 8888     `88.   8 8888         8 8888         \n" +
                        " ,8 8888       `8. ,8 8888       `8b  8 8888         8 8888         \n" +
                        " 88 8888           88 8888        `8b 8 8888         8 8888         \n" +
                        " 88 8888           88 8888         88 8 8888         8 888888888888 \n" +
                        " 88 8888           88 8888         88 8 8888         8 8888         \n" +
                        " 88 8888   8888888 88 8888        ,8P 8 8888         8 8888         \n" +
                        " `8 8888       .8' `8 8888       ,8P  8 8888         8 8888         \n" +
                        "    8888     ,88'   ` 8888     ,88'   8 8888         8 8888         \n" +
                        "     `8888888P'        `8888888P'     8 888888888888 8 8888         \n");
    }

    @Override
    protected int GameStartOptions() {
        System.out.print("How many players for this game: ");
        return in.nextInt();
    }

    //  4 As a player, on each turn, I would like the game to display who’s turn it is, their hand, the
//  draw pile and last discarded card
    @Override
    protected void DisplayGameState(GolfGameModel game) {
        int index = 1;
        System.out.println("Player" + index + "s Turn");
        System.out.println("1."+game.players[game.PlayerIndex()].hand[0].toString()+ " 2." + game.players[game.PlayerIndex()].hand[1].toString()+ " 3." + game.players[game.PlayerIndex()].hand[2].toString());
        System.out.println("4."+game.players[game.PlayerIndex()].hand[3].toString()+ " 5." + game.players[game.PlayerIndex()].hand[4].toString()+ " 6." + game.players[game.PlayerIndex()].hand[5].toString());
        System.out.println("The deck has " + game.deck.size() + " remaining");

        if(game.discard.size() == 0){
            System.out.println("The discard pile is empty");
        }
        else{
            System.out.println("The top card on the discard pile is " + game.discard.get(game.discard.size()-1));
        }
    }

    @Override
    protected int PromptDecision() throws InputMismatchException {
        System.out.println("1. Print Game State Again");
        System.out.println("2. Pick Up From Deck");
        System.out.println("3. Pick Up From Discard");
        System.out.print("Enter Number To Proceed: ");
        int option = in.nextInt();
        if(option > 3 || option < 1){
            throw new InputMismatchException();
        }
        return option;
    }

    @Override
    protected String PromptDiscard() {
        return null;
    }

    @Override
    protected void SendMessageToPlayer() {

    }
}
