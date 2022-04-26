package csc439teamllama.cardgame;

import java.util.InputMismatchException;

public class GolfController {
    protected GolfGameModel game;
    protected GolfView view;

    /**
     * Constructor for the controller which handles the game logic
     * @param view
     */
    public GolfController(GolfView view){
        this.view = view;
    }

//     2 if there are more than four players, there should be a shoe of two decks
//     used, so  that the game is fair for larger numbers of players.
//     use game options call, then call gamemodel constructor:
//
//    3 when the game starts, it should deal all players in the game a six card hand,
//    in two  rows of three, from the deck or shoe. Each hand should start with four face
//    down cards and two random cards faced up.

    /**
     * Set up the game by create the correct number of deck and deals the hand to all the players. Then starts the game
     */
    public void GameStart(){
//      ame.players[0].hand.add(game.deck.remove(game.deck.size()-1));
//      game.discard.add(game.players[1].hand.remove(game.players[2].hand.size()-1));
//      example of hand/deck access
        boolean correctPlayer = false;
        view.TitleScreen();
        do {
            try {
                int players = view.GameStartOptions();
                if (players==-1){
                    return;
                }
                game = new GolfGameModel(players);
                correctPlayer = true;
            }
            catch (InputMismatchException e) {
                view.SendMessageToPlayer("please input a number: greater than 0");
                view.ClearScanner();
            }
        }
        while (!correctPlayer);
        for (int i = 0; i < game.players.length; i++) {
            game.players[i] = new GolfPlayerModel();
            for (int j = 0; j < 6; j++) {
                game.players[i].hand[j] = game.deck.remove(game.deck.size()-1);
            }
            game.players[i].hand[0].flipCard();
            game.players[i].hand[1].flipCard();
        }
        GameRunner();
    }

//    method with loop to continue game until completion, uses other method calls in controller

//    7 As a player, when my turn is over, the game proceeds to the next player’s turn, so that
//    the game  continues.

    /**
     * Check if the gameover and if it is not it continues to the next player turn
     */
    protected void GameRunner(){
        while (!game.gameOver){
            Turn();
            game.turnOver = false;
        }
    }

//    5 on each turn, I should be asked to draw a card from the draw pile or discard
//      --decision, instead of creating a separate variable to show selected card, just display info of
//      card to player, so just redisplay top discard, and flip and show top of deck
//    pile, be shown the card, and then allowed to select what card to replace, so that I can take
//    my turn.

//    6 As a player, when I choose a card in my hand to replace, the drawn card is
//    placed face up in the  correct position, and the card that was there is placed on top of the
//    discard pile, so that the rules  are properly followed.-- recalls prompt discard on illegal action,
//    displays the reason using send message toplayer
//        subtask of story--
//        As a player, if I draw the card on top of the discard pile, I shouldn’t be allowed to place
//        that  card back in the discard pile, and should be forced to replace a card in my hand,
//        so that I don’t  break the game’s rules unintentionally.

    /**
     * This method implements the action invloved in one players turn in a game of golf. It displays cards
     * and actions allowed to be done by the player. During the player's turn the method prompts the user to discard a
     * card from hand and take on from the deck or discard pile.Then indicated that the current player turn is over
     */
    protected void Turn(){
        view.DisplayGameState(game);
        //main turn loop, all loops in this method use try catch top enforce proper inputs for each prompt,
        //and the nested loops and try catches make it so that each error is handled appropriately
        //turn has ended so we increment turn counter
        while (!game.turnOver && !game.gameOver)
            game.phase.execute(game,view);
    }
}
