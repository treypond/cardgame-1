package csc439teamllama.cardgame;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class GolfCLITestView extends GolfView {
    protected String output = "";
    protected ArrayList<String> input = new ArrayList<>();

    @Override
    protected void TitleScreen() {
        output+=
                        "     ,o888888o.        ,o888888o.     8 8888         8 8888888888   \n" +
                        "    8888     `88.   . 8888     `88.   8 8888         8 8888         \n" +
                        " ,8 8888       `8. ,8 8888       `8b  8 8888         8 8888         \n" +
                        " 88 8888           88 8888        `8b 8 8888         8 8888         \n" +
                        " 88 8888           88 8888         88 8 8888         8 888888888888 \n" +
                        " 88 8888           88 8888         88 8 8888         8 8888         \n" +
                        " 88 8888   8888888 88 8888        ,8P 8 8888         8 8888         \n" +
                        " `8 8888       .8' `8 8888       ,8P  8 8888         8 8888         \n" +
                        "    8888     ,88'   ` 8888     ,88'   8 8888         8 8888         \n" +
                        "     `8888888P'        `8888888P'     8 888888888888 8 8888         \n\n";
    }

    @Override
    protected int GameStartOptions()  throws InputMismatchException{
        try{
            output += "How many players for this game: ";
            int option;
            if(!input.isEmpty())
            { option = Integer.parseInt(input.remove(0));}
            else {
                option = -1;
            }
            if( option < 1 && option != -1){
                throw new InputMismatchException();
            }
            return option;
        }
        catch (NumberFormatException e){
            throw new InputMismatchException();
        }
    }

    @Override
    protected void DisplayGameState(GolfGameModel game) {
        int index = game.PlayerIndex()+1;
        output += "Player " + index + "\'s Turn\n";
        DisplayHand(game);
        output+= "The deck has " + game.deck.size() + " remaining\n";

        if( game.discard.size() == 0){
            output+="The discard pile is empty\n";
        }
        else{
            output+= "The top card on the discard pile is " + game.discard.get(game.discard.size()-1)+"\n";
        }
    }

    @Override
    protected int PromptDecision() throws InputMismatchException {
        try{
            output += "1. Print Game State Again\n";
            output += "2. Pick Up From Deck\n";
            output += "3. Pick Up From Discard\n";
            output += "Or Enter -1 To Exit\n";
            output += "Enter Number To Proceed: ";
            int option;
            if(!input.isEmpty())
            { option = Integer.parseInt(input.remove(0));}
            else {
                option = -1;
            }
            if(option > 3 || option < 1 && option != -1){
                throw new InputMismatchException();
            }
            return option;
        }
        catch (NumberFormatException e){
            throw new InputMismatchException();
        }
    }

    @Override
    protected int PromptDiscard(GolfGameModel game) throws InputMismatchException {
        try{
            output+=("Enter 1-6 To Choose A Card From Your Hand To Replace\n" +
                    "Enter 7 To Discard Chosen Card.\n");
            output += "Or -1 To Exit\n";
            DisplayHand(game);
            output+= "Enter Number To Proceed: ";
            int option;
            if(!input.isEmpty())
                { option = Integer.parseInt(input.remove(0));}
            else {
                option = -1;
                game.gameOver = true;
            }
            if(option > 7 || option < 1 && option != -1) {
                throw new InputMismatchException();
            }
            return option;
        }
        catch (NumberFormatException e){
            throw new InputMismatchException();
        }
    }

    @Override
    protected void SendMessageToPlayer(String message) {
        output+=message+"\n";
    }

    @Override
    protected void DisplayHand(GolfGameModel game) {
        output+= "1."+game.players[game.PlayerIndex()].hand[0].toString()+ " 2." + game.players[game.PlayerIndex()].hand[1].toString()+ " 3." + game.players[game.PlayerIndex()].hand[2].toString() + "\n";
        output+= "4."+game.players[game.PlayerIndex()].hand[3].toString()+ " 5." + game.players[game.PlayerIndex()].hand[4].toString()+ " 6." + game.players[game.PlayerIndex()].hand[5].toString() + "\n";
    }

    @Override
    protected void ClearScanner() {
        ;
    }

    public void clearVars() {
        output = "";
        input.clear();
    }
}
