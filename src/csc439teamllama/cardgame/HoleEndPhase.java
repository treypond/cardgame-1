package csc439teamllama.cardgame;

import java.util.Collections;

/**
 * This class collect all players cards,
 * return them to the deck, shuffle the deck, and add new cards to players hands.
 */
public class HoleEndPhase implements Phase {
    @Override
    public void execute(GolfGameModel game, GolfView view ){
          game.scoreBoardUpdate();

          game.deck.addAll(game.discard);
          for(int i=0; i<game.players.length;i++){
              Collections.addAll(game.deck, game.players[i].hand);
          }

          Collections.shuffle(game.deck);

          for(int i=0; i<game.players.length;i++){
              for(int j=0; j<6; j++ ){
                  game.players[i].hand[j]=game.deck.remove(game.deck.size()-1);
              }
          }
       game.phase= new DrawPhase();
          game.currentHole+=1;
          if(game.currentHole==game.totalHoles){
            view.SendMessageToPlayer(" Congrats "+game.scoreBoard[0].name+"!");
            game.scoreBoard.displayScoreBoard();
            game.gameOver=true;
          }

    }

}
