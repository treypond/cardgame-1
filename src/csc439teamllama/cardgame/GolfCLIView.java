package csc439teamllama.cardgame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GolfCLIView extends GolfView {
    private final Scanner in;

    public GolfCLIView() {
        in = new Scanner(System.in);
    }

    /**
     *Simple Title screen that displays the name of the game
     */
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

    /**
     * Prompts the user to input the number of player
     * @return the number of players playing the game
     * @throws InputMismatchException
     */
    @Override
    protected int GameStartOptions() throws InputMismatchException{
        System.out.print("How many players for this game: ");
        int option = in.nextInt();
        if( option < 1 && option != -1){
            throw new InputMismatchException();
        }
        return option;
    }

    //  4 As a player, on each turn, I would like the game to display who’s turn it is, their hand, the
//  draw pile and last discarded card

    /**
     * Displays the which players turn it is, their current, the number of remaining card in the deck and
     * the top card in the card pile
     * @param game
     */
    @Override
    protected void DisplayGameState(GolfGameModel game) {
        int playerNum = game.PlayerIndex()+1;
        System.out.println("Player " +playerNum+ "\'s Turn");
        DisplayHand(game);
        System.out.println("The deck has " + game.deck.size() + " remaining");

        if(game.discard.size() == 0){
            System.out.println("The discard pile is empty");
        }
        else{
            System.out.println("The top card on the discard pile is " + game.discard.get(game.discard.size()-1));
        }
    }

    /**
     * Display and prompts user for a decision. Also allows user to exit out of the game.
     * @return an int value which is related to the decision that were displayed
     * @throws InputMismatchException
     */
    @Override
    protected int PromptDecision() throws InputMismatchException {
        System.out.println("1. Print Game State Again");
        System.out.println("2. Pick Up From Deck");
        System.out.println("3. Pick Up From Discard");
        System.out.println("Or Enter -1 To Exit");
        System.out.print("Enter Number To Proceed: ");
        int option = in.nextInt();
        if(option > 3 || option < 1 && option != -1){
            throw new InputMismatchException();
        }
        return option;
    }

    /**
     * Displays player's hand and prompts the user to pick which card from their hand they want to discard
     * @param game
     * @return an int value which is related to the card in their hand that were displayed
     * @throws InputMismatchException
     */
    @Override
    protected int PromptDiscard(GolfGameModel game) throws InputMismatchException{
        System.out.println("Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                "Enter 7 To Discard Chosen Card.");
        System.out.println("Or -1 To Exit");
        DisplayHand(game);
        System.out.print("Enter Number To Proceed: ");
        int option = in.nextInt();
        if(option > 7 || option < 1 && option != -1){
            throw new InputMismatchException();
        }
        return option;
    }

    /**
     * Prints out a message to the user
     * @param message
     */
    @Override
    protected void SendMessageToPlayer(String message) {
        System.out.println(message);
    }

    /**
     * Displays the hand of the current player
     * @param game
     */
    @Override
    protected void DisplayHand(GolfGameModel game) {
        System.out.println("1."+game.players[game.PlayerIndex()].hand[0].toString()+ " 2." + game.players[game.PlayerIndex()].hand[1].toString()+ " 3." + game.players[game.PlayerIndex()].hand[2].toString());
        System.out.println("4."+game.players[game.PlayerIndex()].hand[3].toString()+ " 5." + game.players[game.PlayerIndex()].hand[4].toString()+ " 6." + game.players[game.PlayerIndex()].hand[5].toString());
    }

    /**
     *allow user to re-input decision
     */
    protected void ClearScanner(){
        in.nextLine();
    }
}
