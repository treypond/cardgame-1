package csc439teamllama.cardgame;

public class GolfController {
    protected GolfGameModel game;
    protected GolfView view;

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
    public void GameStart(){
//        game.players[0].hand.add(game.deck.remove(game.deck.size()-1));
//        game.discard.add(game.players[1].hand.remove(game.players[2].hand.size()-1));
//        example of hand/deck access
        view.TitleScreen();
        game = new GolfGameModel(view.GameStartOptions());
    }

//  method with loop to continue game until completion, uses other method calls in controller

//    7 As a player, when my turn is over, the game proceeds to the next player’s turn, so that
//    the game  continues.
    private void GameRunner(){
//        while (!game.gameOver){
//            return;
//        }
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
    private void Turn(){

    }
}
