package csc439teamllama.cardgame;

import java.util.ArrayList;

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
    protected int GameStartOptions() {
        output += "How many players for this game:  ";
        return Integer.parseInt(input.remove(0));
    }

    @Override
    protected void DisplayGameState() {

    }

    @Override
    protected String PromptDecision() {
        return null;
    }

    @Override
    protected String PromptDiscard() {
        return null;
    }

    @Override
    protected void SendMessageToPlayer() {

    }


    public void clearVars() {
        output = "";
        input.clear();
    }
}
