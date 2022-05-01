package csc439teamllama.cardgame;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GolfCLIView extends GolfView {
    private final Scanner in;

    public GolfCLIView() {
        in = new Scanner(System.in);
    }

    /**Simple Title screen that displays the name of the game*/
    @Override
    protected void titleScreen() {
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
    protected int[] gameStartOptions() throws InputMismatchException{
        System.out.println();
        int[] options = new int[2];
        System.out.print("How many players for this game: ");
        options[0] = in.nextInt();
        if( options[0] < 1 && options[0] != -1){
            throw new InputMismatchException();
        }
        System.out.print("How many holes for this game: ");
        options[1] = in.nextInt();
        if( options[1] < 1 && options[1] != -1){
            throw new InputMismatchException();
        }
        return options;
    }

    //  4 As a player, on each turn, I would like the game to display who’s turn it is, their hand, the
//  draw pile and last discarded card

    /**
     * Displays the which players turn it is, their current, the number of remaining card in the deck and
     * the top card in the card pile
     * @param game
     */
    @Override
    protected void displayGameState(GolfGameModel game) {
        System.out.println();
        System.out.println("Current player hands:");
        for (GolfPlayerModel player: game.players
             ) {
            System.out.println();
            System.out.println(player.name+"'s current hand:");
            System.out.println("1."+player.hand[0].toString()+ " 2." + player.hand[1].toString()+ " 3." + player.hand[2].toString());
            System.out.println("4."+player.hand[3].toString()+ " 5." + player.hand[4].toString()+ " 6." + player.hand[5].toString());
        }
        System.out.println("\nThe deck has " + game.deck.size() + " remaining");

        if(game.discard.size() == 0){
            System.out.println("The discard pile is empty");
        }
        else{
            System.out.println("The top card on the discard pile is " + game.discard.get(game.discard.size()-1));
        }

        System.out.println("Player " +game.players[game.playerIndex()].name+ "\'s Turn\n");
    }

    /**
     * Display and prompts user for a decision. Also allows user to exit out of the game.
     * @return an int value which is related to the decision that were displayed
     * @throws InputMismatchException
     */
    @Override
    protected int promptDecision() throws InputMismatchException {
        System.out.println();
        System.out.println("1. Print Game State Again");
        System.out.println("2. Pick Up From Deck");
        System.out.println("3. Pick Up From Discard");
        System.out.println("4. Display Current Scores");
        System.out.println("Or Enter -1 To Exit");
        System.out.print("Enter Number To Proceed: ");
        int option = in.nextInt();
        if(option > 4 || option < 1 && option != -1){
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
    protected int promptDiscard(GolfGameModel game) throws InputMismatchException{
        System.out.println();
        System.out.println("Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                "Enter 7 To Discard Chosen Card.");
        System.out.println("Or -1 To Exit");
        displayHand(game);
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
    protected void sendMessageToPlayer(String message) {
        System.out.println();
        System.out.println(message);
    }

    /**
     * Displays the hand of the current player
     * @param game
     */
    @Override
    protected void displayHand(GolfGameModel game) {
        System.out.println();
        System.out.println("1."+game.players[game.playerIndex()].hand[0].toString()+ " 2." + game.players[game.playerIndex()].hand[1].toString()+ " 3." + game.players[game.playerIndex()].hand[2].toString());
        System.out.println("4."+game.players[game.playerIndex()].hand[3].toString()+ " 5." + game.players[game.playerIndex()].hand[4].toString()+ " 6." + game.players[game.playerIndex()].hand[5].toString());
    }

    /**
     * Display the ScoreBoard with the plays Name and Score. It also prints out what hole you
     * are currently on and the total number of holes.
     * @param game
     */
    @Override
    protected void displayScoreBoard(GolfGameModel game) {
        System.out.println();
        System.out.println("-----SCOREBOARD-----");

        for (int i = 0; i<game.scoreBoard.length; i++){
            System.out.println(i+1 +". "+ game.scoreBoard[i].name + ":   score for the hole: " + game.scoreBoard[i].score + " points   score for the rest of the game: " + game.scoreBoard[i].totalscore + " points");
        }
        if (game.currentHole <= game.totalHoles)
            System.out.println("You are currently on hole "+ game.currentHole+ " of a " +game.totalHoles+" hole game.");
    }

    @Override
    protected String promptPlayerName(int playerNum) {
        System.out.println();
        System.out.print("Enter a name for player "+playerNum+ " :");
        return in.next();
    }


    /**allow user to re-input decision*/
    protected void clearScanner(){
        in.nextLine();
    }

    @Override
    protected int promptFlip(GolfGameModel game) throws InputMismatchException{
        System.out.println();
        System.out.print("Please pick a card in you hand to flip: ");
        int option = in.nextInt();
        if(option > 6 || option < 1 && option != -1){
            throw new InputMismatchException();
        }
        return option;
    }

    @Override
    protected void spacer(){
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
