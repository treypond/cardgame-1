package csc439teamllama.cardgame;

import java.util.Collections;

/**
 * This class collect all players cards,
 * return them to the deck, shuffle the deck, and add new cards to players hands.
 */
public class HoleEndPhase implements Phase {
    @Override
    public void execute(GolfGameModel game, GolfView view ){
          game.deck.addAll(game.discard);
          for(int i=0; i<game.players.length;i++){
              for (playingCard card: game.players[i].hand
                   ) {
                  card.setFacing(playingCard.Facing.UP);
              }
              Collections.addAll(game.deck, game.players[i].hand);
          }
          game.scoreboardUpdate();
        for (GolfPlayerModel player: game.players
             ) {
            player.totalscore += player.score;
        }
        for (playingCard card: game.deck
             ) {
            card.setFacing(playingCard.Facing.DOWN);
        }
          Collections.shuffle(game.deck);
       game.phase= new FlipPhase();
        if(game.currentHole+1>game.totalHoles){
            for(int i=0; i<game.players.length;i++){
                for (int j = 0; j < game.players[i].hand.length; j++) {
                    game.players[i].hand[j] = null;
                }
            }
            view.sendMessageToPlayer("Congrats "+game.scoreBoard[0].name+"!");
            game.scoreboardUpdate();
            view.displayScoreBoard(game);
            game.gameOver=true;
        }
        else {
            for(int i=0; i<game.players.length;i++){
                for(int j=0; j<6; j++ ){
                    game.players[i].hand[j]=game.deck.remove(game.deck.size()-1);
                }
            }
            view.sendMessageToPlayer("Hole " + game.currentHole + " over, moving to next hole.");
            view.spacer();
            game.currentHole++;
            game.turn = 1;
        }
    }

}
