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
//        game.players[0].hand.add(game.deck.remove(game.deck.size()-1));
//        game.discard.add(game.players[1].hand.remove(game.players[2].hand.size()-1));
//        example of hand/deck access
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

//  method with loop to continue game until completion, uses other method calls in controller

//    7 As a player, when my turn is over, the game proceeds to the next player’s turn, so that
//    the game  continues.

    /**
     * Check if the gameover and if it is not it continues to the next player turn
     */
    protected void GameRunner(){
        while (!game.gameOver){
            Turn();
        }
    }

//    5 on each turn, I should be asked to draw a card from the draw pile or discard
//      --decison, instead of creating a separate variable to show selected card, just display info of
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
        boolean turnOver = false;
        boolean discardOver = false;
        view.DisplayGameState(game);
        //main turn loop, all loops in this method use try catch top enforce proper inputs for each prompt,
        //and the nested loops and try catches make it so that each error is handled appropriately
        while (!turnOver) {
            try {
                //get user input from 1-3, or -1 to exit
                int drawResponse = view.PromptDecision();
                //-1 so we exit turn and end game
                    if (drawResponse == -1){
                        game.gameOver = true;
                        return;
                    }
                    //1 means we redisplay game state and return to being of loop to propmt user once again
                    else if (drawResponse == 1) {
                        view.DisplayGameState(game);
                        continue;
                    }
                    //we enter here because the user has either entered 2 or three so we know they have chose to draw,
                    //and we must handle it appropriately, including errors like drawing from empty piles.
                    else {
                        playingCard drawnCard;
                        String drawnFrom;
                        //2 means we are drawing from deck, if it is empty we prompt user and inform them as such
                        if (drawResponse == 2) {
                            if (game.deck.isEmpty()){
                                view.SendMessageToPlayer("Deck Is Empty! Please Draw from Discard");
                                continue;
                            }
//                            if it is not empty it is allowed so we set our drawn card to the top of the deck
                            else {
                                game.deck.get(game.deck.size() - 1).flipCard();
                                drawnCard = game.deck.remove(game.deck.size() - 1);
                                drawnFrom = "Deck";
                            }
                        }
                        //option 3, draw from discard, same logic as above except with discard
                        else {
                            if (game.discard.isEmpty()){
                                view.SendMessageToPlayer("Discard Is Empty! Please Draw from Deck");
                                continue;
                            }
                            else {
                                drawnCard = game.discard.remove(game.discard.size() - 1);
                                drawnFrom = "Discard";
                            }
                        }
                        //now that we have legally chose a drawn card we choose which card to discard and replace from our hand
                        //and if we drew deck possibly discard our drawn card
                        while (!discardOver) {
                            try {
                                view.SendMessageToPlayer("Drawn card: "+drawnCard.toString()+" From: "+drawnFrom);
                                int discardResponse = view.PromptDiscard(game);
                                //exit code
                                if (discardResponse == -1){
                                    game.gameOver = true;
                                    return;
                                }
                                // this means we are discarding from player hand, this is legal no matter where we drew
                                //from
                                else if (discardResponse < 7) {
                                    discardOver = true;
                                    //set the discarded card from hand to face up
                                    game.players[game.PlayerIndex()].hand[discardResponse-1].setFacing(playingCard.Facing.UP);
//                                    move the card from hand to discard and the popped drawn card to hand
                                    game.discard.add(game.players[game.PlayerIndex()].hand[discardResponse-1]);
                                    game.players[game.PlayerIndex()].hand[discardResponse-1] = drawnCard;
                                }
                                //this is the case where we discard drawn card, not legal if we drew from discard
                                else {
                                    //drawn from deck so legal
                                    if (drawResponse == 2) {
                                        game.discard.add(drawnCard);
                                        discardOver = true;
                                    }
                                    //drawn from discard, so we ask them to choose a card from their hand to discard
                                    else {
                                        view.SendMessageToPlayer("You Picked This Card From Discard! Please Discard A Card From Your Hand.");
                                    }
                                }
                            }
                            //catch for out of bounds response to prompt discard, clears error from scanner
                            catch (InputMismatchException e) {
                                view.SendMessageToPlayer("please input a number between 1 and 7");
                                view.ClearScanner();
                            }
                        }
                    }
                turnOver = true;
                }
            //catch for out of bounds response to prompt to start turn, clears error from scanner
            catch (InputMismatchException e){
                view.SendMessageToPlayer("please input a number between 1 and 3");
                view.ClearScanner();
            }
        }
//        turn has ended so we increment turn counter
        game.turn++;
    }
}
